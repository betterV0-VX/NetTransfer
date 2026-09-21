package com.example.nettransferdemo

import android.R.attr.top
import androidx.annotation.FontRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nettransferdemo.ui.theme.NetTransferDemoTheme

@Composable
fun MainScreen(
    isChecked: Boolean,
    isUsbConnected: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    moveToInstruction: () -> Unit,
    moveToDeveloperInfo: () -> Unit,
    currentClipboardText: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Верхний Spacer - занимает 30% пространства
            Spacer(modifier = Modifier.weight(0.3f))

            // Switch - фиксированное положение
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 32.dp)
            ) {
                Switch(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xffBDF55A),
                        checkedTrackColor = Color(0xff7EA93C),
                    )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    stringResource(R.string.turn_on_off_data_transfer),
                    fontWeight = FontWeight.Normal
                )
            }

            // Промежуточный Spacer - минимальный отступ
            Spacer(modifier = Modifier.height(16.dp))

            // Card - центрируется в оставшемся пространстве
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill=false) // Занимает всё доступное пространство, но не более
                    .heightIn(min = 100.dp, max = 400.dp) // Ограничиваем максимальную высоту
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 32.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.content_copy_24dp),
                            contentDescription = stringResource(R.string.instruction),
                            tint = if (currentClipboardText != "") Color(0xff7EA93C) else Color.Gray,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            "Текущий текст в буфере обмена:",
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

                    Text(
                        currentClipboardText,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    )
                }
            }

            // Нижний Spacer - занимает 30% пространства
            Spacer(modifier = Modifier.weight(0.3f))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(end = 8.dp, bottom = 8.dp)
        ) {
            FloatingActionButton(
                onClick = { /* TODO: ваше действие */ },
                containerColor = if (isUsbConnected) {
                    Color(0xffBDF55A)
                } else {
                    Color.LightGray
                },
                contentColor = if (isUsbConnected) {
                    Color(0xff7EA93C)
                } else {
                    Color(0xffC34542)
                },
                modifier = Modifier.size(46.dp)
            ) {
                Icon(
                    painter = if (isUsbConnected) {
                        painterResource(R.drawable.usb_24dp)
                    } else {
                        painterResource(R.drawable.usb_off_24dp)
                    },
                    contentDescription = "Настроить usb-накопитель",
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(modifier: Modifier= Modifier){
    NetTransferDemoTheme {
        MainScreen(
            isChecked = true,
            isUsbConnected = true,
            onCheckedChange = {},
            moveToDeveloperInfo = {},
            moveToInstruction = {},
            currentClipboardText = "Clipboard data")
    }
}