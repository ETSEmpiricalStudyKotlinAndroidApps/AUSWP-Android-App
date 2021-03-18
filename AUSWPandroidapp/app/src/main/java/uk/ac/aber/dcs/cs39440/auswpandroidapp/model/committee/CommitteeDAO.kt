package uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CommitteeDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSingleCommittee(committee: Committee)

    @Insert
    fun insertMultipleCommittee(committeeList: List<Committee>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCommittee(committee: Committee)

    @Delete
    fun deleteCommittee(committee: Committee)

    @Query("DELETE FROM committee")
    fun deleteAll()

    @Query("SELECT * FROM committee")
    fun getAllCommittee(): LiveData<List<Committee>>
}