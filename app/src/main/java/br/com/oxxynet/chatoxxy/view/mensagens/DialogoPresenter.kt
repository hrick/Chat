package br.com.oxxynet.chatoxxy.view.mensagens

import android.util.Log
import br.com.oxxynet.chatoxxy.api.ApiUtils
import br.com.oxxynet.chatoxxy.api.requests.Mensagem
import br.com.oxxynet.chatoxxy.api.requests.MensagemRequest
import br.com.oxxynet.chatoxxy.events.NotificarMensagemEvent
import br.com.oxxynet.chatoxxy.model.Dialog
import br.com.oxxynet.chatoxxy.model.Message
import br.com.oxxynet.chatoxxy.repository.DatabaseManager
import br.com.oxxynet.chatoxxy.repository.DialogRepository
import br.com.oxxynet.chatoxxy.repository.MensagemRepository
import br.com.oxxynet.chatoxxy.repository.UsuarioRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class DialogoPresenter(val view: DialogoView, private val databaseManager: DatabaseManager, private val idDialog: String) {

    var mensagemRepository: MensagemRepository? = null
    var usuarioRepository: UsuarioRepository? = null
    var dialogRepository: DialogRepository? = null
    var dialog: Dialog? = null
    lateinit var apiUtils: ApiUtils

    init {

        usuarioRepository = UsuarioRepository(databaseManager.userDao)
        mensagemRepository = MensagemRepository(databaseManager.messageDao)
        dialogRepository = DialogRepository(databaseManager.dialogDao, databaseManager.userDialogDao)
        dialog = dialogRepository?.obterDialog(idDialog)

    }


    fun salvarEnviarMensagem(mensagem: String) {
        apiUtils = ApiUtils()


        val usuario = usuarioRepository?.obterUsuarioLogado()
        val mensagemCriada = mensagemRepository?.criarMensagem(usuario, mensagem, dialog, true)

        val mensagemRequest = Mensagem(texto = mensagem, remetente = usuario?.id)

        val request = MensagemRequest(mensagem = mensagemRequest, to = "/topics/talonario_${dialog?.users!![0].id}")

        mensagemCriada?.let { view.addMensagemEnviada(it) }

        apiUtils.getFirebaseCloudMessageApi().enviarMensagem(request).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.i("", "")
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response?.isSuccessful == true) {
                }
                Log.i("", "")
            }

        })
    }

    fun atualizarMensagemLidaUsuario(mensagemLida: Boolean, mensagem: Message?) {
        mensagem?.mensagemLida = mensagemLida
        mensagemRepository?.salvarMesangem(mensagem)
    }

    fun salvarMensagemRecebida(mensagemEvent: NotificarMensagemEvent) {
        Log.i("DialogoPresenter", "salvarMensagemRecebida")
        val adicionarMensagemNaConversaAtual = dialog?.users?.any { it.id == mensagemEvent.idRemente }
        if (adicionarMensagemNaConversaAtual == true) {
            val mensagem = mensagemRepository?.obterMensagem(mensagemEvent.idMensagem)
            mensagem?.let { view.addMensagemRecebida(it) }
            atualizarMensagemLidaUsuario(true, mensagem)
        }
    }

    fun obterIdUsuarioLogado(): String =
            usuarioRepository?.obterUsuarioLogado()?.id.toString()

    fun obterMensagens(lastLoadedDate: Date?): List<Message?>? {
        return mensagemRepository?.obterMensagensDialog(dialog?.id, lastLoadedDate)
    }

    fun carregarMensagensNaoLidas() {
        val mensagens = mensagemRepository?.obterMensagensDialogNaoLidas(idDialog)
        mensagens?.forEach {
            it?.let { view.addMensagemRecebida(it) }
            atualizarMensagemLidaUsuario(true, it)
        }
    }

    fun carregarMensagens() {
        mensagemRepository?.obterMensagensDialogNaoLidas(idDialog)?.forEach {
            it?.let { view.addMensagemRecebida(it) }
            atualizarMensagemLidaUsuario(true, it)
        }
        view.loadMessages()

    }
}