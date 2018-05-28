package br.com.oxxynet.chatoxxy.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.com.oxxynet.chatoxxy.model.Dialog
import br.com.oxxynet.chatoxxy.model.Message
import br.com.oxxynet.chatoxxy.model.User
import br.com.oxxynet.chatoxxy.model.UserDialog
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.io.Closeable
import java.sql.SQLException

class DatabaseHelper(context: Context) : OrmLiteSqliteOpenHelper(context, "chat_talonario.db", null, 1), Closeable {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource) {
        try {
            Log.i("DatabaseHelper", "onCreate database")
            criarTabelas(connectionSource)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        try {

//            if (oldVersion < 2) {
//                upgrade2(connectionSource,db)
//            }

        } catch (e: SQLException) {
            Log.e("DatabaseHelper","Can't drop databases", e)
            throw RuntimeException(e)
        }

    }

    private fun upgrade2(connectionSource: ConnectionSource, db: SQLiteDatabase) {

    }

    private fun criarTabelas(connectionSource: ConnectionSource) {
        TableUtils.createTable<User>(connectionSource, User::class.java)
        TableUtils.createTable<Dialog>(connectionSource, Dialog::class.java)
        TableUtils.createTable<Message>(connectionSource, Message::class.java)
        TableUtils.createTable<UserDialog>(connectionSource, UserDialog::class.java)
    }




}