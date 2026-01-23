package com.example.nettransferdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nettransferdemo.ui.theme.NetTransferDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetTransferDemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    InstructionArticle()
                }
            }
        }
    }
}

@Composable
fun InstructionArticle(){
    val slide1 = painterResource(id=R.drawable.slide1_work_principle)
    val slide2 = painterResource(id=R.drawable.slide2_algorithm)
    val slide3 = painterResource(id=R.drawable.slide3_restrictions)
    val slide4 = painterResource(id=R.drawable.slide4_possible_connections)
    val slide5 = painterResource(id=R.drawable.slide5_scheme_smartphone_dex)
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .background(color=Color(0xFFF6EDFF))) {
        Image(
            painter = slide1,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = slide2,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Image(
            painter = slide3,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Image(
            painter = slide4,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Image(
            painter = slide5,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )



    }
}

@Preview(showBackground = true)
@Composable
fun InstructionArcticlePreview() {
    NetTransferDemoTheme {
        InstructionArticle()
    }
}