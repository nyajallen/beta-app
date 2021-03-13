package child.wellness.app.childactivity

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import child.wellness.app.R


class MainActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private val locationPermissionCode = 2
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
    private var phoneNumber = "tel:555-555-5555"



    private fun callParent() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1);
        val phoneIntent = Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse(phoneNumber));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "Error making call...", Toast.LENGTH_LONG).show()
            return
        }
        startActivity(phoneIntent);
    }


    private fun sendSMSTextInput() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1);
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
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1);
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


    @SuppressLint("MissingPermission")
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
            callParent();
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
            } else {
                sendSMSTextInput()
                textButton.setVisibility(View.VISIBLE);
                textInput.setVisibility(View.GONE);
                sendButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);
                Toast.makeText(this, "Text Successfully Sent...", Toast.LENGTH_LONG).show()
                textInput.setText("")
            }
        }

        emergencyButton.setOnClickListener { view: View ->
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1);


            var lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location: Location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)

            var longitude = location.longitude
            var latitude = location.latitude
            sendSMSEmoticon("I HAVE AN EMERGENCY!");
            sendSMSEmoticon("CHILD'S LOCATION http://www.google.com/maps/place/" + latitude.toString() + "," + longitude.toString());

        }

        happyButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Happy!")
            Toast.makeText(this, "HAPPY EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        sadButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Sad!")
            Toast.makeText(this, "SAD EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        madButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Mad!")
            Toast.makeText(this, "MAD EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        sickButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Sick!")
            Toast.makeText(this, "SICK EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        hurtButton.setOnClickListener { view: View ->
            sendSMSEmoticon("I'm Hurt!")
            Toast.makeText(this, "HURT EMOTION SENT", Toast.LENGTH_LONG).show()
        }

        checkInTextView.setOnClickListener { view: View ->
            //function goes here
        }


    }

}