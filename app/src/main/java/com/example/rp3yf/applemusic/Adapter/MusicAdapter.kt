package com.example.rp3yf.applemusic.Adapter

/**
 * Created by rp3yf on 1/18/2018.
 */
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rp3yf.applemusic.Database.Music
import com.example.rp3yf.applemusic.DetailActivity
import com.example.rp3yf.applemusic.R
import java.util.ArrayList
import kotlin.collections.List

class MusicAdapter(_musicList: ArrayList<Music>, ctx: Context) : RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
    private var musicList: ArrayList<Music> = _musicList;
    private val mCtx: Context

    init{
        this.musicList = _musicList
        this.mCtx = ctx
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var mMusicName: TextView = itemView.findViewById(R.id.musicName)
        internal var mArtist: TextView = itemView.findViewById(R.id.musicArtist)
        internal var mDate: TextView = itemView.findViewById(R.id.musicDate)
        internal var mIcon: ImageView = itemView.findViewById(R.id.musicIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.music_list_row, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        initMusic(holder, position)
        holder.itemView.setOnClickListener{
            val intent = Intent(mCtx, DetailActivity::class.java)
            intent.putExtra("myIcon",musicList[position].getIcon())
            var tempList: ArrayList<String> = ArrayList<String>()
            tempList.add("Name: " + musicList[position].getmusicName())
            tempList.add("Artist: " + musicList[position].getartistName())
            tempList.add("Kind: " + musicList[position].getKind())
            tempList.add("Copyright: " + musicList[position].getCopyright())
            tempList.add("CollectionName: " + musicList[position].getCollectionName())
            intent.putExtra("musicList",tempList)

            startActivity(mCtx,intent,null)
        }
    }

    private fun initMusic(holder: MyViewHolder, position: Int) {
        holder.mMusicName.text = musicList[position].getmusicName()
        holder.mArtist.text = musicList[position].getartistName()
        holder.mDate.text = musicList[position].getreleaseDate()
        Glide.with(mCtx)
                .load(musicList[position].getIcon())
                .fitCenter()
                .into(holder.mIcon)
    }

    fun setData(dataList: List<Music>) {
        musicList.clear()
        musicList.addAll(dataList)
    }


    override fun getItemCount(): Int = musicList.size
}
