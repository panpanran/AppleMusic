package com.example.rp3yf.applemusic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.rp3yf.applemusic.Adapter.MusicAdapter
import com.example.rp3yf.applemusic.Database.DataGenerator
import com.example.rp3yf.applemusic.Database.Music
import com.example.rp3yf.applemusic.Database.RealmManager
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.support.v4.widget.SwipeRefreshLayout






/**
 * Created by rp3yf on 1/19/2018.
 */
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var shareMusicList: List<Music>

    }

    private lateinit var musicList: ArrayList<Music>
    private lateinit var mAdapter: MusicAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RealmManager.open()
        initViews()
        Log.d(TAG, "Initial")
        getMusicData()
        Log.d(TAG, "Refresh Database")
        loadUserListAsync()
        Log.d(TAG, "Main Data")

        var refreshLayout:SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        refreshLayout.setOnRefreshListener( {
            getMusicData()
            loadUserListAsync()
            refreshLayout.setRefreshing(false);
        })
    }

    override fun onDestroy() {
        RealmManager.close()
        super.onDestroy()
    }


    private fun initViews() {
        musicList = ArrayList()
        mAdapter = MusicAdapter(musicList, this)

        // Create LinearLayoutManager instance
        linearLayoutManager = LinearLayoutManager(this)

        // Divider for items
        dividerItemDecoration = DividerItemDecoration(
                mainList.context,
                linearLayoutManager.orientation
        )

        // Set-Up RecyclerView
        mainList.setHasFixedSize(true)
        mainList.layoutManager = linearLayoutManager
        mainList.addItemDecoration(dividerItemDecoration)
        mainList.adapter = mAdapter
    }

    /**
     * Prepares sample data to provide data set to adapter
     */
    private fun getMusicData() {
        DataGenerator().execute()
        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged()
    }

    private fun loadUserListAsync() {
        val dataList = RealmManager.createMusicDao().loadAllAsync()
        // Listeners will be notified when data changes
        dataList.addChangeListener(object : RealmChangeListener<RealmResults<Music>> {
           override fun onChange(results: RealmResults<Music>) {
               updateRecyclerView(dataList)
            }
        })
    }

    private fun updateRecyclerView(musicList: List<Music>) {
        if (mAdapter != null && musicList != null) {
            mAdapter.setData(musicList)
            mAdapter.notifyDataSetChanged()
        }
    }
}