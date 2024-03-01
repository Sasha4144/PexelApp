package com.example.pexelapp.presentation.downloader

interface Downloader {
    fun downloadFile(url: String, fileName: String): Long
}