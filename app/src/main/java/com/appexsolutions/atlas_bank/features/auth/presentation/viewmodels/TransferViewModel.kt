package com.appexsolutions.atlas_bank.features.auth.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appexsolutions.atlas_bank.core.session.SessionManager
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Recipient
import com.appexsolutions.atlas_bank.features.auth.domain.entities.TransferRequest
import com.appexsolutions.atlas_bank.features.auth.domain.usescases.GetUsersUseCase
import com.appexsolutions.atlas_bank.features.auth.domain.usescases.TransferUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val transferUseCase: TransferUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransferUiState())
    val uiState: StateFlow<TransferUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<TransferEvent>()
    val events: SharedFlow<TransferEvent> = _events.asSharedFlow()

    fun loadUsers() {
        val currentCuentaId = sessionManager.getCuentaId()
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            getUsersUseCase()
                .onSuccess { users ->
                    val filtered = users.filter { it.id != currentCuentaId }
                    Log.d("TRANSFER", "Usuarios recibidos: ${users.size}, mostrados: ${filtered.size} (excluye cuentaId=$currentCuentaId)")
                    filtered.forEach { user ->
                        Log.d("TRANSFER", "Recipient: id=${user.id}, name=${user.name}")
                    }
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        users = filtered
                    )
                }
                .onFailure { e ->
                    Log.d("TRANSFER", "Error: ${e.message}")
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
        }
    }

    fun selectRecipient(recipient: Recipient) {  // 👈 Recipient no User
        _uiState.value = _uiState.value.copy(selectedRecipient = recipient)
    }

    fun setAmount(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
    }

    fun setConcept(concept: String) {
        _uiState.value = _uiState.value.copy(concept = concept)
    }

    fun resetState() {
        _uiState.value = TransferUiState()
    }

    fun confirmTransfer() {
        val fromUserId = sessionManager.getCuentaId() ?: run {
            Log.e("TRANSFER", "No se encontró cuentaId en sesión")
            return
        }
        val state = _uiState.value
        val recipient = state.selectedRecipient ?: return
        val amount = state.amount.toFloatOrNull() ?: return

        Log.d("TRANSFER", "Enviando transferencia: origen=$fromUserId destino=${recipient.id} monto=$amount")

        val saldoAnterior = state.currentBalance
        _uiState.value = _uiState.value.copy(
            currentBalance = saldoAnterior - amount,
            isTransferLoading = true
        )

        viewModelScope.launch {
            transferUseCase(
                TransferRequest(
                    cuentaOrigenId = fromUserId,
                    cuentaDestinoId = recipient.id,
                    monto = amount,
                    concepto = state.concept.ifEmpty { "Transferencia" }
                )
            )
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isTransferLoading = false)
                    _events.emit(TransferEvent.Success)
                }
                .onFailure { e ->
                    _uiState.value = _uiState.value.copy(
                        currentBalance = saldoAnterior,
                        isTransferLoading = false,
                        error = e.message
                    )
                    _events.emit(TransferEvent.Error(e.message ?: "Error en transferencia"))
                }
        }
    }
}

data class TransferUiState(
    val isLoading: Boolean = false,
    val isTransferLoading: Boolean = false,
    val users: List<Recipient> = emptyList(),
    val selectedRecipient: Recipient? = null,
    val amount: String = "",
    val concept: String = "",
    val currentBalance: Float = 0f,
    val error: String? = null
)

sealed class TransferEvent {
    object Success : TransferEvent()
    data class Error(val message: String) : TransferEvent()
}