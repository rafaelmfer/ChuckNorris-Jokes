package mobi.pulsus.challenge.presentation.randomjoke

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import mobi.pulsus.challenge.R
import mobi.pulsus.challenge.commons.extensions.gone
import mobi.pulsus.challenge.commons.extensions.onSingleClick
import mobi.pulsus.challenge.commons.extensions.sectionTextBold
import mobi.pulsus.challenge.commons.extensions.share
import mobi.pulsus.challenge.commons.extensions.toSpannableStringBuilder
import mobi.pulsus.challenge.commons.extensions.viewBinding
import mobi.pulsus.challenge.commons.extensions.visible
import mobi.pulsus.challenge.databinding.ActivityRandomJokeBinding
import mobi.pulsus.challenge.domain.model.JokeModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomJokeActivity : AppCompatActivity() {

    private val viewModel by viewModel<RandomJokeViewModel>()
    private val binding by viewBinding(ActivityRandomJokeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onViewCreated()
    }

    private fun ActivityRandomJokeBinding.onViewCreated() {
        observables()
        setupClickNewJoke()
    }

    private fun ActivityRandomJokeBinding.observables() {
        viewModel.randomJokeLiveData.observe(this@RandomJokeActivity) {
            handlerRandomJokeUIState(it)
        }
    }

    private fun ActivityRandomJokeBinding.handlerRandomJokeUIState(state: RandomJokeUIState) {
        when (state) {
            is RandomJokeUIState.Loading -> {
                pbRandomJokeCategory.visible
                groupRandomJokeTextsButtons.gone
                tvRandomJokeCategoryErrorText.gone
            }
            is RandomJokeUIState.Success -> {
                pbRandomJokeCategory.gone
                bindJokeInformation(state.joke)
                groupRandomJokeTextsButtons.visible
                tvRandomJokeCategoryErrorText.gone
            }
            is RandomJokeUIState.Error -> {
                pbRandomJokeCategory.gone
                groupRandomJokeTextsButtons.gone
                tvRandomJokeCategoryErrorText.visible
            }
        }
    }

    private fun ActivityRandomJokeBinding.bindJokeInformation(joke: JokeModel) {
        Glide.with(this@RandomJokeActivity)
            .load(joke.iconUrl)
            .placeholder(R.drawable.ic_image_loading)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    tvRandomJokeImageErrorMessage.visible
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    tvRandomJokeImageErrorMessage.gone
                    return true
                }
            })
            .error(R.drawable.ic_image_broken)
            .into(ivRandomJokeImage)

        tvRandomJokeCategory.text = getString(R.string.category, if (joke.categories.isEmpty()) getString(R.string.uncategorized) else joke.categories.joinToString()).toSpannableStringBuilder()
            .sectionTextBold(getString(R.string.uncategorized), joke.categories.joinToString())
        tvRandomJokeCreation.text = getString(R.string.create_at, joke.createdAt).toSpannableStringBuilder().sectionTextBold(joke.createdAt)
        tvRandomJokeText.text = getString(R.string.joke, joke.value).toSpannableStringBuilder().sectionTextBold(joke.value)
        setupClickShareJoke(joke.value, joke.url)
        setupClickFavoriteJoke(joke)
    }

    private fun ActivityRandomJokeBinding.setupClickNewJoke() {
        mbtRandomJokeNewJoke.onSingleClick {
            tvRandomJokeImageErrorMessage.gone
            viewModel.getRandomJoke()
        }
    }

    private fun ActivityRandomJokeBinding.setupClickShareJoke(jokeText: String, url: String) {
        mbtRandomJokeShare.onSingleClick {
            share(jokeText, url)
        }
    }

    private fun ActivityRandomJokeBinding.setupClickFavoriteJoke(joke: JokeModel) {
        mbtRandomJokeFavorite.apply {
            text = handlerFavoriteText(joke.isFavorite)
            onSingleClick {
                text = handlerFavoriteText(!joke.isFavorite)
                viewModel.saveOrDeleteJoke(joke)
            }
        }
    }

    private fun handlerFavoriteText(isFavorite: Boolean): String {
        return if (isFavorite) {
            getString(R.string.unfavorite)
        } else {
            getString(R.string.favorite)
        }
    }
}