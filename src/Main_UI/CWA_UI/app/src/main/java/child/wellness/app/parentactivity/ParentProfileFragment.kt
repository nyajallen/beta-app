package child.wellness.app.parentactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import child.wellness.app.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ParentProfileFragment : Fragment() {

    private lateinit var activitesDbInfo: DatabaseReference
    private lateinit var usersDbInfo: DatabaseReference
    private lateinit var profileView: View
    private lateinit var checkInNum: TextView
    private lateinit var childName: TextView
    private lateinit var recyclerView: RecyclerView
    private val userID: String = Firebase.auth.currentUser.uid


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileView = inflater.inflate(R.layout.fragment_parent_profile, container, false)
        checkInNum = profileView.findViewById(R.id.checkin_number)
        childName = profileView.findViewById(R.id.child_name_profile)
        activitesDbInfo = FirebaseDatabase.getInstance().reference.child("Activities")
        usersDbInfo = FirebaseDatabase.getInstance().reference.child("User")
        recyclerView = profileView.findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        generateLogList()



        return profileView
    }


    // Creates the RecyclerView log to display check-ins
    private fun generateLogList() {

        var size: Int
        val list = ArrayList<LogItem>()
        var imageID: Int
        lateinit var feeling : String
        lateinit var date: String

        // Retrieves information for every activity in the database under that child
        // This contains nested listeners in order to obtain all information that is needed
        // for the check-in log. Values cannot be used outside its listener which is why listeners
        // are nested.
        activitesDbInfo.child(userID).child("checkInNum").get().addOnSuccessListener { it ->
            checkInNum.text = it.value.toString()
            size = it.value.toString().toInt()

            for (i in 0 until size){
                val id = "activity$i"
                list.add(LogItem( null, null, null))

                activitesDbInfo.child(userID).child(id).child("emoticonId").get().addOnSuccessListener {
                    imageID = it.value.toString().toInt()
                    list[i].imageResource = imageID

                    activitesDbInfo.child(userID).child(id).child("feeling").get().addOnSuccessListener {
                        feeling = it.value.toString()
                        list[i].feeling = feeling

                        activitesDbInfo.child(userID).child(id).child("date").get().addOnSuccessListener {
                            date = it.value.toString()
                            list[i].date = date

                            recyclerView.adapter = LogAdapter(list)

                        }
                    }
                }
            }
        }

        // Displays child name on fragment
        usersDbInfo.child(userID).child("child_name").get().addOnSuccessListener {
            childName.text = it.value.toString()
        }
    }

}