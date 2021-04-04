/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.androidsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sorrowblue.twitlin.androidsample.databinding.RecyclerViewItemBinding
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import kotlin.properties.Delegates

typealias TweetObject = Pair<Tweet, User>

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var currentItem: List<TweetObject> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        DiffUtil.calculateDiff(Diff(oldValue, newValue)).dispatchUpdatesTo(this)
    }

    override fun getItemCount() = currentItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentItem[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    inner class ViewHolder private constructor(val binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        constructor(parent: ViewGroup) : this(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

        fun bind(tweetObject: TweetObject) {
            binding.textView.text = tweetObject.first.text
            binding.textView2.text =
                binding.root.resources.getString(R.string.username, tweetObject.second.username)
            binding.imageView.load(tweetObject.second.profileImageUrl)
        }
    }

    class Diff(private val old: List<TweetObject>, private val new: List<TweetObject>) :
        DiffUtil.Callback() {

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition].first.id == new[newItemPosition].first.id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition] == new[newItemPosition]
    }
}
