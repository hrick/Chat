package br.com.oxxynet.chatoxxy.view.mensagens

import br.com.oxxynet.chatoxxy.model.Message

interface DialogoView {
    fun addMensagemEnviada(mensagem: Message)
    fun addMensagemRecebida(mensagem: Message)
    fun loadMessages()
}