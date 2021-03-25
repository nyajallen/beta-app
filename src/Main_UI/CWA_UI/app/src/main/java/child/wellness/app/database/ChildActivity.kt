package child.wellness.app.database

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChildActivity(
    val emoticonId: Int? = null,
    val feeling: String? = null,
    val date: String? = null
)

