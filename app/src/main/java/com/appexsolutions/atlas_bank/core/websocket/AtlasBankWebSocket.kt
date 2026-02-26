// core/websocket/AtlasBankWebSocket.kt
package com.appexsolutions.atlas_bank.core.websocket

import android.util.Log
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import java.net.URI

class AtlasBankWebSocket(
    serverUri: URI,
    private val cuentaId: String,
    private val onTransferReceived: () -> Unit
) : WebSocketClient(serverUri) {

    override fun onOpen(handshake: ServerHandshake) {
        Log.d("WS", "Conectado al servidor WS. Registrando cuentaId=$cuentaId")
        val registerMsg = JSONObject().apply {
            put("type", "register")
            put("accountId", cuentaId)
        }
        send(registerMsg.toString())
    }

    override fun onMessage(message: String) {
        Log.d("WS", "Mensaje recibido: $message")
        try {
            val json = JSONObject(message)
            val type = json.getString("type")
            Log.d("WS", "Tipo del mensaje: $type")
            if (type == "transfer_update") {
                Log.d("WS", "Notificacion de transferencia recibida, refrescando dashboard...")
                onTransferReceived()
            }
        } catch (e: Exception) {
            Log.e("WS", "Error al parsear mensaje WS: ${e.message}", e)
        }
    }

    override fun onClose(code: Int, reason: String, remote: Boolean) {
        Log.d("WS", "Conexion cerrada. code=$code reason=$reason remote=$remote")
    }

    override fun onError(ex: Exception) {
        Log.e("WS", "Error en WebSocket: ${ex.message}", ex)
    }
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