package com.appexsolutions.atlas_bank.features.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ─── Colors ────────────────────────────────────────────────────────────────────
private val BgDark        = Color(0xFF0E0E0E)
private val SurfaceDark   = Color(0xFF1A1A1A)
private val GoldAccent    = Color(0xFFD4A847)
private val TextPrimary   = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF888888)
private val BorderColor   = Color(0xFF2A2A2A)
private val DividerColor  = Color(0xFF2A2A2A)

// ─── Data ──────────────────────────────────────────────────────────────────────
private data class Contact(val name: String, val accountSuffix: String)

private val recentContacts = listOf(
    Contact("Marcus Chen", "4521"),
    Contact("Sarah Klein", "8934"),
    Contact("James Wilson", "2167")
)

// ─── Screen ────────────────────────────────────────────────────────────────────
@Composable
fun TransferStep1RecipientScreen(
    onBack: () -> Unit = {},
    onContinue: (String) -> Unit = {}
) {
    var accountNumber by remember { mutableStateOf("") }
    var selectedContact by remember { mutableStateOf<Contact?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        // ── Top Bar ──────────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(SurfaceDark)
                    .border(1.dp, BorderColor, RoundedCornerShape(8.dp))
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = GoldAccent,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Transfer",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // ── Step Indicator ───────────────────────────────────────────────────
        StepIndicator(currentStep = 1)

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Select Recipient",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Choose from recent contacts or enter new details",
                color = TextSecondary,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ── Account Number Input ─────────────────────────────────────────
            Text(
                text = "ACCOUNT NUMBER",
                color = TextSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = accountNumber,
                onValueChange = {
                    accountNumber = it
                    selectedContact = null
                },
                textStyle = TextStyle(
                    color = TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ),
                cursorBrush = SolidColor(GoldAccent),
                modifier = Modifier.fillMaxWidth(),
                decorationBox = { innerTextField ->
                    if (accountNumber.isEmpty()) {
                        Text(
                            text = "Enter account number",
                            color = Color(0xFF555555),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = DividerColor,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "RECENT CONTACTS",
                color = TextSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.2.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            recentContacts.forEach { contact ->
                val isSelected = selectedContact == contact
                ContactItem(
                    contact = contact,
                    isSelected = isSelected,
                    onClick = {
                        selectedContact = contact
                        accountNumber = contact.accountSuffix
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // ── Continue Button ──────────────────────────────────────────────────
        val suffix = selectedContact?.accountSuffix ?: accountNumber
        val canContinue = suffix.isNotBlank()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(52.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (canContinue) GoldAccent else Color(0xFF6B5A2A))
                .clickable(enabled = canContinue) { onContinue(suffix) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CONTINUE",
                color = if (canContinue) BgDark else Color(0xFF555555),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
        }
    }
}

// ─── Contact Item ──────────────────────────────────────────────────────────────
@Composable
private fun ContactItem(
    contact: Contact,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) Color(0xFF222222) else SurfaceDark)
            .border(
                width = 1.dp,
                color = if (isSelected) GoldAccent.copy(alpha = 0.4f) else BorderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Column {
            Text(
                text = contact.name,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "•••• ${contact.accountSuffix}",
                color = TextSecondary,
                fontSize = 12.sp
            )
        }
    }
}

// ─── Step Indicator ────────────────────────────────────────────────────────────
@Composable
fun StepIndicator(currentStep: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(3) { index ->
            val step = index + 1
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        if (step <= currentStep) GoldAccent else Color(0xFF2A2A2A)
                    )
            )
        }
    }
}

// ─── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E, showSystemUi = true)
@Composable
fun TransferStep1Preview() {
    TransferStep1RecipientScreen()
}
