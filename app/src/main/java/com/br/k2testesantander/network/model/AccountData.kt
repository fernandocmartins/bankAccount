package com.br.k2testesantander.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class AccountData(

    @SerializedName("userId")
    var userId : Int,
    @SerializedName("name")
    var name : String?,
    @SerializedName("bankAccount")
    var bankAccount : String?,
    @SerializedName("agency")
    var agency : String?,
    @SerializedName("balance")
    var balance : Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    )

    companion object CREATOR : Parcelable.Creator<AccountData> {
        override fun createFromParcel(parcel: Parcel): AccountData {
            return AccountData(parcel)
        }
        override fun newArray(size: Int): Array<AccountData?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(name)
        parcel.writeString(bankAccount)
        parcel.writeString(agency)
        parcel.writeFloat(balance)
    }

    override fun describeContents(): Int {
        return 0
    }
}