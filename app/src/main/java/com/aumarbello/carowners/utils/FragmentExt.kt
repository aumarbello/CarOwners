package com.aumarbello.carowners.utils

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showSnackBar(message: Int) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.setTitle(@StringRes title: Int, showBackButton: Boolean = false) {
    val activity = requireActivity()
    if (activity is AppCompatActivity) {
        activity.supportActionBar?.also {
            it.title = getString(title)
            it.setDisplayHomeAsUpEnabled(showBackButton)
        }
    }
}