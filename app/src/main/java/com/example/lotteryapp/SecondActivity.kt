package com.example.lotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import javax.annotation.processing.Generated

class SecondActivity : AppCompatActivity() {
    // Declaring the Views
    lateinit var textViewGeneratedNumbers: TextView
    lateinit var shareButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        shareButton = findViewById(R.id.btn_share)
        textViewGeneratedNumbers = findViewById(R.id.result)
        val randomNumbers = generateRandomNumbers(6)
        textViewGeneratedNumbers.text = randomNumbers
        val username = receiveUsername()
        shareButton.setOnClickListener {
            shareResult(username, randomNumbers)
        }
    }

    fun generateRandomNumbers(count: Int): String {

        var randomNumbers = List(count) {
            (0..42).random()
        }
        return randomNumbers.joinToString { " " }
    }

    fun receiveUsername(): String {
        var bundle: Bundle? = intent.extras
        var userName = bundle?.getString("username").toString()
        return userName
    }

    fun shareResult(username: String, generatedNums: String) {
        var i = Intent(Intent.ACTION_SEND)
        i.setType("text/plain")
        i.putExtra(Intent.EXTRA_SUBJECT, "$username generates these numbers")
        i.putExtra(Intent.EXTRA_TEXT, "The Lottery Numbers are :$generatedNums")
        startActivity(i)
    }
}