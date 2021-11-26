package ru.fefu.activitytracker

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ActivityFragmentAdapter(fragment : Activity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) MyActivity.newInstance()
        else UsersActivity.newInstance()
    }
}