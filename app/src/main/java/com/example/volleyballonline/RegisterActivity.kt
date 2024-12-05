package com.example.volleyballonline

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class RegisterActivity : AppCompatActivity() {

    private val mySQLHelper = MySQLHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerUsernameEditText: EditText = findViewById(R.id.registerUsernameEditText)
        val registerPasswordEditText: EditText = findViewById(R.id.registerPasswordEditText)
        val registerAgeEditText: EditText = findViewById(R.id.registerAgeEditText)
        val registerPhoneNumberText: EditText = findViewById(R.id.registerPhoneNumberEditText)
        val confirmRegisterButton: Button = findViewById(R.id.confirmRegisterButton)

        confirmRegisterButton.setOnClickListener {
            val username = registerUsernameEditText.text.toString()
            val password = registerPasswordEditText.text.toString()
            val age = registerAgeEditText.text.toString() // 尝试输入
            val phoneNumber = registerPhoneNumberText.text.toString() // 尝试输入

            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    mySQLHelper.insertUser(username, password, age, phoneNumber)
                }

                // Show a Toast message indicating successful registration
                Toast.makeText(this@RegisterActivity, "注册成功!", Toast.LENGTH_SHORT).show()

                // Finish the registration activity
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Close the database connection when the activity is destroyed
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
}