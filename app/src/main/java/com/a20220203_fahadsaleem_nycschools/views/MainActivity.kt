package com.a20220203_fahadsaleem_nycschools.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.a20220203_fahadsaleem_nycschools.R
import com.a20220203_fahadsaleem_nycschools.adapter.SchoolListAdapter
import com.a20220203_fahadsaleem_nycschools.databinding.ActivityMainBinding
import com.a20220203_fahadsaleem_nycschools.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navHostFragment.navController.navigateUp()
    }
}