package br.com.oxxynet.chatoxxy.api

import com.google.gson.annotations.SerializedName

data class BaseResponse (
        @SerializedName("Mensagem")
        val mensagem: String?,
        @SerializedName("Codigo")
        val codigo: Int,
        @SerializedName("Sucesso")
        val sucesso: Boolean)