package uk.ac.aber.dcs.cs39440.auswpandroidapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    val title = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val location = MutableLiveData<String>()
    val time = MutableLiveData<String>()

    fun sendTitle(text: String ){
        title.value = text
    }

    fun sendDate(text: String){
        date.value = text

    }

    fun sendLocation(text: String){
        location.value = text
    }

    fun sendTime(text: String){
        time.value = text
    }
}