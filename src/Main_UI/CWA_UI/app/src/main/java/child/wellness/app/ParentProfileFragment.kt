package child.wellness.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class ParentProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_parent_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val logList = generateLogItem()

        //recycler_view.adapter = LogAdapter(logList)
        //recycler_view.layoutManager = LinearLayoutManager(activity)
    }


    /*
    fun generateLogItem(): List<LogItem>{

        val list = ArrayList<LogItem>()

        val item = LogItem(imageView, feeling, date)
        list += item

        return list
    }
    */
}