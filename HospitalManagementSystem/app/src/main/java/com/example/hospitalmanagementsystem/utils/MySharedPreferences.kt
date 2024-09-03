package com.example.hospitalmanagementsystem.utils

import android.content.Context
import android.content.SharedPreferences


object MySharedPreferences {


    private var mAppContext: Context? = null
  const val SHARED_PREFERENCES_NAME = "hospital data"
     const val USER_PHONE = "mobile"
     const val TYPE = "type"
     const val USER_NAME = "user name"
   const val USER_EMAIL= "user email"
   const val USER_TOKEN= "token"
    const val USER_ID =  "user id"
 const val USER_ATTENDED =  "attended"
   const val USER_LEAVING =  "leaving"
    const val USER_GENDER =  "male"
    const val USER_ADDRESS =  "map"
  const val USER_DATE =  "date"
    const val USER_STATE =  "single"
    const val USER_ID_PROFILE =  "profile"
    const val IS_LOGGED_IN = "is_logged_in"




    private fun mySharedPreference() {}



    fun init(appContext: Context?) {
        mAppContext = appContext
    }

    fun getSharedPreferences(): SharedPreferences {
        return mAppContext!!.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }



    fun setUserPhone (container : String){
        val editor = getSharedPreferences().edit()
        editor.putString(USER_PHONE, container).apply()
    }

    fun getUserPhone ():String
    {
        return getSharedPreferences().getString(USER_PHONE , "")!!
    }






    fun setUserType (container : String){

        val editor = getSharedPreferences().edit()
        editor.putString(TYPE, container).apply()

    }

    fun isLoggedIn(): Boolean {
        return getSharedPreferences().getBoolean(IS_LOGGED_IN, false)
    }
    fun getUserType ():String{
        return getSharedPreferences().getString(TYPE , "")!!
    }
    fun setUserEmail (email : String){
        val editor = getSharedPreferences().edit()
        editor.putString(USER_EMAIL, email).apply()
    }

    fun getUserEmail ():String{
        return getSharedPreferences().getString(USER_EMAIL , "")!!
    }

    fun setUserName (name : String){
        val editor = getSharedPreferences().edit()
        editor.putString(USER_NAME, name).apply()
    }

    fun getUserName ():String{
        return getSharedPreferences().getString(USER_NAME , "")!!


    }
    fun setUserProfile (id : Int){
        val editor = getSharedPreferences().edit()
        editor.putInt(USER_ID_PROFILE, id).apply()
    }

    fun getUserProfile ():Int{
        return getSharedPreferences().getInt(USER_ID_PROFILE,0)
    }


    fun setUserId (id : Int){

        val editor = getSharedPreferences().edit()
        editor.putInt(USER_ID, id).apply()

    }

    fun getUserId ():Int{
        return getSharedPreferences().getInt(USER_ID , 0)


    }

    fun setUserTOKEN (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_TOKEN, id).apply()

    }

    fun getUserToken(): String? {
        return getSharedPreferences().getString(USER_TOKEN, "" )
    }

    fun setUserAttended (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_ATTENDED, id).apply()

    }
    fun setUserGender (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_GENDER, id).apply()

    }
    fun setUserMap (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_ADDRESS, id).apply()

    }

    fun getUserAttended  (): String? {
        return getSharedPreferences().getString(USER_ATTENDED, "" )

    }
    fun getUserMap  (): String? {
        return getSharedPreferences().getString(USER_ADDRESS, "" )

    }
 fun getUserGender  (): String? {
        return getSharedPreferences().getString(USER_GENDER, "" )

    }


    fun setUserLeaving (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_LEAVING, id).apply()

    }

    fun getUserLeaving  (): String? {
        return getSharedPreferences().getString(USER_LEAVING, "" )


    }
    fun setUserDate (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_DATE, id).apply()

    }

    fun getUserDate  (): String? {
        return getSharedPreferences().getString(USER_DATE, "" )


    }
    fun setUserState (id : String){

        val editor = getSharedPreferences().edit()
        editor.putString(USER_STATE, id).apply()

    }

    fun getUserState  (): String? {
        return getSharedPreferences().getString(USER_STATE, "" )


    }






}