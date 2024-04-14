package com.thechance.newspal.ui.util

import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

fun formatTimeAgo(dateTimeStr: String): String {
    val dateTime = ZonedDateTime.parse(dateTimeStr)
    val now = ZonedDateTime.now()

    val minutesAgo = ChronoUnit.MINUTES.between(dateTime, now)
    val hoursAgo = ChronoUnit.HOURS.between(dateTime, now)
    val daysAgo = ChronoUnit.DAYS.between(dateTime, now)

    return when {
        minutesAgo < 60 -> "${minutesAgo}m ago"
        hoursAgo < 24 -> "${hoursAgo}h ago"
        daysAgo < 7 -> "${daysAgo}d ago"
        daysAgo < 30 -> "${daysAgo / 7}w ago"
        daysAgo < 365 -> "${daysAgo / 30}mo ago"
        else -> "${daysAgo / 365}y ago"
    }
}

fun formatDate(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)

    val date = inputFormat.parse(input)
    return if (date != null) {
        outputFormat.format(date)
    } else {
        "Invalid date"
    }
}
fun formatMonth(dateTimeStr: String): String {
    val dateTime = ZonedDateTime.parse(dateTimeStr)
    val monthFormat = DateTimeFormatter.ofPattern("d'th' MMM")
    return dateTime.format(monthFormat)
}