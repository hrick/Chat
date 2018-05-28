package br.com.oxxynet.chatoxxy.model

import android.speech.tts.Voice
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import com.stfalcon.chatkit.commons.models.IMessage
import com.stfalcon.chatkit.commons.models.MessageContentType
import java.util.*
import kotlin.Comparator

@DatabaseTable(tableName = "TB_MESSAGE")
data class Message @JvmOverloads constructor(
        @DatabaseField(columnName = Message.Fields.ID_MESSAGE, id = true)
        private val id: String = UUID.randomUUID().toString(),
        @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = Message.Fields.USUARIO)
        private val user: User? = null,
        @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = Message.Fields.DIALOGO)
        private val dialog: Dialog? = null,
        @DatabaseField(columnName = Message.Fields.TEXTO)
        private var text: String? = null,
        @DatabaseField(columnName = Message.Fields.MENSAGEM_LIDA)
        var mensagemLida: Boolean? = null,
        @DatabaseField(columnName = Fields.DATA_HORA_CRIACAO)
        private var createdAt: Date? = Date()) : MessageContentType.Image, Comparator<Message?> {


    private var image: Image? = null
    var voice: Voice? = null

    val status: String
        get() = "Sent"

    override fun getId(): String? {
        return id
    }

    override fun getText(): String? {
        return text
    }

    override fun getCreatedAt(): Date? {
        return createdAt
    }

    override fun getUser(): User? {
        return this.user
    }

    override fun getImageUrl(): String? {
        return if (image == null) null else image!!.url
    }

    fun setText(text: String) {
        this.text = text
    }

    fun setCreatedAt(createdAt: Date) {
        this.createdAt = createdAt
    }

    fun setImage(image: Image) {
        this.image = image
    }

    object Fields {

        const val ID_MESSAGE = "ID_MESSAGE"
        const val USUARIO = "USUARIO"
        const val DIALOGO = "DIALOGO"
        const val TEXTO = "TEXTO"
        const val MENSAGEM_LIDA = "MENSAGEM_LIDA"
        const val DATA_HORA_CRIACAO = "DATA_HORA_CRIACAO"

    }

    data class Image(val url: String)

    data class Voice(val url: String, val duration: Int)

    override fun compare(o1: Message?, o2: Message?): Int {
        return if (o1?.createdAt?.after(o2?.createdAt) == true) -1 else 0
    }

}

