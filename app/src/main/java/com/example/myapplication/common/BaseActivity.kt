package com.example.myapplication.common

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected fun showToast(obj: Any?) {
        Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(obj: Any?, isLong: Boolean) {
        if (isLong) {
            Toast.makeText(this, obj.toString(), Toast.LENGTH_LONG).show()
        } else {
            showToast(obj)
        }
    }
}