package com.questro.itunes.domain.model

data class Result(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionViewUrl: String,
    val currency: String,
    val kind: String,
    val previewUrl: String,
    val releaseDate: String,
    val trackName: String,
    val trackPrice: Double,
    val trackTimeMillis: Int,
    val trackViewUrl: String,
    val primaryGenreName: String,
    val trackExplicitness: String
)