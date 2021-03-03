package com.example.inquiryhome.viewmodel


import android.content.ContentValues
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.FirebaseData
import com.example.inquiryhome.model.Register.RegisterUsersPatient
import com.example.inquiryhome.model.UserPacient

class MainViewModel: ViewModel() {

    private val firebaseData: FirebaseData = FirebaseData()

    fun ShowProfile(context: Context, id: String): MutableLiveData<UserPacient>{
        return firebaseData.GetUserInfo(context, id)
    }

    fun GetPatientData(context: Context, fragmentManager: FragmentManager, patient: UserPacient){
        val registerUsersPatient = RegisterUsersPatient(fragmentManager)

        if(patient.Name.isNullOrEmpty() || patient.toString().isNullOrEmpty()
                || patient.Email.toString().isNullOrEmpty() || patient.Birth.toString().isNullOrEmpty()
                || patient.Password.toString().isNullOrEmpty()
        ){
            var dialog = DialogManager("You should complete all the fields")
            dialog.show(fragmentManager, ContentValues.TAG)
        }
        else{
            try{
                registerUsersPatient.RegisterPatient(context, patient)
            }
            catch(e: Exception){
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
        }

    }

}