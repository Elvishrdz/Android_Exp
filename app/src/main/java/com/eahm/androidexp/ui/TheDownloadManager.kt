package com.eahm.androidexp.ui

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.eahm.androidexp.R
import kotlinx.android.synthetic.main.activity_download_manager.*

class TheDownloadManager : AppCompatActivity() {

    //region variables
    private val tag = "TheDownloadManager"
    private val urlMp4 = "https://video.openedu.tw/Examples/big_buck_bunny_720p_10mb.mp4"
    private val urlM3U8 = "https://content.jwplatform.com/manifests/yp34SRmf.m3u8"
    private val urlMP3 = "https://host2.rj-mw1.com/media/podcast/mp3-192/Tehranto-41.mp3"
    private val urlOGG = "http://backyard.vaslapp.com/res/036e77f0-a94e-4165-9832-1a8e39c1e089/USER_FILES/audio.ogg"

    private lateinit var downloadManager : DownloadManager
    private lateinit var currentUri : Uri
    //endregion variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_manager)

        /**
         * Getting Started
         * 1. Create a variable of type DownloadManager and initialize it
         *  1.1 Get the system services and pass as parameter Context.DOWNLOAD_SERVICE
         *  1.2 Cast the service as DownloadManager
         *
         * 2. Provide a Uri (from android.net.uri) with a file URL
         * 3. Create a DownloadManager.Request with the uri.
         *  3.1 Specify a notification visibility
         *
         * 4. Add the request to the downloadManager reference
         * 5. REMEMBER to declare the INTERNET permission in the Manifest (See AndroidManifest.xml)
         *
         * Done!
         */

        downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        sDMFile.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i(tag, "position: $position")
                setCurrentFile(position)
            }

        }

        btnDMDownload.setOnClickListener{
            val downloadRequest = DownloadManager.Request(currentUri)
            downloadRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

            /**
             * Execute the download by calling ...enqueue(...)
             *
             * Also use:
             * 'val downloadUniqueID = downloadManager.enqueue(downloadRequest)'
             * to obtain the download ID. This ID is used to make future calls related to this download.
              */
            downloadManager.enqueue(downloadRequest)
        }
    }
    /**
     * Set a different URL to download a specific file type.
     *
     * @param type of file to download. 0 = MP4, 1 = M3U8, 2 = MP3, 3 = OGG
     */
    private fun setCurrentFile(type : Int){

        val selectedUri : Uri = when(type){
            1 -> {
                Uri.parse(urlM3U8)
            }
            2 -> {
                Uri.parse(urlMP3)
            }
            3 -> {
                Uri.parse(urlOGG)
            }
            else -> {
                Uri.parse(urlMp4)
            }
        }

        currentUri = selectedUri
    }

}