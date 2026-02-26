package com.appexsolutions.atlas_bank.features.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val GoldAccent = Color(0xFFD4A847)

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