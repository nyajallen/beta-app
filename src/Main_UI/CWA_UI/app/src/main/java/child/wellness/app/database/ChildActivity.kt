package child.wellness.app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ChildActivity(
    val emoticonId: String? = null,
    val feeling: String? = null,
    val date: String? = null
)

