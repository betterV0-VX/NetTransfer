package com.example.nettransferdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nettransferdemo.ui.theme.NetTransferDemoTheme

@Composable
fun MainScreen(
    onCheckedChange: (Boolean)->Unit,
    moveToInstruction: ()->Unit,
    moveToDeveloperInfo: ()->Unit,
    modifier: Modifier= Modifier,
){
    var isChecked by remember { mutableStateOf(false) }
    Column (modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Top){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){

            IconButton(onClick = moveToInstruction) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = stringResource(R.string.instruction)
                )
            }
            IconButton(onClick = moveToDeveloperInfo) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.about_developer)
                )
            }
        }
        Spacer(Modifier.fillMaxHeight(0.5f))
        Row(modifier = modifier) {
            Switch(checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                    onCheckedChange(isChecked)
                }
            )
            Text(stringResource(R.string.turn_on_off_data_transfer))
        }
    }
}
@Preview
@Composable
fun MainScreenPreview(modifier: Modifier= Modifier){
    NetTransferDemoTheme {
        MainScreen(onCheckedChange = {}, moveToDeveloperInfo = {}, moveToInstruction = {})
    }
}