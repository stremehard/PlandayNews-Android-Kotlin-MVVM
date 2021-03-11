package planday.review.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import planday.review.app.ui.feed.FeedViewModel

val viewModelsModule = module {
//    viewModel { GalleryViewModel() }
//    viewModel { HomeViewModel(get()) }
//    viewModel { SlideshowViewModel() }

    viewModel { FeedViewModel(get()) }
}