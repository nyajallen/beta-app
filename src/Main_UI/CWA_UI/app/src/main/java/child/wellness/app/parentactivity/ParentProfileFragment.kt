package child.wellness.app.parentactivity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import child.wellness.app.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_parent_profile.*
import org.w3c.dom.Text
import java.util.concurrent.ArrayBlockingQueue

class ParentProfileFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var profileView: View
    private lateinit var checkInNum: TextView
    private lateinit var checkInList: List<LogItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileView = inflater.inflate(R.layout.fragment_parent_profile, container, false)
        checkInNum = profileView.findViewById(R.id.checkin_number)
        database = FirebaseDatabase.getInstance().reference

        Log.d("DEBUG", "OnCreate")

        return profileView
    }

    override fun onStart() {
        super.onStart()
        checkInList = generateLogList()
        recycler_view.adapter = LogAdapter(checkInList)
        recycler_view.layoutManager = LinearLayoutManager(activity)
        Log.d("DEBUG", "Is this called afte onCreate?")
    }


    private fun generateLogList(): List<LogItem> {

        var size = 0;
        database.child("checkInNum").get().addOnSuccessListener {
            checkInNum.text = it.value.toString()
            size = it.value.toString().toInt()
        }
        Log.d("DEBUG", size.toString())

        val list = ArrayList<LogItem>()
        var imageID = 0
        lateinit var feeling : String
        lateinit var date: String

        for(i in 0 until 7) {
            var id = "activity" + i.toString()

            database.child(id).child("emoticonId").get().addOnSuccessListener {
                    imageID = it.value.toString().toInt()
                    Log.d("DEBUG", imageID.toString())
            }

            database.child(id).child("feeling").get().addOnSuccessListener {
                    feeling = it.value.toString()
                    Log.d("DEBUG", feeling)
            }
            database.child(id).child("date").get().addOnSuccessListener {
                    date = it.value.toString()
                    Log.d("DEBUG", date)
            }

            val item = LogItem(imageID, feeling, date)
            Log.d("DEBUG", item.toString())
            list += item
        }

        return list
    }

}