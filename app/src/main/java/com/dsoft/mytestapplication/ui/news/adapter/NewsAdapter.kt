package com.dsoft.mytestapplication.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.dsoft.mytestapplication.databinding.ItemArticleBinding
import com.dsoft.mytestapplication.domain.model.Article
import com.google.android.material.textview.MaterialTextView

/**
 * В обычном случае я бы тут DiffUtils бы использовал дополнительно,
 * но в данной задаче это кажется излишним
 */
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private var list: List<Article> = emptyList()

    inner class MyViewHolder(binding: ItemArticleBinding) : ViewHolder(binding.root) {
        val titleTextView: MaterialTextView = binding.title
        val articleTextView: MaterialTextView = binding.text
        val publishDateTextView: MaterialTextView = binding.publishedDate
        val articleImageView: ImageView = binding.articleImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.titleTextView.text = currentItem.title
        holder.articleTextView.text = currentItem.content
        holder.publishDateTextView.text = currentItem.formattedDate

        holder.articleImageView.load(currentItem.urlToImage) {
            crossfade(true)
            transformations(RoundedCornersTransformation(bottomLeft = 8f, topLeft = 8f))
            size(125, 120)
            scale(Scale.FILL)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<Article>) {
        list = newList
        notifyDataSetChanged()
    }
}