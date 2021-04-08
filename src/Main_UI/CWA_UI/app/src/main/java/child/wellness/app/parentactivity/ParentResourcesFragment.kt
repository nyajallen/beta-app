package child.wellness.app.parentactivity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import child.wellness.app.R


class ParentResourcesFragment : Fragment() {

    private lateinit var resourcesView: View
    private lateinit var rewardsButton: Button
    private lateinit var effectiveButton: Button
    private lateinit var behaveProbButton: Button
    private lateinit var tenTipsButton: Button
    private lateinit var managingButton: Button
    private lateinit var encouragingdButton: Button
    private lateinit var reactButton: Button
    private lateinit var positiveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        resourcesView = inflater.inflate(R.layout.fragment_parent_resources, container, false)
        rewardsButton = resourcesView.findViewById(R.id.rewards)
        effectiveButton = resourcesView.findViewById(R.id.effective_parenting)
        behaveProbButton = resourcesView.findViewById(R.id.behavior_problems)
        tenTipsButton = resourcesView.findViewById(R.id.better_behavior)
        managingButton = resourcesView.findViewById(R.id.managing_behavior)
        encouragingdButton = resourcesView.findViewById(R.id.encouraging_behavior)
        reactButton = resourcesView.findViewById(R.id.reacting)
        positiveButton = resourcesView.findViewById(R.id.responding_positive)

        return resourcesView
    }

    // Sets the links to the buttons. When the user clicks a button, it will open up
    // their default browser and go to the website.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rewardsButton.setOnClickListener {
            goToUrl("https://www.cdc.gov/parents/essentials/consequences/rewards.html")
        }

        effectiveButton.setOnClickListener {
            goToUrl("https://kidshealth.org/en/parents/nine-steps.html")
        }

        behaveProbButton.setOnClickListener {
            goToUrl("https://www.nhs.uk/conditions/baby/babys-development/behaviour/dealing-with-child-behaviour-problems/")
        }

        tenTipsButton.setOnClickListener {
            goToUrl("https://www.positiveparentingsolutions.com/parenting/start-10-tips-better-behavior")
        }

        managingButton.setOnClickListener {
            goToUrl("https://childmind.org/article/managing-problem-behavior-at-home/")
        }

        encouragingdButton.setOnClickListener {
            goToUrl("https://raisingchildren.net.au/toddlers/behaviour/encouraging-good-behaviour/good-behaviour-tips")
        }

        reactButton.setOnClickListener {
            goToUrl("https://imperfectfamilies.com/do-you-react-or-respond-to-your-kids/")
        }

        positiveButton.setOnClickListener {
            goToUrl("https://eclkc.ohs.acf.hhs.gov/publication/responding-positively-your-childs-behavior")
        }
    }

    // function that starts the browser
    private fun goToUrl(url: String) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }


}