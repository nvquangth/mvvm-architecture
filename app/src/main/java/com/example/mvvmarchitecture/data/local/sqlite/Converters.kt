package com.example.mvvmarchitecture.data.local.sqlite

import androidx.room.TypeConverter
import com.example.mvvmarchitecture.data.model.Component
import com.example.mvvmarchitecture.data.model.Step
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun objectToString(value: List<String>): String = gson.toJson(value)

    @TypeConverter
    fun stringToObject(value: String): List<String> =
        gson.fromJson<List<String>>(value, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun componentToString(value: List<Component>): String = gson.toJson(value)

    @TypeConverter
    fun stringToComponent(value: String): List<Component> =
        gson.fromJson<List<Component>>(value, object : TypeToken<List<Component>>() {}.type)

    @TypeConverter
    fun stepToString(value: List<Step>): String = gson.toJson(value)

    @TypeConverter
    fun stringToStep(value: String): List<Step> =
        gson.fromJson<List<Step>>(value, object : TypeToken<List<Step>>() {}.type)
}