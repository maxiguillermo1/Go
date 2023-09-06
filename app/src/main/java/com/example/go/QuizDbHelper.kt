package com.example.go

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class QuizDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Quiz.db"
        private const val DATABASE_VERSION = 3 // new ver to write new questions
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE ${QuizContract.QuestionsTable.TABLE_NAME} (
                ${QuizContract.QuestionsTable.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${QuizContract.QuestionsTable.COLUMN_QUESTION} TEXT,
                ${QuizContract.QuestionsTable.COLUMN_OPTION1} TEXT,
                ${QuizContract.QuestionsTable.COLUMN_OPTION2} TEXT,
                ${QuizContract.QuestionsTable.COLUMN_OPTION3} TEXT,
                ${QuizContract.QuestionsTable.COLUMN_ANSWER_NR} INTEGER,
                ${QuizContract.QuestionsTable.COLUMN_CATEGORY} TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
        fillQuestionsTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE ${QuizContract.QuestionsTable.TABLE_NAME} ADD COLUMN ${QuizContract.QuestionsTable.COLUMN_CATEGORY} TEXT")
        }

    }

    private fun fillQuestionsTable(db: SQLiteDatabase) {
        val hiraganaQuestions = listOf(

            // a - o hiragana
            Question(1, "あ", "a", "i", "u", 1, "Hiragana"),
            Question(2, "い", "o", "a", "i", 3,"Hiragana"),
            Question(3, "う", "u", "e", "o", 1, "Hiragana"),
            Question(4, "え", "e", "i", "u", 1, "Hiragana"),
            Question(5, "お", "a", "o", "i", 2, "Hiragana"),

            // ka - ko hiragana
            Question(6, "か", "ka", "ki", "ku", 1, "Hiragana"),
            Question(7, "き", "ko", "ka", "ki", 3, "Hiragana"),
            Question(8, "く", "ku", "ke", "ko", 1, "Hiragana"),
            Question(9, "け", "ke", "ki", "ku", 1, "Hiragana"),
            Question(10, "こ", "ka", "ko", "ki", 2, "Hiragana"),

            // sa - so hiragana
            Question(11, "さ", "sa", "shi", "su", 1, "Hiragana"),
            Question(12, "し", "so", "sa", "shi", 3, "Hiragana"),
            Question(13, "す", "su", "se", "so", 1, "Hiragana"),
            Question(14, "せ", "se", "shi", "su", 1, "Hiragana"),
            Question(15, "そ", "sa", "so", "shi", 2, "Hiragana"),

            // ta - to hiragana
            Question(16, "た", "ta", "chi", "tsu", 1, "Hiragana"),
            Question(17, "ち", "to", "ta", "chi", 3, "Hiragana"),
            Question(18, "つ", "tsu", "te", "to", 1, "Hiragana"),
            Question(19, "て", "te", "chi", "tsu", 1, "Hiragana"),
            Question(20, "と", "ta", "to", "chi", 2, "Hiragana"),

            // na - no hiragana
            Question(21, "な", "na", "ni", "nu", 1, "Hiragana"),
            Question(22, "に", "no", "na", "ni", 3, "Hiragana"),
            Question(23, "ぬ", "nu", "ne", "no", 1, "Hiragana"),
            Question(24, "ね", "ne", "ni", "nu", 1, "Hiragana"),
            Question(25, "の", "na", "no", "ni", 2, "Hiragana"),

            // ha - ho hiragana
            Question(26, "は", "ha", "hi", "hu", 1, "Hiragana"),
            Question(27, "ひ", "ho", "ha", "hi", 3, "Hiragana"),
            Question(28, "ふ", "hu", "he", "ho", 1, "Hiragana"),
            Question(29, "へ", "he", "hi", "hu", 1, "Hiragana"),
            Question(30, "ほ", "ha", "ho", "hi", 2, "Hiragana"),

            // ma - mo hiragana
            Question(31, "ま", "ma", "mi", "mu", 1, "Hiragana"),
            Question(32, "み", "mo", "ma", "mi", 3, "Hiragana"),
            Question(33, "む", "mu", "me", "mo", 1, "Hiragana"),
            Question(34, "め", "me", "mi", "mu", 1, "Hiragana"),
            Question(35, "も", "ma", "mo", "mi", 2, "Hiragana"),

            // ra - ro hiragana
            Question(36, "ら", "ra", "ri", "ru", 1, "Hiragana"),
            Question(37, "り", "ro", "ra", "ri", 3, "Hiragana"),
            Question(38, "る", "ru", "re", "ro", 1, "Hiragana"),
            Question(39, "れ", "re", "ri", "ru", 1, "Hiragana"),
            Question(40, "ろ", "ra", "ro", "ri", 2, "Hiragana"),

            // ga - go hiragana
            Question(41, "が", "ga", "gi", "gu", 1, "Hiragana"),
            Question(42, "ぎ", "go", "ga", "gi", 3,"Hiragana"),
            Question(43, "ぐ", "gu", "ge", "go", 1, "Hiragana"),
            Question(44, "げ", "ge", "gi", "gu", 1, "Hiragana"),
            Question(45, "ご", "ga", "go", "gi", 2, "Hiragana"),

            // za - zo hiragana
            Question(46, "ざ", "za", "ji", "zu", 1, "Hiragana"),
            Question(47, "じ", "zo", "za", "ji", 3, "Hiragana"),
            Question(48, "ず", "zu", "ze", "zo", 1, "Hiragana"),
            Question(49, "ぜ", "ze", "ji", "zu", 1, "Hiragana"),
            Question(50, "ぞ", "za", "zo", "ji", 2, "Hiragana"),

            // da - do hiragana
            Question(51, "だ", "da", "dji", "dzu", 1, "Hiragana"),
            Question(52, "ぢ", "do", "da", "dji", 3,"Hiragana"),
            Question(53, "づ", "dzu", "de", "do", 1, "Hiragana"),
            Question(54, "で", "de", "dji", "dzu", 1, "Hiragana"),
            Question(55, "ど", "da", "do", "dji", 2, "Hiragana"),

            // ba - bo hiragana
            Question(56, "ば", "ba", "bi", "bu", 1, "Hiragana"),
            Question(57, "び", "bo", "ba", "bi", 3, "Hiragana"),
            Question(58, "ぶ", "bu", "be", "bo", 1, "Hiragana"),
            Question(59, "べ", "be", "bi", "bu", 1, "Hiragana"),
            Question(60, "ぼ", "ba", "bo", "bi", 2, "Hiragana"),

            // pa - po hiragana
            Question(61, "ぱ", "pa", "pi", "pu", 1, "Hiragana"),
            Question(62, "ぴ", "po", "pa", "pi", 3, "Hiragana"),
            Question(63, "ぷ", "pu", "pe", "po", 1, "Hiragana"),
            Question(64, "ぺ", "pe", "pi", "pu", 1, "Hiragana"),
            Question(65, "ぽ", "pa", "po", "pi", 2, "Hiragana"),

            // ya yu yo hiragana
            Question(66, "や", "ya", "yo", "yu", 1, "Hiragana"),
            Question(67, "ゆ", "ya", "yu", "yo", 2,"Hiragana"),
            Question(68, "よ", "yu", "ya", "yo", 3, "Hiragana"),

            // misc hiragana
            Question(69, "わ", "wa", "wo", "n", 1, "Hiragana"),
            Question(70, "を", "wa", "wo", "n", 2,"Hiragana"),
            Question(71, "ん", "n", "wo", "wa", 1, "Hiragana")

        )

        val katakanaQuestions = listOf(
            // a - o katakana
            Question(1, "ア", "a", "i", "u", 1, "Katakana"),
            Question(2, "イ", "o", "a", "i", 3, "Katakana"),
            Question(3, "ウ", "u", "e", "o", 1, "Katakana"),
            Question(4, "エ", "e", "i", "u", 1, "Katakana"),
            Question(5, "オ", "a", "o", "i", 2, "Katakana"),

            // ka - ko katakana
            Question(6, "カ", "ka", "ki", "ku", 1, "Katakana"),
            Question(7, "キ", "ko", "ka", "ki", 3, "Katakana"),
            Question(8, "ク", "ku", "ke", "ko", 1, "Katakana"),
            Question(9, "ケ", "ke", "ki", "ku", 1, "Katakana"),
            Question(10, "コ", "ka", "ko", "ki", 2, "Katakana"),

            // sa - so hiragana
            Question(11, "サ", "sa", "shi", "su", 1, "Katakana"),
            Question(12, "シ", "so", "sa", "shi", 3, "Katakana"),
            Question(13, "ス", "su", "se", "so", 1, "Katakana"),
            Question(14, "セ", "se", "shi", "su", 1, "Katakana"),
            Question(15, "ソ", "sa", "so", "shi", 2, "Katakana"),

            // ta - to hiragana
            Question(16, "タ", "ta", "chi", "tsu", 1, "Katakana"),
            Question(17, "チ", "to", "ta", "chi", 3, "Katakana"),
            Question(18, "ツ", "tsu", "te", "to", 1, "Katakana"),
            Question(19, "テ", "te", "chi", "tsu", 1, "Katakana"),
            Question(20, "ト", "ta", "to", "chi", 2, "Katakana"),

            // na - no hiragana
            Question(21, "ナ", "na", "ni", "nu", 1, "Katakana"),
            Question(22, "ニ", "no", "na", "ni", 3, "Katakana"),
            Question(23, "ヌ", "nu", "ne", "no", 1, "Katakana"),
            Question(24, "ネ", "ne", "ni", "nu", 1, "Katakana"),
            Question(25, "ノ", "na", "no", "ni", 2, "Katakana"),

            // ha - ho hiragana
            Question(26, "ハ", "ha", "hi", "hu", 1, "Katakana"),
            Question(27, "ヒ", "ho", "ha", "hi", 3, "Katakana"),
            Question(28, "フ", "hu", "he", "ho", 1, "Katakana"),
            Question(29, "ヘ", "he", "hi", "hu", 1, "Katakana"),
            Question(30, "ホ", "ha", "ho", "hi", 2, "Katakana"),

            // ma - mo hiragana
            Question(31, "マ", "ma", "mi", "mu", 1, "Katakana"),
            Question(32, "ミ", "mo", "ma", "mi", 3, "Katakana"),
            Question(33, "ム", "mu", "me", "mo", 1, "Katakana"),
            Question(34, "メ", "me", "mi", "mu", 1, "Katakana"),
            Question(35, "モ", "ma", "mo", "mi", 2, "Katakana"),

            // ra - ro hiragana
            Question(36, "ラ", "ra", "ri", "ru", 1, "Katakana"),
            Question(37, "リ", "ro", "ra", "ri", 3, "Katakana"),
            Question(38, "ル", "ru", "re", "ro", 1, "Katakana"),
            Question(39, "レ", "re", "ri", "ru", 1, "Katakana"),
            Question(40, "ロ", "ra", "ro", "ri", 2, "Katakana"),

            // ga - go hiragana
            Question(41, "ガ", "ga", "gi", "gu", 1, "Katakana"),
            Question(42, "ギ", "go", "ga", "gi", 3,"Katakana"),
            Question(43, "グ", "gu", "ge", "go", 1, "Katakana"),
            Question(44, "ゲ", "ge", "gi", "gu", 1, "Katakana"),
            Question(45, "ゴ", "ga", "go", "gi", 2, "Katakana"),

            // za - zo hiragana
            Question(46, "ザ", "za", "ji", "zu", 1, "Katakana"),
            Question(47, "ジ", "zo", "za", "ji", 3, "Katakana"),
            Question(48, "ズ", "zu", "ze", "zo", 1, "Katakana"),
            Question(49, "ゼ", "ze", "ji", "zu", 1, "Katakana"),
            Question(50, "ゾ", "za", "zo", "ji", 2, "Katakana"),

            // da - do hiragana
            Question(51, "ダ", "da", "dji", "dzu", 1, "Katakana"),
            Question(52, "ヂ", "do", "da", "dji", 3,"Katakana"),
            Question(53, "ヅ", "dzu", "de", "do", 1, "Katakana"),
            Question(54, "デ", "de", "dji", "dzu", 1, "Katakana"),
            Question(55, "ド", "da", "do", "dji", 2, "Katakana"),

            // ba - bo hiragana
            Question(56, "バ", "ba", "bi", "bu", 1, "Katakana"),
            Question(57, "ビ", "bo", "ba", "bi", 3, "Katakana"),
            Question(58, "ブ", "bu", "be", "bo", 1, "Katakana"),
            Question(59, "ベ", "be", "bi", "bu", 1, "Katakana"),
            Question(60, "ボ", "ba", "bo", "bi", 2, "Katakana"),

            // pa - po hiragana
            Question(61, "パ", "pa", "pi", "pu", 1, "Katakana"),
            Question(62, "ピ", "po", "pa", "pi", 3, "Katakana"),
            Question(63, "プ", "pu", "pe", "po", 1, "Katakana"),
            Question(64, "ペ", "pe", "pi", "pu", 1, "Katakana"),
            Question(65, "ポ", "pa", "po", "pi", 2, "Katakana"),

            // ya yu yo hiragana
            Question(66, "ヤ", "ya", "yo", "yu", 1, "Katakana"),
            Question(67, "ユ", "ya", "yu", "yo", 2,"Katakana"),
            Question(68, "ヨ", "yu", "ya", "yo", 3, "Katakana"),

            // misc hiragana
            Question(69, "ワ", "wa", "wo", "n", 1, "Katakana"),
            Question(70, "ヲ", "wa", "wo", "n", 2,"Katakana"),
            Question(71, "ン", "n", "wo", "wa", 1, "Katakana")
            )

        val allQuestions = hiraganaQuestions + katakanaQuestions

        allQuestions.forEach { question ->
            val values = ContentValues().apply {
                put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.question)
                put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.option1)
                put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.option2)
                put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.option3)
                put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.answerNr)
                put(QuizContract.QuestionsTable.COLUMN_CATEGORY, question.category)
            }
            val insertId = db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, values)
            Log.d("QuizDbHelper", "Inserted question with ID: $insertId")
        }
    }

    @SuppressLint("Range")
    fun getAllQuestions(): List<Question> {
        val questionList = mutableListOf<Question>()
        val db = readableDatabase
        val columns = arrayOf(
            QuizContract.QuestionsTable.COLUMN_ID,
            QuizContract.QuestionsTable.COLUMN_QUESTION,
            QuizContract.QuestionsTable.COLUMN_OPTION1,
            QuizContract.QuestionsTable.COLUMN_OPTION2,
            QuizContract.QuestionsTable.COLUMN_OPTION3,
            QuizContract.QuestionsTable.COLUMN_ANSWER_NR,
            QuizContract.QuestionsTable.COLUMN_CATEGORY
        )
        val cursor: Cursor? = db.query(QuizContract.QuestionsTable.TABLE_NAME, columns, null, null, null, null, null)
        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ID))
                    val question = it.getString(it.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION))
                    val option1 = it.getString(it.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1))
                    val option2 = it.getString(it.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2))
                    val option3 = it.getString(it.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3))
                    val answerNr = it.getInt(it.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR))
                    val category = it.getString(it.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY))
                    val currentQuestion = Question(id, question, option1, option2, option3, answerNr, category)
                    Log.d("QuizDbHelper", "Retrieved question: $currentQuestion")
                    questionList.add(currentQuestion)
                } while (it.moveToNext())
            }
            cursor.close()
        }
        db.close()
        return questionList.shuffled().take(10)
    }
}
