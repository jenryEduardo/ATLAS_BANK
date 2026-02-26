// core/websocket/AtlasBankWebSocket.kt
package com.appexsolutions.atlas_bank.core.websocket

import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import java.net.URI

class AtlasBankWebSocket(
    serverUri: URI,
    private val cuentaId: String,
    private val onTransferReceived: () -> Unit // 👈 sin parámetros
) : WebSocketClient(serverUri) {

    override fun onOpen(handshake: ServerHandshake) {
        val registerMsg = JSONObject().apply {
            put("type", "register")
            put("accountId", cuentaId)
        }
        send(registerMsg.toString())
    }

    override fun onMessage(message: String) {
        try {
            val json = JSONObject(message)
            if (json.getString("type") == "transfer_notification") {
                onTransferReceived()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {}
    override fun onError(ex: Exception) { ex.printStackTrace() }
}

data class TransferNotification(
    val transferId: String,
    val fromAccount: String,
    val toAccount: String,
    val amount: Double,
    val currency: String,
    val status: String,
    val timestamp: String
)