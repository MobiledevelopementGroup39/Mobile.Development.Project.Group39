package com.example.foodtutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtutorial.adapters.PostAdapter
import com.example.foodtutorial.models.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // simulated data
        val posts = listOf(
            Post(
                title = "Mapo Tofu",
                description = "It's a spicy and tasty hot dish, perfect for a proper meal!",
                userId = "Gao",
                likeCount = 27200,
                imageUrl = "https://images.pexels.com/photos/20943933/pexels-photo-20943933.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                avatarUrl = "https://images.pexels.com/photos/31457818/pexels-photo-31457818.jpeg?auto=compress&cs=tinysrgb&w=1200"
            ),
            // add other 3 posts
            Post(
                title = "hot and numbing Chicken strips",
                description = "It's a spicy and tasty hot dish, perfect for a proper meal!",
                userId = "Gao",
                likeCount = 27200,
                imageUrl = "https://images.pexels.com/photos/20943933/pexels-photo-20943933.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                avatarUrl = "https://images.pexels.com/photos/31457818/pexels-photo-31457818.jpeg?auto=compress&cs=tinysrgb&w=1200"
            ),
            Post(
                title = "Chicken strips",
                description = "It's a spicy and tasty hot dish, perfect for a proper meal!",
                userId = "Gao",
                likeCount = 27200,
                imageUrl = "https://images.pexels.com/photos/20943933/pexels-photo-20943933.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                avatarUrl = "https://images.pexels.com/photos/31457818/pexels-photo-31457818.jpeg?auto=compress&cs=tinysrgb&w=1200"
            ),
            Post(
                title = "Pasta",
                description = "Italy noodle",
                userId = "Gao",
                likeCount = 27200,
                imageUrl = "https://images.pexels.com/photos/20943933/pexels-photo-20943933.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                avatarUrl = "https://images.pexels.com/photos/31457818/pexels-photo-31457818.jpeg?auto=compress&cs=tinysrgb&w=1200"
            ),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = PostAdapter(posts)
    }
}

