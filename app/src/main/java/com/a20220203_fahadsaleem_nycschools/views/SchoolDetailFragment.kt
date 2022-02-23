package com.a20220203_fahadsaleem_nycschools.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.a20220203_fahadsaleem_nycschools.R
import com.a20220203_fahadsaleem_nycschools.databinding.SchoolDetailFragmentBinding
import com.a20220203_fahadsaleem_nycschools.factory.ViewModelFactory
import com.a20220203_fahadsaleem_nycschools.model.School
import com.a20220203_fahadsaleem_nycschools.viewmodel.SchoolDetailViewModel

class SchoolDetailFragment : Fragment() {

    private lateinit var viewModel: SchoolDetailViewModel
    private lateinit var binding: SchoolDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SchoolDetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val school: School? = arguments?.getParcelable(SchoolListFragment.PARCELABLE_KEY_SCHOOL)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(school!!.dbn)
        )[SchoolDetailViewModel::class.java]

        with(binding) {
            errorMessage.visibility = View.INVISIBLE
            errorMessage.visibility = View.INVISIBLE
        }

        viewModel.schoolDetailMutableLiveData.observe(this, Observer { schoolDetail ->
            schoolDetail?.let {
                val schoolName =
                    getString(R.string.name) + " " + schoolDetail.schoolName
                val avgReadScore =
                    getString(R.string.sat_avg_reading) + " " + schoolDetail.readingSATScore
                val avgMathScore =
                    getString(R.string.sat_avg_math) + " " + schoolDetail.mathSATScore
                val avgWriteScore =
                    getString(R.string.sat_avg_writing) + " " + schoolDetail.writingSATScore

                binding.schoolNameTV.text = schoolName
                binding.satAvgReadScore.text = avgReadScore
                binding.satAvgMathScore.text = avgMathScore
                binding.satAvgWritingScore.text = avgWriteScore
            }
        })

        viewModel.errorMessage.observe(this, Observer { message: String? ->
            binding.errorMessage.visibility = View.VISIBLE
            binding.errorMessage.text = message
        })

        viewModel.progressBar.observe(this, { isShow: Boolean ->
            binding.loadingPB.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
        })
    }

}