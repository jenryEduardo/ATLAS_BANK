package com.appexsolutions.atlas_bank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.appexsolutions.atlas_bank.core.theme.AppTheme
import com.appexsolutions.atlas_bank.features.auth.presentation.screens.BankingDashboardScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                BankingDashboardScreen()
            }
        }
    }
}
