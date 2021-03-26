package child.wellness.app.loginmenus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import child.wellness.app.R
import child.wellness.app.database.UserInfo
import child.wellness.app.parentactivity.ParentActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*


private lateinit var registerButton: Button
private lateinit var usernameInput: EditText
private lateinit var passwordInput: EditText
private lateinit var parentNameInput: EditText
private lateinit var parentPhoneInput: EditText
private lateinit var childNameInput: EditText
private lateinit var childPhoneInput: EditText

private lateinit var userDbInfo: DatabaseReference;


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




        userDbInfo = FirebaseDatabase.getInstance().getReference().child("User")


        fun registerUserData(){
            val username : String = usernameInput.getText().toString();
            val password : String = passwordInput.getText().toString();
            val parentname : String = parentNameInput.getText().toString();
            val parentphone : String = parentPhoneInput.getText().toString();
            val childname : String = childNameInput.getText().toString();
            val childphone : String = childPhoneInput.getText().toString();

            val user: UserInfo = UserInfo(parentname, parentphone, childname, childphone, username, password);
            userDbInfo.push().setValue(user);

        }

        registerButton.setOnClickListener { view: View ->

            if (usernameInput.text.isEmpty() || passwordInput.text.isEmpty() || parentNameInput.text.isEmpty() || parentPhoneInput.text.isEmpty() || childNameInput.text.isEmpty() || childPhoneInput.text.isEmpty())
            {
                Toast.makeText(this, "Please fill out all boxes...", Toast.LENGTH_SHORT).show()
            }
            else if (!TextUtils.isDigitsOnly(parentPhoneInput.text) || !TextUtils.isDigitsOnly(childPhoneInput.text) )
            {
                Toast.makeText(this, "Input numbers only for phone numbers...", Toast.LENGTH_SHORT).show()
            }
            else
            {
                registerUserData();
            }

        }


    }

}