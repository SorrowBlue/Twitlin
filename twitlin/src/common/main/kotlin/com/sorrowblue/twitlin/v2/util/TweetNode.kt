package com.sorrowblue.twitlin.v2.util

import com.sorrowblue.twitlin.v2.objects.Tweet

public class TweetNode(public val tweet: Tweet) {

    public companion object {
        public fun buildConversation(parent: Tweet, children: List<Tweet>): TweetNode {
            val root = TweetNode(parent)
            var orphans = mutableListOf<TweetNode>()
            children.forEach {
                val node = TweetNode(it)
                orphans = orphans.filterNot(node::findParentOf).toMutableList()
                if (!root.findParentOf(node)) {
                    orphans += node
                }
            }
            return root
        }
    }

    private val _children: MutableList<TweetNode> = mutableListOf()
    private val replyTo get() = tweet.referencedTweets?.find { it.type == Tweet.ReferenceTweet.Type.REPLIED_TO }?.id

    private val id
        get() = tweet.referencedTweets?.find { it.type == Tweet.ReferenceTweet.Type.RETWEETED }?.id ?: tweet.id

    public val children: List<TweetNode> get() = _children

    public fun findParentOf(node: TweetNode): Boolean {
        if (node.replyTo == id) {
            _children.add(node)
            return true
        }
        _children.forEach {
            if (it.findParentOf(node)) {
                return true
            }
        }
        return false
    }
}
