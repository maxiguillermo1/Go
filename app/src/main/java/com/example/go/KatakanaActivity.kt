package com.example.go

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KatakanaActivity : AppCompatActivity() {

    private lateinit var dataList: RecyclerView
    private lateinit var titles: Array<String>
    private lateinit var back_Hiragana: ImageButton

    private val images = intArrayOf(
        R.drawable.a2, R.drawable.i2, R.drawable.u2, R.drawable.e2, R.drawable.o2,
        R.drawable.ka2, R.drawable.ki2, R.drawable.ku2, R.drawable.ke2, R.drawable.ko2,
        R.drawable.sa2, R.drawable.si2, R.drawable.su2, R.drawable.se2, R.drawable.so2,
        R.drawable.ta2, R.drawable.ti2, R.drawable.tu2, R.drawable.te2, R.drawable.to2,
        R.drawable.na2, R.drawable.ni2, R.drawable.nu2, R.drawable.ne2, R.drawable.no2,
        R.drawable.ha2, R.drawable.hi2, R.drawable.hu2, R.drawable.he2, R.drawable.ho2,
        R.drawable.ma2, R.drawable.mi2, R.drawable.mu2, R.drawable.me2, R.drawable.mo2,
        R.drawable.ya2, R.drawable.yu2, R.drawable.yo2, R.drawable.wa2, R.drawable.wo2,
        R.drawable.ra2, R.drawable.ri2, R.drawable.ru2, R.drawable.re2, R.drawable.ro2,
        R.drawable.n
    )
    private lateinit var adapter: KanaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.katakana_page)

        back_Hiragana = findViewById(R.id.back_Katakana)
        dataList = findViewById(R.id.recyclerKatakana)

        titles = resources.getStringArray(R.array.kana)

        adapter = KanaAdapter(this, titles, images)

        val gridLayoutManager = GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false)
        dataList.layoutManager = gridLayoutManager
        dataList.adapter = adapter

        back_Hiragana.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@KatakanaActivity, HiraganaActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}
