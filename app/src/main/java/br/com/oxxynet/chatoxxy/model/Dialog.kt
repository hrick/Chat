package br.com.oxxynet.chatoxxy.model

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable
import com.stfalcon.chatkit.commons.models.IDialog

import java.util.ArrayList

@DatabaseTable(tableName = "TB_DIALOG")
data class Dialog(
        @DatabaseField(columnName = Fields.ID_DIALOG, id = true)
        private val id: String? = null,
        @DatabaseField(columnName = Fields.DIALOG_NAME)
        private val dialogName: String? = null,
        @DatabaseField(columnName = Fields.DIALOG_FOTO)
        private val dialogPhoto: String? = null,
        @ForeignCollectionField(eager = false)
        private val users: ForeignCollection<UserDialog>? = null,
        @ForeignCollectionField(eager = false)
        val mensagens: ForeignCollection<Message>? = null,
        @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = Fields.ULTIMA_MENSAGEM)
        private var lastMessage: Message? = null,
        @DatabaseField(columnName = Fields.QUANTIDADE_MSG_NAO_LIDAS)
        private var unreadCount: Int = 0) : IDialog<Message> {

    override fun getId(): String? {
        return id
    }

    override fun getDialogPhoto(): String? {
        return dialogPhoto
    }

    override fun getDialogName(): String? {
        return dialogName
    }

    override fun getUsers(): ArrayList<User> {
        return ArrayList(users?.map { it.user!! })
    }

    override fun getLastMessage(): Message? {
        return lastMessage
    }

    override fun setLastMessage(lastMessage: Message) {
        this.lastMessage = lastMessage
    }

    override fun getUnreadCount(): Int {
        return unreadCount
    }

    fun setUnreadCount(unreadCount: Int) {
        this.unreadCount = unreadCount
    }

    object Fields {

        const val ID_DIALOG = "ID_DIALOG"
        const val DIALOG_NAME = "DIALOG_NAME"
        const val DIALOG_FOTO = "DIALOG_FOTO"
        const val USUARIOS = "USUARIOS"
        const val ULTIMA_MENSAGEM = "ULTIMA_MENSAGEM"
        const val QUANTIDADE_MSG_NAO_LIDAS = "QUANTIDADE_MSG_NAO_LIDAS"

    }
}
