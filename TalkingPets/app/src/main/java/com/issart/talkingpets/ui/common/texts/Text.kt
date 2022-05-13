package com.issart.talkingpets.ui.common.texts

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.issart.talkingpets.R
import com.issart.talkingpets.ui.theme.TextBodySecondaryColor
import com.issart.talkingpets.ui.theme.TextTitleColor

@Composable
fun BodyMediumText(
    title: String,
    fontSize: TextUnit = 24.sp
) = Text(
    text = title,
    color = TextTitleColor,
    fontSize = fontSize,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight(500),
    fontFamily = FontFamily(Font(R.font.baloo_bhaijaan2_medium)),
    lineHeight = 23.sp
)

@Composable
fun BodySecondaryText(
    title: String
) = Text(
    text = title,
    color = TextBodySecondaryColor,
    fontSize = 20.sp,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight(500),
    fontFamily = FontFamily(Font(R.font.baloo_bhaijaan2_medium)),
    lineHeight = 28.sp
)

@Composable
fun TitleBoldText(title: String) = Text(
    text = title,
    color = TextTitleColor,
    fontSize = 36.sp,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight(600),
    fontFamily = FontFamily(Font(R.font.baloo_bhaijaan2_semi_bold)),
    lineHeight = 43.sp
)
