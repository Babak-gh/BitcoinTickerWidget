package com.example.android.alephba.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


const val USER_KEY = "user"

class SharedPreferenceUtil @Inject constructor(@ApplicationContext context: Context) {

    private var sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)


    fun saveString(tag: String?, string: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(tag, string)
        editor.apply()
    }

    fun getString(tag: String?, defVal: String?): String? {
        return sharedPreferences.getString(tag, defVal)
    }

    fun saveStringSet(
        tag: String?,
        string: Set<String?>?
    ) {
        val editor = sharedPreferences.edit()
        editor.putStringSet(tag, string)
        editor.apply()
    }

    fun getStringSet(
        tag: String?,
        defVal: Set<String?>?
    ): Set<String?>? {
        return sharedPreferences.getStringSet(tag, defVal)
    }

    fun saveLong(tag: String?, `val`: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(tag, `val`)
        editor.apply()
    }

    fun getLong(tag: String?, defVal: Long): Long {
        return sharedPreferences.getLong(tag, defVal)
    }

    fun saveInteger(tag: String?, `val`: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(tag, `val`)
        editor.apply()
    }

    fun getInteger(tag: String?, defVal: Int): Int {
        return sharedPreferences.getInt(tag, defVal)
    }

    fun saveFloat(tag: String?, `val`: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat(tag, `val`)
        editor.apply()
    }

    fun getFloat(tag: String?, defVal: Float): Float {
        return sharedPreferences.getFloat(tag, defVal)
    }

    fun saveBoolean(tag: String?, `val`: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(tag, `val`)
        editor.apply()
    }

    fun getBoolean(tag: String?, defVal: Boolean): Boolean {
        return sharedPreferences.getBoolean(tag, defVal)
    }

    fun clear(tag: String?) {
        sharedPreferences.edit().remove(tag).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}