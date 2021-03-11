package planday.review.app.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import planday.review.app.R
import planday.review.app.databinding.DetailsFragmentBinding
import planday.review.app.databinding.FeedFragmentBinding

class DetailsFragment : Fragment(R.layout.details_fragment){
    val args : DetailsFragmentArgs by navArgs()
    private lateinit var binding: DetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = DetailsFragmentBinding.bind(view)

        Glide.with(binding.image)
            .load(args.article.urlToImage)
            .into(binding.image)

        binding.title.text = args.article.title
        binding.description.text = "Description:  " + args.article.description
        binding.author.text = "Author:  " + args.article.author
        binding.source.text = "Source:  " + args.article.source.name
        binding.link.text = "URL:  " + args.article.url
    }
}