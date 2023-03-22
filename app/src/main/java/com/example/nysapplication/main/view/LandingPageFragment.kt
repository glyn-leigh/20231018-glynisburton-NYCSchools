package com.example.nysapplication.main.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
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
        //if there is data (from a search) in the db, don't update the recycleview with the init call
        val internet = checkForInternet(requireActivity())
        if(internet) {
            if (db.schoolList.isEmpty()) {
                callBaseSchoolList()

            } else {
                setUpRecyclerView()
            }
        }else{
            val text = "Please check your Internet Connection"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(requireActivity(), text, duration)
            toast.show()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init views, would have used viewbinding/databinding
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
            //Log.d("TAG", it.school_name)
        }
    }

    fun updateRecycler(){
        val type = viewModel.determineType(searchEdit.text.toString())
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
                //if nothing is in the field, reset the view
                callBaseSchoolList()
                setUpRecyclerView()

            }
        }

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