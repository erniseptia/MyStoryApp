package app.story.mystoryapp.detail

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.story.mystoryapp.R
import app.story.mystoryapp.databinding.ActivityDetailStoryBinding
import app.story.mystoryapp.dataclass.Story
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import setLocalDateFormat

class DetailStory : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Wait until all resource is already loaded
        supportPostponeEnterTransition()

        val story = intent.getParcelableExtra<Story>(EXTRA_DETAIL)
        parseStoryData(story)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun parseStoryData(story: Story?) {
        if (story != null) {
            binding.apply {
                tvStoryUsername.text = story.name
                tvStoryDescription.text = story.description
                toolbar.title = getString(R.string.detail_toolbar, story.name)
                tvStoryDate.setLocalDateFormat(story.createdAt)

                // Parse image to ImageView
                // Using listener for make sure the enter transition only called when loading completed
                Glide
                    .with(this@DetailStory)
                    .load(story.photoUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            // Continue enter animation after image loaded
                            supportStartPostponedEnterTransition()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            supportStartPostponedEnterTransition()
                            return false
                        }
                    })
                    .placeholder(R.drawable.img_loading)
                    .error(R.drawable.img_loading_error)
                    .into(ivStoryImage)
            }
        }
    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}