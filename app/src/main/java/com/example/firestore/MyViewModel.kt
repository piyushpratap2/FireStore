package com.example.firestore

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MyViewModel : ViewModel() {

    val db = Firebase.firestore

    val getListLiveData: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }


    fun insert(user: User) {
        val dataref = db.collection("Students")
        dataref.add(user.map())
            .addOnSuccessListener {
                Log.d(TAG, "Data add successfully")
            }
            .addOnFailureListener {
                Log.w(TAG, "error occur while add data", it)
            }
    }

    //  var id: String = db.collection("Students").document().getId()

    fun delete(user: User) {
        val dataref = db.collection("Students")

        dataref.whereEqualTo("Mobile", user.mobile).get().addOnSuccessListener {
            dataref.document(it.documents.get(0).id).delete()
                .addOnSuccessListener {
                    Log.d(TAG, "Data delete successfully")
                }
                .addOnFailureListener {
                    Log.w(TAG, "error while deleting data", it)
                }
        }

    }

    fun update(user: User) {
        val dataref = db.collection("Students")

        dataref.whereEqualTo("Mobile", user.mobile).get().addOnSuccessListener {
            dataref.document(it.documents.get(0).id).update(user.map())
                .addOnSuccessListener {
                    Log.d(TAG, "Data updated successfully")
                }
                .addOnFailureListener {
                    Log.w(TAG, "error while deleting data", it)
                }
        }
    }

    fun getList() {
        val docRef = db.collection("Students")
        docRef.get().addOnSuccessListener {
            val users = ArrayList<User>()
            for (item in it.documents) {
                val user = User()
                user.name = item.data!!["Name"] as String?
                user.course = item.data!!["Course"] as String?
                user.mobile = item.data!!["Mobile"] as String?

                users.add(user)
            }

            getListLiveData.postValue(users)
        }.addOnFailureListener {
            Log.d("get", it.localizedMessage!!)
            getListLiveData.postValue(null)
        }
    }
}

