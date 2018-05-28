package br.com.oxxynet.chatoxxy.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

// id é o id do usuario com o id da mensagem "${user.id}.${dialog.id}"

@DatabaseTable(tableName = "TB_USER_MESSAGE")
data class UserDialog(@DatabaseField(columnName = Fields.ID, id = true)
                      val id: String? = null,
                      @DatabaseField(foreign = true, columnName = Fields.ID_USUARIO)
                      val user: User? = null,
                      @DatabaseField(foreign = true, columnName = Fields.ID_DIALOGO)
                      val dialog: Dialog? = null) {
    object Fields {

        const val ID_DIALOGO = "ID_DIALOGO"
        const val ID_USUARIO = "ID_USUARIO"
        const val ID = "ID"

    }
}