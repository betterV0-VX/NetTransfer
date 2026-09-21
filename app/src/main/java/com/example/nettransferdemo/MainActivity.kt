package com.example.nettransferdemo

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nettransferdemo.ui.theme.NetTransferDemoTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel

enum class NTScreen {
    Start,
    Instruction,
    DeveloperInfo
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NTApp(
    navController: NavHostController = rememberNavController()
) {
    val ntViewModel: NTViewModel = viewModel()
    val uiState by ntViewModel.uiState.collectAsState()
    //?
    val clipboardText by ntViewModel.clipboardText.collectAsState()

    Scaffold(
        topBar = {
            NTAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                moveToInstruction = { navController.navigate(NTScreen.Instruction.name) },
                moveToDeveloperInfo = { navController.navigate(route= NTScreen.DeveloperInfo.name) },
                modifier = Modifier
            )

        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = NTScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = NTScreen.Start.name) {
                MainScreen(
                    isChecked = uiState.isTransferTurnedOn,
                    isUsbConnected = uiState.isUsbConnected,
                    onCheckedChange = { ntViewModel.setIsTransferTurnedOn(it) },
                    moveToInstruction = { navController.navigate(NTScreen.Instruction.name) },
                    moveToDeveloperInfo = { navController.navigate(route= NTScreen.DeveloperInfo.name) },
                    currentClipboardText = clipboardText ?: ""
                )
            }
            composable(route = NTScreen.DeveloperInfo.name){
                DeveloperPage(navigateUp = {navController.navigate(NTScreen.Start.name)})
            }
            composable(route = NTScreen.Instruction.name){
                InstructionArticle(navigateUp = {navController.navigate(NTScreen.Start.name)})
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NTAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    moveToInstruction: ()->Unit,
    moveToDeveloperInfo: ()->Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("NetTransfer", fontWeight = FontWeight.W700, color = Color(0xff2D441E)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xff7EA93C),//MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            IconButton(onClick = moveToInstruction) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = stringResource(R.string.instruction),
                    tint = Color(0xff2D441E),
                    modifier = Modifier.size(32.dp)
                )
            }
            IconButton(onClick = moveToDeveloperInfo) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.about_developer),
                    tint = Color(0xff2D441E),
                    modifier = Modifier.size(32.dp)
                )
            }

        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NetTransferDemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NTApp()
                }
            }
        }
    }
}

