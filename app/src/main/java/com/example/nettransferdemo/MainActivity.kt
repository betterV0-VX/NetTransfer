package com.example.nettransferdemo

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nettransferdemo.ui.theme.NetTransferDemoTheme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.navigation.NavHost
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

enum class NTScreen {
    Start,
    Instruction,
    DeveloperInfo
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NTApp(
    viewModel: NTViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            NTAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
            )
        }


    ) { innerPadding ->
        val uiState = viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = NTScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(route = NTScreen.Start.name) {
                MainScreen(
                    onCheckedChange = {viewModel.setIsTransferTurnedOn(it)},
                    moveToInstruction = {navController.navigate(NTScreen.Instruction.name)},
                    moveToDeveloperInfo = {navController.navigate(route= NTScreen.DeveloperInfo.name)}
                )
            }
            composable(route = NTScreen.DeveloperInfo.name){
                DeveloperPage(navigateUp = {navController.navigate(NTScreen.Start.name)})
            }
            composable(route = NTScreen.Instruction.name){
                InstructionArticle(navigateUp = {navController.navigate(NTScreen.Start.name)})
            }
//            composable(route = CupcakeScreen.Flavor.name){
//                val localContext = LocalContext.current
//                SelectOptionScreen(
//                    subtotal = uiState.price,
//                    onNextButtonClicked = {
//                        navController.navigate(CupcakeScreen.Pickup.name)
//                    },
//                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
//                    options = DataSource.flavors.map{ id ->
//                        localContext.resources.getString(id)
//                    },
//                    onSelectionChanged = { viewModel.setFlavor(it) },
//                    modifier = Modifier.fillMaxHeight()
//                )
//            }
//            composable(route = CupcakeScreen.Pickup.name){
//                SelectOptionScreen(
//                    subtotal = uiState.price,
//                    options = uiState.pickupOptions,
//                    onNextButtonClicked = {navController.navigate(CupcakeScreen.Summary.name)},
//                    onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
//                    onSelectionChanged = {viewModel.setDate(it)},
//                    modifier = Modifier.fillMaxHeight()
//                )
//            }
//            composable(route = CupcakeScreen.Summary.name){
//                val context = LocalContext.current
//                OrderSummaryScreen(
//                    orderUiState = uiState,
//                    onSendButtonClicked = {
//                            subject: String, summary: String ->
//                        shareOrder(context, subject=subject, summary=summary)
//                    },
//                    onCancelButtonClicked= {cancelOrderAndNavigateToStart(viewModel, navController)},
//                    modifier = Modifier.fillMaxHeight()
//                )
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NTAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("NetTransfer") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
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

@Composable
fun NTAppBar(canNavigateBack: Boolean, navigateUp: () -> Boolean) {
    TODO("Not yet implemented")
}
