package com.example.foodtutorial.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodtutorial.R
import com.example.foodtutorial.models.Post
import java.util.Locale

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMain: ImageView = itemView.findViewById(R.id.ivMain)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDesc: TextView = itemView.findViewById(R.id.tvDesc)

        // 更新为新的视图引用
        val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)
        val tvLikeContainer: TextView = itemView.findViewById(R.id.tvLikeContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.tvTitle.text = post.title
        holder.tvDesc.text = post.description

        // 设置用户信息（头像和ID）
        holder.tvUserId.text = post.userId
        Glide.with(holder.itemView)
            .load(post.avatarUrl)
            .circleCrop() // 圆形裁剪
            .placeholder(R.drawable.avatar) // 默认头像
            .error(R.drawable.error_image) // 错误占位图
            .into(holder.ivAvatar)

        // 设置点赞数
        holder.tvLikeContainer.text = String.format(Locale.getDefault(), "%,d", post.likeCount)

        // 主图加载
        Glide.with(holder.itemView)
            .load(post.imageUrl)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error_image)
            .into(holder.ivMain)

        // 点赞点击事件
        holder.tvLikeContainer.setOnClickListener {
            post.likeCount++
            holder.tvLikeContainer.text = String.format(Locale.getDefault(), "%,d", post.likeCount)
        }
    }

    override fun getItemCount() = posts.size
}