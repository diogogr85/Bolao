package com.sushicode.bolao.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun MainView(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(modifier = Modifier) {
                BottomNavigationBar(navHostController)
            }
        }
    ) { innerpadding ->
        Box(
            modifier = Modifier.padding(
                0.dp,
                0.dp,
                0.dp,
                innerpadding.calculateBottomPadding()
            )
        ) {
            Navigations(navHostController)
        }
    }
}