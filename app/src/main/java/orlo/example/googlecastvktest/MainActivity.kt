package com.example.castplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.CastSession
import com.google.android.gms.cast.framework.SessionManagerListener
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaLoadRequestData
import com.google.android.gms.cast.framework.media.RemoteMediaClient

class MainActivity : ComponentActivity() {
    private var castSession: CastSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val castContext = CastContext.getSharedInstance(this)
        castSession = castContext.sessionManager.currentCastSession

        setContent {
            CastVideoScreen {
                playVideo()
            }
        }
    }

    private fun playVideo() {
        val videoUrl =
            "https://videolink-test.mycdn.me/?pct=1&sig=6QNOvp0y3BE&ct=0&clientType=45&mid=193241622673&type=5"

        val metadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE).apply {
            putString(MediaMetadata.KEY_TITLE, "Test Video")
        }

        val mediaInfo = MediaInfo.Builder(videoUrl)
            .setMetadata(metadata)
            .setContentType("video/mp4")
            .build()

        castSession?.remoteMediaClient?.let { remoteMediaClient ->
            val request = MediaLoadRequestData.Builder()
                .setMediaInfo(mediaInfo)
                .build()
            remoteMediaClient.load(request)
        }
    }
}

@Composable
fun CastVideoScreen(onPlayClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onPlayClick, modifier = Modifier.padding(16.dp)) {
            Text("Play on Chromecast")
        }
    }
}
