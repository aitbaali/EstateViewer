package com.seloger.android.estateviewer.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "estates")
data class Estate(
    @PrimaryKey
    val id: Int,
    val bedrooms: Int,
    val city: String,
    val area: Double,
    @SerializedName("url") val imageUrl: String,
    val price: Double,
    val professional: String,
    val propertyType: String,
    val rooms: Int
) : Parcelable