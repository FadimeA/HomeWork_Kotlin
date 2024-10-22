package com.example.homework_kotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val SELECT_IMAGE = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.edt_email)
        val messageEditText = findViewById<EditText>(R.id.edt_message)
        val gmailButton = findViewById<Button>(R.id.btn_gmail)
        val secondButton = findViewById<Button>(R.id.btn_second)


        secondButton.setOnClickListener {
            val intent =  Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Выбрать изображение"), SELECT_IMAGE)
        }


        gmailButton.setOnClickListener {
            val recipient = emailEditText.text.toString()
            val message  = messageEditText.text.toString()

            val intent = Intent(Intent.ACTION_SEND)


            if(recipient.isEmpty() || message.isEmpty()){
                Toast.makeText(this, "Заполните поля!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            intent.data = Uri.parse("mailto:")
            intent.type = "text/plain"
            intent.setPackage("com.google.android.gm")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            intent.putExtra(Intent.EXTRA_TEXT,message)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.data?.let { imageUri ->
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("imageUri", imageUri.toString())
                    startActivity(intent)
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Отменено", Toast.LENGTH_SHORT).show()
            }
        }
    }

}