package com.example.firestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.btn1.setOnClickListener {
            var name:String = binding.edit1.text.toString()
            var course:String = binding.edit2.text.toString()
            var duration:String = binding.edit3.text.toString()

            val db = Firebase.firestore
            val user = hashMapOf(
                "Name:" to name,
                "Course:" to course,
                "Duration:" to duration
            )

            db.collection("Students")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext,"Document added with",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext,"error adding document" + it,Toast.LENGTH_LONG).show()
                }
        }*/

    }
}