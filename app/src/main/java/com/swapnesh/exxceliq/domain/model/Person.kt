package com.swapnesh.exxceliq.domain.model


import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
@Entity
data class Person(
    @Expose
    @SerializedName("data")
    internal val `data`: List<PersonData>,
    @PrimaryKey
    @SerializedName("page")
    val page: Int,
    @Expose
    @SerializedName("per_page")
    val per_page: Int,
    @Expose
    @SerializedName("support")
    val support: Support,
    @Expose
    @SerializedName("total")
    val total: Int,
    @Expose
    @SerializedName("total_pages")
    val total_pages: Int
): Serializable



    @Keep
    @Entity
    data class PersonData(
        @PrimaryKey
        @Expose
        @SerializedName("id")
        val id: Int,
        @Expose
        @SerializedName("email")
        val email: String,
        @Expose
        @SerializedName("first_name")
        val first_name: String,
        @Expose
        @SerializedName("last_name")
        val last_name: String,
        @Expose
        @SerializedName("avatar")
        val avatar: String

    ): Serializable

    @Keep
    data class Support(
        @Expose
        @SerializedName("text")
        val text: String,
        @Expose
        @SerializedName("url")
        val url: String
    )
