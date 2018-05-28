package br.com.oxxynet.chatoxxy.repository

import br.com.oxxynet.chatoxxy.model.User
import com.j256.ormlite.dao.Dao

class UsuarioRepository(val dao: Dao<User, String>?) {

    fun obterUsuarioPorId(idUsuario: String?): User? {
        return dao?.queryForId(idUsuario)
    }

    fun obterUsuarioLogado(): User? {
        val usuarioLogado = dao?.queryForId("123456")
        if (usuarioLogado == null) {
            dao?.createOrUpdate(User(id = "123456", name = "Henrique", avatar = "https://img.freepik.com/vetores-gratis/silhueta-do-homem-de-estilo-moderno_1020-452.jpg?size=338&ext=jpg"))
        }
        return dao?.queryForId("123456")

    }

    fun criarUsuarioParaComunicar(): User? {

        val usuarioLogado = dao?.queryForId("987654")
        if (usuarioLogado == null) {
            dao?.createOrUpdate(User(id = "987654", name = "Juca", avatar = "https://i.pinimg.com/736x/55/5a/03/555a03880ea8c4e1a66d8c4ea674d91c--emoji-emoticons-emojis.jpg"))
        }
        return dao?.queryForId("987654")


    }

}