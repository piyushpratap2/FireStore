package com.example.firestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestore.databinding.Fragment2Binding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class Fragment2 : Fragment(),RecyclerAdapter.clickListener{

    private lateinit var binding: Fragment2Binding
    lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var myviewmodel: MyViewModel
    private lateinit  var list:ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment2Binding.inflate(inflater, container, false)
        myviewmodel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            recyclerAdapter = RecyclerAdapter(this@Fragment2, requireContext())
            adapter = recyclerAdapter

            val divider = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }




        binding!!.floating.setOnClickListener {
            var fragment1 = Fragment1()
            val fm = activity?.supportFragmentManager

            if (fm != null) {
                val transaction = fm.beginTransaction()
                transaction.replace(R.id.container, fragment1)
                fm.popBackStack()

                transaction.commit()
            }
            if (fm != null) {

            }
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        myviewmodel.getList()
        myviewmodel.getListLiveData.observe(viewLifecycleOwner, Observer{
            recyclerAdapter.setContentList(ArrayList(it))
            recyclerAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(),"on resume call",Toast.LENGTH_LONG).show()
        })
    }

      override fun onDeleteUser(user:User) {
         myviewmodel.delete(user)
    }

     override fun onupdateUser(user:User) {
         val bundle = Bundle()
         var name:String = user.name.toString()
         var course:String = user.course.toString()
         var mobile:String = user.mobile.toString()

         bundle.putInt("id",id)
         bundle.putString("name",name)
         bundle.putString("course",course)
         bundle.putString("mobile",mobile)
         val fragment = Fragment1()
         fragment.arguments = bundle
         val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
         if (fragmentManager != null) {
             fragmentManager.replace(R.id.container, fragment).commit()
         }
    }

}