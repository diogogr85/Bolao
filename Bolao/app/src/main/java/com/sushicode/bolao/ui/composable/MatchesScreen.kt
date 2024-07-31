package com.sushicode.bolao.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sushicode.bolao.ui.viewModels.MatchesViewModel
import org.koin.android.java.KoinAndroidApplication
import org.koin.androidx.compose.koinViewModel
import org.koin.core.context.KoinContext

@Composable
fun MatchesScreen(
//    viewModel: MatchesViewModel = koinViewModel()
) {
//    val stateList = viewModel.matchesLiveData.value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        stateList?.forEach { match ->
//            Text(text = match.toString())
            Text(text = "Matches")
//        }
    }

}