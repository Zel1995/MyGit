package com.example.mygit.ui

import android.os.Bundle
import com.example.mygit.R
import com.example.mygit.di.App
import com.example.mygit.di.MainSubcomponent
import com.example.mygit.ui.users.Screen
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router
    private val navigator = AppNavigator(this, R.id.main_container)
    var mainSubcomponent: MainSubcomponent? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainSubcomponent = (application as? App)?.appComponent?.mainSubcomponent()?.create()
        mainSubcomponent?.inject(this)
        router.navigateTo(Screen.repos())
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