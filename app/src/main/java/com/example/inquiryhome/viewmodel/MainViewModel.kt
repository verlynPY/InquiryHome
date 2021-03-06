package com.example.inquiryhome.viewmodel


import android.content.ContentValues
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.FirebaseData
import com.example.inquiryhome.model.Login.Login
import com.example.inquiryhome.model.Register.RegisterUsersPatient
import com.example.inquiryhome.model.User.UserPacient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val firebaseData: FirebaseData = FirebaseData()
    val isActive = mutableStateOf(false)

    fun ShowProfile(context: Context, id: String): MutableLiveData<UserPacient>{
        return firebaseData.GetUserInfo(context, id)
    }

    fun LogIn(context: Context, fragmentManager: FragmentManager, Email: String, Password: String){
        val login = Login(fragmentManager)
        if(Email.isNullOrEmpty() || Password.isNullOrEmpty()){
            var dialog = DialogManager("You should complete all fields")
            dialog.show(fragmentManager, ContentValues.TAG)
        }
        else{
            try{
                login.PatientLogin(context, Email, Password)
            }
            catch (e: Exception){
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
        }

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
                viewModelScope.launch {
                    isActive.value = true
                    delay(1000)
                    registerUsersPatient.RegisterPatient(context, patient)
                    //isActive.value = false
                }

            }
            catch(e: Exception){
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
        }

    }



}