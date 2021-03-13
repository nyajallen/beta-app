package child.wellness.app.parentactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import child.wellness.app.R

class ParentSettingsFragment : Fragment() {

    private lateinit var childNameTextView: TextView
    private lateinit var phoneNoTextView: TextView
    private lateinit var childEditButton: ImageButton
    private lateinit var phoneEditButton: ImageButton
    private lateinit var childNameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var childDoneButton: Button
    private lateinit var phoneDoneButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_parent_settings, container, false)
        childNameTextView = view.findViewById(R.id.child_name_text)
        phoneNoTextView = view.findViewById(R.id.phone_number_text)
        childEditButton = view.findViewById(R.id.edit_button1)
        phoneEditButton = view.findViewById(R.id.edit_button2)
        childNameEditText = view.findViewById(R.id.child_edit)
        phoneEditText = view.findViewById(R.id.phone_edit)
        childDoneButton = view.findViewById(R.id.done_button1)
        phoneDoneButton = view.findViewById(R.id.done_button2)

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

        phoneEditButton.setOnClickListener { view: View ->
            //function goes here
            phoneEditButton.setVisibility(View.INVISIBLE)
            phoneEditButton.isClickable = false
            phoneNoTextView.setVisibility(View.INVISIBLE)
            phoneEditText.setVisibility(View.VISIBLE)
            phoneEditText.isActivated = true
            phoneDoneButton.setVisibility(View.VISIBLE)
            phoneDoneButton.isClickable = true
            phoneEditText.setText(phoneNoTextView.text)
        }

        phoneDoneButton.setOnClickListener { view: View ->
            phoneEditButton.setVisibility(View.VISIBLE)
            phoneEditButton.isClickable = true
            phoneNoTextView.setVisibility(View.VISIBLE)
            phoneEditText.setVisibility(View.INVISIBLE)
            phoneEditText.isActivated = false
            phoneDoneButton.setVisibility(View.INVISIBLE)
            phoneDoneButton.isClickable = false

            changePhoneNumber()
        }
    }

    private fun changeChildName(){
        childNameTextView.setText(childNameEditText.text)
        childNameEditText.setText("")

    }

    private fun changePhoneNumber(){
        phoneNoTextView.setText(phoneEditText.text)
        phoneEditText.setText("")
    }
}