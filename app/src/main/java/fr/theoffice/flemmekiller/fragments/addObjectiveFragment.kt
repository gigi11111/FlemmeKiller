package com.example.flemkillergeraud.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import fr.theoffice.flemmekiller.MainActivity
import fr.theoffice.flemmekiller.ObectiveRepository
import fr.theoffice.flemmekiller.ObjectiveModel
import fr.theoffice.flemmekiller.R
class addObjectiveFragment (
    private val context: MainActivity
        ) : Fragment() {
    //private var TitreObjective:
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_add_objective, container, false)
        val addObjectiveBotton = view.findViewById<Button>(R.id.add_button)
        addObjectiveBotton.setOnClickListener(sendForm(view))
        return view
    }

    private fun sendForm(view: View) {
        val ObjectiveName = view.findViewById<EditText>(R.id.nameObjective_imput).text.toString()
        val ObjectiveContent =
            view.findViewById<EditText>(R.id.contentObjective_imput).text.toString()
        val username = "1"
        val iduser = "15"
        // creation de l'obejet d'envoie à la base de donnée
        val Objectives = ObjectiveModel(
            iduser,
            ObjectiveName,
            ObjectiveContent,
            username
        )
        val repo = ObectiveRepository
        repo.insertObjective(Objectives)
    }
}