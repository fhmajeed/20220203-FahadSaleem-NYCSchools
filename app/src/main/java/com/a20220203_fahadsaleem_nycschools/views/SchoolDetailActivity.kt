package com.a20220203_fahadsaleem_nycschools.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a20220203_fahadsaleem_nycschools.databinding.ActivitySchoolDetailBinding
import com.a20220203_fahadsaleem_nycschools.model.School

class SchoolDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySchoolDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchoolDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var school = intent.getParcelableExtra<School>("school")
        with(binding){

        }
    }
}