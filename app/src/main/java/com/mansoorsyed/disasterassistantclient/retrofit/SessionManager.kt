package com.mansoorsyed.disasterassistantclient.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.example.disasterassistantclient.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    // saving auth token
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor?.putString(USER_TOKEN, token)
        editor?.apply()
    }

    // fetching auth token
    fun getAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

}