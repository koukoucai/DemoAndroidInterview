package com.koko.interview.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.koko.interview.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val nunitoSansFamily = FontFamily(
    Font(R.font.nunitosans_bold,FontWeight.Bold),
    Font(R.font.nunitosans_semibold,FontWeight.SemiBold),
    Font(R.font.nunitosans_light,FontWeight.Light)
)

val H1 = TextStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    letterSpacing = 0.sp
)

val H2 = TextStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 0.15.sp
)

val Subtitle1 = TextStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Light,
    fontSize = 16.sp,
    letterSpacing = 0.sp
)
val Body1 = TextStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Light,
    fontSize = 14.sp,
    letterSpacing = 0.sp
)
val Body2 = TextStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Light,
    fontSize = 12.sp,
    letterSpacing = 0.sp,

)

val Body2Span = SpanStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.Light,
    fontSize = 12.sp,
    letterSpacing = 0.sp,
    textDecoration = TextDecoration.Underline
)
val Button = TextStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    letterSpacing = 1.sp
)

val Caption = TextStyle(
    fontFamily = nunitoSansFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 12.sp,
    letterSpacing = 0.sp
)


