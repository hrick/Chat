package br.com.oxxynet.chatoxxy.repository

import br.com.oxxynet.chatoxxy.model.Dialog
import br.com.oxxynet.chatoxxy.model.Message
import br.com.oxxynet.chatoxxy.model.User
import com.j256.ormlite.dao.Dao
import java.util.*

class MensagemRepository(private val dao: Dao<Message, String>?) {

    fun salvarMesangem(message: Message?) {
        dao?.createOrUpdate(message)
    }

    fun obterMensagem(idMensagem: String?): Message? =
            dao?.queryForId(idMensagem)

    fun criarMensagem(usuario: User?, mensagem: String, dialog: Dialog?, mensagemLida: Boolean): Message? {
        val message = Message(id = UUID.randomUUID().toString(), text = mensagem, dialog = dialog, user = usuario, mensagemLida = mensagemLida)
        salvarMesangem(message)
        return message
    }

    fun obterMensagensDialog(idDialog: String?, ultimaData: Date?): List<Message?>? {
        val queryBuilder = dao?.queryBuilder()
        if (ultimaData == null) {
            queryBuilder
                    ?.orderBy(Message.Fields.DATA_HORA_CRIACAO, false)
                    ?.limit(30)
                    ?.where()
                    ?.eq(Message.Fields.DIALOGO, idDialog)
        } else {
            queryBuilder
                    ?.orderBy(Message.Fields.DATA_HORA_CRIACAO, false)
                    ?.limit(30)
                    ?.where()
                    ?.eq(Message.Fields.DIALOGO, idDialog)
                    ?.and()
                    ?.lt(Message.Fields.DATA_HORA_CRIACAO, ultimaData)
        }
        return queryBuilder?.query()
    }

    fun obterMensagensDialogNaoLidas(idDialog: String?): List<Message?>? {
        val queryBuilder = dao?.queryBuilder()
        queryBuilder
                ?.orderBy(Message.Fields.DATA_HORA_CRIACAO, true)
                ?.where()
                ?.eq(Message.Fields.DIALOGO, idDialog)
                ?.and()
                ?.eq(Message.Fields.MENSAGEM_LIDA, false)

        return queryBuilder?.query()
    }
}