package com.sorrowblue.twitlin.androidsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sorrowblue.twitlin.androidsample.databinding.RecyclerViewItemBinding
import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.objects.Tweet
import kotlin.properties.Delegates

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

	var currentItem: List<Response.Success<Tweet>> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
		DiffUtil.calculateDiff(Diff(oldValue, newValue)).dispatchUpdatesTo(this)
	}

	override fun getItemCount() = currentItem.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(currentItem[position])
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

	class ViewHolder private constructor(val binding: RecyclerViewItemBinding) :
		RecyclerView.ViewHolder(binding.root) {

		constructor(parent: ViewGroup) : this(
			RecyclerViewItemBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)

		fun bind(tweet: Response.Success<Tweet>) {
			binding.textView.text = tweet.data?.text
//			binding.imageView.setImageURI(Uri.parse(tweet.includes?.users?.firstOrNull()?.profileImageUrl))
		}
	}

	class Diff(val old: List<Response.Success<Tweet>>, val new: List<Response.Success<Tweet>>) : DiffUtil.Callback() {
		override fun getOldListSize() = old.size

		override fun getNewListSize() = new.size

		override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
			old[oldItemPosition].data?.id == new[newItemPosition].data?.id

		override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
			old[oldItemPosition].data == new[newItemPosition].data
	}
}
