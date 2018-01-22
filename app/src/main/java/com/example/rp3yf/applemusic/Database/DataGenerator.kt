package com.example.rp3yf.applemusic.Database

import android.os.AsyncTask
import com.example.rp3yf.applemusic.MainActivity.Companion.shareMusicList
import com.example.rp3yf.applemusic.SourceData.JSONData
import java.util.ArrayList

/**
 * Created by rp3yf on 1/20/2018.
 */
class DataGenerator : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg strings: String): String {
        shareMusicList = generateMusicList()
        return ""
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        RealmManager.createMusicDao().save(shareMusicList)
    }

    companion object {
        private var musicUrl = "https://rss.itunes.apple.com/api/v1/us/apple-music/hot-tracks/all/10/explicit.json";

        fun generateTestList(): ArrayList<Music> {
            val musicList = ArrayList<Music>()

            for (i in 1..100) {
                val music = Music()
                music.setId(i.toLong())
                music.setartistName("Ran" + i)
                music.setmusicName("Music" + i)
                music.setreleaseDate(String.format("20180112", i))

                musicList.add(music)
            }

            return musicList
        }

        fun generateMusicList(): ArrayList<Music> {
            val musicList = JSONData.getJson(musicUrl)
            return musicList
        }
    }
}
