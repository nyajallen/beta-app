package child.wellness.app.loginmenus

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import child.wellness.app.R
import child.wellness.app.parentactivity.ParentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class UserAccess : Activity() {
    private lateinit var loginButton: Button
    private lateinit var cancelButton: Button
    private lateinit var ed1: EditText
    private lateinit var ed2: EditText
    private lateinit var tx1: TextView
    private var auth: FirebaseAuth = Firebase.auth
    lateinit var user: FirebaseUser
    private var counter = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_access)
        loginButton = findViewById<View>(R.id.button) as Button
        ed1 = findViewById<View>(R.id.editText) as EditText
        ed2 = findViewById<View>(R.id.editText2) as EditText
        cancelButton = findViewById<View>(R.id.button2) as Button
        tx1 = findViewById<View>(R.id.textView3) as TextView
        tx1.visibility = View.GONE


        showStartDialog()

        loginButton.setOnClickListener {
            if (ed1.text.toString() == "admin" && ed2.text.toString() == "admin") {
                Toast.makeText(
                        applicationContext,
                        "Redirecting...", Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@UserAccess, RegisterActivity::class.java)
                startActivity(intent)
            }
            else {
                auth.signInWithEmailAndPassword(ed1.text.toString(), ed2.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            user = auth.currentUser
                            val intent = Intent(this@UserAccess, ParentActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        cancelButton.setOnClickListener { finish() }
    }
    private fun showStartDialog() {
        AlertDialog.Builder(this)
                .setTitle("Welcome to CWA!")
                .setMessage("Username and password is: \"admin\" ")
                .setPositiveButton("ok") { dialog, which -> dialog.dismiss() }
                .create().show()
    }

    //Not called by anything right now but may be used to generate a random password
    private fun generateString(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789".toCharArray()
        val stringBuilder = StringBuilder()
        val random = Random()
        for (i in 0 until length) {
            val c = chars[random.nextInt(chars.size)]
            stringBuilder.append(c)
        }
        return stringBuilder.toString()
    }
}