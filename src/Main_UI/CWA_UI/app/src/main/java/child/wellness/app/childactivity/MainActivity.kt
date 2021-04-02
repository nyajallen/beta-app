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
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import child.wellness.app.R
import child.wellness.app.database.ChildActivity
import child.wellness.app.loginmenus.UserAccess
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class MainActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    val REQUEST_ID_MULTIPLE_PERMISSIONS = 1
    val userAccess: UserAccess = UserAccess()
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
    private lateinit var activityDbInfo: DatabaseReference
    private lateinit var userDbInfo: DatabaseReference
    private lateinit var childID: String
    private val user: FirebaseUser? = Firebase.auth.currentUser
    private val dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
    private var checkInCount = 0
    private var totalCheckInCount = 0
    private lateinit var phoneNumber: String


    //Function that makes a call
    private fun emergencyLocation():Boolean {

        if((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) || (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED))
        {
            return false
        }
        else
        {
            var lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if(lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) == null)
            {
                Toast.makeText(this, "NO LOCATION FOUND", Toast.LENGTH_LONG).show()
                return false;
            }
            else
            {
                val location: Location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                var longitude = location.longitude
                var latitude = location.latitude
                sendSMSEmoticon("I HAVE AN EMERGENCY!");
                sendSMSEmoticon("CHILD'S LOCATION http://www.google.com/maps/place/" + latitude.toString() + "," + longitude.toString());
                return true;
            }
        }
    }




    //Function that makes a call
    private fun callParent():Boolean {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            return false
        }
        else
        {
            val phoneIntent = Intent(Intent.ACTION_CALL);
            phoneIntent.setData(Uri.parse(phoneNumber));
            startActivity(phoneIntent);
            return true
        }

    }


    //Function that sends an SMS message, used with the send text message button
    private fun sendSMSTextInput():Boolean {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Can't send SMS without permission.", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Enable SMS permission in phone settings or at app startup", Toast.LENGTH_LONG).show()
            return false
        }
        else
        {
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
            return true
        }

        }


    //Function that sends an SMS message, used with emoticon buttons in the app.
    private fun sendSMSEmoticon(string: String) :Boolean {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Can't send SMS without permission.", Toast.LENGTH_LONG).show()
            Toast.makeText(this, "Enable SMS permission in phone settings or at app startup", Toast.LENGTH_LONG).show()
            return false
        }
        else
        {
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
            return true
        }
    }


    //Function that requests all needed permissions for the app to run properly
    private fun checkAndRequestPermissions(): Boolean {
        val permissionSendMessage = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.SEND_SMS
        )
        val locationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val locationCoarsePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        val callPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CALL_PHONE
        )

        val internetPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.SEND_SMS)
        }
        if (locationCoarsePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (callPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CALL_PHONE)
        }
        if (internetPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.INTERNET)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    private fun saveCheckIn(emojiId: Int, feeling: String){
        var id = "activity" + checkInCount.toString()
        val date = LocalDateTime.now().format(dateFormat)
        val checkin = ChildActivity(emojiId, feeling, date)

        activityDbInfo.child(childID).child(id).setValue(checkin).addOnCompleteListener {
            Log.d("Check-Ins", "Activity saved in Database.")
        }
            .addOnFailureListener {
                Log.d("Check-Ins", "Activity didn't save.")
            }

        checkInCount++
        totalCheckInCount++
        activityDbInfo.child(childID).child("checkInNum").setValue(checkInCount)
        activityDbInfo.child("totalActivities").setValue(totalCheckInCount)
    }




    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize buttons and views
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
        activityDbInfo = FirebaseDatabase.getInstance().reference.child("Activities")
        userDbInfo = FirebaseDatabase.getInstance().reference.child("User")
        if(user != null) {
            childID = user.uid
            Log.d("Check-Ins", "Parent UID is " + childID)
        }
        else {
            Log.d("Check-Ins", "Parent not logged in")
        }
        activityDbInfo.child(childID).child("checkInNum").get().addOnSuccessListener {
            checkInCount = it.value.toString().toInt()
        }
        activityDbInfo.child("totalActivities").get().addOnSuccessListener {
            totalCheckInCount = it.value.toString().toInt()
        }
        userDbInfo.child(childID).child("parent_phone_number").get().addOnSuccessListener {
            phoneNumber = "tel: " + it.value.toString()
        }


        //Ask for all permissions
        if(checkAndRequestPermissions()) {
            // carry on the normal flow, as the case of  permissions  granted.
        }
        else
        {
            Toast.makeText(this, "Some features will not work because you did not enable permissions", Toast.LENGTH_LONG).show()
        }


        //Call button listener
        callButton.setOnClickListener { view: View ->
            var status = false;
            status = callParent();
            if(status != true)
            {
                Toast.makeText(this, "Can't make call without permission.", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Enable call permission in phone settings", Toast.LENGTH_LONG).show()
            }
        }

        //text button listener
        textButton.setOnClickListener { view: View ->
            textButton.setVisibility(View.GONE);
            textInput.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.VISIBLE);
            //Start SMS_Messenger activity
            //val intent = Intent(this, SMS_Messenger::class.java)
            //startActivity(intent)
        }


        //cancel button listener
        cancelButton.setOnClickListener { view: View ->
            textButton.setVisibility(View.VISIBLE);
            textInput.setVisibility(View.GONE);
            sendButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
        }



        //send button listener
        sendButton.setOnClickListener { view: View ->
            var status = false;
            if (textInput.text.isEmpty())
            {
                Toast.makeText(this, "No text entered", Toast.LENGTH_SHORT).show()
            }
            else
            {
                status = sendSMSTextInput()
                if(status == true)
                {
                    textButton.setVisibility(View.VISIBLE);
                    textInput.setVisibility(View.GONE);
                    sendButton.setVisibility(View.GONE);
                    cancelButton.setVisibility(View.GONE);
                    Toast.makeText(this, "Text Successfully Sent...", Toast.LENGTH_LONG).show()
                    textInput.setText("")
                }
            }
        }


        //emergency button listener
        emergencyButton.setOnClickListener { view: View ->
            var status = false;

            status = emergencyLocation()

            if(status == true)
            {
                Toast.makeText(this, "Emergency text successfully sent", Toast.LENGTH_LONG).show()
            }
            else
            {
                Toast.makeText(this, "Permission not enabled for location, or location unknown", Toast.LENGTH_LONG).show()
            }

            }


        //happy button listener
        happyButton.setOnClickListener { view: View ->
            var status = false;
            status = sendSMSEmoticon("I'm Happy!")
            if(status == true)
            {
                Toast.makeText(this, "HAPPY EMOTION SENT", Toast.LENGTH_LONG).show()
                saveCheckIn(R.drawable.happy, "I'm Happy!")
            }
        }


        //sad button listener
        sadButton.setOnClickListener { view: View ->
            var status = false;
            status = sendSMSEmoticon("I'm Sad!")
            if(status == true)
            {
                Toast.makeText(this, "SAD EMOTION SENT", Toast.LENGTH_LONG).show()
                saveCheckIn(R.drawable.sad, "I'm Sad!")
            }
        }


        //mad button listener
        madButton.setOnClickListener { view: View ->
            var status = false;
            status = sendSMSEmoticon("I'm Mad!")
            if(status == true)
            {
                Toast.makeText(this, "MAD EMOTION SENT", Toast.LENGTH_LONG).show()
                saveCheckIn(R.drawable.mad, "I'm Mad!")
            }
        }


        //sick button listener
        sickButton.setOnClickListener { view: View ->
            var status = false;
            status = sendSMSEmoticon("I'm Sick!")
            if(status == true)
            {
                Toast.makeText(this, "SICK EMOTION SENT", Toast.LENGTH_LONG).show()
                saveCheckIn(R.drawable.sick, "I'm Sick!")
            }

        }


        //hurt button listener
        hurtButton.setOnClickListener { view: View ->
            var status = false;
            status = sendSMSEmoticon("I'm Hurt!")
            if(status == true)
            {
                Toast.makeText(this, "HURT EMOTION SENT", Toast.LENGTH_LONG).show()
                saveCheckIn(R.drawable.hurt, "I'm Hurt!")
            }

        }


        //check in view listener
        checkInTextView.setOnClickListener { view: View ->
            //function goes here
        }
    }
}