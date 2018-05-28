package br.com.oxxynet.chatoxxy.services

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class SisviFirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        val firebaseInstanceId = FirebaseInstanceId.getInstance()
        val refreshedToken = firebaseInstanceId.token
        Log.d("Token Message: ", refreshedToken)
//        refreshedToken?.let { enviarTokenParaServidor(it) }
    }

//    private fun enviarTokenParaServidor(refreshedToken: String) {
//        SisviApplication.Components.applicationComponent.enviarTokenCMCase().executar(refreshedToken)
//    }
}
