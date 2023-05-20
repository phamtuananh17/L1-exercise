package com.example.backgroundservice

import java.io.Serializable


class Song (
    private var title: String,
    private var single: String,
    private var image: Int,
    private var resource: Int
) : Serializable {

    fun getTitle(): String {
        return title
    }

    fun getSingle(): String {
        return single
    }

    fun getImage(): Int {
        return image
    }

    fun getResource(): Int {
        return resource
    }}