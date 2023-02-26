package com.example.travel.api.data

import com.example.travel.api.model.BaseModel
import com.google.gson.annotations.SerializedName

data class ATTR001_Rs( //Attractions
    @field:SerializedName("total") val total: Int?,
    @field:SerializedName("data") val data: List<ATTR002_Rs> = listOf()
) : BaseModel()

data class ATTR002_Rs(
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("introduction") val introduction: String?,
    @field:SerializedName("open_time") val open_time: String?,
    @field:SerializedName("address") val address: String?,
    @field:SerializedName("ticket") val ticket: String?,
    @field:SerializedName("remind") val remind: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("tel") val tel: String?,
    @field:SerializedName("images") val images: List<TCMSV_003_Rs>?
) : BaseModel()

data class TCMSV_003_Rs(
    @field:SerializedName("src") val src: String? = "001",
    @field:SerializedName("subject") val subject: String?,
    @field:SerializedName("ext") val ext: String?,
) : BaseModel()