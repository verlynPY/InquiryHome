package com.example.inquiryhome.viewmodel


import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.example.inquiryhome.model.Chat.Chat
import com.example.inquiryhome.model.Chat.ManageChat
import com.example.inquiryhome.model.DialogManager
import com.example.inquiryhome.model.FirebaseData
import com.example.inquiryhome.model.Login.Login
import com.example.inquiryhome.model.ManageDoctor
import com.example.inquiryhome.model.Register.RegisterUsersPatient
import com.example.inquiryhome.model.RegisterUsersDoctor
import com.example.inquiryhome.model.User.UserDoctor
import com.example.inquiryhome.model.User.UserPacient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class MainViewModel: ViewModel() {

    private val firebaseData: FirebaseData = FirebaseData()
    private val manageDoctor: ManageDoctor = ManageDoctor()
    private lateinit var manageChat: ManageChat
    var listDoctor = MutableLiveData<ArrayList<UserDoctor>>()
    var listChat = MutableLiveData<Chat>()
    val isActive = mutableStateOf(false)
    val HasValue = mutableStateOf(false)

    fun ShowProfile(context: Context, id: String): MutableLiveData<UserPacient>{
        return firebaseData.GetUserInfo(context, id)
    }

    fun ShowProfileDoctor(context: Context, id: String): MutableLiveData<UserDoctor>{
        return firebaseData.GetUserInfoDoctor(context, id)
    }

     fun ShowDoctors(Speciality: String){

        listDoctor.value = (manageDoctor.GetAllDoctor(Speciality))
         Thread.sleep(500)
        if(listDoctor.value != null){
            HasValue.value = true
        }

        //return manageDoctor.GetAllDoctor()
    }


    fun ShowPatient(): MutableLiveData<List<UserPacient>>{
        return manageDoctor.GetPatient()
    }

    fun GettingMessages(myid: String, userid: String,
                        fragmentManager: FragmentManager): MutableLiveData<Chat>{
        manageChat = ManageChat(fragmentManager)
        return manageChat.readMessagges(myid, userid)
    }

    /*fun GettingMessagess(myid: String, userid: String,
                        fragmentManager: FragmentManager){
        manageChat = ManageChat(fragmentManager)
        //listChat.value = manageChat.readMessagges(myid, userid)
        //return manageChat.readMessagges(myid, userid)
    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    fun SendMessage(sender: String, receiver: String, message: String,
                    fragmentManager: FragmentManager){
        manageChat = ManageChat(fragmentManager)
        if(sender.isNullOrEmpty() || receiver.isNullOrEmpty() || message.isNullOrEmpty()){
            var dialog = DialogManager("Error message")
            dialog.show(fragmentManager, ContentValues.TAG)
        }
        else{
            try{
                val currentDateTime = LocalDateTime.now()
                var date = currentDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))
                manageChat.SendMessage(sender, receiver, message, date)
            }
            catch (e: Exception){
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
        }

    }

    fun LogIn(context: Context, fragmentManager: FragmentManager, Email: String, Password: String){
        val login = Login(fragmentManager)
        if(Email.isNullOrEmpty() || Password.isNullOrEmpty()){
            var dialog = DialogManager("You should complete all fields")
            dialog.show(fragmentManager, ContentValues.TAG)
        }
        else{
            try{
                login.PatientLogin(context, Email, Password, true, false)
            }
            catch (e: Exception){
                var dialog = DialogManager("$e")
                dialog.show(fragmentManager, ContentValues.TAG)
            }
        }

    }

    fun LogInDoctor(context: Context, fragmentManager: FragmentManager, Email: String, Password: String){
        val login = Login(fragmentManager)
        if(Email.isNullOrEmpty() || Password.isNullOrEmpty()){
            var dialog = DialogManager("You should complete all fields")
            dialog.show(fragmentManager, ContentValues.TAG)
        }
        else{
            try{
                login.PatientLogin(context, Email, Password, false, true)
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