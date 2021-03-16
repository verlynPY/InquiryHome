package com.example.inquiryhome.model.Chat

class Chat {
    var sender: String? = null
    var receiver: String? = null
    var message: String? = null
    var date: String? = null

    constructor(sender: String?, receiver: String?, message: String?, date: String?) {
        this.sender = sender
        this.receiver = receiver
        this.message = message
        this.date = date
    }

    constructor() {}
}
