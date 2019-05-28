package com.br.k2testesantander.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class StatementResponse (
    @SerializedName("statementList")
    var statementListResponse: List<Statement>?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Statement))

    companion object CREATOR : Parcelable.Creator<StatementResponse> {
        override fun createFromParcel(parcel: Parcel): StatementResponse {
            return StatementResponse(parcel)
        }

        override fun newArray(size: Int): Array<StatementResponse?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(statementListResponse)
    }

    override fun describeContents(): Int {
        return 0
    }
}
