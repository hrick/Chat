package br.com.oxxynet.chatoxxy.api

import br.com.oxxynet.chatoxxy.api.requests.MensagemRequest
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseCloudMessageApi {

    @POST("/fcm/send")
    @Headers("Authorization: ${Constants.KEY_FIREBASE}")
    fun enviarMensagem(@Body requestMensagem: MensagemRequest): Call<ResponseBody>

}