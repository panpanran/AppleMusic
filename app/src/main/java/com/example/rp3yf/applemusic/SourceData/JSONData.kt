package com.example.rp3yf.applemusic.SourceData

import android.os.AsyncTask
import com.example.rp3yf.applemusic.Database.Music
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


/**
 * Created by rp3yf on 1/19/2018.
 */
class JSONData {

    companion object {
        fun getJson(urlString: String): ArrayList<Music> {
            var musicList = ArrayList<Music>()

            try {
                var html = getHtml(urlString);
                var json = JSONObject(html).getJSONObject("feed").getJSONArray("results")
                for (i in 0..(json.length() - 1)) {
                    val music = Music()
                    music.setId(json.optJSONObject(i).get(JSONKey.MUSIC_ID).toString().toLong())
                    music.setartistName(json.optJSONObject(i).get(JSONKey.MUSIC_ARTISTNAME).toString())
                    music.setmusicName(json.optJSONObject(i).get(JSONKey.MUSIC_NAME).toString())
                    music.setreleaseDate(json.optJSONObject(i).get(JSONKey.MUSIC_RELSEASEDATE).toString())
                    music.setCollectionName(json.optJSONObject(i).get(JSONKey.MUSIC_COLLECTIONNAME).toString())
                    music.setCopyright(json.optJSONObject(i).get(JSONKey.MUSIC_COPYRIGHT).toString())
                    music.setKind(json.optJSONObject(i).get(JSONKey.MUSIC_KIND).toString())
                    music.setArtistId(json.optJSONObject(i).get(JSONKey.MUSIC_ARTISTID).toString())
                    music.setArtistUrl(json.optJSONObject(i).get(JSONKey.MUSIC_ARTISTURL).toString())
                    music.setIcon(json.optJSONObject(i).get(JSONKey.MUSIC_ICON).toString())
                    musicList.add(music)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return musicList
        }

        fun getHtml(urlString: String): String? {
            try {
                val client = OkHttpClient()
                var request: Request = Request.Builder()
                        .url(urlString)
                        .build();
                var result: String = client.newCall(request).execute().body().toString();
                return result   //

            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        }
    }
}
