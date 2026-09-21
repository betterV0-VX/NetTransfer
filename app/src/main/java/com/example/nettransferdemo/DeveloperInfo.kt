package com.example.nettransferdemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.nettransferdemo.ui.theme.NetTransferDemoTheme

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.unit.sp


@Composable
fun DeveloperPage(navigateUp: () -> Unit){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()){
        Intro(navigateUp)
        Contacts()
    }
}

@Composable
fun Contacts(){
    Column (verticalArrangement = Arrangement.Center) {
        IconRow(
            Icons.Filled.Home,
            "Moscow, Russia"
        )
        IconRow(
            Icons.Filled.Email,
            "arhidany@mail.ru"
        )
    }
}

@Composable
fun IconRow(iconImage: ImageVector, text: String){
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 50.dp, end = 50.dp
        )) {
        Icon(
            imageVector = iconImage,
            tint = Color(0xff7EA93C),
            contentDescription = text,
            modifier = Modifier.size(16.dp)
        )
        
        Text(
            text = text,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 7.dp)
        )
    }
}

@Composable
fun Intro(navigateUp: ()->Unit){
    val mockImage = painterResource(id=R.drawable.nettransfer_black_version)
    Column(verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.size(200.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Column ( horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = mockImage,
                    contentDescription = null,
                    alpha = 0.8f,
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = stringResource(R.string.full_name)
                )
                Text(
                    text = stringResource(R.string.title),
                    fontWeight = FontWeight.Thin
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DevInfoPreview(){
    NetTransferDemoTheme {
        DeveloperPage({})
    }
}

