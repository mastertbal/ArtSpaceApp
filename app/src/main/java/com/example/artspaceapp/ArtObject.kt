package com.example.artspaceapp

import androidx.annotation.DrawableRes

data class ArtObject(
    val artistName: String,
    val yearOfPub: String,
    val description: String,
    @DrawableRes val image: Int
    )