package com.example.cinema_app.data.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.cinema_app.common.Constants
import javax.inject.Singleton

@Singleton
class TokenLocalStorage(context: Context) {
    private val masterKey: MasterKey =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    private val encryptedSharedPreferences =
        EncryptedSharedPreferences.create(
            context, Constants.TOKEN_REFERENCES, masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun deleteToken() {
        encryptedSharedPreferences.edit().remove("token").apply()
    }

    fun getToken(): String {
        return encryptedSharedPreferences.getString("token", Constants.EMPTY_STRING)
            ?: Constants.EMPTY_STRING
    }

    fun saveToken(token: String) {
        encryptedSharedPreferences.edit().putString("token", token).apply()
    }

}