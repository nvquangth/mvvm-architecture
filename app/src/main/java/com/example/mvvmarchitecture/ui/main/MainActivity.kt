package com.example.mvvmarchitecture.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.base.BaseActivity
import com.example.mvvmarchitecture.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.fragmentContainer)
        val appBarConfiguration = AppBarConfiguration(navController.graph, viewBinding.drawerLayout)
        viewBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}