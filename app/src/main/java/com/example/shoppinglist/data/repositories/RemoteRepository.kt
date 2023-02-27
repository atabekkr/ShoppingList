package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.User
import com.example.shoppinglist.data.models.ResultData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RemoteRepository(private val fb: FirebaseFirestore) {

    suspend fun getClientList() = flow<ResultData<List<User>>> {
    }
}