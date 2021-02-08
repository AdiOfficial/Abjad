package com.example.animalname

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var a: Button
    lateinit var b: Button
    lateinit var c: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        a = findViewById(R.id.bt_play)
        b = findViewById(R.id.bt_about)
        c = findViewById(R.id.bt_close)

        a.setOnClickListener(this)
        b.setOnClickListener(this)
        c.setOnClickListener(this)
    }
    override fun onClick(view: View){
        when (view.id){
            R.id.bt_play -> {
                val pindah =
                    Intent(this@MainActivity, Play::class.java)
                startActivity(pindah)
            }
            R.id.bt_about ->{
                val pindah =
                    Intent(this@MainActivity, TentangPengembang::class.java)
                startActivity(pindah)
            }
            R.id.bt_close -> {
                AlertDialog.Builder(this)
                    .setTitle("Keluar Dari Aplikasi?")
                    .setMessage("Apakah Anda Ingin Keluar Dari Aplikasi?")
                    .setPositiveButton("Ya",{dialog, which->finish()})
                    .setNegativeButton("Tidak", null)
                    .show()

            }
        }

    }
}
