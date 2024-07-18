package com.example.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.data.local.dao.CitiesForSearchDao
import com.example.weatherapp.data.local.dao.CurrentWeatherDao
import com.example.weatherapp.data.local.dao.ForecastDao
import com.example.weatherapp.data.entity.CitiesForSearchEntity
import com.example.weatherapp.data.entity.CurrentWeatherEntity
import com.example.weatherapp.data.entity.ForecastEntity
import com.example.weatherapp.utils.DataConverter

@Database(
    entities = [
        ForecastEntity::class,
        CurrentWeatherEntity::class,
        CitiesForSearchEntity::class
    ],
    version = 2
)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun citiesForSearchDao(): CitiesForSearchDao
}
