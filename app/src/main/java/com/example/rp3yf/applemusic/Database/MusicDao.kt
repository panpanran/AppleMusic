package com.example.rp3yf.applemusic.Database

//import com.vicpin.krealmextensions.save
import com.example.rp3yf.applemusic.SourceData.JSONKey
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.Sort


/**
 * Created by rp3yf on 1/19/2018.
 */
class MusicDao(realm: Realm) {

    private var mRealm: Realm = realm

    fun save(musicList: List<Music>) {
        mRealm.executeTransaction { realm -> realm.copyToRealmOrUpdate(musicList) }
    }

    fun loadAllAsync(): RealmResults<Music> {
        return mRealm.where(Music::class.java).sort(JSONKey.MUSIC_RELSEASEDATE, Sort.DESCENDING).findAllAsync()
    }

    fun loadBy(id: Long): Music {
        return mRealm.where(Music::class.java).equalTo(JSONKey.MUSIC_ID, id).findFirstAsync()
    }

    fun remove(`object`: RealmObject) {
        mRealm.executeTransaction { `object`.deleteFromRealm() }
    }

    fun removeAll() {
        val results = mRealm.where(Music::class.java).findAll()
// All changes to data must happen in a transaction
        mRealm.beginTransaction()
        results.deleteAllFromRealm()
        mRealm.commitTransaction()
    }

    fun count(): Long {
        return mRealm.where(Music::class.java).count()
    }
}