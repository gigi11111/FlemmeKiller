package fr.theoffice.flemmekiller

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.theoffice.flemmekiller.ObectiveRepository.Singelton.ObjectiveList
import fr.theoffice.flemmekiller.ObectiveRepository.Singelton.databaseRef

class ObectiveRepository {
    object Singelton {
        //se connecter à la bdd
        val databaseRef = FirebaseDatabase.getInstance().getReference("Objective")
        val ObjectiveList = arrayListOf<ObjectiveModel>()
    }

    fun updateData(){
        databaseRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                ObjectiveList.clear()
                for (ds in snapshot.children){
                    //on reconstruit un Objectif
                    val Objective = ds.getValue(ObjectiveModel::class.java)
                    if(Objective != null){
                        ObjectiveList.add(Objective)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
    fun updateObjective(Objective: ObjectiveModel){
        databaseRef.child(Objective.id).setValue(Objective)
    }
    //envoyer en bdd

    fun insertObjective(Objective: ObjectiveModel) = databaseRef.child(Objective.id).setValue()
}