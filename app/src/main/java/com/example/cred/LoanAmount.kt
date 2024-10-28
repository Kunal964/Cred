package com.example.cred

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun LoanAmountScreen() {
    var amount by remember { mutableStateOf(150000f) }
    var maxAmount = 487891f
    var monthlyRate = 5.04f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Dark background
            .padding(top = 150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "nikunj, how much do you need?",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "move the dial and set any amount you need up to ₹${maxAmount.roundToInt()}",
            color = Color.Gray,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        CardSection(
            amount = amount,
            maxAmount = maxAmount,
            monthlyRate = monthlyRate,
            onAmountChange = { amount = it }
        )

        ButtonComponent()
    }
}

@Composable
fun CardSection(
    modifier: Modifier = Modifier,
    amount: Float,
    maxAmount: Float,
    monthlyRate: Float,
    onAmountChange: (Float) -> Unit
) {
    Box(
        modifier = modifier
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ElevatedCard(
                modifier = Modifier.size(450.dp),
            ) {
                // Centered box to overlay CircularSlider and text inside it
                Box(
                    modifier = Modifier.size(370.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Circular Slider
                    CircularSlider(
                        modifier = Modifier
                            .size(330.dp),
                        stroke = 40f,
                        maxAmount = maxAmount,
                        amount = amount,
                        onValueChange = { newAmount ->
                            onAmountChange(newAmount) // Update amount as slider moves
                        }
                    )

                    // Text inside the circular slider
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "credit amount",
                            color = Color.Black,
                            fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "₹${amount.roundToInt()}",
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "@ $monthlyRate% monthly",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }

                // Text outside the circular slider
                Text(
                    text = "stash is instant. money will be credited within seconds",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}


@Composable
fun ButtonComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
             // Optional padding for spacing
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
//                .clip(RoundedCornerShape(4.dp)),
            onClick = { },
            shape = RectangleShape

        ) {
            Text(text = "Proceed to EMI",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }
    }
}





@Preview
@Composable
fun LoanAmountPreview() {
    LoanAmountScreen()
}
