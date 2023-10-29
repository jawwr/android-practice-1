package com.example.learningkt.present.day

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learningkt.data.Day
import com.example.learningkt.data.Week
import com.example.learningkt.databinding.ScheduleItemDayBinding

class WeekAdapter : RecyclerView.Adapter<WeekAdapter.WeekViewHolder>() {
    private lateinit var week: Week
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ScheduleItemDayBinding.inflate(inflater, parent, false)
        return WeekViewHolder(binding)
    }

    override fun getItemCount(): Int = week.days.size

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind(week.days[position])
    }

    fun submitWeek(week: Week) {
        this.week = week
        notifyDataSetChanged()
    }

    class WeekViewHolder(
        private val binding: ScheduleItemDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Day) = with(binding) {
            val dayAdapter = DayAdapter()
            dayTitle.text = data.ofWeek.value
            dayLessonRecycler.apply {
                layoutManager = LinearLayoutManager(itemView.context)
                adapter = dayAdapter
                dayAdapter.submitList(data.lessons)
            }
        }
    }
}