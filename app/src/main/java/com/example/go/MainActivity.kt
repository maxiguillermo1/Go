package com.example.go

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputNama: EditText
    private lateinit var btnNama: Button
    private lateinit var checkBox: CheckBox
    private lateinit var sharedPref: SharedPreferences

    private var isRemembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNama = findViewById(R.id.inputNama)
        btnNama = findViewById(R.id.btnNama)
        checkBox = findViewById(R.id.checkBox)
        sharedPref = getSharedPreferences("SHARED_PREF", MODE_PRIVATE)
        isRemembered = sharedPref.getBoolean("CHECKBOX", false)

        if (isRemembered) {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnNama.setOnClickListener {
            val name = inputNama.text.toString()
            val checked = checkBox.isChecked

            val editor = sharedPref.edit()
            editor.putString("NAME", name)
            editor.putBoolean("CHECKBOX", checked)
            editor.apply()

            Toast.makeText(this, "Information Saved!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
