package com.example.rp3yf.applemusic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rp3yf.applemusic.Adapter.MusicAdapter
import com.example.rp3yf.applemusic.Database.DataGenerator
import com.example.rp3yf.applemusic.Database.Music
import com.example.rp3yf.applemusic.Database.RealmManager
import com.example.rp3yf.applemusic.MainActivity.Companion.shareMusicList
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/**
 * Created by rp3yf on 1/21/2018.
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var musicList: ArrayList<Music>
    private lateinit var mAdapter: MusicAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_detail)
        RealmManager.open()
        initViews()
    }

    override fun onDestroy() {
        RealmManager.close()
        super.onDestroy()
    }


    private fun initViews() {
        var imageView: ImageView= findViewById(R.id.musicAlbum)
        var myIcon = getIntent().getStringExtra("myIcon")
        var myList = getIntent().getStringArrayListExtra("musicList")

        Glide.with(this)
                .load(myIcon)
                .fitCenter()
                .into(imageView)

        val strList = myList.toTypedArray()
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strList)
        val listView:ListView = findViewById(R.id.musicDetail)
        listView.adapter = adapter
        Log.d(TAG, "Detail Data")
    }

}