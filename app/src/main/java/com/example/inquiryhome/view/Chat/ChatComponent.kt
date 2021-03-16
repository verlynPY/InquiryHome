package com.example.inquiryhome.view.Chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.inquiryhome.model.Chat.Chat


    var textColor = Color(200,200,200)

    @Composable
    fun ChatItems(MyId: String, chat: ArrayList<Chat>){
        LazyColumn{
            itemsIndexed(items = chat){ index, chat ->
                Column(modifier = Modifier.padding(top = 1.dp, start = 6.dp, bottom = 1.dp, end = 6.dp)) {
                    if(chat.sender.equals(MyId)){
                        ItemRigth(chat)
                    }
                    else{
                        ItemLeft(chat)
                    }
                }
            }
        }
    }

    @Composable
    fun ItemRigth(chat: Chat){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(2.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.primary)
                ){
                Row() {
                    Text(text = "${chat.message}", color = textColor, modifier = Modifier.padding(8.dp))
                    Text(text = "${chat.date}", color = Color(240,240,240), fontWeight = FontWeight.Medium, fontSize = 12.sp,
                            modifier = Modifier.padding(6.dp))
                }
            }
        }
    }

    @Composable
    fun ItemLeft(chat: Chat){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Box(modifier = Modifier
                .wrapContentSize()
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.onSecondary)) {
                Row() {
                    Text(text = "${chat.message}", color = Color(240,240,240), modifier = Modifier.padding(8.dp))
                    Text(text = "${chat.date}", color = Color(240,240,240),fontWeight = FontWeight.Medium, fontSize = 12.sp,
                            modifier = Modifier.padding(6.dp))
                }
            }
        }
    }