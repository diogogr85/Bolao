package com.sushicode.bolao.ui.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class NavItem(
    var title: String,
    var route: String,
    var icon: Int
) {
    object Matches : NavItem("Matches", "Matches", android.R.drawable.ic_menu_my_calendar)
    object Bets : NavItem("Bets", "Bets", android.R.drawable.ic_menu_slideshow)
    object Ranking : NavItem("Ranking", "Classification", android.R.drawable.ic_menu_compass)
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navList = listOf(
        NavItem.Matches,
        NavItem.Bets,
        NavItem.Ranking
    )

    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    var currentRoute by remember {
        mutableStateOf(NavItem.Matches.route)
    }
    navList.forEachIndexed { index, navItem ->
        if (navItem.route == currentRoute) { selectedItem = index }
    }

    NavigationBar {
        navList.forEachIndexed { index, navItem ->
            NavigationBarItem(
                label = {
                    Text(text = navItem.title)
                },
                icon = {
                    Icon(
                        painterResource(id = navItem.icon),
                        contentDescription = navItem.title
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = navItem.route
                    navController.navigate(navItem.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                },
                colors = NavigationBarItemDefaults.colors()
            )
        }
    }
}

@Composable
fun Navigations(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavItem.Matches.route) {
        composable(NavItem.Matches.route) {
            MatchesScreen()
        }
        composable(NavItem.Bets.route) {
            BetsScreen()
        }
        composable(NavItem.Ranking.route) {
            RankingScreen()
        }
    }
}
