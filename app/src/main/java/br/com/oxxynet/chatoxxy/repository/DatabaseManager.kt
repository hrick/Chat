package br.com.oxxynet.chatoxxy.repository

import android.content.Context
import br.com.oxxynet.chatoxxy.model.Dialog
import br.com.oxxynet.chatoxxy.model.Message
import br.com.oxxynet.chatoxxy.model.User
import br.com.oxxynet.chatoxxy.model.UserDialog
import com.j256.ormlite.dao.Dao

class DatabaseManager(context: Context) {

    var userDao: Dao<User, String>? = null
    var messageDao: Dao<Message, String>? = null
    var dialogDao: Dao<Dialog, String>? = null
    var userDialogDao: Dao<UserDialog, String>? = null

    companion object {
        lateinit var instance: DatabaseHelper
    }

    init {
        instance = DatabaseHelper(context)
        userDao = if (userDao == null) instance.getDao(User::class.java) else userDao
        messageDao = if (messageDao == null) instance.getDao(Message::class.java) else messageDao
        dialogDao = if (dialogDao == null) instance.getDao(Dialog::class.java) else dialogDao
        userDialogDao = if (userDialogDao == null) instance.getDao(UserDialog::class.java) else userDialogDao
    }
}