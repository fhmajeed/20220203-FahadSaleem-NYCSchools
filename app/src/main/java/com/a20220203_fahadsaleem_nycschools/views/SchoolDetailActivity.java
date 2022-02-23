package com.a20220203_fahadsaleem_nycschools.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.a20220203_fahadsaleem_nycschools.R;
import com.a20220203_fahadsaleem_nycschools.databinding.ActivitySchoolDetailBinding;
import com.a20220203_fahadsaleem_nycschools.factory.ViewModelFactory;
import com.a20220203_fahadsaleem_nycschools.model.School;
import com.a20220203_fahadsaleem_nycschools.viewmodel.SchoolDetailActivityViewModel;

/**
 * School Detail Activity Maintain Selected School Detail and its score via model pattern.
 */
public class SchoolDetailActivity extends AppCompatActivity {

    private ActivitySchoolDetailBinding binding = null;
    private SchoolDetailActivityViewModel viewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySchoolDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.errorMessage.setVisibility(View.INVISIBLE);
        binding.errorMessage.setVisibility(View.INVISIBLE);

        School school = getIntent().getParcelableExtra(SchoolListFragment.PARCELABLE_KEY_SCHOOL);

        viewModel = new ViewModelProvider(this, new ViewModelFactory(school.getDbn())).get(SchoolDetailActivityViewModel.class);

        viewModel.schoolDetailMutableLiveData.observe(this, schoolDetail -> {
            String schoolName = getString(R.string.name) + " " + schoolDetail.getSchoolName();
            String avgReadScore = getString(R.string.sat_avg_reading) + " " + schoolDetail.getReadingSATScore();
            String avgMathScore = getString(R.string.sat_avg_math) + " " + schoolDetail.getMathSATScore();
            String avgWriteScore = getString(R.string.sat_avg_writing) + " " + schoolDetail.getWritingSATScore();

            binding.schoolNameTV.setText(schoolName);
            binding.satAvgReadScore.setText(avgReadScore);
            binding.satAvgMathScore.setText(avgMathScore);
            binding.satAvgWritingScore.setText(avgWriteScore);
        });

        viewModel.errorMessage.observe(this, message -> {
            binding.errorMessage.setVisibility(View.VISIBLE);
            binding.errorMessage.setText(message);
        });

        viewModel.progressBar.observe(this, isShow -> binding.loadingPB.setVisibility((isShow) ? View.VISIBLE : View.INVISIBLE));
    }
}