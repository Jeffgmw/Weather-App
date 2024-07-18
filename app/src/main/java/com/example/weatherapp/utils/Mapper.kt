package com.example.weatherapp.utils

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}

//interface Mapper<R, D> {
//    fun mapFrom(type: R): D
//}