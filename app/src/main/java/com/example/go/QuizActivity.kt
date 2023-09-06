package com.example.go

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.collections.shuffle
import android.media.MediaPlayer

class QuizActivity : AppCompatActivity() {
    private lateinit var textViewQuestion: TextView
    private lateinit var textViewScore: TextView
    private lateinit var textViewQuestionCount: TextView
    private lateinit var textViewCountDown: TextView
    private lateinit var rbGroup: RadioGroup
    private lateinit var rb1: RadioButton
    private lateinit var rb2: RadioButton
    private lateinit var rb3: RadioButton
    private lateinit var buttonConfirmNext: Button
    private lateinit var textColorDefaultRb: ColorStateList
    private lateinit var textColorDefaultCd: ColorStateList
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0
    private var questionList: MutableList<Question>? = null
    private var questionCounter = 0
    private var questionCountTotal = 0
    private var currentQuestion: Question? = null
    private var score = 0
    private var answered = false
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actual_quiz_page)

        textViewQuestion = findViewById(R.id.txtQuestion)
        textViewScore = findViewById(R.id.txtScore)
        textViewQuestionCount = findViewById(R.id.questionCount)
        textViewCountDown = findViewById(R.id.countDown)
        rbGroup = findViewById(R.id.radioGroup)
        rb1 = findViewById(R.id.radioButton1)
        rb2 = findViewById(R.id.radioButton2)
        rb3 = findViewById(R.id.radioButton3)
        buttonConfirmNext = findViewById(R.id.btnConfirmNext)

        textColorDefaultRb = rb1.textColors
        textColorDefaultCd = textViewCountDown.textColors

        if (savedInstanceState == null) {
            val dbHelper = QuizDbHelper(this)
            questionList = dbHelper.getAllQuestions().toMutableList()
            questionCountTotal = questionList!!.size
            questionList!!.shuffle() // Use the Kotlin standard library shuffle function
            showNextQuestion()
        } else {
            val questionArray = savedInstanceState.getParcelableArrayList<Question>(KEY_QUESTION_LIST)!!
            questionList = questionArray.toList() as MutableList<Question>?
            questionCountTotal = questionList!!.size
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT)
            currentQuestion = questionList!![questionCounter - 1]
            score = savedInstanceState.getInt(KEY_SCORE)
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT)
            answered = savedInstanceState.getBoolean(KEY_ANSWERED)
            if (!answered) {
                startCountDown()
            } else {
                updateCountDownText()
                showSolution()
            }
        }

        buttonConfirmNext.setOnClickListener {
            if (!answered) {
                if (rb1.isChecked || rb2.isChecked || rb3.isChecked) {
                    checkAnswer()
                } else {
                    Toast.makeText(this@QuizActivity, getString(R.string.select_answer), Toast.LENGTH_SHORT).show()
                }
            } else {
                showNextQuestion()
            }
        }
    }

    private fun showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb)
        rb2.setTextColor(textColorDefaultRb)
        rb3.setTextColor(textColorDefaultRb)
        rbGroup.clearCheck()
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList!![questionCounter]
            textViewQuestion.text = currentQuestion?.question
            rb1.text = currentQuestion?.option1
            rb2.text = currentQuestion?.option2
            rb3.text = currentQuestion?.option3
            questionCounter++
            textViewQuestionCount.text = getString(R.string.question_count, questionCounter, questionCountTotal)
            answered = false
            buttonConfirmNext.text = getString(R.string.confirm)
            timeLeftInMillis = COUNTDOWN_IN_MILLIS
            startCountDown()
        } else {
            finishQuiz()
        }
    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateCountDownText()
                checkAnswer()
            }
        }.start()
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000).toInt() / 60
        val seconds = (timeLeftInMillis / 1000).toInt() % 60
        val timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        textViewCountDown.text = timeFormatted
        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED)
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd)
        }
    }

    private fun checkAnswer() {
        answered = true
        countDownTimer?.cancel()
        val rbsSelected = findViewById<RadioButton>(rbGroup.checkedRadioButtonId)
        val answerNr = rbGroup.indexOfChild(rbsSelected) + 1
        if (answerNr == currentQuestion?.answerNr) {
            score++
            textViewScore.text = getString(R.string.score, score)
            playSound(R.raw.correct_answer)
        } else {
            playSound(R.raw.incorrect_answer)
        }
        showSolution()
    }

    private fun showSolution() {
        rb1.setTextColor(Color.RED)
        rb2.setTextColor(Color.RED)
        rb3.setTextColor(Color.RED)
        when (currentQuestion?.answerNr) {
            1 -> {
                rb1.setTextColor(Color.GREEN)
                textViewQuestion.text = getString(R.string.answer1_correct)
            }
            2 -> {
                rb2.setTextColor(Color.GREEN)
                textViewQuestion.text = getString(R.string.answer2_correct)
            }
            3 -> {
                rb3.setTextColor(Color.GREEN)
                textViewQuestion.text = getString(R.string.answer3_correct)
            }
        }
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.text = getString(R.string.next)
        } else {
            buttonConfirmNext.text = getString(R.string.finish)
        }
    }

    private fun finishQuiz() {
        val resultIntent = Intent()
        resultIntent.putExtra(EXTRA_SCORE, score)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    private fun playSound(soundResId: Int) {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener { mp ->
            mp.release()
        }
    }

    // If user wants to go back during quiz activity
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz()
        } else {
            Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SCORE, score)
        outState.putInt(KEY_QUESTION_COUNT, questionCounter)
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis)
        outState.putBoolean(KEY_ANSWERED, answered)
        outState.putParcelableArrayList(KEY_QUESTION_LIST, ArrayList(questionList!!))
    }

    companion object {
        const val EXTRA_SCORE = "extraScore"
        private const val COUNTDOWN_IN_MILLIS: Long = 31000
        private const val KEY_SCORE = "keyScore"
        private const val KEY_QUESTION_COUNT = "keyQuestionCount"
        private const val KEY_MILLIS_LEFT = "keyMillisLeft"
        private const val KEY_ANSWERED = "keyAnswered"
        private const val KEY_QUESTION_LIST = "keyQuestionList"
    }
}
