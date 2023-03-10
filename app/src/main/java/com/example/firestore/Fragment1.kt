package com.example.firestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.firestore.databinding.Fragment1Binding
import io.grpc.InternalChannelz.id


class Fragment1 : Fragment() {
    private lateinit var binding: Fragment1Binding
    private lateinit var myviewmodel: MyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment1Binding.inflate(inflater, container, false)
        myviewmodel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        val bundle = arguments
        if (bundle != null) {
            var name = bundle.getString("name")
            var course = bundle.getString("course")
            var mobile = bundle.getString("mobile")
            binding!!.edit1.setText(name)
            binding!!.edit2.setText(course)
            binding!!.edit3.setText(mobile)
            binding!!.btn1.setText("update")
        }

            if (binding.btn1.text == "save") {
                binding.btn1.setOnClickListener {
                    if(binding.edit1.text.toString() != null && binding.edit2.text.toString() !=null && binding.edit3.text.toString() !=null){
                        var name: String = binding.edit1.text.toString()
                        var course: String = binding.edit2.text.toString()
                        var mobile: String = binding.edit3.text.toString()
                        myviewmodel.insert(User(name, course, mobile))


                        var fragment2 = Fragment2()
                        val fm = activity?.supportFragmentManager
                        if (fm != null) {
                            val transaction = fm.beginTransaction()
                            transaction.replace(R.id.container, fragment2)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                    }
                }

            }
           else{
               binding.btn1.setOnClickListener {
                   var tvdata1 = binding!!.edit1.text.toString()
                   var tvdata2 = binding!!.edit2.text.toString()
                   var tvdata3 = binding!!.edit3.text.toString()


                   myviewmodel.update(User(tvdata1, tvdata2, tvdata3))
                   var fragment2 = Fragment2()
                   val fm = activity?.supportFragmentManager
                   if (fm != null) {
                       val transaction = fm.beginTransaction()
                       transaction.replace(R.id.container, fragment2)
                       transaction.addToBackStack(null)
                       transaction.commit()
                   }
               }
            }


        binding.btn2.setOnClickListener {
            var fragment2 = Fragment2()
            val fm = activity?.supportFragmentManager
            if (fm != null) {
                val transaction = fm.beginTransaction()
                transaction.replace(R.id.container, fragment2)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        return binding.root
    }
}