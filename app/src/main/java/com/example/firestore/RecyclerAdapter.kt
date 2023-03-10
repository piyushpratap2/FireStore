package com.example.firestore

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.firestore.databinding.RecyclerRowBinding
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class RecyclerAdapter(val listener: Fragment2, val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var userList = ArrayList<User>()

    fun setContentList(user: ArrayList<User>) {
        this.userList = user
        notifyDataSetChanged()
    }


    inner class MyViewHolder(val databinding: RecyclerRowBinding, val listener: Fragment2) :
        RecyclerView.ViewHolder(databinding.root) {
        val tv1 = databinding.tv1
        val tv2 = databinding.tv2
        val tv3 = databinding.tv3
        val btn3 = databinding.btn3
        val btn4 = databinding.btn4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecyclerRowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.recycler_row, parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var name: String = userList.get(position).name.toString()
        var course: String = userList.get(position).course.toString()
        var mobile: String = userList.get(position).mobile.toString()


        holder.tv1.text = name
        holder.tv2.text = course
        holder.tv3.text = mobile

        holder.btn3.setOnClickListener {

            listener.onDeleteUser(userList.get(position))
        }
        holder.btn4.setOnClickListener {

            listener.onupdateUser(userList.get(position))
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    interface clickListener {
        fun onDeleteUser(user:User)
        fun onupdateUser(user: User)
    }

}