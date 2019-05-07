package com.gurukul.sqlitekt

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var i:Int = 0

        var dBase = openOrCreateDatabase("DB", Context.MODE_PRIVATE,null)

        btnLogin.setOnClickListener {
            var c = dBase.query("Table1",null,"email=? and pass=?", arrayOf(etLEmail.text.toString(),etLPass.text.toString()),null,null,null)
            i = c.count
            if(i != 0){
                var builder = AlertDialog.Builder(this@LoginActivity)
                builder.setPositiveButton("Okay", DialogInterface.OnClickListener { dialog, which ->
                }).setMessage("Successful....").show()
            }else{
                var builder = AlertDialog.Builder(this@LoginActivity)
                builder.setPositiveButton("Okay",DialogInterface.OnClickListener { dialog, which ->

                }).setMessage("Failed....").show()
            }
        }
    }
}
