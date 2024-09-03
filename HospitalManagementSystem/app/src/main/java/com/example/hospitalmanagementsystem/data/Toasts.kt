// Extensions.kt
package com.example.hospitalmanagementsystem.data

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.showToast(message: Any) {
    Toast.makeText(this, "$message", Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(message: Any) {
    activity?.showToast(message)
}
