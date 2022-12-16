package br.edu.infnet.myapplication.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Endereco (
    val cep :String?  = "",


    @Json(name = "localidade")
    val cidade: String?  = "",

    @Json(name = "uf")
    val estado: String? = ""
)
