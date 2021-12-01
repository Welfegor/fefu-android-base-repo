package ru.fefu.activitytracker.innerTracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityMainBinding
import ru.fefu.activitytracker.innerTracker.fragments.ActivityFragment
import ru.fefu.activitytracker.innerTracker.fragments.ProfileFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActivity(savedInstanceState)

        binding.navbarTracker.setOnNavigationItemSelectedListener {
            setupNavHandler(it.itemId)
            true
        }
    }

    private fun setupActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.tracker_view, ActivityFragment.newInstance(), ActivityFragment.tag,)
                commit()
            }
        }
    }

    private fun setupNavHandler(btn_id: Int) {
        val tabFragments = listOf(
            TabFragment(R.id.tracker_activity, ActivityFragment.Companion::newInstance, ActivityFragment.tag),
            TabFragment(R.id.tracker_profile, ProfileFragment.Companion::newInstance, ProfileFragment.tag))

        val activeFragment = supportFragmentManager.fragments.firstOrNull{!it.isHidden}
        val hiddenMetaFragment = tabFragments.first { it.buttonId == btn_id }
        val hiddenFragment = supportFragmentManager.findFragmentByTag(hiddenMetaFragment.tag)

        if (activeFragment == hiddenFragment) return
        if (hiddenFragment == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.tracker_view, hiddenMetaFragment.fragment(), hiddenMetaFragment.tag)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                show(hiddenFragment)
                commit()
            }
        }
        if (activeFragment != null) {
            supportFragmentManager.beginTransaction().apply {
                hide(activeFragment)
                commit()
            }
        }

    }

    override fun onBackPressed() {
        val active = supportFragmentManager.fragments.firstOrNull{!it.isHidden}!!
        if (active.childFragmentManager.backStackEntryCount != 0) active.childFragmentManager.popBackStack()
    }
}


class TabFragment(val buttonId: Int, val fragment: () -> Fragment, val tag: String)
