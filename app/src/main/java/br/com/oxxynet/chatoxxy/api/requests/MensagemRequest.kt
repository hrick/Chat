package br.com.oxxynet.chatoxxy.api.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
to = /topics/talonario_0
 */

data class MensagemRequest (

    @SerializedName("to")
    @Expose
    var to: String? = null,
    @SerializedName("data")
    @Expose
    var mensagem: Mensagem? = null)




data class Mensagem(
        @SerializedName("texto")
        @Expose
        var texto: String? = null,
        @SerializedName("remetente")
        @Expose
        var remetente: String? = null)

