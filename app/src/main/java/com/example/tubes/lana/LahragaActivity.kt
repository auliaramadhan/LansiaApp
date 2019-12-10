package com.example.tubes.lana

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import kotlinx.android.synthetic.main.activity_lahraga.*

class LahragaActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var myoutubeplayer : YouTubePlayer
    private lateinit var youTubePlayerFragment : YouTubePlayerSupportFragment

    private val RECOVERY_DIALOG_REQUEST = 1
    var id_video: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lahraga)

        id_video = "G63uL4TaP3o"
        youTubePlayerFragment = supportFragmentManager.findFragmentById(R.id.official_player_view) as YouTubePlayerSupportFragment
        youTubePlayerFragment.initialize("f6d5afbe453d40d0b27133bce01d2604", this)


//        third_party_player_view.getPlayerUiController().showFullscreenButton(true)
//        third_party_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
//                videoId = "YE7VzlLtp-4"
//                youTubePlayer.cueVideo(videoId, 0f)
//            }
//        })
//
//        third_party_player_view.getPlayerUiController().setFullScreenButtonClickListener(View.OnClickListener {
//            if (third_party_player_view.isFullScreen()) {
//                third_party_player_view.exitFullScreen()
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
//                // Show ActionBar
//                if (supportActionBar != null) {
//                    supportActionBar!!.show()
//                }
//            } else {
//                third_party_player_view.enterFullScreen()
//                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//                // Hide ActionBar
//                if (supportActionBar != null) {
//                    supportActionBar!!.hide()
//                }
//            }
//        })
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider,youTubePlayer: YouTubePlayer,wasRestored: Boolean) {
        if (!wasRestored) {

            youTubePlayer.cueVideo(id_video)
            if (youTubePlayer!= null)  myoutubeplayer= youTubePlayer
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider,youTubeInitializationResult: YouTubeInitializationResult) {
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
        id_video = "G63uL4TaP3o"
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
