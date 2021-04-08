package child.wellness.app.parentactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import child.wellness.app.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

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

    private lateinit var userDbInfo: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var currentUser: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_parent_settings, container, false)

        userDbInfo = FirebaseDatabase.getInstance().reference.child("User")
        auth = Firebase.auth
        currentUser = auth.currentUser

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


        // Retrieves the user's information from the database and displays it in the settings fragment
        userDbInfo.child(currentUser.uid).child("child_name").get().addOnSuccessListener {
            childNameTextView.text = it.value.toString()
        }
        userDbInfo.child(currentUser.uid).child("child_phone_number").get().addOnSuccessListener {
            childPhoneNoTextView.text = it.value.toString()
        }
        userDbInfo.child(currentUser.uid).child("parent_name").get().addOnSuccessListener {
            parentNameTextView.text = it.value.toString()
        }
        userDbInfo.child(currentUser.uid).child("parent_phone_number").get().addOnSuccessListener {
            parentPhoneNoTextView.text = it.value.toString()
        }

        return view
    }

    companion object;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // When a user wants the edit their information, the edit button can clicked, the information
        // can be edited and it will be sent back to the database when the done button is clicked.

        childEditButton.setOnClickListener {
            childEditButton.visibility = View.INVISIBLE
            childEditButton.isClickable = false
            childNameTextView.visibility = View.INVISIBLE
            childNameEditText.visibility = View.VISIBLE
            childNameEditText.isActivated = true
            childDoneButton.visibility = View.VISIBLE
            childDoneButton.isClickable = true
            childNameEditText.setText(childNameTextView.text)
        }

        childDoneButton.setOnClickListener {
            childEditButton.visibility = View.VISIBLE
            childEditButton.isClickable = true
            childNameTextView.visibility = View.VISIBLE
            childNameEditText.visibility = View.INVISIBLE
            childNameEditText.isActivated = false
            childDoneButton.visibility = View.INVISIBLE
            childDoneButton.isClickable = false

            changeChildName()
        }

        childPhoneEditButton.setOnClickListener {
            childPhoneEditButton.visibility = View.INVISIBLE
            childPhoneEditButton.isClickable = false
            childPhoneNoTextView.visibility = View.INVISIBLE
            childPhoneEditText.visibility = View.VISIBLE
            childPhoneEditText.isActivated = true
            childPhoneDoneButton.visibility = View.VISIBLE
            childPhoneDoneButton.isClickable = true
            childPhoneEditText.setText(childPhoneNoTextView.text)
        }

        childPhoneDoneButton.setOnClickListener {
            childPhoneEditButton.visibility = View.VISIBLE
            childPhoneEditButton.isClickable = true
            childPhoneNoTextView.visibility = View.VISIBLE
            childPhoneEditText.visibility = View.INVISIBLE
            childPhoneEditText.isActivated = false
            childPhoneDoneButton.visibility = View.INVISIBLE
            childPhoneDoneButton.isClickable = false

            changeChildPhoneNumber()
        }





        parentEditButton.setOnClickListener {
            parentEditButton.visibility = View.INVISIBLE
            parentEditButton.isClickable = false
            parentNameTextView.visibility = View.INVISIBLE
            parentNameEditText.visibility = View.VISIBLE
            parentNameEditText.isActivated = true
            parentDoneButton.visibility = View.VISIBLE
            parentDoneButton.isClickable = true
            parentNameEditText.setText(parentNameTextView.text)
        }

        parentDoneButton.setOnClickListener {
            parentEditButton.visibility = View.VISIBLE
            parentEditButton.isClickable = true
            parentNameTextView.visibility = View.VISIBLE
            parentNameEditText.visibility = View.INVISIBLE
            parentNameEditText.isActivated = false
            parentDoneButton.visibility = View.INVISIBLE
            parentDoneButton.isClickable = false

            changeParentName()
        }

        parentPhoneEditButton.setOnClickListener {
            parentPhoneEditButton.visibility = View.INVISIBLE
            parentPhoneEditButton.isClickable = false
            parentPhoneNoTextView.visibility = View.INVISIBLE
            parentPhoneEditText.visibility = View.VISIBLE
            parentPhoneEditText.isActivated = true
            parentPhoneDoneButton.visibility = View.VISIBLE
            parentPhoneDoneButton.isClickable = true
            parentPhoneEditText.setText(parentPhoneNoTextView.text)
        }

        parentPhoneDoneButton.setOnClickListener {
            parentPhoneEditButton.visibility = View.VISIBLE
            parentPhoneEditButton.isClickable = true
            parentPhoneNoTextView.visibility = View.VISIBLE
            parentPhoneEditText.visibility = View.INVISIBLE
            parentPhoneEditText.isActivated = false
            parentPhoneDoneButton.visibility = View.INVISIBLE
            parentPhoneDoneButton.isClickable = false

            changeParentPhoneNumber()
        }


    }

    // functions that change the displays on the UI

    private fun changeChildName(){
        userDbInfo.child(currentUser.uid).child("child_name").setValue(childNameEditText.text.toString())
        childNameTextView.text = childNameEditText.text
        childNameEditText.setText("")

    }

    private fun changeChildPhoneNumber(){
        userDbInfo.child(currentUser.uid).child("child_phone_number").setValue(childPhoneEditText.text.toString())
        childPhoneNoTextView.text = childPhoneEditText.text
        childPhoneEditText.setText("")
    }


    private fun changeParentName(){
        userDbInfo.child(currentUser.uid).child("parent_name").setValue(parentNameEditText.text.toString())
        parentNameTextView.text = parentNameEditText.text
        parentNameEditText.setText("")

    }

    private fun changeParentPhoneNumber(){
        userDbInfo.child(currentUser.uid).child("parent_phone_number").setValue(parentPhoneEditText.text.toString())
        parentPhoneNoTextView.text = parentPhoneEditText.text
        parentPhoneEditText.setText("")
    }
}