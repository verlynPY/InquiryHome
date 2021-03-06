package com.example.inquiryhome.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.inquiryhome.R

class ExploreDoctorAtivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bundle = intent.extras
        var Speciality = bundle!!.getCharSequence("Speciality")


        setContent {
            MaterialTheme(
                colors = if (isSystemInDarkTheme())
                    MaterialThemeColors.DarkColor else MaterialThemeColors.LigthColor
            ) {
                ShowUsers()
            }
        }
    }
}

    @Composable
    fun ShowUsers(){
        Card(modifier = Modifier
            .fillMaxWidth()
            .preferredHeight(90.dp)
            .absolutePadding(left = 12.dp, right = 12.dp)
            .clip(RoundedCornerShape(20.dp)),
            backgroundColor = MaterialTheme.colors.primary
        ){
            Row(modifier = Modifier.fillMaxWidth().padding(4.dp), Arrangement.Start, verticalAlignment = Alignment.CenterVertically){
                val image = R.mipmap.dafault2
                Image(imageResource(id = image), modifier = Modifier
                    .preferredHeight(80.dp)
                    .preferredWidth(80.dp)
                    .clip(CircleShape)
                )
                Column(modifier = Modifier.absolutePadding(left = 8.dp)) {
                    Text(text = "Name", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Message Here", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }
            }

        }
    }