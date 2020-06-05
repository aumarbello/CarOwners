package com.aumarbello.carowners.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Filter (
    val gender: String,
    val colors: List<String>,
    val countries: List<String>
) : Parcelable