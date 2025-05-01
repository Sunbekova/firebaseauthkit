package com.example.firebasev1.authUI

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasev1.databinding.ActivityUserBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


internal class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if the user is logged in or not
        val user = Firebase.auth.currentUser
        if (user == null) {
            // If user is not logged in, redirect to Login activity
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()  // Finish MainActivity to prevent user from going back
        }

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}