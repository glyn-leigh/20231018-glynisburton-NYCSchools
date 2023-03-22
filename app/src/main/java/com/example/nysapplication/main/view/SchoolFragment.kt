package com.example.nysapplication.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.nysapplication.R
import com.example.nysapplication.main.model.NYCDatabase
import com.example.nysapplication.main.model.SchoolsSATModel
import com.example.nysapplication.main.viewmodel.NYCViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolFragment : Fragment() {

    private val viewModel: NYCViewModel by activityViewModels()
    private val db = NYCDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSATDetails(db.schoolsModel.dbn).observe(viewLifecycleOwner, Observer {
            var schoolTitle : TextView? = view.findViewById(R.id.school_name)
            schoolTitle?.text = it.school_name
            var schoolSize : TextView? = view.findViewById(R.id.school_size)
            schoolSize?.text = db.schoolsModel.total_students.toString()
            var satReading : TextView? = view.findViewById(R.id.sat_critical_reading)
            val rs = it.sat_critical_reading_avg_score
            satReading?.text = "Reading: $rs"
            var satWriting:TextView? = view.findViewById(R.id.sat_writing)
            val ws = it.sat_writing_avg_score
            satWriting?.text = "Writing: $ws"
            var satMath:TextView? = view.findViewById(R.id.sat_math)
            val ms = it.sat_math_avg_score
            satMath?.text = "Math: $ms"
            var schoolDesc : TextView? = view.findViewById(R.id.school_desc)
            schoolDesc?.text = db.schoolsModel.overview_paragraph
            var schoolAddress: TextView = view.findViewById(R.id.school_address)
            schoolAddress.text = db.schoolsModel.location.substringBefore("(")
            var schoolGraduation:TextView = view.findViewById(R.id.school_graduation_rate)
            schoolGraduation.text = db.schoolsModel.graduation_rate.toString()
            var schoolCollege:TextView = view.findViewById(R.id.school_college_rate)
            schoolCollege.text = db.schoolsModel.college_career_rate.toString()
            var schoolWebsite:TextView = view.findViewById(R.id.school_website)
            schoolWebsite.text = db.schoolsModel.website
            var schoolPhone:TextView = view.findViewById(R.id.school_phone)
            schoolPhone.text = db.schoolsModel.phone_number

        })

        var backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener{
            (activity as NYSMainActivity).changeFragment(LandingPageFragment())
        }

    }



    companion object {

        fun newInstance(param1: String, param2: String) =
            SchoolFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}