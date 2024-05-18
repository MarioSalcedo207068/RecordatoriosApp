package salcedo.mario.recordatoriosapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ReminderAdapter(options: FirestoreRecyclerOptions<Reminder>) :
    FirestoreRecyclerAdapter<Reminder, ReminderAdapter.ReminderViewHolder>(options) {

    class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int, model: Reminder) {
        holder.textViewDescription.text = model.description
        holder.textViewDate.text = model.date
    }
}