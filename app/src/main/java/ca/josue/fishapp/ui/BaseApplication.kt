package ca.josue.fishapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){
    companion object{
        const val BASE_URL: String = "https://fish-sales-application.herokuapp.com/api/v1/"
        var ID_USER_CURRENT : String? = null
        var TOKEN : String? = null
        var EMAIL : String? = null
        var PASSWORD : String ? = null
        var PHONE : String? = null
        var APARTEMENT : String? = null
        var AVENUE : String? = null
        var NAME_USER : String? = null
    }
}