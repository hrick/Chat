package br.com.oxxynet.chatoxxy.model

import com.j256.ormlite.dao.ForeignCollection
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.field.ForeignCollectionField
import com.j256.ormlite.table.DatabaseTable
import com.stfalcon.chatkit.commons.models.IUser

@DatabaseTable(tableName = "TB_USER")
data class User(
        @DatabaseField(columnName = User.Fields.ID_USUARIO, id = true)
        private val id: String? = null,
        @DatabaseField(columnName = User.Fields.NAME)
        private val name: String? = null,
        @DatabaseField(columnName = User.Fields.AVATAR)
        private val avatar: String? = null,
        @ForeignCollectionField(eager = false)
        private val userDialog: ForeignCollection<UserDialog>? = null,
        @DatabaseField(columnName = User.Fields.ESTA_ONLINE)
        val isOnline: Boolean = false) : IUser {

    override fun getId(): String? {
        return id
    }

    override fun getName(): String? {
        return name
    }

    override fun getAvatar(): String? {
        return avatar
    }

    object Fields {

        const val NAME = "NAME"
        const val ID_USUARIO = "ID_USUARIO"
        const val AVATAR = "AVATAR"
        const val ESTA_ONLINE = "ESTA_ONLINE"

    }
}
