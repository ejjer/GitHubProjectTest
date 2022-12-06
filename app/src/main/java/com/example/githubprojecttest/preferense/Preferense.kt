package com.example.githubprojecttest.preferense

import android.content.Context

data class PreferenseHelper(val context: Context) {
    val THEMEID = "them_id"

    companion object {
        private const val SHARED_PREF = "shared_pref"
    }

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    fun saveTheme(name: String) {
        sharedPreferences.edit().putString(THEMEID, name).apply()
    }

    fun getTheme(): String {
        return sharedPreferences.getString(THEMEID, "") ?: ""
    }
}