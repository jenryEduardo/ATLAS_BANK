package com.appexsolutions.atlas_bank

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Recipient
import com.appexsolutions.atlas_bank.features.auth.presentation.screens.BankingDashboardScreen
import com.appexsolutions.atlas_bank.features.auth.presentation.screens.LoginScreen
import com.appexsolutions.atlas_bank.features.auth.presentation.screens.TransferStep1RecipientScreen
import com.appexsolutions.atlas_bank.features.auth.presentation.screens.TransferStep2AmountScreen
import com.appexsolutions.atlas_bank.features.auth.presentation.screens.TransferStep3ConfirmScreen
import com.appexsolutions.atlas_bank.features.auth.presentation.screens.TransferSuccessScreen
import com.appexsolutions.atlas_bank.features.auth.presentation.viewmodels.AuthViewModel
import com.appexsolutions.atlas_bank.features.auth.presentation.viewmodels.DashboardViewModel
import com.appexsolutions.atlas_bank.features.auth.presentation.viewmodels.TransferEvent
import com.appexsolutions.atlas_bank.features.auth.presentation.viewmodels.TransferViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val transferViewModel: TransferViewModel = hiltViewModel() // 👈 UNA sola instancia

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            val uiState by authViewModel.uiState.collectAsState()

            LaunchedEffect(uiState.user) {
                if (uiState.user != null) {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }

            LoginScreen(
                uiState = uiState,
                onLogin = { email, password -> authViewModel.login(email, password) }
            )
        }

        composable(route = "dashboard") {
            val dashboardViewModel: DashboardViewModel = hiltViewModel()
            val dashboardUiState by dashboardViewModel.uiState.collectAsState()
            val authUiState by authViewModel.uiState.collectAsState()

            LaunchedEffect(authUiState.user) {
                authUiState.user?.let { user ->
                    dashboardViewModel.setUser(user)
                }
            }

            BankingDashboardScreen(
                uiState = dashboardUiState,
                onSendClick = { navController.navigate("transfer_step1") }
            )
        }

        composable(route = "transfer_step1") {
            val uiState by transferViewModel.uiState.collectAsState()

            LaunchedEffect(Unit) {
                transferViewModel.loadUsers()
            }

            TransferStep1RecipientScreen(
                users = uiState.users,
                isLoading = uiState.isLoading,
                onBack = { navController.popBackStack() },
                onContinue = { recipient: Recipient ->
                    transferViewModel.selectRecipient(recipient)
                    navController.navigate("transfer_step2")
                }
            )
        }

        composable(route = "transfer_step2") {
            val uiState by transferViewModel.uiState.collectAsState()

            TransferStep2AmountScreen(
                onBack = { navController.popBackStack() },
                onContinue = { amount, concept ->
                    transferViewModel.setAmount(amount)
                    transferViewModel.setConcept(concept)  // 👈 también guarda el concepto
                    navController.navigate("transfer_step3")
                }
            )
        }

        composable(route = "transfer_step3") {
            val uiState by transferViewModel.uiState.collectAsState()

            LaunchedEffect(Unit) {
                transferViewModel.events.collect { event ->
                    when (event) {
                        is TransferEvent.Success -> {
                            navController.navigate("transfer_success") {
                                popUpTo("dashboard")
                            }
                        }
                        is TransferEvent.Error -> { /* puedes mostrar snackbar aquí */ }
                    }
                }
            }

            TransferStep3ConfirmScreen(
                recipientName = uiState.selectedRecipient?.name ?: "",
                amount = uiState.amount,
                isLoading = uiState.isTransferLoading,
                onBack = { navController.popBackStack() },
                onConfirm = { transferViewModel.confirmTransfer() }
            )
        }

        composable(route = "transfer_success") {
            TransferSuccessScreen(
                onDone = {
                    transferViewModel.resetState() // 👈 limpia el estado al terminar
                    navController.navigate("dashboard") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
    }
}