package child.wellness.app.parentactivity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import child.wellness.app.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_parent_profile.*

class ParentProfileFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var profileView: View
    private lateinit var checkInNum: TextView
    private lateinit var checkInList: List<LogItem>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileView = inflater.inflate(R.layout.fragment_parent_profile, container, false)
        checkInNum = profileView.findViewById(R.id.checkin_number)
        database = FirebaseDatabase.getInstance().reference
        recyclerView = profileView.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        generateLogList()



        return profileView
    }


    private fun generateLogList() {

        var size = 0
        val list = ArrayList<LogItem>()
        var imageID = 0
        lateinit var feeling : String
        lateinit var date: String

        database.child("checkInNum").get().addOnSuccessListener {
            checkInNum.text = it.value.toString()
            size = it.value.toString().toInt()

            for (i in 0 until size){
                var id = "activity" + i.toString()
                list.add(LogItem( null, null, null))

                database.child(id).child("emoticonId").get().addOnSuccessListener {
                    imageID = it.value.toString().toInt()
                    list[i].imageResource = imageID

                    database.child(id).child("feeling").get().addOnSuccessListener {
                        feeling = it.value.toString()
                        list[i].feeling = feeling

                        database.child(id).child("date").get().addOnSuccessListener {
                            date = it.value.toString()
                            list[i].date = date

                            recyclerView.adapter = LogAdapter(list)

                        }
                    }
                }
            }
        }

        recyclerView.adapter = LogAdapter(list)


    }

}