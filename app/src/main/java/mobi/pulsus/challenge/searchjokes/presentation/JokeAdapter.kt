package mobi.pulsus.challenge.searchjokes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mobi.pulsus.challenge.commons.extensions.onSingleClick
import mobi.pulsus.challenge.commons.extensions.share
import mobi.pulsus.challenge.databinding.ItemJokeBinding
import mobi.pulsus.challenge.domain.model.JokeModel


class JokeAdapter : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    private val jokeList: MutableList<JokeModel> = mutableListOf()
    private var favoriteListener: (joke: JokeModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(binding)
    }

    override fun getItemCount(): Int = jokeList.size

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokeList[position]
        holder.bind(joke)
    }

    fun updateJokeList(newJokeList: List<JokeModel>) {
        val diffUtilCallback = JokeDiffUtil(
            oldList = jokeList,
            newList = newJokeList
        )
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)

        jokeList.clear()
        jokeList.addAll(newJokeList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class JokeViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: JokeModel) {
            binding.tvJokeText.text = joke.value
//            binding.mbtJokeFavorite.isSelected = joke.isFavorite
            binding.mbtJokeFavorite.onSingleClick {
                favoriteListener.invoke(joke)
//                it.isSelected = !it.isSelected
            }

            binding.mbtJokeShare.onSingleClick {
                it?.context?.share(joke.value, joke.url)
            }
        }
    }

    class JokeDiffUtil(
        private val oldList: List<JokeModel>,
        private val newList: List<JokeModel>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].value == newList[newItemPosition].value
        }
    }

    fun setFavoriteAction(action: (joke: JokeModel) -> Unit) {
        favoriteListener = action
    }
}