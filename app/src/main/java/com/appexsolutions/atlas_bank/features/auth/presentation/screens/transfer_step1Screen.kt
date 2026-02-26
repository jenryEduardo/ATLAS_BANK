package com.appexsolutions.atlas_bank.features.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appexsolutions.atlas_bank.features.auth.domain.entities.Recipient
import com.appexsolutions.atlas_bank.features.auth.presentation.components.StepIndicator

private val BgDark        = Color(0xFF0E0E0E)
private val SurfaceDark   = Color(0xFF1A1A1A)
private val GoldAccent    = Color(0xFFD4A847)
private val TextPrimary   = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF888888)
private val BorderColor   = Color(0xFF2A2A2A)

@Composable
fun TransferStep1RecipientScreen(
    users: List<Recipient> = emptyList(),
    isLoading: Boolean = false,
    onBack: () -> Unit = {},
    onContinue: (Recipient) -> Unit = {}
) {
    var selectedUser: Recipient? by remember { mutableStateOf(null) } // ✅ nullable

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp))
                    .background(SurfaceDark)
                    .border(1.dp, BorderColor, RoundedCornerShape(8.dp))
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atras",
                    tint = GoldAccent,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text("Transfer", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }

        StepIndicator(currentStep = 1)
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 16.dp)
        ) {
            Text("Select Recipient", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Choose from registered accounts", color = TextSecondary, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "CONTACTOS RECIENTES", color = TextSecondary, fontSize = 10.sp,
                fontWeight = FontWeight.Medium, letterSpacing = 1.2.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = GoldAccent)
                }
            } else {
                users.forEach { recipient ->
                    ContactItem(
                        name = recipient.name,
                        email = recipient.id,
                        isSelected = selectedUser == recipient,
                        onClick = { selectedUser = recipient }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        val canContinue = selectedUser != null  // ✅ ahora funciona correctamente
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(52.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (canContinue) GoldAccent else Color(0xFF6B5A2A))
                .clickable(enabled = canContinue) { selectedUser?.let { onContinue(it) } },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "CONTINUE",
                color = if (canContinue) BgDark else Color(0xFF555555),
                fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp
            )
        }
    }
}

@Composable
private fun ContactItem(
    name: String,
    email: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) Color(0xFF222222) else SurfaceDark)
            .border(
                1.dp,
                if (isSelected) GoldAccent.copy(alpha = 0.4f) else BorderColor,
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Column {
            Text(name, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Spacer(modifier = Modifier.height(2.dp))
            Text(email, color = TextSecondary, fontSize = 12.sp)
        }
    }
}

