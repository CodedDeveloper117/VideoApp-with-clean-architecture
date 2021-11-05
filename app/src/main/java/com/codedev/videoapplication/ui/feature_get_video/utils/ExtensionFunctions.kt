package com.codedev.videoapplication.ui.feature_get_video.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.STATE_READY
import com.google.android.exoplayer2.SimpleExoPlayer

fun SimpleExoPlayer.addListener(
    ready: () -> Unit = {},
    buffering: () -> Unit = {},
    ended: () -> Unit = {},
    idle: () -> Unit = {},
    onListen: (Player.Listener) -> Unit
) {
    val listener = object: Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            when(playbackState) {
                STATE_READY -> {
                    ready()
                }
                Player.STATE_BUFFERING -> {
                    buffering()
                }
                Player.STATE_ENDED -> {
                    ended()
                }
                Player.STATE_IDLE -> {
                    idle()
                }
            }
        }
    }
    onListen(listener)
    this.addListener(listener)
}

fun TextView.openBrowser(page: String, context: Context) {
    this.setOnClickListener {
        Intent(Intent.ACTION_VIEW, Uri.parse(page)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            `package` = "com.android.chrome"
            try {
                context.startActivity(this)
            }catch (e: Exception) {
                `package` = null
                context.startActivity(Intent.createChooser(this, "Select Browser"))
            }
        }
    }
}