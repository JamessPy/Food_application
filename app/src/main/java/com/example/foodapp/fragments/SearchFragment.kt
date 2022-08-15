package com.example.foodapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.foodapp.adapter.FoodAdapter
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentSearchBinding
import com.example.foodapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding:FragmentSearchBinding
    private lateinit var viewModel:SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        LiveData()
        searchFood()

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        return binding.root
    }

    private fun LiveData(){
        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodAdapter(it)
            binding.foodAdapter = adapter

        }
    }

    private fun searchFood(){
        binding.etSearchHome.setOnFocusChangeListener { _, focused ->
            if(focused){
                binding.etSearchHome.addTextChangedListener(object: TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        viewModel.searchFood(p0.toString())
                    }
                    override fun afterTextChanged(p0: Editable?) {}
                })
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SearchViewModel by viewModels()
        viewModel = tempViewModel

    }

}