package ca.josue.fishapp.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import ca.josue.fishapp.R
import ca.josue.fishapp.data.data_source.network.RetrofitClient
import ca.josue.fishapp.domain.dto.UserInfoResponse
import ca.josue.fishapp.domain.dto.UserLoginDTO
import ca.josue.fishapp.domain.dto.UserLoginResponse
import ca.josue.fishapp.domain.model.UserRoom
import ca.josue.fishapp.domain.repository.UserRepository
import ca.josue.fishapp.ui.BaseApplication
import ca.josue.fishapp.ui.BaseApplication.Companion.APARTEMENT
import ca.josue.fishapp.ui.BaseApplication.Companion.AVENUE
import ca.josue.fishapp.ui.BaseApplication.Companion.EMAIL
import ca.josue.fishapp.ui.BaseApplication.Companion.ID_USER_CURRENT
import ca.josue.fishapp.ui.BaseApplication.Companion.NAME_USER
import ca.josue.fishapp.ui.BaseApplication.Companion.PHONE
import ca.josue.fishapp.ui.BaseApplication.Companion.SAVEDME
import ca.josue.fishapp.ui.fragment.CommandesFragment
import ca.josue.fishapp.ui.util.CheckForm.Companion.checkEmailPasswordValid
import ca.josue.fishapp.ui.util.CheckForm.Companion.initField
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class Login : AppCompatActivity() {

    lateinit var preferences: SharedPreferences
    lateinit var emailPreference: String
    lateinit var passwordPreferences: String
    private lateinit var switchSaveCredentials : SwitchCompat

    companion object{
        const val NAME_PREFERENCE = "Credentials"

        fun getInformationUser(idUser : String) {
            RetrofitClient
                .getApiService()
                .getInfoUser(idUser)
                .enqueue(object : Callback<UserInfoResponse?> {
                    override fun onResponse(
                        call: Call<UserInfoResponse?>,
                        response: Response<UserInfoResponse?>
                    ) {
                        if (!response.isSuccessful)
                            return

                        val userInfo = response.body()!!
                        AVENUE = userInfo.avenue
                        APARTEMENT = userInfo.apartment
                        PHONE = userInfo.phone
                        EMAIL = userInfo.email
                    }

                    override fun onFailure(call: Call<UserInfoResponse?>, t: Throwable) {
                        println("Erreur lors de la recuperation des commandes : ${t.message}")
                    }
                })
        }
    }

    @Inject
    lateinit var userRepository : UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Vérifier les données de la préference
        preferences = getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)
        val emailPrefs = preferences.getString(getString(R.string.save_username_key), null)
        val passwordPrefs = preferences.getString(getString(R.string.save_password_key), null)
        val idUserCurrent = preferences.getString("ID_USER_CURRENT", null)
        val nameUser = preferences.getString("NAME_USER", null)

        if(emailPrefs != null && passwordPrefs != null && idUserCurrent != null && nameUser != null) {
            ID_USER_CURRENT = idUserCurrent
            NAME_USER = nameUser
            EMAIL = emailPrefs
            val userLogin = UserLoginDTO(emailPrefs, passwordPrefs)
            getLoginUser(userLogin)
            //navBar.show(2, true)
        }

        // récupèration des components
        switchSaveCredentials = findViewById(R.id.switch_save_credentials)
        val inputEmail : TextInputEditText = findViewById(R.id.email_input_txt)
        val inputPassword : TextInputEditText = findViewById(R.id.password_toggle_txt)
        var inputEmailBool = true
        var inputPasswordBool = true

        // Supprimer les Text des composants
        initField(inputEmail, inputEmailBool) { inputEmailBool = false }
        initField(inputPassword, inputPasswordBool) { inputPasswordBool = false }

        val connectBtn : TextView = findViewById(R.id.connect_btn)
        connectBtn.setOnClickListener {
            connectBtnActionPerformed(inputEmail, inputPassword)
        }

        /* Listener pour le switch */
        switchSaveCredentials.setOnCheckedChangeListener {
                _: CompoundButton?, isChecked: Boolean ->
            // Si le switch est activé
            SAVEDME = isChecked
        }
    }

    /**
     * Methode qui se déclenche la vérification des champs email et password
     * ensuite lance l'opération de Login si les formulaires ne contient pas d'erreur
     * @param inputEmail : TextView qui contient l'email entré par l'utilisateur
     * @param inputPassword : TextView qui contient le password entré par l'utilisateur
     * */
    private fun connectBtnActionPerformed(inputEmail: TextView, inputPassword : TextView) {
        checkEmailPasswordValid(inputEmail, inputPassword)

        val user = UserLoginDTO(
            inputEmail.text.toString().lowercase(Locale.getDefault()),
            inputPassword.text.toString()
        )

        // Go to log user
        getLoginUser(user)
    }

    /***
     * Methode qui permet de logger un Utilisateur
     * @param user les informations servant de connexion (i.e: email and password)
     */
    private fun getLoginUser(user: UserLoginDTO) {
        RetrofitClient
            .getApiService()
            .login(user)
            .enqueue(object : Callback<UserLoginResponse?> {
                override fun onResponse(call: Call<UserLoginResponse?>, response: Response<UserLoginResponse?>) {
                    if(!response.isSuccessful)
                        return

                    val userLogged = response.body()!!

                    val newUser = UserRoom(
                        idUser = userLogged.id,
                        email = userLogged.email,
                        name = userLogged.name,
                        token = userLogged.token
                    )

                    if(SAVEDME){
                        // TODO Ajouter la condition du switch
                        val editor = preferences.edit()
                        editor.apply{
                            putString(getString(R.string.save_username_key), newUser.email)
                            putString(getString(R.string.save_password_key), user.password)
                            putString("ID_USER_CURRENT", newUser.idUser)
                            putString("TOKEN", newUser.token)
                            putString("NAME_USER", newUser.name)
                        }.apply()
                    }

                    runBlocking {
                        userRepository.insertUser(newUser)
                    }

                    // Setter l'ID de l'utilisateur courant
                    ID_USER_CURRENT = userLogged.id
                    BaseApplication.TOKEN = userLogged.token
                    EMAIL = userLogged.email
                    BaseApplication.PASSWORD = user.password
                    NAME_USER = userLogged.name

                    // Récupèration des commandes Refresh la Liste
                    CommandesFragment.ordersListRoom.clear()
                    CommandesFragment.getCommandesUser()

                    // Récupèrer les informations de l'utilisateur
                    ID_USER_CURRENT?.let { getInformationUser(it) }

                    if(ID_USER_CURRENT != null){
                        val intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this@Login, "Désolé, une erreur est survenue lors de la connexion", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<UserLoginResponse?>, t: Throwable) {
                    println("Erreur lors de Login : ${t.message}")
                }
            })
    }


}