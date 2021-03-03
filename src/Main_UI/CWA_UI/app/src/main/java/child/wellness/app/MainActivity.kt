package child.wellness.app

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var callButton: Button
    private lateinit var textButton: Button
    private lateinit var sendButton: Button
    private lateinit var cancelButton: Button
    private lateinit var emergencyButton: Button
    private lateinit var happyButton: ImageButton
    private lateinit var sadButton: ImageButton
    private lateinit var madButton: ImageButton
    private lateinit var sickButton: ImageButton
    private lateinit var hurtButton: ImageButton
    private lateinit var checkInTextView: TextView
    private lateinit var textInput: EditText
    private var phoneNumber = "555-555-5555"


    private fun sendSMSTextInput() {
        val scAddress: String? = null
        val sms: String = textInput.getText().toString()
        val smsIntent = Intent(Intent.ACTION_SENDTO)
        smsIntent.setData(Uri.parse(phoneNumber))
        val sentIntent: PendingIntent? = null
        val deliveryIntent: PendingIntent? = null
        val smsManager: SmsManager = SmsManager.getDefault()

        smsManager.sendTextMessage(
            phoneNumber, scAddress, sms,
            sentIntent, deliveryIntent)
    }

    private fun sendSMSEmoticon(string: String) {
        val scAddress: String? = null
        val sms: String = string
        val smsIntent = Intent(Intent.ACTION_SENDTO)
        smsIntent.setData(Uri.parse(phoneNumber))
        val sentIntent: PendingIntent? = null
        val deliveryIntent: PendingIntent? = null
        val smsManager: SmsManager = SmsManager.getDefault()

        smsManager.sendTextMessage(
            phoneNumber, scAddress, sms,
            sentIntent, deliveryIntent)
    }


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
        textInput = findViewById(R.id.text_input)
        sendButton = findViewById(R.id.send_btn)
        cancelButton = findViewById(R.id.cancel_btn)
        checkInTextView = findViewById(R.id.check_in)



        callButton.setOnClickListener { view: View ->
            //function goes here
        }

        textButton.setOnClickListener { view: View ->
            //function goes here
            textButton.setVisibility(View.GONE);
            textInput.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
            //Start SMS_Messenger activity
            //val intent = Intent(this, SMS_Messenger::class.java)
            //startActivity(intent)
        }

        cancelButton.setOnClickListener { view: View ->
            textButton.setVisibility(View.VISIBLE);
            textInput.setVisibility(View.GONE);
            sendButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
        }

        sendButton.setOnClickListener { view: View ->
            if (textInput.text.isEmpty()) {
                Toast.makeText(this, "No text entered", Toast.LENGTH_SHORT).show()
            }
            else{
                sendSMSTextInput()
                textButton.setVisibility(View.VISIBLE);
                textInput.setVisibility(View.GONE);
                sendButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);
                Toast.makeText(this,"Text Successfully Sent...", Toast.LENGTH_LONG).show()
                textInput.setText("")
            }
        }

        emergencyButton.setOnClickListener { view: View ->
            //function goes here
        }

        happyButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Happy!")
            Toast.makeText(this,"HAPPY EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        sadButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Sad!")
            Toast.makeText(this,"SAD EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        madButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Mad!")
            Toast.makeText(this,"MAD EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        sickButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Sick!")
            Toast.makeText(this,"SICK EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        hurtButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Hurt!")
            Toast.makeText(this,"HURT EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        checkInTextView.setOnClickListener { view: View ->
            //function goes here
        }





    }
}
