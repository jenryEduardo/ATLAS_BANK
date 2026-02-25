package com.appexsolutions.atlas_bank.features.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
private val TextMuted     = Color(0xFF555555)
private val BorderColor   = Color(0xFF2A2A2A)
private val DividerColor  = Color(0xFF2A2A2A)

// ─── Screen ────────────────────────────────────────────────────────────────────
@Composable
fun TransferStep3ConfirmScreen(
    recipientSuffix: String = "2167",
    amount: String = "33.00",
    fromAccount: String = "Atlas Bank •••• ----",
    onBack: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
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

        // ── Step Indicator (all 3 active) ────────────────────────────────────
        StepIndicator(currentStep = 3)

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Confirm Transfer",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Review the details before confirming",
                color = TextSecondary,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ── Detail Card ──────────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceDark)
                    .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                DetailRow(
                    label = "RECIPIENT",
                    value = "•••• $recipientSuffix",
                    valueStyle = TextStyle(
                        color = TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Divider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = DividerColor,
                    thickness = 1.dp
                )
                DetailRow(
                    label = "AMOUNT",
                    value = "\$$amount",
                    valueStyle = TextStyle(
                        color = GoldAccent,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Divider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = DividerColor,
                    thickness = 1.dp
                )
                DetailRow(
                    label = "FROM",
                    value = fromAccount,
                    valueStyle = TextStyle(
                        color = TextPrimary,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        // ── Confirm Button ───────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(52.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(GoldAccent)
                .clickable { onConfirm() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CONFIRM TRANSFER",
                color = BgDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
        }
    }
}

// ─── Detail Row ───────────────────────────────────────────────────────────────
@Composable
private fun DetailRow(
    label: String,
    value: String,
    valueStyle: TextStyle
) {
    Column {
        Text(
            text = label,
            color = TextMuted,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.2.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(text = value, style = valueStyle)
    }
}

// ─── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E, showSystemUi = true)
@Composable
fun TransferStep3ConfirmPreview() {
    TransferStep3ConfirmScreen()
}
