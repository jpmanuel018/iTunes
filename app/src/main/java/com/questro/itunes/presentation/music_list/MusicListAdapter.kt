package com.questro.itunes.presentation.music_list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.button.MaterialButton
import com.questro.itunes.R
import com.questro.itunes.data.remote.dto.MusicInfoDto
import com.questro.itunes.data.remote.dto.ResultDto
import com.questro.itunes.presentation.music_detail.MusicDetailActivity
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class MusicListAdapter(private val context: Context): RecyclerView.Adapter<MusicListAdapter.MusicListViewHolder>() {

    private var musicList = emptyList<ResultDto>()

    @SuppressLint("NotifyDataSetChanged")
    internal fun setItems(musicList: List<ResultDto>){
        this.musicList = musicList
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun clearItems(){
        musicList = emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.music_item_list,parent,false)
        return MusicListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MusicListViewHolder, position: Int) {
        val music = musicList[position]

        Glide.with(context)
            .load(music.artworkUrl100)
            .centerInside()
            .placeholder(R.drawable.ic_baseline_library_music_24)
            .addListener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.imgThumbnail.setImageResource(R.drawable.ic_baseline_library_music_24)
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
            .into(holder.imgThumbnail)

        holder.tvTrackName.text = music.trackName
        holder.tvArtistName.text = music.artistName
        val price = "${music.trackPrice} ${music.currency}"
        holder.tvTrackPrice.text = price

        val minutes = music.trackTimeMillis / 1000 / 60
        val seconds = music.trackTimeMillis / 1000 % 60

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val output = SimpleDateFormat("MMMM dd, yyyy")
        val date = sdf.parse(music.releaseDate)
        val strDate = output.format(date)

        holder.itemView.setOnClickListener {
            val intent = Intent(context,MusicDetailActivity::class.java)
                .putExtra("thumbnail",music.artworkUrl100)
                .putExtra("artistName",music.artistName)
                .putExtra("trackName",music.trackName)
                .putExtra("collectionName",music.collectionName)
                .putExtra("trackTime","${minutes}:${seconds}")
                .putExtra("trackPrice","${music.currency} ${music.trackPrice}")
                .putExtra("releaseDate",strDate)
                .putExtra("genre",music.primaryGenreName)
                .putExtra("trackExplicitness",music.trackExplicitness)
                .putExtra("artistViewUrl",music.artistViewUrl)
                .putExtra("collectionViewUrl",music.collectionViewUrl)
                .putExtra("trackViewUrl",music.trackViewUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    class MusicListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var imgThumbnail: ImageView
        var tvTrackName: TextView
        var tvArtistName: TextView
        var tvTrackPrice: TextView

        init {
            imgThumbnail = view.findViewById(R.id.imgThumbnail)
            tvTrackName = view.findViewById(R.id.tvTrackName)
            tvArtistName = view.findViewById(R.id.tvArtistName)
            tvTrackPrice = view.findViewById(R.id.tvTrackPrice)
        }
    }
}