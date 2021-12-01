package ru.fefu.activitytracker.innerTracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.innerTracker.models.ActivityModel
import ru.fefu.activitytracker.innerTracker.models.DateModel
import ru.fefu.activitytracker.innerTracker.models.UsersActivityModel
import java.time.Duration
import java.time.LocalDateTime


private var clickTracker: (Int) -> Unit = {}

class TasksAdapter(private val activities: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if(viewType == 0) MyActivityCard(LayoutInflater.from(parent.context).inflate(R.layout.my_task, parent, false))
        else if (viewType == 1) UsersActivityCard(LayoutInflater.from(parent.context).inflate(R.layout.users_task, parent, false))
        else DateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_time, parent, false))
    }

    override fun getItemCount(): Int = activities.size;

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, pos: Int) {
        if (activities[pos] is ActivityModel)
                (viewHolder as MyActivityCard).setupInfo(activities[pos] as ActivityModel)
        else if(activities[pos] is UsersActivityModel)
                (viewHolder as UsersActivityCard).setupInfo(activities[pos] as UsersActivityModel)
        else if (activities[pos] is DateModel)
                (viewHolder as DateViewHolder).setup(activities[pos] as DateModel)
    }

    override fun getItemViewType(pos: Int): Int {
        return when {
            activities[pos] is ActivityModel -> 0
            activities[pos] is UsersActivityModel -> 1
            else -> 2
        }
    }

    fun setClickTracker(tracker: (Int) -> Unit) {clickTracker = tracker}
}

class MyActivityCard(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val distance = itemView.findViewById<TextView>(R.id.task_distance)
    private val duration = itemView.findViewById<TextView>(R.id.task_duration)
    private val type = itemView.findViewById<TextView>(R.id.task_type)
    private val date = itemView.findViewById<TextView>(R.id.task_date)

    init {itemView.setOnClickListener {clickTracker.invoke(adapterPosition)}}

    fun setupInfo(activity: ActivityModel) {
        distance.text = activity.distance
        type.text = activity.taskType
        var inSeconds =
            kotlin.math.abs(Duration.between(activity.endTime, activity.startTime).seconds)
        val inHours = inSeconds / 3600
        val inMinutes = (inSeconds - inHours * 3600) / 60
        duration.text = inHours.toString() + " ч. " + inMinutes.toString() + " м."
        if (LocalDateTime.now().year == activity.endTime.year &&
            LocalDateTime.now().monthValue == activity.endTime.monthValue &&
            LocalDateTime.now().dayOfMonth == activity.endTime.dayOfMonth) date.text = Duration.between(activity.endTime, LocalDateTime.now()).toHours().toString() + " ч. назад"
        else date.text = activity.endTime.dayOfMonth.toString() + '.' + activity.endTime.monthValue.toString() + '.' + activity.endTime.year.toString()
    }
}

class UsersActivityCard(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val distance = itemView.findViewById<TextView>(R.id.task_distance)
    private val duration = itemView.findViewById<TextView>(R.id.task_duration)
    private val type = itemView.findViewById<TextView>(R.id.task_type)
    private val date = itemView.findViewById<TextView>(R.id.task_date)
    private val user = itemView.findViewById<TextView>(R.id.task_user_tag)

    init {itemView.setOnClickListener {clickTracker.invoke(adapterPosition)}}

    fun setupInfo(activity: UsersActivityModel) {
        distance.text = activity.distance
        type.text = activity.taskType
        user.text = activity.username
        var inSeconds =
            kotlin.math.abs(Duration.between(activity.endTime, activity.startTime).seconds)
        val inHours = inSeconds / 3600
        val inMinutes = (inSeconds - inHours * 3600) / 60
        duration.text = inHours.toString() + " ч. " + inMinutes.toString() + " м."
        if (LocalDateTime.now().year == activity.endTime.year &&
            LocalDateTime.now().monthValue == activity.endTime.monthValue &&
            LocalDateTime.now().dayOfMonth == activity.endTime.dayOfMonth) date.text = Duration.between(activity.endTime, LocalDateTime.now()).toHours().toString() + " ч. назад"
        else date.text = activity.endTime.dayOfMonth.toString() + '.' + activity.endTime.monthValue.toString() + '.' + activity.endTime.year.toString()
    }
}

class DateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val date = itemView.findViewById<TextView>(R.id.task_time)

    fun setup(dateItem: DateModel) {
        date.text = dateItem.Date
    }
}