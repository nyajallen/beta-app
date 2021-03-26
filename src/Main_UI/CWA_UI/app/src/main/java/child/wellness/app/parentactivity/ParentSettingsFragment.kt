package child.wellness.app.parentactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import child.wellness.app.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ParentSettingsFragment : Fragment() {

    private lateinit var childNameTextView: TextView
    private lateinit var childPhoneNoTextView: TextView
    private lateinit var childEditButton: ImageButton
    private lateinit var childPhoneEditButton: ImageButton
    private lateinit var childNameEditText: EditText
    private lateinit var childPhoneEditText: EditText
    private lateinit var childDoneButton: Button
    private lateinit var childPhoneDoneButton: Button

    private lateinit var parentNameTextView: TextView
    private lateinit var parentPhoneNoTextView: TextView
    private lateinit var parentEditButton: ImageButton
    private lateinit var parentPhoneEditButton: ImageButton
    private lateinit var parentNameEditText: EditText
    private lateinit var parentPhoneEditText: EditText
    private lateinit var parentDoneButton: Button
    private lateinit var parentPhoneDoneButton: Button

    private lateinit var userDbInfo: DatabaseReference;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_parent_settings, container, false)

        userDbInfo = FirebaseDatabase.getInstance().getReference().child("User")

        childNameTextView = view.findViewById(R.id.child_name_text)
        childPhoneNoTextView = view.findViewById(R.id.child_number_text)
        childEditButton = view.findViewById(R.id.edit_button1)
        childPhoneEditButton = view.findViewById(R.id.edit_button2)
        childNameEditText = view.findViewById(R.id.child_edit)
        childPhoneEditText = view.findViewById(R.id.child_phone_edit)
        childDoneButton = view.findViewById(R.id.done_button1)
        childPhoneDoneButton = view.findViewById(R.id.done_button2)

        parentNameTextView = view.findViewById(R.id.parent_name_text)
        parentPhoneNoTextView = view.findViewById(R.id.parent_number_text)
        parentEditButton = view.findViewById(R.id.edit_button3)
        parentPhoneEditButton = view.findViewById(R.id.edit_button4)
        parentNameEditText = view.findViewById(R.id.parent_edit)
        parentPhoneEditText = view.findViewById(R.id.parent_phone_edit)
        parentDoneButton = view.findViewById(R.id.done_button3)
        parentPhoneDoneButton = view.findViewById(R.id.done_button4)

        return view
    }

    companion object {
        fun newInstance(): ParentSettingsFragment {
            return ParentSettingsFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childEditButton.setOnClickListener { view: View ->
            //function goes here
            childEditButton.setVisibility(View.INVISIBLE)
            childEditButton.isClickable = false
            childNameTextView.setVisibility(View.INVISIBLE)
            childNameEditText.setVisibility(View.VISIBLE)
            childNameEditText.isActivated = true
            childDoneButton.setVisibility(View.VISIBLE)
            childDoneButton.isClickable = true
            childNameEditText.setText(childNameTextView.text)
        }

        childDoneButton.setOnClickListener { view: View ->
            childEditButton.setVisibility(View.VISIBLE)
            childEditButton.isClickable = true
            childNameTextView.setVisibility(View.VISIBLE)
            childNameEditText.setVisibility(View.INVISIBLE)
            childNameEditText.isActivated = false
            childDoneButton.setVisibility(View.INVISIBLE)
            childDoneButton.isClickable = false

            changeChildName()
        }

        childPhoneEditButton.setOnClickListener { view: View ->
            //function goes here
            childPhoneEditButton.setVisibility(View.INVISIBLE)
            childPhoneEditButton.isClickable = false
            childPhoneNoTextView.setVisibility(View.INVISIBLE)
            childPhoneEditText.setVisibility(View.VISIBLE)
            childPhoneEditText.isActivated = true
            childPhoneDoneButton.setVisibility(View.VISIBLE)
            childPhoneDoneButton.isClickable = true
            childPhoneEditText.setText(childPhoneNoTextView.text)
        }

        childPhoneDoneButton.setOnClickListener { view: View ->
            childPhoneEditButton.setVisibility(View.VISIBLE)
            childPhoneEditButton.isClickable = true
            childPhoneNoTextView.setVisibility(View.VISIBLE)
            childPhoneEditText.setVisibility(View.INVISIBLE)
            childPhoneEditText.isActivated = false
            childPhoneDoneButton.setVisibility(View.INVISIBLE)
            childPhoneDoneButton.isClickable = false

            changeChildPhoneNumber()
        }





        parentEditButton.setOnClickListener { view: View ->
            //function goes here
            parentEditButton.setVisibility(View.INVISIBLE)
            parentEditButton.isClickable = false
            parentNameTextView.setVisibility(View.INVISIBLE)
            parentNameEditText.setVisibility(View.VISIBLE)
            parentNameEditText.isActivated = true
            parentDoneButton.setVisibility(View.VISIBLE)
            parentDoneButton.isClickable = true
            parentNameEditText.setText(parentNameTextView.text)
        }

        parentDoneButton.setOnClickListener { view: View ->
            parentEditButton.setVisibility(View.VISIBLE)
            parentEditButton.isClickable = true
            parentNameTextView.setVisibility(View.VISIBLE)
            parentNameEditText.setVisibility(View.INVISIBLE)
            parentNameEditText.isActivated = false
            parentDoneButton.setVisibility(View.INVISIBLE)
            parentDoneButton.isClickable = false

            changeParentName()
        }

        parentPhoneEditButton.setOnClickListener { view: View ->
            //function goes here
            parentPhoneEditButton.setVisibility(View.INVISIBLE)
            parentPhoneEditButton.isClickable = false
            parentPhoneNoTextView.setVisibility(View.INVISIBLE)
            parentPhoneEditText.setVisibility(View.VISIBLE)
            parentPhoneEditText.isActivated = true
            parentPhoneDoneButton.setVisibility(View.VISIBLE)
            parentPhoneDoneButton.isClickable = true
            parentPhoneEditText.setText(parentPhoneNoTextView.text)
        }

        parentPhoneDoneButton.setOnClickListener { view: View ->
            parentPhoneEditButton.setVisibility(View.VISIBLE)
            parentPhoneEditButton.isClickable = true
            parentPhoneNoTextView.setVisibility(View.VISIBLE)
            parentPhoneEditText.setVisibility(View.INVISIBLE)
            parentPhoneEditText.isActivated = false
            parentPhoneDoneButton.setVisibility(View.INVISIBLE)
            parentPhoneDoneButton.isClickable = false

            changeParentPhoneNumber()
        }


    }

    private fun changeChildName(){
        childNameTextView.setText(childNameEditText.text)
        childNameEditText.setText("")

    }

    private fun changeChildPhoneNumber(){
        childPhoneNoTextView.setText(childPhoneEditText.text)
        childPhoneEditText.setText("")
    }


    private fun changeParentName(){
        parentNameTextView.setText(parentNameEditText.text)
        parentNameEditText.setText("")

    }

    private fun changeParentPhoneNumber(){
        parentPhoneNoTextView.setText(parentPhoneEditText.text)
        parentPhoneEditText.setText("")
    }
}