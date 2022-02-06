package com.a20220203_fahadsaleem_nycschools.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.a20220203_fahadsaleem_nycschools.adapter.SchoolListAdapterV2
import com.a20220203_fahadsaleem_nycschools.databinding.ActivityMainBinding
import com.a20220203_fahadsaleem_nycschools.repository.NycHighSchoolRepositoryImp
import com.a20220203_fahadsaleem_nycschools.viewmodel.MainActivityViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel = MainActivityViewModel(NycHighSchoolRepositoryImp())
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SchoolListAdapterV2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            errorMessageTV.isVisible = false
            binding.loadingPB.isVisible = false

            nycSchoolRV.layoutManager = LinearLayoutManager(binding.root.context)
            adapter = SchoolListAdapterV2(arrayListOf(), onClick = { school ->
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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainActivityViewModel.listOfSchool.collect { list ->
                        adapter.apply {
                            addSchools(list)
                            notifyDataSetChanged()
                        }
                    }
                }
                launch {
                    mainActivityViewModel.errorMessage.collect { message ->
                        binding.errorMessageTV.isVisible = true
                        binding.errorMessageTV.text = message
                    }
                }
                launch {
                    mainActivityViewModel.showProgress.collect { isShow ->
                        binding.loadingPB.isVisible = isShow
                    }
                }
            }
        }
        mainActivityViewModel.getSchoolsList()
    }

    companion object {
        const val PARCELABLE_KEY = "School"
    }
}