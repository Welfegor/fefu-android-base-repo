package ru.fefu.activitytracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.databinding.ActivityMainBinding


class TrackerActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActivity(savedInstanceState)
        setupNavbarHandler()
    }

    private fun setupActivity(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(
                    R.id.fragment_view_tracker,
                    Activity.newInstance(),
                    Activity.tag
                )
                commit()
            }
        }
    }

    private fun setupNavbarHandler() {
        val tabFragments = listOf(
            TabFragment(R.id.activity, getFragment(Activity.tag),
                Activity.tag
            ),
            TabFragment(R.id.profile, getFragment(Profile.tag),
                Profile.tag
            )
        )

        val navbarHandler = NavbarHandler(tabFragments, supportFragmentManager)

        binding.navbarTracker.setOnItemSelectedListener { item ->
            navbarHandler.switchFragments(item.itemId)
            true
        }
    }

    private fun getFragment(tag: String) : Fragment {
        return supportFragmentManager.findFragmentByTag(tag)
            ?: when (tag) {
                Activity.tag -> Activity.newInstance()
                Profile.tag -> Profile.newInstance()
                else -> return Fragment()
            }
    }
}

