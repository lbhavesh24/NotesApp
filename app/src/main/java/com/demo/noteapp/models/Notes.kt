package com.demo.noteapp.models

import android.os.Parcelable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.demo.noteapp.timeAgo
import com.demo.noteapp.timeAgoInSeconds
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "notes_table", indices = [Index(value = ["noteId"], unique = true)])
data class Notes(var description: String? = ""):Parcelable{
    @PrimaryKey
    var noteId: String = UUID.randomUUID().toString()
    var createdOn: Long = System.currentTimeMillis()
    var updatedOn: Long = System.currentTimeMillis()
}

@BindingAdapter("setTime")
fun setTime(view: TextView,time:Long){
    view.text = time.timeAgoInSeconds()
}
