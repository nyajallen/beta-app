package child.wellness.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ParentProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.fragment_parent_profile, container, false)
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