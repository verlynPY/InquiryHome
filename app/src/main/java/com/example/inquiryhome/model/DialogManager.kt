package com.example.inquiryhome.model

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class DialogManager(var Error: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("$Error")
                    .setPositiveButton("Ok",
                            DialogInterface.OnClickListener { dialog, id ->

                            })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}