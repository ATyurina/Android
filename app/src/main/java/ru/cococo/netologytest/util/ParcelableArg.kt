package ru.cococo.netologytest.util

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import ru.cococo.netologytest.kot.Post
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
inline fun <reified T : Parcelable>
        Bundle.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >=
        Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}
object ParcelableArg: ReadWriteProperty<Bundle, Parcelable?> {


    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Parcelable?) {
        thisRef.putParcelable(property.name, value)
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): Parcelable? =
        thisRef.getParcelableCompat(property.name)
}



