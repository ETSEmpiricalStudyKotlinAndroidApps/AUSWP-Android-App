package uk.ac.aber.dcs.cs39440.auswpandroidapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    val message = MutableLiveData<String>()

    fun sendMessage(title: String, date:String, location:String, time: String ){
        message.value = title + date + location + time
    }
}