package mobi.pulsus.challenge.presentation.randomjokebycategory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mobi.pulsus.challenge.commons.extensions.gone
import mobi.pulsus.challenge.commons.extensions.onSingleClick
import mobi.pulsus.challenge.commons.extensions.share
import mobi.pulsus.challenge.commons.extensions.viewBinding
import mobi.pulsus.challenge.commons.extensions.visible
import mobi.pulsus.challenge.databinding.ActivityRandomJokeCategoryBinding
import mobi.pulsus.challenge.domain.model.JokeModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomJokeCategoryActivity : AppCompatActivity() {

    private val viewModel by viewModel<RandomJokeCategoryViewModel>()
    private val binding by viewBinding(ActivityRandomJokeCategoryBinding::inflate)

    private val categoryAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.onViewCreated()
    }

    private fun ActivityRandomJokeCategoryBinding.onViewCreated() {
        observables()
    }

    private fun ActivityRandomJokeCategoryBinding.observables() {
        viewModel.run {
            categoriesLiveData.observe(this@RandomJokeCategoryActivity) {
                handlerUIState(it)
            }

            randomJokeByCategoryLiveData.observe(this@RandomJokeCategoryActivity) {
                handlerJoke(it)
            }
        }
    }

    private fun ActivityRandomJokeCategoryBinding.handlerUIState(state: RandomJokeCategoryUIState) {
        when (state) {
            is RandomJokeCategoryUIState.Loading -> {
                pbRandomJokeCategory.visible
                tvRandomJokeByCategoryInstructions.gone
                mcvDetailFact.gone
                rvFactCategories.gone
                tvRandomJokeCategoryErrorText.gone
            }
            is RandomJokeCategoryUIState.SuccessCategories -> {
                pbRandomJokeCategory.gone
                setupCategoriesRecycler(state.categories)
                tvRandomJokeByCategoryInstructions.visible
                tvRandomJokeCategoryErrorText.gone
            }
            is RandomJokeCategoryUIState.Error -> {
                pbRandomJokeCategory.gone
                tvRandomJokeByCategoryInstructions.gone
                mcvDetailFact.gone
                rvFactCategories.gone
                tvRandomJokeCategoryErrorText.visible
            }
        }
    }

    private fun ActivityRandomJokeCategoryBinding.setupCategoriesRecycler(categories: List<String>) {
        rvFactCategories.visible
        categoryAdapter.apply {
            setList(categories)
            setCategoryAction {
                viewModel.getRandomJokeFromOneCategory(it.lowercase())
                tvMcvDetailFactCategoryName.text = it
            }
        }
        rvFactCategories.adapter = categoryAdapter
    }

    private fun ActivityRandomJokeCategoryBinding.handlerJoke(joke: JokeModel) {
        tvRandomJokeByCategoryInstructions.gone
        pbMcvDetailFact.gone
        mcvDetailFact.visible
        factGroup.visible
        tvMcvDetailFactContent.text = joke.value
        mbtMcvDetailFactFavorite.apply {
            isSelected = joke.isFavorite
            onSingleClick {
                it.isSelected = !it.isSelected
                viewModel.saveOrDeleteJoke(joke)
            }
        }
        mbtMcvDetailFactLoadNextFact.onSingleClick {
            viewModel.getRandomJokeFromOneCategory(joke.categories.first())
        }
        mbtMcvDetailFactShare.onSingleClick {
            share(joke.value, joke.url)
        }
    }
}