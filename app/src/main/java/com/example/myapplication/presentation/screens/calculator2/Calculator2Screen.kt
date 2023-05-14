package com.example.myapplication.presantation.screens.calculator2

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presantation.screens.main.MainScreen
import com.example.myapplication.presantation.ui.components.CalculatorButton
import com.example.myapplication.presantation.ui.theme.Background_main
import com.example.myapplication.presantation.ui.theme.EasycalcTheme
import com.example.myapplication.presantation.ui.theme.Orange
import com.example.myapplication.presentation.navigation.NavigationViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.pow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Calculator2Screen(navController: NavController, navigationViewModel: NavigationViewModel) {
    val viewModel = hiltViewModel<Calculator2ViewModel>()
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val mContext = LocalContext.current
    val buttonSpacing = 8.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background_main)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            if (viewModel.isEqual) {
                EqualText(
                    calculator = viewModel.calculation,
                    clipboardManager = clipboardManager,
                    mContext = mContext
                )
            } else {
                Text(
                    text = viewModel.calculation,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                        .horizontalScroll(ScrollState(10000 * 100000))
                        .clickable {
                            clipboardManager.setText(
                                AnnotatedString((viewModel.calculation))
                            )
                            viewModel.makeToast(mContext, "Ответ скопирован")
                        },
                    fontWeight = FontWeight.Light,
                    fontSize = 80.sp,
                    color = Color.White,
                    maxLines = 1
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    color = LightGray,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation = ""
                }
                CalculatorButton(
                    symbol = "Del",
                    color = LightGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation = viewModel.calculation.dropLast(1)
                }
                CalculatorButton(
                    symbol = "/",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "/"
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    color = DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "7"
                }
                CalculatorButton(
                    symbol = "8",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "8"
                }
                CalculatorButton(
                    symbol = "9",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "9"
                }
                CalculatorButton(
                    symbol = "x",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "*"
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "4"
                }
                CalculatorButton(
                    symbol = "5",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "5"
                }
                CalculatorButton(
                    symbol = "6",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "6"
                }
                CalculatorButton(
                    symbol = "-",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "-"
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "1"
                }
                CalculatorButton(
                    symbol = "2",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "2"
                }
                CalculatorButton(
                    symbol = "3",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "3"
                }
                CalculatorButton(
                    symbol = "+",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "+"
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    color = DarkGray,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "0"
                }
                CalculatorButton(
                    symbol = ".",
                    color = DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.calculation += "."
                }
                CalculatorButton(
                    symbol = "=",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f),
                    viewModel = viewModel
                ) {
                    viewModel.count(navigationViewModel)
                }
            }
        }
    }
}


@Composable
fun EqualText(calculator: String, clipboardManager: ClipboardManager, mContext: Context) {
    val textStyleBody1 = MaterialTheme.typography.h4
    var textStyle by remember { mutableStateOf(textStyleBody1) }
    var readyToDraw by remember { mutableStateOf(false) }
    Text(
        text = calculator,
        style = textStyle,
        maxLines = 2,
        fontWeight = FontWeight.Light,
        color = Color.White,
        softWrap = false,
        modifier = Modifier
            .drawWithContent {
                if (readyToDraw) drawContent()
            }
            .clickable {
                clipboardManager.setText(
                    AnnotatedString((calculator))
                )
                Calculator2ViewModel().makeToast(mContext, "Ответ скопирован")
            },
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth) {
                textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
            } else {
                readyToDraw = true
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun Calculator2ScreenPreview() {
//        Calculator2Screen(navController = rememberNavController())
//}