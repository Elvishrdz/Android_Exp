package com.eahm.androidexp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.eahm.androidexp.adapters.MainAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.eahm.androidexp.models.MenuElement
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val menu = listOf(
        MenuElement(
            "Menu is ready",
            "Lets implement some awesome android features!",
            MainActivity::class.java
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMenu.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MainAdapter(menu.reversed())
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }
}