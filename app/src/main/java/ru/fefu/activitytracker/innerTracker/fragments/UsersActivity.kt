package ru.fefu.activitytracker.innerTracker.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.UsersTaskFragmentBinding
import ru.fefu.activitytracker.innerTracker.adapter.TasksAdapter
import ru.fefu.activitytracker.innerTracker.models.DateModel
import ru.fefu.activitytracker.innerTracker.models.UsersActivityModel
import java.time.LocalDateTime

class UsersActivity : Fragment(R.layout.users_task_fragment) {
    private var _binding: UsersTaskFragmentBinding? = null
    private val binding get() = _binding!!
    private val activitiesDate = mutableListOf<Any>()

    private val tasks = listOf(
        UsersActivityModel("30 км", "Перо ястреба", LocalDateTime.now(), LocalDateTime.now(), "welfegor1"),
        UsersActivityModel("40 км", "Перо феникса", LocalDateTime.of(2020, 1, 1, 10, 0),
            LocalDateTime.of(2020, 1, 1, 20, 20), "welfegor2"))

    private val datesBind = mapOf(1 to "Январь", 2 to "Февраль", 3 to "Март", 4 to "Апрель", 5 to "Май", 6 to "Июнь",
        7 to "Июль", 8 to "Август", 9 to "Сентябрь", 10 to "Октябрь", 11 to "Ноярь", 12 to "Декабрь")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UsersTaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupDate(tasks: List<UsersActivityModel>) {
        val nowTime = LocalDateTime.now()
        var date = DateModel("")
        for (task in tasks) {
            if (nowTime.year == task.endTime.year && nowTime.monthValue == task.endTime.monthValue &&
                nowTime.dayOfMonth == task.endTime.dayOfMonth && date.Date != "Сегодня")
                activitiesDate.add(DateModel("Сегодня"))
            else activitiesDate.add(DateModel(datesBind[task.endTime.monthValue] + ' ' + task.endTime.year.toString() + " года"))
            activitiesDate.add(task)
        }
    }

    private val adapter = TasksAdapter(activitiesDate)

    private fun switchFragment(pos: Int) {
        if (pos in activitiesDate.indices) {
            val manager = activity?.supportFragmentManager?.findFragmentByTag(ActivityFragment.tag)?.childFragmentManager
            manager?.beginTransaction()?.apply {
                manager.fragments.forEach(::hide)
                add (R.id.fragment_my_activity, MyActivityInfo.newInstance(), MyActivityInfo.tag)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDate(tasks)
        val recycleView = binding.recyclerView
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        adapter.setClickTracker {switchFragment(it)}
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
