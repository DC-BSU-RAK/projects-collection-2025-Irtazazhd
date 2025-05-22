package com.example.poemcraft

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var moodInput: EditText
    private lateinit var poemOutput: TextView
    private lateinit var poemCard: CardView
    private lateinit var craftButton: Button
    private lateinit var infoButton: Button
    private lateinit var shareButton: Button

    private val emojiTextViews = mutableListOf<TextView>()
    private var selectedEmoji: String? = null

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moodInput = findViewById(R.id.moodInput)
        poemOutput = findViewById(R.id.poemOutput)
        poemCard = findViewById(R.id.poemCard)
        craftButton = findViewById(R.id.craftButton)
        infoButton = findViewById(R.id.infoButton)
        shareButton = findViewById(R.id.shareButton)

        setupEmojiClickListeners()

        craftButton.setOnClickListener {
            craftPoem()
        }

        infoButton.setOnClickListener {
            showInfoDialog()
        }

        shareButton.setOnClickListener {
            sharePoem()
        }

        // Music Setup with Fade-In
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        fadeInMusic()
    }

    private fun setupEmojiClickListeners() {
        val emojiLayout = findViewById<LinearLayout>(R.id.emojiLayout)

        for (i in 0 until emojiLayout.childCount) {
            val emojiView = emojiLayout.getChildAt(i) as TextView
            emojiTextViews.add(emojiView)

            emojiView.setOnClickListener {
                selectedEmoji = emojiView.text.toString()
                resetEmojiBackgrounds()
                emojiView.setBackgroundResource(R.drawable.selected_emoji_background)
            }
        }
    }

    private fun resetEmojiBackgrounds() {
        for (emojiView in emojiTextViews) {
            emojiView.setBackgroundResource(0)
        }
    }

    private fun craftPoem() {
        val mood = moodInput.text.toString()

        if (mood.isEmpty()) {
            poemOutput.text = "Please enter your mood to craft your poem!"
            poemCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            return
        }

        when (selectedEmoji) {
            "😊" -> {
                poemOutput.text = """
                    Smiles bloom in the skies so blue,
                    Your heart sings joy in all you do.
                    Light like petals, gently dance,
                    In happy thoughts, we find our chance.
                """.trimIndent()
                poemCard.setCardBackgroundColor(Color.parseColor("#FFF9C4"))
            }

            "😢" -> {
                poemOutput.text = """
                    Tears fall like gentle rain,
                    Soft echoes of your quiet pain.
                    But in the sorrow, truth may lie,
                    And from the ache, your soul can fly.
                """.trimIndent()
                poemCard.setCardBackgroundColor(Color.parseColor("#E1F5FE"))
            }

            "😮" -> {
                poemOutput.text = """
                    Eyes wide open, breath held tight,
                    The world unfolds in shining light.
                    Surprise ignites the stars above,
                    In awe we stumble, fall in love.
                """.trimIndent()
                poemCard.setCardBackgroundColor(Color.parseColor("#F3E5F5"))
            }

            "😌" -> {
                poemOutput.text = """
                    Calm as moonlight on a stream,
                    Life flows gently like a dream.
                    In every breath, a silent song,
                    In peace we know where we belong.
                """.trimIndent()
                poemCard.setCardBackgroundColor(Color.parseColor("#DCEDC8"))
            }

            "😠" -> {
                poemOutput.text = """
                    Like thunder rolling through the sky,
                    Anger roars, then says goodbye.
                    Fire burns but clears the way,
                    For strength and truth to have their say.
                """.trimIndent()
                poemCard.setCardBackgroundColor(Color.parseColor("#FFCDD2"))
            }

            else -> {
                poemOutput.text = """
                    In a mood of "$mood",
                    With the vibe of ✨,
                    The world feels poetic and bright.
                    Let the words flow like a river of light...
                """.trimIndent()
                poemCard.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    private fun sharePoem() {
        val poem = poemOutput.text.toString()
        if (poem.isNotEmpty() && poem != "Your poem will appear here...") {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, poem)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share your poem via"))
        }
    }

    private fun showInfoDialog() {
        val dialog = InfoDialogFragment()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        dialog.show(transaction, "InfoDialog")
    }

    // MUSIC FADE-IN FUNCTION
    private fun fadeInMusic(duration: Long = 3000) {
        val steps = 20
        val delay = duration / steps
        var volume = 0f

        mediaPlayer.setVolume(0f, 0f)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        val handler = Handler()
        for (i in 1..steps) {
            handler.postDelayed({
                volume = i.toFloat() / steps
                mediaPlayer.setVolume(volume, volume)
            }, i * delay)
        }
    }

    // MUSIC FADE-OUT FUNCTION
    private fun fadeOutMusic(duration: Long = 3000) {
        val steps = 20
        val delay = duration / steps
        val handler = Handler()
        for (i in steps downTo 1) {
            handler.postDelayed({
                val volume = i.toFloat() / steps
                mediaPlayer.setVolume(volume, volume)
                if (i == 1) {
                    mediaPlayer.pause()
                    mediaPlayer.seekTo(0)
                }
            }, (steps - i) * delay)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            fadeOutMusic()
        }
    }
}

// Info Dialog Fragment
class InfoDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_info, container, false)

        view.findViewById<Button>(R.id.closeButton).setOnClickListener {
            dismiss()
        }

        return view
    }
}
