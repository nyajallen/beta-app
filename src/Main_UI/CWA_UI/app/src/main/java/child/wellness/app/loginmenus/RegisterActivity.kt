package child.wellness.app.loginmenus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import child.wellness.app.R
import child.wellness.app.database.UserInfo
import child.wellness.app.database.childActivityNum
import child.wellness.app.parentactivity.ParentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


@SuppressLint("StaticFieldLeak")
private lateinit var registerButton: Button
@SuppressLint("StaticFieldLeak")
private lateinit var usernameInput: EditText
@SuppressLint("StaticFieldLeak")
private lateinit var passwordInput: EditText
@SuppressLint("StaticFieldLeak")
private lateinit var parentNameInput: EditText
@SuppressLint("StaticFieldLeak")
private lateinit var parentPhoneInput: EditText
@SuppressLint("StaticFieldLeak")
private lateinit var childNameInput: EditText
@SuppressLint("StaticFieldLeak")
private lateinit var childPhoneInput: EditText

private lateinit var userDbInfo: DatabaseReference
private lateinit var activitesDbInfo: DatabaseReference
var numUsers = 0
lateinit var auth: FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton = findViewById(R.id.register_button)
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        parentNameInput = findViewById(R.id.parentname_input)
        parentPhoneInput = findViewById(R.id.parentphone_input)
        childNameInput = findViewById(R.id.childname_input)
        childPhoneInput = findViewById(R.id.childphone_input)


        // Initialize Firebase Auth
        auth = Firebase.auth

        userDbInfo = FirebaseDatabase.getInstance().reference.child("User")
        activitesDbInfo = FirebaseDatabase.getInstance().reference.child("Activities")
        userDbInfo.child("numOfUsers").get().addOnSuccessListener {
            numUsers = it.value.toString().toInt()
        }


        fun registerUserData(){
            val username : String = usernameInput.text.toString()
            val password : String = passwordInput.text.toString()
            val parentname : String = parentNameInput.text.toString()
            val parentphone : String = parentPhoneInput.text.toString()
            val childname : String = childNameInput.text.toString()
            val childphone : String = childPhoneInput.text.toString()

            val user = UserInfo(parentname, parentphone, childname, childphone, username, password)
            val initialActivityNum: childActivityNum = childActivityNum()

            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val currentUser = auth.currentUser
                        userDbInfo.child(currentUser.uid).setValue(user)
                        numUsers ++
                        userDbInfo.child("numOfUsers").setValue(numUsers)
                        activitesDbInfo.child(currentUser.uid).setValue(initialActivityNum)
                        Toast.makeText(baseContext, "Welcome, " + user.parent_name,
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ParentActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        task.addOnFailureListener { Log.d("REGISTER", it.message) }
                    }
                }


        }


        registerButton.setOnClickListener {

            if (usernameInput.text.isEmpty() || passwordInput.text.isEmpty() || parentNameInput.text.isEmpty() || parentPhoneInput.text.isEmpty() || childNameInput.text.isEmpty() || childPhoneInput.text.isEmpty())
            {
                Toast.makeText(this, "Please fill out all boxes...", Toast.LENGTH_SHORT).show()
            }
            else if (!TextUtils.isDigitsOnly(parentPhoneInput.text) || !TextUtils.isDigitsOnly(childPhoneInput.text) )
            {
                Toast.makeText(this, "Input numbers only for phone numbers...", Toast.LENGTH_SHORT).show()
            }
            else if( !usernameInput.text.toString().contains("@"))
            {
                Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show()
            }
            else if(parentPhoneInput.text.length != 10 || childPhoneInput.text.length != 10)
            {
                Toast.makeText(this, "Enter valid phone numbers", Toast.LENGTH_SHORT).show()
            }
            else if (passwordInput.text.length < 6)
            {
                Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.fetchSignInMethodsForEmail(usernameInput.text.toString()).addOnCompleteListener {

                    if(it.result?.signInMethods?.isEmpty() == true){
                        registerUserData()
                    }
                    else {
                        Toast.makeText(this, "An account with this email already exists", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}