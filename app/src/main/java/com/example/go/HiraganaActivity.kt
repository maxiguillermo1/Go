package com.example.go


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HiraganaActivity : AppCompatActivity() {

    private lateinit var dataList: RecyclerView
    private lateinit var titles: Array<String>
    private lateinit var btnKatakana2: ImageButton

    private val images = intArrayOf(
        R.drawable.a, R.drawable.i, R.drawable.u, R.drawable.e, R.drawable.o,
        R.drawable.ka, R.drawable.ki, R.drawable.ku, R.drawable.ke, R.drawable.ko,
        R.drawable.sa, R.drawable.shi, R.drawable.su, R.drawable.se, R.drawable.so,
        R.drawable.ta, R.drawable.chi, R.drawable.tsu, R.drawable.te, R.drawable.to,
        R.drawable.na, R.drawable.ni, R.drawable.nu, R.drawable.ne, R.drawable.no,
        R.drawable.ha, R.drawable.hi, R.drawable.fu, R.drawable.he, R.drawable.ho,
        R.drawable.ma, R.drawable.mi, R.drawable.mu, R.drawable.me, R.drawable.mo,
        R.drawable.ya, R.drawable.yi, R.drawable.yo, R.drawable.wa, R.drawable.wo,
        R.drawable.ra, R.drawable.ri, R.drawable.ru, R.drawable.re, R.drawable.ro,
        R.drawable.n
    )

    private lateinit var adapter: KanaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hiragana_page)

        btnKatakana2 = findViewById(R.id.back_Katakana)
        dataList = findViewById(R.id.recyclerHiragana)

        titles = resources.getStringArray(R.array.kana)

        adapter = KanaAdapter(this, titles, images)

        val gridLayoutManager = GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false)
        dataList.layoutManager = gridLayoutManager
        dataList.adapter = adapter

        btnKatakana2.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@HiraganaActivity, KatakanaActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}
