package child.wellness.app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_child_activity_table")
data class ChildActivity(
    @PrimaryKey(autoGenerate = true)
    var feelingId: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "quality_rating")
    var activityQuality: Int = -1
<<<<<<< HEAD
)
=======
)
>>>>>>> nya-branch
