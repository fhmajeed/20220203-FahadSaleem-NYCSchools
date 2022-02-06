package com.a20220203_fahadsaleem_nycschools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a20220203_fahadsaleem_nycschools.databinding.SchoolListBinding
import com.a20220203_fahadsaleem_nycschools.model.School

class SchoolListAdapterV2(
    private val list: ArrayList<School>,
    private val onClick: (school: School) -> Unit = {}
) : RecyclerView.Adapter<SchoolListAdapterV2.DataViewHolder>() {

    class DataViewHolder(private val binding: SchoolListBinding, private val onClick: (school: School) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(school: School) {
            with(binding) {
                schoolNameTV.text = school.schoolName
                emailTV.text = school.schoolEmail
                phoneNumberTV.text = school.phoneNumber
            }

            binding.root.setOnClickListener {
                onClick(school)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            SchoolListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onClick
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addSchools(list: List<School>) {
        this.list.apply {
            clear()
            addAll(list)
        }

    }
}