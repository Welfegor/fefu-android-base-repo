package ru.fefu.activitytracker.innerTracker.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activitytracker.innerTracker.fragments.UsersActivity
import ru.fefu.activitytracker.innerTracker.fragments.Activity
import ru.fefu.activitytracker.innerTracker.fragments.MyActivity

class ActivityFragmentAdapter(fragment: Activity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) MyActivity()
        else UsersActivity()
    }
}