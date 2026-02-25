package com.appexsolutions.atlas_bank.features.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ─── Colors ────────────────────────────────────────────────────────────────────
private val BgDark        = Color(0xFF0E0E0E)
private val SurfaceDark   = Color(0xFF1A1A1A)
private val GoldAccent    = Color(0xFFD4A847)
private val GoldDisabled  = Color(0xFF6B5A2A)
private val TextPrimary   = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF888888)
private val TextMuted     = Color(0xFF555555)
private val BorderColor   = Color(0xFF2A2A2A)
private val DividerColor  = Color(0xFF2A2A2A)

// ─── Screen ────────────────────────────────────────────────────────────────────
@Composable
fun TransferStep2AmountScreen(
    onBack: () -> Unit = {},
    onContinue: (String) -> Unit = {}
) {
    var amount by remember { mutableStateOf("") }
    var concept by remember { mutableStateOf("") }

    val hasAmount = amount.isNotEmpty() && amount != "0"

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

        // ── Step Indicator (step 2 active) ───────────────────────────────────
        StepIndicator(currentStep = 2)

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Enter Amount",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = "Available balance: ", color = TextSecondary, fontSize = 12.sp)
                Text(text = "Atlas Bank", color = GoldAccent, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // ── Amount Input ─────────────────────────────────────────────────
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$",
                    color = GoldAccent,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = amount,
                    onValueChange = { newVal ->
                        val filtered = newVal.filter { it.isDigit() || it == '.' }
                        amount = filtered
                    },
                    textStyle = TextStyle(
                        color = if (hasAmount) TextPrimary else TextMuted,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    cursorBrush = SolidColor(GoldAccent),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    decorationBox = { innerTextField ->
                        if (amount.isEmpty()) {
                            Text(
                                text = "0.00",
                                color = TextMuted,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        innerTextField()
                    },
                    modifier = Modifier.width(IntrinsicSize.Min)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // ── Concept ──────────────────────────────────────────────────────
            Text(
                text = "CONCEPT (OPTIONAL)",
                color = TextSecondary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.2.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = concept,
                onValueChange = { concept = it },
                textStyle = TextStyle(color = TextPrimary, fontSize = 14.sp),
                cursorBrush = SolidColor(GoldAccent),
                decorationBox = { innerTextField ->
                    if (concept.isEmpty()) {
                        Text(text = "Add a note", color = TextMuted, fontSize = 14.sp)
                    }
                    innerTextField()
                },
                modifier = Modifier.fillMaxWidth()
            )
            Divider(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                color = DividerColor,
                thickness = 1.dp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // ── Quick Amount Buttons ─────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("$100", "$500", "$1000").forEach { preset ->
                    QuickAmountButton(
                        label = preset,
                        modifier = Modifier.weight(1f),
                        onClick = { amount = preset.removePrefix("$") }
                    )
                }
            }
        }

        // ── Continue Button ──────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .height(52.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (hasAmount) GoldAccent else GoldDisabled)
                .clickable(enabled = hasAmount) { onContinue(amount) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CONTINUE",
                color = if (hasAmount) BgDark else TextMuted,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )
        }
    }
}

// ─── Quick Amount Button ───────────────────────────────────────────────────────
@Composable
private fun QuickAmountButton(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1A1A1A))
            .border(1.dp, Color(0xFF2A2A2A), RoundedCornerShape(8.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}

// ─── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E, showSystemUi = true)
@Composable
fun TransferStep2Preview() {
    TransferStep2AmountScreen()
}
