package com.questro.itunes.presentation.music_detail

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.questro.itunes.R

class MusicDetailActivity : AppCompatActivity() {

    private lateinit var imgTrack: ImageView
    private lateinit var tvArtistName: TextView
    private lateinit var tvTrackName: TextView
    private lateinit var tvCollectionName: TextView
    private lateinit var tvTrackTime: TextView
    private lateinit var tvTrackPrice: TextView
    private lateinit var tvReleaseDate: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvTrackExplicitness: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_detail)

        supportActionBar?.title = "Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imgTrack = findViewById(R.id.imgTrack)
        tvArtistName = findViewById(R.id.tvArtistName)
        tvTrackName = findViewById(R.id.tvTrackName)
        tvCollectionName = findViewById(R.id.tvCollectionName)
        tvTrackTime = findViewById(R.id.tvTrackTime)
        tvTrackPrice = findViewById(R.id.tvTrackPrice)
        tvReleaseDate = findViewById(R.id.tvReleaseDate)
        tvGenre = findViewById(R.id.tvGenre)
        tvTrackExplicitness = findViewById(R.id.tvTrackExplicitness)

        val intent = intent
        if(intent.extras != null){
            val thumbnail: String? = intent.extras!!.getString("thumbnail")
            Glide.with(this)
                .load(thumbnail)
                .centerInside()
                .placeholder(R.drawable.ic_baseline_library_music_24)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imgTrack.setImageResource(R.drawable.ic_baseline_library_music_24)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(imgTrack)

            val artistName: String? = intent.extras!!.getString("artistName")
            val trackName: String? = intent.extras!!.getString("trackName")
            val collectionName: String? = intent.extras!!.getString("collectionName")
            val trackTime = "Track Time: ${intent.extras!!.getString("trackTime")}"
            val trackPrice = "Track Price: ${intent.extras!!.getString("trackPrice")}"
            val releaseDate = "Release Date: ${intent.extras!!.getString("releaseDate")}"
            val genre = "Genre: ${intent.extras!!.getString("genre")}"
            val trackExplicitness = "Track Explicitness: ${intent.extras!!.getString("trackExplicitness")}"

            tvArtistName.text = artistName
            tvTrackName.text = trackName
            tvCollectionName.text = collectionName
            tvTrackTime.text = trackTime
            tvTrackPrice.text = trackPrice
            tvReleaseDate.text = releaseDate
            tvGenre.text = genre
            tvTrackExplicitness.text = trackExplicitness

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}