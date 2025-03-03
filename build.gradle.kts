// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    //id ("org.jetbrains.kotlin.android") version "1.8.0" apply false
    //Navigation
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false
}