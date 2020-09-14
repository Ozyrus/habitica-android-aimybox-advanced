package com.habitrpg.android.habitica.models.inventory

import com.google.gson.annotations.SerializedName

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Equipment : RealmObject() {

    var value: Double = 0.toDouble()
    var type: String? = ""
    @PrimaryKey
    var key: String? = ""
    var klass: String = ""
    var specialClass: String = ""
    var index: String = ""
    var text: String = ""
    var notes: String = ""
    var con: Int = 0
    var str: Int = 0
    var per: Int = 0
    @SerializedName("int")
    var _int: Int = 0
    var owned: Boolean? = null
    var twoHanded = false
}
