package mobi.pulsus.challenge.randomjokebycategory.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mobi.pulsus.challenge.commons.extensions.gone
import mobi.pulsus.challenge.commons.extensions.viewBinding
import mobi.pulsus.challenge.commons.extensions.visible
import mobi.pulsus.challenge.databinding.ActivityRandomJokeCategoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomJokeCategoryActivity : AppCompatActivity() {

    private val viewModel by viewModel<RandomJokeCategoryViewModel>()
    private val binding by viewBinding(ActivityRandomJokeCategoryBinding::inflate)

    private val categoryAdapter = CategoryAdapter()
    private val list = listOf("animal", "career", "celebrity", "dev", "explicit", "fashion", "food", "history", "money", "movie", "music", "political", "religion", "science", "sport", "travel")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.gtRandomJokeByCategory.setLeftButtonText("<--")

        categoryAdapter.setList(list)
        categoryAdapter.setCategoryAction {
            binding.pbMcvDetailFact.gone
            binding.factGroup.visible
            binding.tvMcvDetailFactCategoryName.text = it
        }
        binding.rvFactCategories.adapter = categoryAdapter


    }
}