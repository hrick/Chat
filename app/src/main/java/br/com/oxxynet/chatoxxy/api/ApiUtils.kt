package br.com.oxxynet.chatoxxy.api

import br.com.oxxynet.chatoxxy.api.Constants.BASE_URL


class ApiUtils  {

    fun getFirebaseCloudMessageApi(): FirebaseCloudMessageApi {
        return RetrofitClient.getClient(BASE_URL).create(FirebaseCloudMessageApi::class.java)
    }
}