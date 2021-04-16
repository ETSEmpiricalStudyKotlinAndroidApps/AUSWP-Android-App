package uk.ac.aber.dcs.cs39440.auswpandroidapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

object TestLiveDataUtil {

    fun <T>getValue(liveData: LiveData<T>):T{
        val data: MutableList<T?> = MutableList(1){null}
        val observer: Observer<T> = object:Observer<T>{
            override fun onChanged(t: T) {
                data[0]=t
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)

        return data[0]!!
    }
}