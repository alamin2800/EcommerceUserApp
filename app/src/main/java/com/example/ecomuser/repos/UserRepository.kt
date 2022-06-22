package com.example.ecomuserbatch4.repos

import com.example.ecomuser.utils.collectionUser
import com.example.ecomuserbatch4.models.EcomUser
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    private val db = FirebaseFirestore.getInstance()
    fun addNewUser(ecomUser: EcomUser) {
        db.collection(collectionUser).document(ecomUser.userId!!)
            .set(ecomUser)
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun updateAppExitTimeAndAvailableStatus(userId: String, time: Timestamp, status: Boolean, callback: (() -> Unit)? = null) {
        db.collection(collectionUser)
            .document(userId)
            .update(mapOf("available" to status, "lastUserAppExitTime" to time))
            .addOnSuccessListener {
                callback?.invoke()
            }.addOnFailureListener {

            }
    }

    fun updateLastSignInTimeAndAvailableStatus(userId: String, time: Timestamp, status: Boolean) {
        db.collection(collectionUser)
            .document(userId)
            .update(mapOf("available" to status, "userLastSignInTimestamp" to time))
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun updateAvailableStatus(userId: String, status: Boolean) {
        db.collection(collectionUser)
            .document(userId)
            .update("available", status)
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }
}