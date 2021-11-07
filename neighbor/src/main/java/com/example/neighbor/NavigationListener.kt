package com.example.neighbor

import androidx.fragment.app.Fragment
import androidx.annotation.StringRes

interface NavigationListener {
    fun showFragment(fragment: Fragment)
    fun updateTitle(@StringRes title: Int)
}
