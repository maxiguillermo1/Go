package com.example.go

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private lateinit var mTxtNama: TextView
    private lateinit var mBtnOut: Button
    private lateinit var mBtnHiragana: Button
    private lateinit var mBtnKatakana: Button
    private lateinit var mBtnQuiz: Button
    private lateinit var mBtnGuide: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        mTxtNama = findViewById(R.id.txtNama)
        mBtnOut = findViewById(R.id.btnOut)
        mBtnHiragana = findViewById(R.id.btnHiragana)
        mBtnKatakana = findViewById(R.id.btnKatakana)
        mBtnQuiz = findViewById(R.id.btnQuiz)
        mBtnGuide = findViewById(R.id.btnGuide)

        preferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)

        val name = preferences.getString("NAME", "Anon")
        mTxtNama.text = name

        mBtnOut.setOnClickListener {
            val editor = preferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        mBtnHiragana.setOnClickListener {
            val intent = Intent(this, HiraganaActivity::class.java)
            startActivity(intent)
        }

        mBtnKatakana.setOnClickListener {
            val intent = Intent(this, KatakanaActivity::class.java)
            startActivity(intent)
        }

        mBtnQuiz.setOnClickListener {
            val intent = Intent(this, QuizStartingActivity::class.java)
            startActivity(intent)
        }

        //mBtnQuiz.setOnClickListener {
            //val intent = Intent(this, QuizActivity::class.java)
            //startActivity(intent)
        //}

        mBtnGuide.setOnClickListener {
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)
        }
    }
}
