package br.com.oxxynet.chatoxxy.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import br.com.oxxynet.chatoxxy.view.mensagens.DialogoActivity
import br.com.oxxynet.chatoxxy.R
import br.com.oxxynet.chatoxxy.events.NotificarMensagemEvent
import br.com.oxxynet.chatoxxy.repository.DatabaseManager
import br.com.oxxynet.chatoxxy.repository.DialogRepository
import br.com.oxxynet.chatoxxy.repository.MensagemRepository
import br.com.oxxynet.chatoxxy.repository.UsuarioRepository
import com.google.firebase.messaging.FirebaseMessagingService
import org.greenrobot.eventbus.EventBus
import java.util.*


class SisviFirebaseMessagingService : FirebaseMessagingService() {


    override fun handleIntent(intent: Intent?) {
        if (intent != null) {
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val idUserRemetente = intent.getStringExtra("remetente")
            val from = intent.getStringExtra("from")
            val mensagem = intent.getStringExtra("texto")


            val databaseManager = DatabaseManager(this)
            val usuarioRepository = UsuarioRepository(databaseManager.userDao)
            val dialogRepository = DialogRepository(databaseManager.dialogDao, databaseManager.userDialogDao)
            val mensagemRepository = MensagemRepository(databaseManager.messageDao)
            val usuario = usuarioRepository.obterUsuarioPorId(idUserRemetente)
            val dialog = dialogRepository.obterDialogPorRemetente(idUserRemetente)

            if (dialog != null) {
                val intentResult = Intent(this, DialogoActivity::class.java)
                intentResult.putExtra("idDialog", dialog.id)
                val mensagemCriada = mensagemRepository.criarMensagem(usuario, mensagem, dialog, false)
                val resultPendingIntent = PendingIntent.getActivity(this, RequestCode.REQUEST_IR_PARA_MENSAGEM, intentResult, PendingIntent.FLAG_UPDATE_CURRENT)
                EventBus.getDefault().post(NotificarMensagemEvent(mensagemCriada?.id, idUserRemetente))

                val build = NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(usuario?.name)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setContentText(mensagem)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setGroup(idUserRemetente)
                        .setColor(applicationContext.resources.getColor(R.color.colorPrimaryDark))
                        .setContentIntent(resultPendingIntent)
                        .setStyle(NotificationCompat.BigTextStyle()
                                .bigText(mensagem)).build()
                usuario?.id?.toInt()?.let { notificationManager.notify(it, build) }

            }
        }

    }
}
