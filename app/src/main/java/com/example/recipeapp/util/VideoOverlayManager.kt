package com.example.recipeapp.util

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.databinding.OverlayVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

/**
 * Floating YouTube-like video card that attaches to the Activity window.
 * Draggable by its handle. Dismissed via ✕ or when the host Fragment is destroyed.
 *
 * Usage from any Fragment:
 *   (requireActivity() as? AppCompatActivity)?.let { VideoOverlayManager.show(it, url) }
 */
object VideoOverlayManager {

    private var overlayBinding: OverlayVideoBinding? = null
    private var dX = 0f
    private var dY = 0f

    fun show(activity: AppCompatActivity, youtubeUrl: String?) {
        if (youtubeUrl.isNullOrBlank()) return
        val videoId = extractVideoId(youtubeUrl) ?: return

        dismiss()

        val root = activity.window.decorView as ViewGroup
        val binding = OverlayVideoBinding.inflate(LayoutInflater.from(activity), root, false)
        overlayBinding = binding

        binding.root.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply { gravity = Gravity.BOTTOM }

        activity.lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })

        binding.btnCloseOverlay.setOnClickListener { dismiss() }
        makeDraggable(binding.dragHandle, binding.root)
        root.addView(binding.root)
    }

    fun dismiss() {
        overlayBinding?.let {
            it.youtubePlayerView.release()
            (it.root.parent as? ViewGroup)?.removeView(it.root)
        }
        overlayBinding = null
    }

    private fun extractVideoId(url: String): String? {
        listOf(
            Regex("youtu\\.be/([^?&\\n]+)"),
            Regex("[?&]v=([^?&\\n]+)"),
            Regex("embed/([^?&\\n]+)")
        ).forEach { rx -> rx.find(url)?.groupValues?.get(1)?.let { return it } }
        return null
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun makeDraggable(handle: View, card: View) {
        handle.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> { dX = card.x - event.rawX; dY = card.y - event.rawY; true }
                MotionEvent.ACTION_MOVE -> {
                    card.animate().x(event.rawX + dX).y(event.rawY + dY).setDuration(0).start(); true
                }
                else -> false
            }
        }
    }
}
