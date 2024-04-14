import com.thechance.newspal.ui.features.ArticleDetailsScreen.ArticleDetailsViewmodel
import com.thechance.newspal.ui.features.BookMark.BookMarkViewModel
import com.thechance.newspal.ui.features.Explore.ExploreViewModel
import com.thechance.newspal.ui.features.Home.HomeViewModel
import com.thechance.newspal.ui.features.Search.SearchViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(getNewsUseCase = get()) }
    viewModel { SearchViewmodel(searchUseCase = get()) }
    viewModel { ArticleDetailsViewmodel(savedStateHandle = get(), get(), get(),get()) }
    viewModel { ExploreViewModel(topHeadlines = get(), trendingNews = get()) }
    viewModel { BookMarkViewModel(get()) }
}