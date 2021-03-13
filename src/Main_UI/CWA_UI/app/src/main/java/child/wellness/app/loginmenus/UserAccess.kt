package child.wellness.app.loginmenus

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import child.wellness.app.R
import java.util.*

class UserAccess : Activity() {
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var ed1: EditText
    private lateinit var ed2: EditText
    private lateinit var tx1: TextView
    private var counter = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_access)
        b1 = findViewById<View>(R.id.button) as Button
        ed1 = findViewById<View>(R.id.editText) as EditText
        ed2 = findViewById<View>(R.id.editText2) as EditText
        b2 = findViewById<View>(R.id.button2) as Button
        tx1 = findViewById<View>(R.id.textView3) as TextView
        tx1.visibility = View.GONE

        showStartDialog()

        b1.setOnClickListener {
            if (ed1.text.toString() == "admin" && ed2.text.toString() == "admin") {
                Toast.makeText(
                        applicationContext,
                        "Redirecting...", Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(applicationContext, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                tx1.visibility = View.VISIBLE
                tx1.setBackgroundColor(Color.RED)
                counter--
                tx1.text = counter.toString()
                if (counter == 0) {
                    b1.isEnabled = false
                }
            }
        }
        b2.setOnClickListener { finish() }
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