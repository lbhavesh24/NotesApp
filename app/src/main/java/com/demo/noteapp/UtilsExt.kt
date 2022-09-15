package com.demo.noteapp

import android.text.format.DateUtils
import com.demo.noteapp.models.Notes
import java.util.*

fun Long.timeAgo() = DateUtils.getRelativeTimeSpanString(
    this,
    Calendar.getInstance().timeInMillis,
    DateUtils.DAY_IN_MILLIS
).toString()

fun Long.timeAgoInSeconds() = DateUtils.getRelativeTimeSpanString(
    this,
    Calendar.getInstance().timeInMillis,
    DateUtils.SECOND_IN_MILLIS
).toString()

interface NoteClickListener{
    fun noteClick(notes: Notes)
}