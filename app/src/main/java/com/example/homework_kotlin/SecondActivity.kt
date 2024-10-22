package com.example.homework_kotlin

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val imageUriString = intent.getStringExtra("imageUri")

        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
           val imageView = findViewById<ImageView>(R.id.ivImage)
            imageView.setImageURI(imageUri)
        } else {
            Toast.makeText(this, "Изображение не найдено", Toast.LENGTH_SHORT).show()
        }

    }
    }
