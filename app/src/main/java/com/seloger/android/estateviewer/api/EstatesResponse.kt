package com.seloger.android.estateviewer.api

import com.google.gson.annotations.SerializedName
import com.seloger.android.estateviewer.data.entity.Estate

data class EstatesResponse(
    @SerializedName("items") val estates: List<Estate>
)