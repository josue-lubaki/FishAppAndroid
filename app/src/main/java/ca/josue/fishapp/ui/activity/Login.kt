package ca.josue.fishapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.josue.fishapp.R
import ca.josue.fishapp.data.data_source.network.RetrofitClient
import ca.josue.fishapp.domain.dto.UserInfoResponse
import ca.josue.fishapp.domain.dto.UserLoginDTO
import ca.josue.fishapp.domain.dto.UserLoginResponse
import ca.josue.fishapp.ui.BaseApplication
import ca.josue.fishapp.ui.BaseApplication.Companion.EMAIL
import ca.josue.fishapp.ui.BaseApplication.Companion.ID_USER_CURRENT
import ca.josue.fishapp.ui.BaseApplication.Companion.NAME_USER
import ca.josue.fishapp.ui.fragment.CommandesFragment
import ca.josue.fishapp.ui.util.CheckForm.Companion.checkEmailPasswordValid
import ca.josue.fishapp.ui.util.CheckForm.Companion.initField
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
//
//    companion object{
//        const val GOTO_COMMANDES  = "GOTO_COMMANDES_TRUE"
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // récupèration des components
        val inputEmail : TextInputEditText = findViewById(R.id.email_input_txt)
        val inputPassword : TextInputEditText = findViewById(R.id.password_toggle_txt)
        var inputEmailBool = true
        var inputPasswordBool = true

        // Supprimer les Text des composants
        initField(inputEmail, inputEmailBool) { inputEmailBool = false }
        initField(inputPassword, inputPasswordBool) { inputPasswordBool = false }

        val connectBtn : TextView = findViewById(R.id.connect_btn)
        connectBtn.setOnClickListener {
            connectBtn_actionPerformed(inputEmail, inputPassword)
        }
    }

    /**
     * Methode qui se déclenche la vérification des champs email et password
     * ensuite lance l'opération de Login si les formulaires ne contient pas d'erreur
     * @param inputEmail : TextView qui contient l'email entré par l'utilisateur
     * @param inputPassword : TextView qui contient le password entré par l'utilisateur
     * */
    private fun connectBtn_actionPerformed(inputEmail: TextView, inputPassword : TextView) {
        checkEmailPasswordValid(inputEmail, inputPassword)

        val user = UserLoginDTO(
            inputEmail.text.toString(),
            inputPassword.text.toString()
        )

        // Go to log user
        getLoginUser(user)
    }

    /**
     * Methode qui permet de récupèrer toutes les informations de l'utilisateur via son ID
     * @param id L'identifiant de l'utilisateur logged
     * */
    private fun getInfoUser(id : String){
        RetrofitClient.getApiService().getInfoUser(id).enqueue(object : Callback<UserInfoResponse?> {
            override fun onResponse(call: Call<UserInfoResponse?>, infoDTO: Response<UserInfoResponse?>) {
                if(!infoDTO.isSuccessful)
                    return

                val userInfo = infoDTO.body()!!
                // setter les valeurs
                NAME_USER = userInfo.name

                // TODO enregistrer les informations de l'utilisateur courant
            }

            override fun onFailure(call: Call<UserInfoResponse?>, t: Throwable) {
                println(t.message)
            }
        })
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

                    // Setter l'ID de l'utilisateur courant
                    ID_USER_CURRENT = userLogged.id
                    BaseApplication.TOKEN = userLogged.token
                    EMAIL = userLogged.email
                    BaseApplication.PASSWORD = user.password

                    // TODO Room enregistrer les coordonnées de l'utilisateur

                    // Récupèrer le nom de l'utilisateur
                    getInfoUser(userLogged.id)

                    // Récupèration des commandes Refresh la Liste - Synchronisation
                    CommandesFragment.commandeList.clear()
                    CommandesFragment.getCommandesUser()

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