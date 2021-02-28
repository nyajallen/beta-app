package child.wellness.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var callButton: Button
    private lateinit var textButton: Button
    private lateinit var emergencyButton: Button
    private lateinit var happyButton: ImageButton
    private lateinit var sadButton: ImageButton
    private lateinit var madButton: ImageButton
    private lateinit var sickButton: ImageButton
    private lateinit var hurtButton: ImageButton
    private lateinit var checkInTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callButton = findViewById(R.id.call_btn)
        textButton = findViewById(R.id.text_btn)
        emergencyButton = findViewById(R.id.emergency_btn)
        happyButton = findViewById((R.id.happy_btn))
        sadButton = findViewById((R.id.sad_btn))
        madButton = findViewById((R.id.mad_btn))
        sickButton = findViewById((R.id.sick_btn))
        hurtButton = findViewById((R.id.injured_btn))
        checkInTextView = findViewById(R.id.check_in)



        callButton.setOnClickListener { view: View ->
            //function goes here
        }

        textButton.setOnClickListener { view: View ->
            //function goes here
            //Start SMS_Messenger activity
            val intent = Intent(this, SMS_Messenger::class.java)
            startActivity(intent)
        }

        emergencyButton.setOnClickListener { view: View ->
            //function goes here
        }

        happyButton.setOnClickListener { view: View ->
            //function goes here
        }

        sadButton.setOnClickListener { view: View ->
            //function goes here
        }

        madButton.setOnClickListener { view: View ->
            //function goes here
        }

        sickButton.setOnClickListener { view: View ->
            //function goes here
        }

        hurtButton.setOnClickListener { view: View ->
            //function goes here
        }

        checkInTextView.setOnClickListener { view: View ->
            //function goes here
        }





        }
    }
