package com.appexsolutions.atlas_bank.features.auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.AccessTime

// ─── Color Palette ─────────────────────────────────────────────────────────────
private val BackgroundDark   = Color(0xFF0E0E0E)
private val SurfaceDark      = Color(0xFF1A1A1A)
private val SurfaceMedium    = Color(0xFF222222)
private val GoldAccent       = Color(0xFFD4A847)
private val GoldLight        = Color(0xFFE8C66A)
private val TextPrimary      = Color(0xFFFFFFFF)
private val TextSecondary    = Color(0xFF888888)
private val TextMuted        = Color(0xFF555555)
private val PositiveGreen    = Color(0xFFD4A847)
private val BorderColor      = Color(0xFF2A2A2A)

// ─── Data Models ───────────────────────────────────────────────────────────────
data class Transaction(
    val title: String,
    val subtitle: String,
    val amount: String,
    val isPositive: Boolean
)

// ─── Sample Data ───────────────────────────────────────────────────────────────
private val sampleTransactions = listOf(
    Transaction("Transfer to Marcus Chen", "Today, 14:32", "-\$2,450.00", false),
    Transaction("Deposit", "Yesterday, 09:15", "+\$12,000.00", true),
    Transaction("Transfer to Sarah Klein", "Feb 22, 16:44", "-\$890.00", false)
)

// ─── Main Screen ───────────────────────────────────────────────────────────────
@Composable
fun BankingDashboardScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDark)
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Header
        item { HeaderSection() }

        // Balance
        item { BalanceSection() }

        // Card
        item {
            Spacer(modifier = Modifier.height(24.dp))
            BankCardSection()
        }

        // Action Buttons
        item {
            Spacer(modifier = Modifier.height(16.dp))
            ActionButtonsSection()
        }

        // Recent Activity Header
        item {
            Spacer(modifier = Modifier.height(28.dp))
            RecentActivityHeader()
        }

        // Transactions
        items(sampleTransactions) { tx ->
            TransactionItem(transaction = tx)
        }
    }
}

// ─── Header ────────────────────────────────────────────────────────────────────
@Composable
private fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "GOOD EVENING",
                color = TextSecondary,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Alexander Morgan",
                color = TextPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(SurfaceDark)
                .border(1.dp, BorderColor, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile",
                tint = TextSecondary,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

// ─── Balance ───────────────────────────────────────────────────────────────────
@Composable
private fun BalanceSection() {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
    ) {
        Text(
            text = "TOTAL BALANCE",
            color = TextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.5.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "\$124,580.00",
            color = TextPrimary,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.TrendingUp,
                contentDescription = null,
                tint = GoldAccent,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "+12.5% this month",
                color = GoldAccent,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// ─── Bank Card ─────────────────────────────────────────────────────────────────
@Composable
private fun BankCardSection() {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(190.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1C1C1C),
                        Color(0xFF141414)
                    )
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF3A3A3A),
                        Color(0xFF252525)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // Subtle gold shimmer accent top-right
        Box(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopEnd)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            GoldAccent.copy(alpha = 0.07f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Bank name
            Column {
                Text(
                    text = "ATLAS BANCK",
                    color = GoldAccent,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Black Card",
                    color = TextSecondary,
                    fontSize = 11.sp
                )
            }

            // Card number
            Text(
                text = "• • • •   • • • •   • • • •   8472",
                color = TextPrimary.copy(alpha = 0.85f),
                fontSize = 15.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Medium
            )

            // Cardholder + Expiry
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "CARDHOLDER",
                        color = TextMuted,
                        fontSize = 9.sp,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "ALEXANDER MORGAN",
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "EXPIRES",
                        color = TextMuted,
                        fontSize = 9.sp,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "12/28",
                        color = TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

// ─── Action Buttons ────────────────────────────────────────────────────────────
@Composable
private fun ActionButtonsSection() {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ActionButton(
            icon = Icons.Default.Send,
            label = "SEND",
            modifier = Modifier.weight(1f)
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            label = "RECEIVE",
            modifier = Modifier.weight(1f)
        )
        ActionButton(
            icon = Icons.Default.CreditCard,
            label = "PAY",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ActionButton(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceDark)
            .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = GoldAccent,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            color = TextPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.8.sp
        )
    }
}

// ─── Recent Activity Header ────────────────────────────────────────────────────
@Composable
private fun RecentActivityHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "RECENT ACTIVITY",
            color = TextPrimary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
        Text(
            text = "VIEW ALL",
            color = TextSecondary,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.5.sp
        )
    }
}

// ─── Transaction Item ──────────────────────────────────────────────────────────
@Composable
private fun TransactionItem(transaction: Transaction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceDark)
            .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = transaction.title,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = transaction.amount,
                color = if (transaction.isPositive) PositiveGreen else TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.AccessTime,
                contentDescription = null,
                tint = TextMuted,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = transaction.subtitle,
                color = TextMuted,
                fontSize = 11.sp
            )
        }
    }
}

// ─── Preview ───────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E, showSystemUi = true)
@Composable
fun BankingDashboardScreenPreview() {
    BankingDashboardScreen()
}