package com.example.rp3yf.applemusic.Database

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by rp3yf on 1/19/2018.
 */
class RealmManager {
    companion object {
    private lateinit var mRealm: Realm
        fun open(): Realm {
            mRealm = Realm.getDefaultInstance()
            return mRealm
        }

        fun close() {
            if (mRealm != null) {
                mRealm.close()
            }
        }

        fun createMusicDao(): MusicDao {
            checkForOpenRealm()
            return MusicDao(mRealm)
        }

//        fun clear() {
//            checkForOpenRealm()
//            mRealm.executeTransaction { realm ->
//                realm.clear(Music::class.java)
//                //clear rest of your dao classes
//            }
//        }

        private fun checkForOpenRealm() {
            if (mRealm == null || mRealm.isClosed) {
                throw IllegalStateException("RealmManager: Realm is closed, call open() method first")
            }
        }
    }
}