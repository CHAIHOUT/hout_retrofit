package kh.edu.rupp.ite.crudretrofit

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    //jea kotlin model ?
    // "data" : {}
    @SerializedName("data") var data: T? = null
)
