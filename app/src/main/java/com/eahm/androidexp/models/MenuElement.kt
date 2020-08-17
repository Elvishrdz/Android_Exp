package com.eahm.androidexp.models

data class MenuElement(
    val title : String,
    val description : String,
    val activity : Class<*>
)