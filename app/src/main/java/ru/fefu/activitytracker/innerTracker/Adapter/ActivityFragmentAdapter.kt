package ru.fefu.activitytracker.innerTracker.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fefu.activitytracker.innerTracker.Fragments.Activity
import ru.fefu.activitytracker.innerTracker.Fragments.MyActivity
import ru.fefu.activitytracker.innerTracker.Fragments.UsersActivity

class ActivityFragmentAdapter(fragment : Activity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) MyActivity.newInstance()
        else UsersActivity.newInstance()
    }
}