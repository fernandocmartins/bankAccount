package com.br.k2testesantander.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("userAccount")
    var userAccount : AccountData?,

    @SerializedName("error")
    var error: Error?
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(AccountData::class.java.classLoader),
        parcel.readParcelable(Error::class.java.classLoader)
    )

    companion object CREATOR : Parcelable.Creator<LoginResponse> {
        override fun createFromParcel(parcel: Parcel): LoginResponse {
            return LoginResponse(parcel)
        }

        override fun newArray(size: Int): Array<LoginResponse?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(userAccount, flags)
        parcel.writeParcelable(error, flags)
    }

    override fun describeContents(): Int {
        return 0
    }
}
