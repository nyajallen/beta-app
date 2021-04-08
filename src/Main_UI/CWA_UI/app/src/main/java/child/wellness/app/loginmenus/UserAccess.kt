package child.wellness.app.loginmenus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import child.wellness.app.R
import child.wellness.app.parentactivity.ParentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_user_access.*
import java.util.*

class UserAccess : Activity() {
    private lateinit var loginButton: Button
    private lateinit var cancelButton: Button
    private lateinit var registerButton: Button
    private lateinit var ed1: EditText
    private lateinit var ed2: EditText
    private var auth: FirebaseAuth = Firebase.auth
    lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_access)
        loginButton = findViewById<View>(R.id.button) as Button
        ed1 = findViewById<View>(R.id.editText) as EditText
        ed2 = findViewById<View>(R.id.editText2) as EditText
        cancelButton = findViewById<View>(R.id.button2) as Button
        registerButton = findViewById<View>(R.id.button3) as Button


        registerButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Redirecting...", Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@UserAccess, RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
                auth.signInWithEmailAndPassword(ed1.text.toString(), ed2.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            user = auth.currentUser
                            val intent = Intent(this@UserAccess, ParentActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            task.addOnFailureListener {
                                if(it.message.equals("The password is invalid or the user does not have a password.")) {
                                    Toast.makeText(baseContext, "Invalid Password",
                                        Toast.LENGTH_SHORT).show()
                                }

                            }

                        }
                    }
        }

        cancelButton.setOnClickListener { finish() }
    }
}