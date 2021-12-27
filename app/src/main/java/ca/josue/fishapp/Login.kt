package ca.josue.fishapp

import android.content.Intent
import android.os.Bundle
import android.text.BoringLayout
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.josue.fishapp.model.MyLogin
import ca.josue.fishapp.model.UserDTO
import ca.josue.fishapp.services.API
import ca.josue.fishapp.services.ApiInterface
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    companion object{
        const val GOTO_COMMANDES  = "GOTO_COMMANDES_TRUE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // récupèration des components
        val inputEmail : TextInputEditText = findViewById(R.id.email_input_txt)
        val inputPassword : TextInputEditText = findViewById(R.id.password_toggle_txt)
        var inputEmailBool = true
        var inputPasswordBool = true

        // Supprimer les Text des composants
        inputEmail.setOnFocusChangeListener { view, b ->
            if (b && inputEmailBool){
                inputEmail.setText("")
                inputEmailBool = false
            }
         }

        inputPassword.setOnFocusChangeListener { view, b ->
            if (b && inputPasswordBool){
                inputPassword.setText("")
                inputPasswordBool = false
            }
        }

        val connectBtn : Button = findViewById(R.id.connect_btn)
        connectBtn.setOnClickListener {

            // Vérifier les inputs de l'utilisateur
            if(TextUtils.isEmpty(inputEmail.toString().trim()) || TextUtils.isEmpty(inputPassword.toString().trim())){
                if(TextUtils.isEmpty(inputEmail.toString().trim()) || isEmailValid(inputEmail)){
                    inputEmail.error = "Veuillez entrer un email valide"
                    inputEmail.requestFocus()
                }

                if(TextUtils.isEmpty(inputPassword.toString().trim())){
                    inputPassword.error = "Veuillez entrer un mot de passe"
                    inputPassword.requestFocus()
                }
                return@setOnClickListener
            }

            val user = UserDTO(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
            )

            // Go to log user
            getLoginUser(user)
        }
    }

    /**
     * Methode qui vérifie si l'Utilisateur a entré un email valide
     * @param str le TextView de l'email à vérifier
     * @return Boolean
     * */
    private fun isEmailValid(str : TextView): Boolean {
        return Regex.fromLiteral("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(
                TextUtils.isEmpty(str.toString()).toString())
    }

    /***
     * Methode qui permet de logger un Utilisateur
     * @param user les informations servant de connexion (i.e: email and password)
     */
    private fun getLoginUser(user: UserDTO) {
        API.getApi()
                ?.create(ApiInterface::class.java)
                ?.login(user)
                ?.enqueue(object : Callback<MyLogin?> {
                    override fun onResponse(call: Call<MyLogin?>, response: Response<MyLogin?>) {
                        if(!response.isSuccessful)
                            return
                        val responseLogin = response.body()!!

                        // Setter l'ID de l'utilisateur courant
                        BaseApplication.ID_USER_CURRENT = responseLogin.id
                        BaseApplication.TOKEN = responseLogin.token
                        BaseApplication.EMAIL = responseLogin.email
                        BaseApplication.PASSWORD = user.password

                        println("Voici à present l'id : ${BaseApplication.ID_USER_CURRENT}")
                        if(BaseApplication.ID_USER_CURRENT != null){
                            val intent = Intent(this@Login, MainActivity::class.java)
                            intent.putExtra(GOTO_COMMANDES, true)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this@Login, "Désolé, une erreur est survenue lors de la connexion", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<MyLogin?>, t: Throwable) {
                        println("Erreur lors de Login : ${t.message}")
                    }
                })
    }
}