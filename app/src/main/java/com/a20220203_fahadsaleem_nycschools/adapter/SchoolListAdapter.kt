package com.a20220203_fahadsaleem_nycschools.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a20220203_fahadsaleem_nycschools.databinding.SchoolListBinding
import com.a20220203_fahadsaleem_nycschools.model.School

/**
 * School list adapter is used to hold list item and display list on a recycler view also handled click event on each item.
 */
class SchoolListAdapter(
    private val list: ArrayList<School>,
    private val onClick: (school: School) -> Unit = {} /** passing onclick function here **/
) : RecyclerView.Adapter<SchoolListAdapter.DataViewHolder>() {

    /**
     * ViewHolder pattern for smooth scrolling and optimize memory performance.
     */
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

    /**
     * Can be used to add school list here.
     */
    fun addSchools(list: List<School>) {
        this.list.apply {
            clear()
            addAll(list)
        }

    }
}