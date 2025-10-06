package com.fake.travelchatbot

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var queryInput: EditText
    private lateinit var sendButton: Button
    private lateinit var responseText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queryInput = findViewById(R.id.queryInput)
        sendButton = findViewById(R.id.sendButton)
        responseText = findViewById(R.id.responseText)

        sendButton.setOnClickListener{
            val query = queryInput.text.toString()
            if(query.isNotEmpty()){
                sendqueryToChatBot(query)
            }
        }
    }

    private fun sendqueryToChatBot(query: String){
        val request = ChatRequest(query)
        RetrofitClient.instance.getChatResponse(request)
            .enqueue(object : Callback<ChatResponse> {
                override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                    if(response.isSuccessful){
                        responseText.text = response.body()?.response ?: "No response"
                    } else {
                        responseText.text = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                    responseText.text = "Failed: ${t.message}"
                }
            })
    }
}