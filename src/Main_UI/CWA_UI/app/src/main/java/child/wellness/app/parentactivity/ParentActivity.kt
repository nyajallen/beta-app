package child.wellness.app.parentactivity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import child.wellness.app.R
import child.wellness.app.childactivity.MainActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ParentActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var settingsFragment: ParentSettingsFragment
    private lateinit var profileFragment: ParentProfileFragment
    private lateinit var weeklyLogFragment: WeeklyLogFragment
    private lateinit var resourcesFragment: ParentResourcesFragment
    private lateinit var parentNameMenu : TextView
    private lateinit var usersDbInfo: DatabaseReference
    private val userID: String = Firebase.auth.currentUser.uid


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)

        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        fragmentContainer = findViewById(R.id.fragment_container)
        settingsFragment = ParentSettingsFragment()
        profileFragment = ParentProfileFragment()
        weeklyLogFragment = WeeklyLogFragment()
        resourcesFragment = ParentResourcesFragment()
        usersDbInfo = FirebaseDatabase.getInstance().reference.child("User")
        val header = navigationView.getHeaderView(0)
        parentNameMenu = header.findViewById(R.id.parent_name_menu)

        usersDbInfo.child(userID).child("parent_name").get().addOnSuccessListener {
            parentNameMenu.text = it.value.toString()
        }

        // Enables Hamburger Menu
        navigationView.setNavigationItemSelectedListener(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toggle = ActionBarDrawerToggle(this, drawer,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){

            onNavigationItemSelected(navigationView.menu.findItem(R.id.nav_profile))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // Activate fragment view when button is clicked in navigation menu
        when(item.itemId){

            // Opens settings fragment
            R.id.nav_settings -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, settingsFragment).commit()
                drawer.closeDrawer(GravityCompat.START)
            }

            // Opens profile fragment
            R.id.nav_profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, profileFragment).commit()
                drawer.closeDrawer(GravityCompat.START)
            }

            // Opens child activity
            R.id.child_side -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            // Opens weekly log fragment
            R.id.nav_log -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, weeklyLogFragment).commit()
                drawer.closeDrawer(GravityCompat.START)
            }

            // Opens parenting tips fragment
            R.id.nav_resources -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, resourcesFragment).commit()
                drawer.closeDrawer(GravityCompat.START)
            }
        }

        return true
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @Override
    override fun onBackPressed() {
        Firebase.auth.signOut()
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
}