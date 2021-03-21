package child.wellness.app.parentactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import child.wellness.app.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.log_item.view.*

class LogAdapter(private val logList: List<LogItem>) : RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.log_item,
        parent, false)

        return LogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        val currentItem = logList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.feelingView.text = currentItem.feeling
        holder.dateView.text = currentItem.date
    }

    override fun getItemCount() = logList.size

    class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.log_image
        val feelingView: TextView = itemView.log_feeling
        val dateView: TextView = itemView.log_date
    }
}