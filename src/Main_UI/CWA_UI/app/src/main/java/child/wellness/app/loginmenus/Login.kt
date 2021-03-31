package child.wellness.app.loginmenus

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import child.wellness.app.childactivity.MainActivity
import child.wellness.app.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class Login : AppCompatActivity() {

    private var cancellationSignal: CancellationSignal? = null
    private var auth: FirebaseAuth = Firebase.auth

    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notifyUser("Authentication error: $errString")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    notifyUser("Authentication success!")

                    if(auth.currentUser == null) {
                        notifyUser("Parent must log in first!")
                    }
                    else {
                        startActivity(Intent(this@Login, MainActivity::class.java))
                    }
                }
            }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        checkBiometricSupport()

        imageFingerPrint.setOnClickListener() {

            val biometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Fingerprint Scanner")
                .setSubtitle("Authentication is required")
                .setDescription("Fingerprint needed to gain access!")
                .setNegativeButton("Cancel", this.mainExecutor,
                    DialogInterface.OnClickListener { dialog, wich ->
                        notifyUser("Authentication cancelled")
                    }).build()

            biometricPrompt.authenticate(
                getCancellationSignal(),
                mainExecutor,
                authenticationCallback
            )
        }

        login_text_view.setOnClickListener {
            Log.d("Login", "Log in button")
            val intent = Intent(this@Login, UserAccess::class.java)
            startActivity(intent)
        }
    }

    private fun notifyUser(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notifyUser("Authentication was cancelled by the user")
        }

        return cancellationSignal as CancellationSignal
    }

    private fun checkBiometricSupport(): Boolean {

        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyguardManager.isKeyguardSecure){
            notifyUser("Fingerprint auth has not been enabled in settings!")
            return false
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC) !=
            PackageManager.PERMISSION_GRANTED) {
            notifyUser("Fingerprint auth permission is not enabled")
            return false
        }

        return if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true}
        else true
    }

}
