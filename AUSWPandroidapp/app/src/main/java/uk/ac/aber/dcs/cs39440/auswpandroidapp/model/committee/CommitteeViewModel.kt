/**
 * ViewModel for the CommitteeFragment. Caches live data object
 * of list of committee. Communicates with repository.
 * @author Callum Robert Binner
 * @version 1
 */

package uk.ac.aber.dcs.cs39440.auswpandroidapp.model.committee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import uk.ac.aber.dcs.cs39440.auswpandroidapp.datasource.committee.CommitteeRepository

class CommitteeViewModel (application: Application): AndroidViewModel(application) {
    private val repository: CommitteeRepository = CommitteeRepository(application)
    var committeeList: LiveData<List<Committee>> = repository.getAllCommittee()
    private set

    fun getCommittee(): LiveData<List<Committee>>{
        committeeList = repository.getAllCommittee()
        return committeeList
    }

    fun deleteWords(){
        repository.deleteAllCommittee()
    }
}