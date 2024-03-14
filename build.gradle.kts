// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
    }
}
