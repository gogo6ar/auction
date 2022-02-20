package com.lot.mobiledemodata.datasources.disk.auth

import android.content.SharedPreferences
import com.google.gson.Gson
import com.lot.mobiledemodata.datasources.disk.SharedPreferencesSource
import javax.inject.Inject

class TokenDiskSource @Inject constructor(
    sharedPreferences: SharedPreferences,
    gson: Gson
) : SharedPreferencesSource<TokenDiskModel>(
    TokenDiskModel::class.java,
    sharedPreferences,
    gson,
    id = "token_cache"
)
