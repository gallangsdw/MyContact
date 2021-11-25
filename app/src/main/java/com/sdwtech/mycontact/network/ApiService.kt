package com.sdwtech.mycontact.network

import com.sdwtech.mycontact.data.ContactResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("contact/{id}")
    fun getContact(
        @Path("id") id: String
    ) : Call<ContactResponse>
}