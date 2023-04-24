package com.example.fooddelivery

import android.os.Parcel
import android.os.Parcelable


data class Popular (val name:String, val img: Int, val price: String, val des: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(img)
        parcel.writeString(price)
        parcel.writeString(des)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Popular> {
        override fun createFromParcel(parcel: Parcel): Popular {
            return Popular(parcel)
        }

        override fun newArray(size: Int): Array<Popular?> {
            return arrayOfNulls(size)
        }
    }
}