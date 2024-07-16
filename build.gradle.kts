// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.0.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.49")
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false


//    id("org.jetbrains.kotlin.kapt") version "1.7.10" apply false
//    id("androidx.navigation.safeargs.kotlin") version "2.5.2" apply false
//    id("kotlin-parcelize") version "1.7.10" apply false
//    id("org.jlleitschuh.gradle.ktlint") version "10.2.1" apply false
//    id("dagger.hilt.android.plugin") version "2.43.2" apply false
}