// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
//        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.0.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.49")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}