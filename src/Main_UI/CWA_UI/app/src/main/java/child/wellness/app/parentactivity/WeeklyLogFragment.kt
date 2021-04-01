package child.wellness.app.parentactivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import child.wellness.app.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class WeeklyLogFragment : Fragment() {

    private lateinit var logView : View
    private lateinit var todayRecyclerView: RecyclerView
    private lateinit var yesterdayRecyclerView: RecyclerView
    private lateinit var weekRecyclerView: RecyclerView
    private lateinit var activitesDbInfo: DatabaseReference
    private lateinit var usersDbInfo: DatabaseReference
    private val userID: String = Firebase.auth.currentUser.uid
    private val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH)
    private val currentDay = LocalDate.now().format(formatter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        logView = inflater.inflate(R.layout.fragment_weekly_log, container, false)
        activitesDbInfo = FirebaseDatabase.getInstance().reference.child("Activities")
        usersDbInfo = FirebaseDatabase.getInstance().reference.child("User")
        todayRecyclerView = logView.findViewById(R.id.today_recycler_view)
        yesterdayRecyclerView = logView.findViewById(R.id.yesterday_recycler_view)
        weekRecyclerView = logView.findViewById(R.id.week_recycler_view)

        todayRecyclerView.layoutManager = LinearLayoutManager(activity)
        yesterdayRecyclerView.layoutManager = LinearLayoutManager(activity)
        weekRecyclerView.layoutManager = LinearLayoutManager(activity)

        generateLogList()

        return logView
    }

    private fun generateLogList() {

        var size: Int
        val list = ArrayList<LogItem>()
        val todayList = ArrayList<LogItem>()
        val yestList = ArrayList<LogItem>()
        val weekList = ArrayList<LogItem>()
        var imageID: Int
        lateinit var feeling : String
        lateinit var date: String


        activitesDbInfo.child(userID).child("checkInNum").get().addOnSuccessListener { it ->
            size = it.value.toString().toInt()

            for (i in 0 until size){
                val id = "activity$i"
                list.add(LogItem(null, null, null))

                activitesDbInfo.child(userID).child(id).child("emoticonId").get().addOnSuccessListener {
                    imageID = it.value.toString().toInt()
                    list[i].imageResource = imageID

                    activitesDbInfo.child(userID).child(id).child("feeling").get().addOnSuccessListener {
                        feeling = it.value.toString()
                        list[i].feeling = feeling

                        activitesDbInfo.child(userID).child(id).child("date").get().addOnSuccessListener {
                            date = it.value.toString()
                            list[i].date = date

                            // change string date into actual date
                            val local = LocalDate.parse(date, formatter)
                            val toDate = Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant())

                            // display list of activities that were made today
                            if(list[i].date?.contains(currentDay) == true){
                                todayList.add(list[i])
                                todayRecyclerView.adapter = LogAdapter(todayList)
                            }

                            // get yesterday's date
                            val yesterday = getYesterdayDate()

                            // display list of activities that were made yesterday
                            if(list[i].date.equals(yesterday)){
                                yestList.add(list[i])
                                yesterdayRecyclerView.adapter = LogAdapter(yestList)
                            }

                            // display list of activities that were made this week
                            if(isDateInCurrentWeek(toDate)){
                                weekList.add(list[i])
                                weekRecyclerView.adapter = LogAdapter(weekList)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun isDateInCurrentWeek(date: Date): Boolean {
        val currentCalendar = Calendar.getInstance()
        val week = currentCalendar[Calendar.WEEK_OF_YEAR]
        val year = currentCalendar[Calendar.YEAR]
        val targetCalendar = Calendar.getInstance()
        targetCalendar.time = date
        val targetWeek = targetCalendar[Calendar.WEEK_OF_YEAR]
        val targetYear = targetCalendar[Calendar.YEAR]
        return week == targetWeek && year == targetYear
    }

    @SuppressLint("SimpleDateFormat")
    private fun getYesterdayDate() : String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val yesterday = cal.time
        return SimpleDateFormat("EEEE, MMMM dd, yyyy").format(yesterday)
    }


}