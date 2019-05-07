package com.gurukul.sqlitekt

import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dBase = openOrCreateDatabase("DB", Context.MODE_PRIVATE,null)
        dBase.execSQL("create table if not exists Table1 (_id integer primary key autoincrement, name varchar(20), email varchar(20), pass varchar(20), gender varchar(10), lang varchar(10), city varchar(20)) ")

        btnSignin.setOnClickListener {
            var cv = ContentValues()
            cv.put("name",etName.text.toString())
            cv.put("email",etEmail.text.toString())
            cv.put("pass",etPass.text.toString())
            var selectedId = rdGender.checkedRadioButtonId
            var srb = findViewById<RadioButton>(selectedId) as RadioButton
            cv.put("gender",srb.text.toString())
            var tempLang:String = ""
            if(chkAnd.isChecked){
                tempLang = tempLang + " " + chkAnd.text.toString()
            }
            if (chkJava.isChecked){
                tempLang = tempLang + " " + chkJava.text.toString()
            }
            if (chkKotlin.isChecked){
                tempLang = tempLang + " " + chkKotlin.text.toString()
            }
            cv.put("lang",tempLang)
            cv.put("city",spCt.selectedItem.toString())

            var res = dBase.insert("Table1",null,cv)
            if(res != -1L){
                var builder = AlertDialog.Builder(this@MainActivity)
                builder.setPositiveButton("Okay",DialogInterface.OnClickListener { dialog, which ->
                    var i = Intent(this@MainActivity,LoginActivity::class.java)
                    startActivity(i)
                }).setMessage("Data inserted....").show()
            }else{
                var builder = AlertDialog.Builder(this@MainActivity)
                builder.setPositiveButton("Okay",DialogInterface.OnClickListener { dialog, which ->
                    var i = Intent()
                    startActivity(i)
                }).setMessage("Data insertion failed...").show()
            }
        }

        btnL.setOnClickListener {
            var i = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(i)
        }
    }
}
