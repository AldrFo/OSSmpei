package ru.acediat.feature_timetable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.acediat.core_android.AdapterCallback
import ru.acediat.core_android.RecyclerViewAdapter
import ru.acediat.core_res.databinding.ItemEventBinding
import ru.acediat.feature_timetable.entities.Lesson

class LessonsAdapter : RecyclerViewAdapter<Lesson, ItemEventBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) = ViewHolder(
        ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        object : AdapterCallback<Lesson, ItemEventBinding>{
            override fun bindViews(binding: ItemEventBinding, item: Lesson, position: Int) = with(binding){
                eventTime.text = item.getFormatTime()
                eventName.text = item.name
                eventType.text = item.type
                eventPlace.text = item.place
                eventOwnerName.text = item.teacher
            }

            override fun onViewClicked(view: View, item: Lesson) {}
        }
    )
}