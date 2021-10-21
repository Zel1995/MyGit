package com.example.mygit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mygit.App
import com.example.mygit.R
import com.example.mygit.ui.users.Screen
import com.example.mygit.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    private val navigator = AppNavigator(this, R.id.main_container)
    private val navigatorHolder = App.navigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.router.navigateTo(Screen.users())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}