package com.sdwtech.mycontact.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sdwtech.mycontact.data.ContactResponse
import com.sdwtech.mycontact.databinding.ActivityMainBinding
import com.sdwtech.mycontact.databinding.ItemContactBinding
import com.sdwtech.mycontact.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemContactBinding: ItemContactBinding

    companion object {
        private const val TAG = "MainActivity"
        private const val CONTACT_ID = "1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvContact.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvContact.addItemDecoration(itemDecoration)

        findContact()
    }

    private fun findContact() {
        showLoading(true)
        val client = ApiConfig.getApiService().getContact(CONTACT_ID)
        client.enqueue(object : Callback<ContactResponse> {
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setContact(responseBody)
                    }
                } else {
                    Log.e(TAG, "salahh: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    private fun setContact(contact: ContactResponse) {
        itemContactBinding.tvName.text = contact.name
//        ItemContactBinding.tvLabel.text = label.title
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}