package com.example.go

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizStartingActivity : AppCompatActivity() {

    private lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_starting_page)

        btnStart = findViewById(R.id.btnStart)

        btnStart.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_QUIZ)
        }
        updateHighscoreView()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_QUIZ && resultCode == RESULT_OK) {
            val score = data?.getIntExtra(QuizActivity.EXTRA_SCORE, 0)
            if (score != null && score > getHighscore()) {
                saveHighscore(score)
                updateHighscoreView()
            }
        }
    }

    private fun saveHighscore(highscore: Int) {
        val prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(KEY_HIGHSCORE, highscore)
        editor.apply()
    }

    private fun getHighscore(): Int {
        val prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        return prefs.getInt(KEY_HIGHSCORE, 0)
    }

    private fun updateHighscoreView() {
        val highScoreText = findViewById<TextView>(R.id.txtHighScore)
        highScoreText.text = "Highscore : ${getHighscore()}"
    }

    companion object {
        private const val REQUEST_CODE_QUIZ = 1
        private const val SHARED_PREFS = "sharedPrefs"
        private const val KEY_HIGHSCORE = "keyHighscore"
    }
}
