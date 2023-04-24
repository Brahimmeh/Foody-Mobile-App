package com.example.fooddelivery

import android.os.Parcel
import android.os.Parcelable

data class OrderedItem(val name:String, val img: Int, var price: Int, var Quantity: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(img)
        parcel.writeInt(price)
        parcel.writeInt(Quantity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OrderedItem> {
        override fun createFromParcel(parcel: Parcel): OrderedItem {
            return OrderedItem(parcel)
        }

        override fun newArray(size: Int): Array<OrderedItem?> {
            return arrayOfNulls(size)
        }
    }
}
