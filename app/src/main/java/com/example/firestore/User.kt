package com.example.firestore

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var name: String? = null,
    var course: String? = null,
    var mobile: String? = null
) {
    @Exclude
    fun map(): Map<String, Any?> {
        return mapOf(
            "Name" to name,
            "Course" to course,
            "Mobile" to mobile
        )
    }
}
