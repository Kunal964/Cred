package com.example.cred

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun EmiSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize() // Fill the entire available space
            .padding(16.dp),
        contentAlignment = Alignment.Center // Center all content within the Box
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "how do you wish to repay?",
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "choose one of our recommended plans or make your own",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmiDetails() // This will be centered along with the other content in the Column
        }
    }
}


@Composable
fun EmiDetails(modifier: Modifier = Modifier) {
    val selectedStates = remember { mutableStateListOf(*Array(emiDetails.size) { false }) }
    LazyRow(
        modifier = modifier,
        contentPadding =  PaddingValues(horizontal = 10.dp)
    ) {
        items(emiDetails.size) { index ->
            EmiDetail(
                emiDetail = emiDetails[index],
                isSelected = selectedStates[index],
                onSelected = {
                    // Set all other items to false and the clicked one to true
                    selectedStates.fill(false)
                    selectedStates[index] = true},
                isRecommended =  index == 1
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun EmiDetail(
    modifier: Modifier = Modifier,
    emiDetail: EmiDetail,
    isSelected: Boolean = false,
    isRecommended: Boolean = false,
    onSelected: () -> Unit
) {
    Box(
        modifier = modifier
            .size(150.dp)
            .clip(RoundedCornerShape(22.dp))
//            .background(if (isSelected) Color(0xFF3A1F1F) else Color(0xFF1C1C1E))
    ) {
        // Recommended Badge at the top
        if (isRecommended) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter) // Position badge at the top center
                    .background(if (isSelected) Color(0xFF3A1F1F) else Color(0xFF1C1C1E), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .offset(y = (-10).dp) // Floating effect above the card
            ) {
                Text(
                    text = "recommended",
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }

        // Card containing checkbox and EMI details
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .clickable { onSelected() }
                .clip(RoundedCornerShape(22.dp)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                // Checkbox at the top left
                SimpleCheckboxComponent(
                    modifier = Modifier.align(Alignment.Start),
                    isChecked = isSelected,
                    onCheckedChange = { onSelected()}
                )

                Spacer(modifier = Modifier.height(8.dp))

                // EMI Amount and Details
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "₹${emiDetail.amount}/mo",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = emiDetail.time,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = emiDetail.desc,
                        color = Color(0xFF6D6D6E),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}


val emiDetails = listOf(
    EmiDetail(
        amount = "4427",
        time = "for 12 months",
        desc = "See Calculations"
    ),
    EmiDetail(
        amount = "5870",
        time = "for 9 months",
        desc = "See Calculations"
    ),
    EmiDetail(
        amount = "6485",
        time = "for 6 months",
        desc = "See Calculations"
    ),
    EmiDetail(
        amount = "7496",
        time = "for 3 months",
        desc = "See Calculations"
    ),

)


data class EmiDetail(
    val amount: String,
    val time: String,
    val desc: String
)


@Preview
@Composable
fun EmiSectionPreview() {
    EmiSection()
}