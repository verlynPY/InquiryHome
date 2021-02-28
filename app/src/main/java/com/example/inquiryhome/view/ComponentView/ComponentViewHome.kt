package com.example.inquiryhome.view.ComponentView

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.inquiryhome.model.Utilss.GoExploreDoctor
import com.example.inquiryhome.view.buttonShape

@Composable
fun HomeView(context: Context){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Column(modifier = Modifier.fillMaxHeight().padding(bottom = 8.dp), verticalArrangement = Arrangement.Center) {
            val speciality = listOf(
                "Dentista",
                "Pediatra",
                "Nutriologo"
            )

            val text = remember { mutableStateOf("") }
            val isOpen = remember { mutableStateOf(false) }
            val openCloseOfDropDownList: (Boolean) -> Unit = {
                isOpen.value = it
            }
            val userSelectedString: (String) -> Unit = {
                text.value = it
            }

            Box {
                Column {
                    OutlinedTextField(
                        value = text.value,
                        onValueChange = { text.value = it },
                        placeholder = { Text(text = "Speciality") },
                        modifier = Modifier.fillMaxWidth(0.7f),
                        inactiveColor = MaterialTheme.colors.primary,
                    )
                    DropDownList(
                        requestToOpen = isOpen.value,
                        list = speciality,
                        openCloseOfDropDownList,
                        userSelectedString
                    )
                }

                Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Transparent)
                        .padding(10.dp)
                        .clickable(
                            onClick = { isOpen.value = true }
                        )
                )
            }
            Button(onClick = {
                GoExploreDoctor(text.value.toString(), context = context)
            }, modifier = Modifier
                .preferredHeight(50.dp)
                .fillMaxWidth(0.7f),
                shape = buttonShape
            ) {
                Text(text = "Inquiry", style = MaterialTheme.typography.h6)
            }
        }
    }

}

    @Composable
    fun SpecialitySelection(){
        val speciality = listOf(
            "Dentista",
            "Pediatra",
            "Nutriologo"
        )

        val text = remember { mutableStateOf("") }
        val isOpen = remember { mutableStateOf(false) }
        val openCloseOfDropDownList: (Boolean) -> Unit = {
            isOpen.value = it
        }
        val userSelectedString: (String) -> Unit = {
            text.value = it
        }

        Box {
            Column {
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    placeholder = { Text(text = "Text Here") },
                    modifier = Modifier.fillMaxWidth(0.7f),
                    inactiveColor = MaterialTheme.colors.primary,
                )
                DropDownList(
                    requestToOpen = isOpen.value,
                    list = speciality,
                    openCloseOfDropDownList,
                    userSelectedString
                )
            }
            Button(onClick = {

            }, modifier = Modifier
                .preferredHeight(50.dp)
                .fillMaxWidth(0.7f),
                shape = buttonShape
            ) {
                Text(text = "Inquiry", style = MaterialTheme.typography.h6)
            }
            Spacer(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Transparent)
                    .padding(10.dp)
                    .clickable(
                        onClick = { isOpen.value = true }
                    )
            )
        }
    }

    @Composable
    fun DropDownList(
        requestToOpen: Boolean = false,
        list: List<String>,
        request: (Boolean) -> Unit,
        selectedString: (String) -> Unit
    ){
        DropdownMenu(
            dropdownModifier = Modifier.fillMaxWidth(),
            toggle = {

            },
            expanded = requestToOpen,
            onDismissRequest = { request(false) }
        ){
            list.forEach {
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(),onClick = {
                    request(false)
                    selectedString(it)
                }) {
                    Text(it, modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.Start))
                }
            }
        }
}