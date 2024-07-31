package com.sushicode.bolao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sushicode.bolao.ui.composable.BottomNavigationBar
import com.sushicode.bolao.ui.composable.MainView
import com.sushicode.bolao.ui.theme.BolaoTheme
import com.sushicode.bolao.ui.viewModels.MatchesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val matchesViewModel: MatchesViewModel by viewModel<MatchesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            BolaoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    MainView(navHostController = navController, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

