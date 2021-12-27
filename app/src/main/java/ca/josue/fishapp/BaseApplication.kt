package ca.josue.fishapp

class BaseApplication(
){
    companion object{
        const val BASE_URL: String = "https://fish-sales-application.herokuapp.com/api/v1/"
        lateinit var ID_USER_CURRENT : String
    }
}