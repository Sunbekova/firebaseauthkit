package com.example.firebasev1.authUI

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasev1.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

internal class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(this, UserActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.tvRegisterRedirect.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            // TODO: Add forgot password flow
            // Create an EditText for email input
            val resetMail = EditText(it.context)

            // Create an AlertDialog to reset password
            val passwordResetDialog = AlertDialog.Builder(it.context)
                .setTitle("Reset Password?")
                .setMessage("Enter Your Email To Receive Reset Link.")
                .setView(resetMail)
                .setPositiveButton("Yes") { dialog, _ ->

                    val email = resetMail.text.toString().trim()

                    if (email.isEmpty()) {
                        Toast.makeText(this, "Please enter an email address.", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(this, "Invalid email address.", Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }
                    // Send password reset email
                    auth.sendPasswordResetEmail(email)
                        .addOnSuccessListener {
                            // Show success toast
                            Toast.makeText(this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            // Show failure toast
                            Toast.makeText(this, "Error! Reset Link is Not Sent: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
                .setNegativeButton("No") { dialog, _ ->
                    // Close the dialog
                    dialog.dismiss()
                }

            // Show the dialog
            passwordResetDialog.create().show()
        }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // If already signed in, go to MainActivity
            startActivity(Intent(this, UserActivity::class.java))
            finish()
        }
    }
}