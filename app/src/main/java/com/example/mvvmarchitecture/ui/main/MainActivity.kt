package com.example.mvvmarchitecture.ui.main

import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.base.BaseActivity
import com.example.mvvmarchitecture.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()

    override val layoutId: Int = R.layout.activity_main

}