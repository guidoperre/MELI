package com.guidoperre.meli.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/*
    Clase para poder handlear eventos de trigger usando MutableLiveData, con el fin de facilitar
    la forma de observar eventos del tipo Snack o transiciones entre pantallas
*/
class TriggerLiveEvent : MutableLiveData<Boolean>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in Boolean>) {
        super.observe(owner, { t ->
            if (t) {
                super.setValue(false)
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    fun call() {
        super.setValue(true)
    }

}