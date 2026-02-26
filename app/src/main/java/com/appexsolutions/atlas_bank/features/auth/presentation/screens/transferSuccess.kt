package com.appexsolutions.atlas_bank.features.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appexsolutions.atlas_bank.features.auth.presentation.components.StepIndicator

// ─── Colors ────────────────────────────────────────────────────────────────────
private val BgDark        = Color(0xFF0E0E0E)
private val GoldAccent    = Color(0xFFD4A847)
private val TextPrimary   = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF888888)

// ─── Screen ────────────────────────────────────────────────────────────────────
@Composable
fun TransferSuccessScreen(
    onDone: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ── Top Bar ──────────────────────────────────────────────────────────
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(36.dp))
            Text(
                text = "Transferir",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(36.dp))
        }

        // ── All steps complete ───────────────────────────────────────────────
        StepIndicator(currentStep = 4)

        // ── Centered Success Content ─────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1A1A1A))
                        .border(
                            width = 1.5.dp,
                            color = GoldAccent,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Exito",
                        tint = GoldAccent,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Transferencia exitosa",
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tu transferencia fue procesada",
                    color = TextSecondary,
                    fontSize = 13.sp
                )
            }
        }

        // ── Done Button ──────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .height(52.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(GoldAccent)
                .clickable { onDone() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "HECHO",
                color = BgDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
        }
    }
}

// ─── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E, showSystemUi = true)
@Composable
fun TransferSuccessScreenPreview() {
    TransferSuccessScreen()
}
