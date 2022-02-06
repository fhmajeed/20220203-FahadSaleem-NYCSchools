package com.a20220203_fahadsaleem_nycschools.model;

import com.google.gson.annotations.SerializedName;

/**
 * School Detail Holder/Model
 */
public class SchoolDetail {

    @SerializedName("dbn")
    private String dbn;
    @SerializedName("school_name")
    private String schoolName;
    @SerializedName("num_of_sat_test_takers")
    private String totalSATTAKER;
    @SerializedName("sat_critical_reading_avg_score")
    private String readingSATScore;
    @SerializedName("sat_math_avg_score")
    private String mathSATScore;
    @SerializedName("sat_writing_avg_score")
    private String writingSATScore;

    public String getDbn() {
        return dbn;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getTotalSATTAKER() {
        return totalSATTAKER;
    }

    public String getReadingSATScore() {
        return readingSATScore;
    }

    public String getMathSATScore() {
        return mathSATScore;
    }

    public String getWritingSATScore() {
        return writingSATScore;
    }
}