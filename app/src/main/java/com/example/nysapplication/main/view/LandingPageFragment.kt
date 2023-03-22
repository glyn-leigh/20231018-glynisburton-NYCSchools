package com.example.nysapplication.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nysapplication.R
import com.example.nysapplication.main.model.NYCDatabase
import com.example.nysapplication.main.model.SchoolsModel
import com.example.nysapplication.main.viewmodel.NYCViewModel
import java.util.*

class LandingPageFragment : Fragment() {
    private val viewModel: NYCViewModel by activityViewModels()
    private val db = NYCDatabase
    lateinit var recyclerview:RecyclerView
    lateinit var searchEdit:EditText
    lateinit var searchButton:Button
    lateinit var adapter: SchoolsAdapter
    lateinit var data:List<SchoolsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing_page, container, false)

    }

    override fun onResume() {
        super.onResume()
    }

    fun populateRecylcerView(){
        if (db.schoolList.isEmpty()) {
            callBaseSchoolList()

        }
        else{
            setUpRecyclerView()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview = view.findViewById(R.id.school_recycler)
        searchEdit = view.findViewById(R.id.search_text)
        searchButton = view.findViewById(R.id.search_button)
        data = db.schoolList
        populateRecylcerView()
        searchButton.setOnClickListener {
            updateRecycler()
        }


    }

    private fun setUpRecyclerView(){
        adapter = SchoolsAdapter(db.schoolList)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireActivity())
        adapter.onItemClick = {
            db.schoolsModel= it
            navigateToSchools()
            Log.d("TAG", it.school_name)
        }
    }

    fun updateRecycler(){
        val type = determineType(searchEdit.text.toString())
        when (type){
            "zip" -> {
                viewModel.getSchoolByZip(searchEdit.text.toString()).observe(viewLifecycleOwner, Observer {
                    data = it
                    db.schoolList = it
                    setUpRecyclerView()
                })
            }
            ///taken out in interest of time, would need to implement typo allowance
            /*"boro" -> {
                searchEdit.text.toString().uppercase()
                viewModel.getSchoolByZip(searchEdit.text.toString()).observe(viewLifecycleOwner, Observer {
                    data = it
                    db.schoolList = it
                    setUpRecyclerView()
                })

            }*/
            "dbn" -> {
                viewModel.getSchoolByDbn(searchEdit.text.toString()).observe(viewLifecycleOwner, Observer {
                    data = it
                    db.schoolList = it
                    setUpRecyclerView()
                })

            }
            "refresh" -> {
                callBaseSchoolList()
                setUpRecyclerView()

            }
        }

    }
    fun determineType(input:String):String{
        val numberMatcher  = "-?[0-9]+(\\.[0-9]+)?".toRegex()
        val letterMatcher = "^[a-zA-Z]*\$".toRegex()

        if(input.matches(numberMatcher)){
            return "zip"
        }
        /*if(input.matches(letterMatcher)){
            return "boro"
        }*/
        if(input == ""){
            return "refresh"
        }
        return "dbn"

    }


    private fun navigateToSchools(){ (activity as NYSMainActivity).changeFragment(SchoolFragment()) }

    private fun callBaseSchoolList(){
        viewModel.getSchoolList().observe(viewLifecycleOwner, Observer {
            data = it
            db.schoolList = it
            setUpRecyclerView()
        })
    }

}