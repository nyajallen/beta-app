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
import child.wellness.app.loginmenus.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_parent_profile.*

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
        activitesDbInfo = FirebaseDatabase.getInstance().getReference().child("Activities")
        usersDbInfo = FirebaseDatabase.getInstance().getReference().child("User")
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

        activitesDbInfo.child(userID).child("checkInNum").get().addOnSuccessListener {
            checkInNum.text = it.value.toString()
            size = it.value.toString().toInt()

            for (i in 0 until size){
                var id = "activity" + i.toString()
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

        usersDbInfo.child(userID).child("child_name").get().addOnSuccessListener {
            childName.setText(it.value.toString())
        }
    }

}