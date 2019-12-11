package com.example.tubes.lana

//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment

class LahragaActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var myoutubeplayer: YouTubePlayer
    private lateinit var youTubePlayerFragment: YouTubePlayerSupportFragment

    private val RECOVERY_DIALOG_REQUEST = 1
    var id_video: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lahraga)

        id_video = "8CMebJ_JR-4"
        youTubePlayerFragment =
            supportFragmentManager.findFragmentById(R.id.official_player_view) as YouTubePlayerSupportFragment
        youTubePlayerFragment.initialize("f6d5afbe453d40d0b27133bce01d2604", this)

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        youTubePlayer: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored) {

            youTubePlayer.cueVideo(id_video)
            if (youTubePlayer != null) myoutubeplayer = youTubePlayer
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        youTubeInitializationResult: YouTubeInitializationResult
    ) {
        if (youTubeInitializationResult.isUserRecoverableError) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show()
        } else {
            val errorMessage = String.format(
                "There was an error initializing the YouTubePlayer (%1\$s)",
                youTubeInitializationResult.toString()
            )
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun beginner(view: View) {
        id_video = "8CMebJ_JR-4"
        myoutubeplayer.cueVideo(id_video)
    }

    fun intermediate(view: View) {
        id_video = "MXMOLdiNdKE"
        myoutubeplayer.cueVideo(id_video)
//        third_party_player_view.release()
//        third_party_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
//                videoId = "0pThnRneDjw"
//                youTubePlayer.cueVideo(videoId, 0f)
//            }
//        })
    }


    fun Advance(view: View) {
        id_video = "hwiwg-C5pT4"
        myoutubeplayer.cueVideo(id_video)
//        third_party_player_view.release()
//        third_party_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
//                videoId = "3xvOVRSRHtg"
//                youTubePlayer.cueVideo(videoId, 0f)
//            }
//        })
    }
}
