package com.mawumbo.movie.data.helper

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Context.parseJsonToList(filePath: String): List<T> {
    val type = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson(assets.open(filePath).bufferedReader().use { it.readText() }, type)
}