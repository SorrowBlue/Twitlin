package com.sorrowblue.twitlin.androidsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sorrowblue.twitlin.androidsample.databinding.RecyclerViewItemBinding
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.objects.User

typealias TweetObject = Pair<Tweet, User>

class MainAdapter : ListAdapter<TweetObject, MainAdapter.ViewHolder>(object : DiffUtil.ItemCallback<TweetObject>() {
    override fun areItemsTheSame(oldItem: TweetObject, newItem: TweetObject) = oldItem.first.id == newItem.first.id
    override fun areContentsTheSame(oldItem: TweetObject, newItem: TweetObject) =
        oldItem.first == newItem.first && oldItem.second == newItem.second
}) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
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
            binding.name.text = tweetObject.second.name
            binding.createdAt.text = tweetObject.first.createdAt.toString()
            binding.username.text =
                binding.root.resources.getString(R.string.username, tweetObject.second.username)
            binding.imageView.load(tweetObject.second.profileImageUrl)
        }
    }
}
