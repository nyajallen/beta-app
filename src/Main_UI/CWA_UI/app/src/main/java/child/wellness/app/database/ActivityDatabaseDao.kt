package child.wellness.app.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


/**
 * Defines methods for using the ChildActivity class with Room.
 */
@Dao
interface ActivityDatabaseDao {

    @Insert
    suspend fun insert(feeling: ChildActivity)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param feeling new value to write
     */
    @Update
    suspend  fun update(feeling: ChildActivity)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from daily_child_activity_table WHERE feelingId = :key")
    suspend fun get(key: Long): ChildActivity?
    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM daily_child_activity_table")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM daily_child_activity_table ORDER BY feelingId DESC")
    fun getAllActivity(): LiveData<List<ChildActivity>>

    /**
     * Selects and returns the latest activity.
     */
    @Query("SELECT * FROM daily_child_activity_table ORDER BY feelingId DESC LIMIT 1")
    suspend fun getActivity(): ChildActivity?
}