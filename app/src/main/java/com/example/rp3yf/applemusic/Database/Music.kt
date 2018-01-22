package com.example.rp3yf.applemusic.Database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass


/**
 * Created by rp3yf on 1/18/2018.
 */
@RealmClass
open class Music : RealmObject() {

    @PrimaryKey
    private var id: Long = 0
    private lateinit var artistName: String
    private lateinit var releaseDate: String
    private lateinit var musicName: String
    private lateinit var icon: String
    private lateinit var collectionName: String
    private lateinit var kind: String
    private lateinit var copyright: String
    private lateinit var artistId: String
    private lateinit var artistUrl: String

    fun getId(): Long {
        return id
    }
    fun setId(id: Long) {
        this.id = id
    }

    fun getmusicName(): String {
        return musicName
    }
    fun setmusicName(musicName: String) {
        this.musicName = musicName
    }

    fun getartistName(): String {
        return artistName
    }
    fun setartistName(artistName: String) {
        this.artistName = artistName
    }

    fun getreleaseDate(): String {
        return releaseDate
    }
    fun setreleaseDate(releaseDate: String) {
        this.releaseDate = releaseDate
    }

    fun getIcon(): String {
        return icon
    }
    fun setIcon(icon: String) {
        this.icon = icon
    }

    fun getCollectionName(): String {
        return collectionName
    }
    fun setCollectionName(collectionName: String) {
        this.collectionName = collectionName
    }

    fun getKind(): String {
        return kind
    }
    fun setKind(kind: String) {
        this.kind = kind
    }

    fun getCopyright(): String {
        return copyright
    }
    fun setCopyright(copyright: String) {
        this.copyright = copyright
    }

    fun getArtistId(): String {
        return icon
    }
    fun setArtistId(artistId: String) {
        this.artistId = artistId
    }

    fun getArtistUrl(): String {
        return icon
    }
    fun setArtistUrl(artistUrl: String) {
        this.artistUrl = artistUrl
    }
}