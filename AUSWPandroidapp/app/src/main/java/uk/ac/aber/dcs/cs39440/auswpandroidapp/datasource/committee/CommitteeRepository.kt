/**
 * The Repository class acting as a face to the underlying Room database
 * @author Callum Robert Binner
 * @version 1
 *
 */


package uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.committee

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee.Committee

class CommitteeRepository(application: Application) {
    private val committeeDAO = CommitteeRoomDatabase.getDatabase(application)!!.committeeDAO()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(committee: Committee) {
        coroutineScope.launch(Dispatchers.IO) {
            committeeDAO.insertSingleCommittee(committee)
        }
    }

    fun insertMultipleCommittee(committees: List<Committee>) {
        coroutineScope.launch(Dispatchers.IO) {
            committeeDAO.insertMultipleCommittee(committees)
        }
    }

    fun getAllCommittee() = committeeDAO.getAllCommittee()

    fun deleteAllCommittee() = committeeDAO.deleteAll()
}