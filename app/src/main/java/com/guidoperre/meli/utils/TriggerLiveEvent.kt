package com.guidoperre.meli.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 *
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call().
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