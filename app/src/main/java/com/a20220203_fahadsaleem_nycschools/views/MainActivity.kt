package com.a20220203_fahadsaleem_nycschools.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.a20220203_fahadsaleem_nycschools.adapter.SchoolListAdapter
import com.a20220203_fahadsaleem_nycschools.databinding.ActivityMainBinding
import com.a20220203_fahadsaleem_nycschools.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SchoolListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        //using kotlin extension function here to avoid boiler plate code
        with(binding) {
            errorMessageTV.text = ""
            errorMessageTV.isVisible = false
            binding.loadingPB.isVisible = false

            nycSchoolRV.layoutManager = LinearLayoutManager(binding.root.context)
            adapter = SchoolListAdapter(arrayListOf(), onClick = { school ->
                val intent = Intent(this@MainActivity, SchoolDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(PARCELABLE_KEY, school)
                intent.putExtras(bundle)
                startActivity(intent)
            })
            nycSchoolRV.addItemDecoration(
                DividerItemDecoration(
                    nycSchoolRV.context,
                    (nycSchoolRV.layoutManager as LinearLayoutManager).orientation
                )
            )
            nycSchoolRV.adapter = adapter
        }

        mainActivityViewModel.errorMessage.observe(this, Observer { message ->
            binding.errorMessageTV.isVisible = true
            binding.errorMessageTV.text = message
        })

        mainActivityViewModel.showProgress.observe(this, Observer { isShow ->
            binding.loadingPB.isVisible = isShow
        })

        mainActivityViewModel.listOfSchoolLiveData.observe(this, Observer { list ->
            adapter.apply {
                addSchools(list!!)
                notifyDataSetChanged()
            }
        })
    }

    companion object {
        const val PARCELABLE_KEY = "School"
    }
}