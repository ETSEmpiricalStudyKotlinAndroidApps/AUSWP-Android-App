package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.tracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.WorkoutItemBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.tracker.Workout

class WorkoutAdapter (
        private val context: Context?):
RecyclerView.Adapter<WorkoutAdapter.ViewHolder>(){

    private var dataSet: MutableList<Workout> = mutableListOf()
    var clickListener: View.OnClickListener? = null

    inner class ViewHolder(
            itemView: View,
            val nameView: TextView,
            val dateView: TextView,
            val statsView: TextView
    ):RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(workout:Workout){
            nameView.text = workout.activity
            dateView.text = workout.date
            statsView.text = workout.set
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val workoutItemBinding = WorkoutItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
                workoutItemBinding.wordList,
                workoutItemBinding.activityName,
                workoutItemBinding.date,
                workoutItemBinding.stats

        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])
    }


    override fun getItemCount(): Int = dataSet.size

    fun changeDataSet( dataSet: MutableList<Workout>){
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }
}