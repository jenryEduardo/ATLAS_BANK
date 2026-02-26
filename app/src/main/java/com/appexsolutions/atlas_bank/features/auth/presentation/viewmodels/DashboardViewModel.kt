package com.appexsolutions.atlas_bank.features.auth.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appexsolutions.atlas_bank.features.auth.domain.entities.User
import com.appexsolutions.atlas_bank.features.auth.domain.usescases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.appexsolutions.atlas_bank.core.session.SessionManager
import com.appexsolutions.atlas_bank.core.websocket.AtlasBankWebSocket
import com.appexsolutions.atlas_bank.core.websocket.TransferNotification
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Login
import com.appexsolutions.atlas_bank.features.auth.domain.usescases.LoginUseCase
import java.net.URI
data class DashboardUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    private var webSocket: AtlasBankWebSocket? = null

    fun setUser(user: User) {
        if (_uiState.value.user == null) {
            _uiState.value = _uiState.value.copy(user = user)
        }
        connectWebSocket()
    }

    private fun connectWebSocket() {
        if (webSocket?.isOpen == true) {
            android.util.Log.d("DashboardVM", "WS ya esta conectado, ignorando reconexion")
            return
        }
        val cuentaId = sessionManager.getCuentaId()
        if (cuentaId == null) {
            android.util.Log.e("DashboardVM", "No se pudo conectar WS: cuentaId es null")
            return
        }
        webSocket?.close()
        android.util.Log.d("DashboardVM", "Conectando WS con cuentaId=$cuentaId")
        webSocket = AtlasBankWebSocket(
            serverUri = URI("ws://3.85.155.29:3000"),
            cuentaId = cuentaId,
            onTransferReceived = {
                android.util.Log.d("DashboardVM", "onTransferReceived disparado, llamando refreshDashboard()")
                refreshDashboard()
            }
        )
        webSocket?.connect()
    }

    fun refreshDashboard() {
        viewModelScope.launch {
            val email = sessionManager.getEmail() ?: return@launch
            val password = sessionManager.getPassword() ?: return@launch

            val result = loginUseCase(Login(email = email, password = password))
            result.onSuccess { updatedUser ->
                _uiState.value = _uiState.value.copy(user = updatedUser)
            }.onFailure { error ->
                android.util.Log.e("DashboardVM", "Error al refrescar datos: ${error.message}", error)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        webSocket?.close()
    }
}