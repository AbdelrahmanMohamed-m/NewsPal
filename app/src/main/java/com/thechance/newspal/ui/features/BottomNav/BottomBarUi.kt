
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.thechance.newspal.ui.navigation.Screen

@Composable
fun BottomBarUi(
    navController: NavHostController,
) {

    val screens = listOf(
        BottomBarItems.Home,
        BottomBarItems.Explore,
        BottomBarItems.BookMark,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.onTertiary,
        tonalElevation = 0.dp,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItems,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected =
        currentDestination?.hierarchy?.any { it.route == screen.route } == true
    NavigationBarItem(
        icon = {
            Icon(
                painter =
                if (selected) painterResource(id = screen.selectedIcon)
                else painterResource(id = screen.unSelectedIcon),
                contentDescription = "icon",
                tint = if (selected) Color.White else MaterialTheme.colorScheme.outlineVariant
            )
        },
        selected = selected,
        label = {
            Text(
                text = if (selected) screen.label else "",
                color = MaterialTheme.colorScheme.onErrorContainer
            )
        },
        onClick = {
            navController.currentBackStackEntry?.destination?.route
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            when (screen) {
                BottomBarItems.Home -> {
                    navController.popBackStack(Screen.HomeScreen.route, false)
                }

                BottomBarItems.Explore -> {
                    navController.popBackStack(Screen.ExploreScreen.route, false)
                }

                BottomBarItems.BookMark -> {
                    navController.popBackStack(Screen.BookMarkScreen.route, false)
                }

                else -> {

                }
            }

        },

        )
}