fun formatDuration(durationMillis: Long): String {
    return if (durationMillis < 1000) {
        "$durationMillis ms"
    } else {
        "${durationMillis / 1000.0} s"
    }
}