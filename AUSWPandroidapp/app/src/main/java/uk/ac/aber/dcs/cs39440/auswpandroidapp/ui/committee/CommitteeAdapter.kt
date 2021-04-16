/**
 * Adapter to store the list of committee members
 * @author Callum Robert Binner
 * @version 1
 *
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.committee

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uk.ac.aber.dcs.cs39440.auswpandroidapp.databinding.CommitteeItemBinding
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee.Committee

class CommitteeAdapter(
    private val context: Context?,
) :
    RecyclerView.Adapter<CommitteeAdapter.ViewHolder>() {
    private var dataSet: MutableList<Committee> = mutableListOf()
    var clickListener: View.OnClickListener? = null

    inner class ViewHolder(
        itemView: View,
        val imageView: ImageView,
        val nameView: TextView,
        val positionView: TextView,
        val emailView: TextView
    ) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener(clickListener)
        }

        fun bindDataSet(committee: Committee) {
            nameView.text = committee.name
            positionView.text = committee.position
            emailView.text = committee.email

            Glide.with(context!!)
                .load(Uri.parse("file:///android_asset/images/${committee.imagePath}"))
                .into(imageView)


        }
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitteeAdapter.ViewHolder {
        val committeeItemBinding =
            CommitteeItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            committeeItemBinding.committeeCard,
            committeeItemBinding.committeeImageView,
            committeeItemBinding.committeeName,
            committeeItemBinding.committeePosition,
            committeeItemBinding.committeeEmail
        )
    }

    override fun onBindViewHolder(holder: CommitteeAdapter.ViewHolder, position: Int) {
        holder.bindDataSet(dataSet[position])
    }

    fun changeDataSet(dataSet: MutableList<Committee>) {
        this.dataSet = dataSet
        this.notifyDataSetChanged()
    }
}