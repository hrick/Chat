package br.com.oxxynet.chatoxxy.repository

import br.com.oxxynet.chatoxxy.model.Dialog
import br.com.oxxynet.chatoxxy.model.User
import br.com.oxxynet.chatoxxy.model.UserDialog
import com.j256.ormlite.dao.Dao

class DialogRepository(val daoDialog: Dao<Dialog, String>?, val daoUserDialog: Dao<UserDialog, String>?) {

    fun obterDialogPorRemetente(idUserRemetente: String?): Dialog? {
        var dialog: Dialog? = null
        val userDialog = daoUserDialog?.queryForEq(UserDialog.Fields.ID_USUARIO, idUserRemetente)
        if (userDialog?.isNotEmpty() == true) {
            dialog = userDialog[0].dialog
        }
        return dialog
    }

    fun obterDialog(idDialog: String): Dialog? {
        return daoDialog?.queryForId(idDialog)
    }

    fun criarDialog(dialog: Dialog?) {
        daoDialog?.createOrUpdate(dialog)
    }

    fun criarDialogFake(user: User?): Dialog? {
        val dialogo = daoDialog?.queryForId("147258")
        if (dialogo == null) {
            val dialog = Dialog(id = "147258", dialogName = "Conversinha", dialogPhoto = "http://www.unilab.edu.br/wp-content/uploads/2014/07/bal%C3%B5es.jpg")
            daoDialog?.createOrUpdate(dialog)
            daoUserDialog?.createOrUpdate(UserDialog(id = "${user?.id}.147258", dialog = dialog, user = user))
        }
        return daoDialog?.queryForId("147258")
    }
}