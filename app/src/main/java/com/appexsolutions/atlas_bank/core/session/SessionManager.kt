package com.appexsolutions.atlas_bank.core.session

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("atlas_session", Context.MODE_PRIVATE)

    fun saveUserId(id: String) {
        prefs.edit().putString(KEY_USER_ID, id).apply()
    }

    fun getUserId(): String? = prefs.getString(KEY_USER_ID, null)

    fun saveCuentaId(id: String) {
        prefs.edit().putString(KEY_CUENTA_ID, id).apply()
    }

    fun getCuentaId(): String? = prefs.getString(KEY_CUENTA_ID, null)

    fun clearSession() {
        prefs.edit().clear().apply()
    }

    // SessionManager.kt
    fun saveCredentials(email: String, password: String) {
        prefs.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_PASSWORD, password)
            .apply()
    }

    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)
    fun getPassword(): String? = prefs.getString(KEY_PASSWORD, null)

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_CUENTA_ID = "cuenta_id"
        private const val KEY_EMAIL = "email"       // 👈 agregar
        private const val KEY_PASSWORD = "password" // 👈 agregar
    }


}
