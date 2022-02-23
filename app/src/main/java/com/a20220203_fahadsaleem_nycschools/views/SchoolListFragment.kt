package com.a20220203_fahadsaleem_nycschools.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.a20220203_fahadsaleem_nycschools.R
import com.a20220203_fahadsaleem_nycschools.adapter.SchoolListAdapter
import com.a20220203_fahadsaleem_nycschools.databinding.FragmentSchoolListBinding
import com.a20220203_fahadsaleem_nycschools.viewmodel.SchoolListViewModel

class SchoolListFragment : Fragment() {

    private lateinit var schoolListViewModel: SchoolListViewModel
    private lateinit var binding: FragmentSchoolListBinding
    private lateinit var adapter: SchoolListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schoolListViewModel = ViewModelProvider(this)[SchoolListViewModel::class.java]

        with(binding) {
            errorMessageTV.text = ""
            errorMessageTV.isVisible = false
            binding.loadingPB.isVisible = false

            nycSchoolRV.layoutManager = LinearLayoutManager(binding.root.context)
            adapter = SchoolListAdapter(arrayListOf(), onClick = { school ->
                val bundle = bundleOf(PARCELABLE_KEY_SCHOOL to school)
                view.findNavController().navigate(R.id.action_schoolListFragment_to_schoolDetailFragment, bundle)
            })
            nycSchoolRV.addItemDecoration(
                DividerItemDecoration(
                    nycSchoolRV.context,
                    (nycSchoolRV.layoutManager as LinearLayoutManager).orientation
                )
            )
            nycSchoolRV.adapter = adapter
        }

        schoolListViewModel.errorMessage.observe(this, Observer { message ->
            binding.errorMessageTV.isVisible = true
            binding.errorMessageTV.text = message
        })

        schoolListViewModel.showProgress.observe(this, Observer { isShow ->
            binding.loadingPB.isVisible = isShow
        })

        schoolListViewModel.listOfSchoolLiveData.observe(this, Observer { list ->
            adapter.apply {
                addSchools(list ?: arrayListOf())
                notifyDataSetChanged()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchoolListBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        const val PARCELABLE_KEY_SCHOOL = "School"
    }

}