package ca.josue.fishapp.ui.util

import android.text.TextUtils
import android.widget.TextView

class CheckForm {
    companion object{
        /**
         * Methode qui permet de vérifier si les champs email et Password sont correct ou pas
         * @param inputEmail le TextView de l'email à vérifier
         * @param inputPassword le TextView du password à vérifier
         * */
        fun checkEmailPasswordValid(inputEmail : TextView, inputPassword : TextView){
            if(TextUtils.isEmpty(inputEmail.toString().trim()) || TextUtils.isEmpty(inputPassword.toString().trim())){
                if(TextUtils.isEmpty(inputEmail.toString().trim()) || isEmailValid(inputEmail)){
                    inputEmail.error = "Veuillez entrer un email valide"
                    inputEmail.requestFocus()
                }

                if(TextUtils.isEmpty(inputPassword.toString().trim())){
                    inputPassword.error = "Veuillez entrer un mot de passe"
                    inputPassword.requestFocus()
                }
                return
            }
        }

        /**
         * Methode qui vérifie si l'Utilisateur a entré un email valide
         * @param inputEmail le TextView de l'email à vérifier
         * @return Boolean
         * */
        private fun isEmailValid(inputEmail : TextView) : Boolean {
            return Regex.fromLiteral("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(
                TextUtils.isEmpty(inputEmail.toString()).toString())
        }

        /**
         * Methode qui permet de réinitialiser le champs input lorsque l'utilisateur fait focus
         * sur le champs pour la première fois
         * @param input le TextView à réinitialisé
         * @param firstTime indique si l'utilisateur fait l'acction pour la première fois ou non
         * @param call une fonction de rappel qui sera appliqué juste après la réinitialisation
         * */
        fun initField(input : TextView, firstTime : Boolean, call: () -> Unit){
            input.setOnFocusChangeListener { _, b ->
                if (b && firstTime){
                    input.text = ""
                    call()
                }
            }
        }

    }
}