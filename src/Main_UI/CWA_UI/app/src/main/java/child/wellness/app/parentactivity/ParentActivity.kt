package child.wellness.app.parentactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import child.wellness.app.R
import com.google.android.material.navigation.NavigationView

class ParentActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var settingsFragment: ParentSettingsFragment
    private lateinit var profileFragment: ParentProfileFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)

        drawer = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        fragmentContainer = findViewById(R.id.fragment_container)
        settingsFragment = ParentSettingsFragment()
        profileFragment = ParentProfileFragment()




        // Enables Hamburger Menu
        navigationView.setNavigationItemSelectedListener(this)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setHomeButtonEnabled(true)
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
        when(item.itemId){
            R.id.nav_settings -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, settingsFragment).commit()
                drawer.closeDrawer(GravityCompat.START)
            }

            R.id.nav_profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, profileFragment).commit()
                drawer.closeDrawer(GravityCompat.START)
            }
        }

        return true;
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    @Override
    override fun onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
}