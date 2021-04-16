/**
 * ViewHolder which binds the data from the
 * database to the elements making up the
 * firebase recyclerView to allow them to
 * be viewed within the app.
 * @author Callum Robert Binner
 * @version 1
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.ui.events

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.ac.aber.dcs.cs39440.auswpandroidapp.R

class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.titleWord)
    var date: TextView = itemView.findViewById(R.id.dateWord)
    var location: TextView = itemView.findViewById(R.id.locationWord)
    var time: TextView = itemView.findViewById(R.id.timeWord)

    fun bind(event: Event) {
        title.text = event.Title
        date.text = event.Date
        location.text = event.Location
        time.text = event.Time
    }

}
