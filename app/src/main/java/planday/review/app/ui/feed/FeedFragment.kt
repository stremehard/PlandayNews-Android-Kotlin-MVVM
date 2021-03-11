package planday.review.app.ui.feed

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import planday.review.app.R
import planday.review.app.databinding.FeedFragmentBinding
import planday.review.app.models.NewsResponse
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val viewModel: FeedViewModel by viewModel()
    private lateinit var binding: FeedFragmentBinding

    var adapter: MainAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeedFragmentBinding.bind(view) // Search for view binding android

        adapter = MainAdapter()

        binding.recyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.onSearch(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            }
        )

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.data.observe(viewLifecycleOwner) {
            if (it is NewsResponse.Success) {
                binding.progress.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                adapter?.submitList(it.articles)
            } else {
                // TODO: Show error message ?
                Timber.i("Error occured")
            }
        }
    }
}