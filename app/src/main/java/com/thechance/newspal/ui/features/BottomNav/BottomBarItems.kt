import com.thechance.newspal.R
import com.thechance.newspal.ui.navigation.Graph

sealed class BottomBarItems(
    val route: String,
    val label: String,
    val selectedIcon: Int,
    val unSelectedIcon: Int,
) {
    object Home : BottomBarItems(
        route = Graph.HOME,
        label = "Home",
        selectedIcon = R.drawable.selectedhome,
        unSelectedIcon = R.drawable.iconhome,
    )

    object BookMark : BottomBarItems(
        route = Graph.bookMark,
        label = "BookMark",
        selectedIcon = R.drawable.bookmarkfoucsed,
        unSelectedIcon = R.drawable.bookmark
    )

    object Explore : BottomBarItems(
        route = Graph.EXPLORE,
        label = "explore",
        selectedIcon = R.drawable.selectedexplore,
        unSelectedIcon = R.drawable.explore
    )

}