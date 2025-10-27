package io.nekohasekai.sagernet.utils

import android.content.Context
import android.media.MediaPlayer
import io.nekohasekai.sagernet.R

object SoundPlayer {

    private var player: MediaPlayer? = null

    fun playConnect(context: Context) {
        playSound(context, R.raw.connect_sound)
    }

    fun playDisconnect(context: Context) {
        playSound(context, R.raw.disconnect_sound)
    }

    private fun playSound(context: Context, resId: Int) {
        player?.release()
        player = MediaPlayer.create(context, resId)
        player?.start()
    }
}
