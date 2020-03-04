package com.adsi.ambiental.commons

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

fun toast(context: Context, message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, message, duration).show()

fun toast(context: Context, resourceId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, resourceId, duration).show()