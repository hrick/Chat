package br.com.oxxynet.chatoxxy.view.mensagens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import br.com.oxxynet.chatoxxy.DemoMessagesActivity
import br.com.oxxynet.chatoxxy.R
import br.com.oxxynet.chatoxxy.events.NotificarMensagemEvent
import br.com.oxxynet.chatoxxy.fixture.MessagesFixtures
import com.stfalcon.chatkit.messages.MessageInput
import kotlinx.android.synthetic.main.activity_main.*
import br.com.oxxynet.chatoxxy.model.Message
import br.com.oxxynet.chatoxxy.repository.DatabaseManager
import br.com.oxxynet.chatoxxy.util.AppUtils
import com.google.firebase.messaging.FirebaseMessaging
import com.stfalcon.chatkit.messages.MessagesListAdapter
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.EventBus
import java.util.*


class DialogoActivity : DemoMessagesActivity(), MessageInput.InputListener,
        MessageInput.AttachmentsListener, DialogoView,
        MessagesListAdapter.OnLoadMoreListener {

    lateinit var presenter: DialogoPresenter
    private val TOTAL_MESSAGES_COUNT = 100
    var lastLoadedDate: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val extras = intent.extras
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (extras != null) {
            input.setInputListener(this)
            val idDialog = extras.getString("idDialog", "")
            presenter = DialogoPresenter(this, DatabaseManager(this), idDialog)

            initAdapter(presenter.obterIdUsuarioLogado())
            val topico = "talonario_${presenter.obterIdUsuarioLogado()}"
            FirebaseMessaging.getInstance().subscribeToTopic(topico)
            presenter.carregarMensagens()
        } else {
// todo teste
            input.setInputListener(this)
            presenter = DialogoPresenter(this, DatabaseManager(this), dialog.id ?: "")

            initAdapter(presenter.obterIdUsuarioLogado())
            val topico = "talonario_${presenter.obterIdUsuarioLogado()}"
            FirebaseMessaging.getInstance().subscribeToTopic(topico)
            presenter.carregarMensagens()
//            Toast.makeText(this,"Erro ao carregar conversa", Toast.LENGTH_LONG).show()
//            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        presenter.carregarMensagensNaoLidas()
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNotificarMensagemEvent(notificarMensagemEvent: NotificarMensagemEvent) {
        presenter.salvarMensagemRecebida(notificarMensagemEvent)
    }


    override fun onSubmit(input: CharSequence?): Boolean {
        presenter.salvarEnviarMensagem(input.toString())
        return true
    }

    override fun addMensagemRecebida(mensagem: Message) {
        super.messagesAdapter.addToStart(mensagem, true)
        if (lastLoadedDate == null) {
            lastLoadedDate = mensagem.createdAt
        }
    }


    override fun addMensagemEnviada(mensagem: Message) {
        super.messagesAdapter.addToStart(mensagem, true)
        if (lastLoadedDate == null) {
            lastLoadedDate = mensagem.createdAt
        }
    }


    override fun onAddAttachments() {
//        super.messagesAdapter.addToStart(MessagesFixtures.getImageMessage(), true)
    }

    override fun onLoadMore(page: Int, totalItemsCount: Int) {
        if (totalItemsCount < this.TOTAL_MESSAGES_COUNT) {
            loadMessages()
        }
    }


    override fun loadMessages() {
        Handler().postDelayed({
            val messages = presenter.obterMensagens(lastLoadedDate)
            if (messages?.isNotEmpty() == true) {
                lastLoadedDate = messages[messages.size - 1]?.createdAt
                messagesAdapter.addToEnd(messages, false)
            }
        }, 1000)
    }


    private fun initAdapter(idUsuarioLogado: String) {
        super.messagesAdapter = MessagesListAdapter<Message>(idUsuarioLogado, super.imageLoader)
        super.messagesAdapter.enableSelectionMode(this)
        super.messagesAdapter.setLoadMoreListener(this)
        super.messagesAdapter.registerViewClickListener(R.id.messageUserAvatar) { _, message ->
            AppUtils.showToast(this@DialogoActivity,
                    message.user?.name + " avatar click",
                    false)
        }
        this.messagesList.setAdapter(super.messagesAdapter)
    }
}
