package com.example.firebasev1.launcher

import android.content.Context
import android.content.Intent
import com.example.firebasev1.authUI.Login

object FirebaseAuthKit {
    fun start(context: Context) {
        val intent = Intent(context, Login::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}