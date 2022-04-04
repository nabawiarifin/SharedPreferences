package com.binar.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.sharedpreferences.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener{
            val id: Int = Integer.parseInt(binding.idEditText.text.toString())
            val name: String = binding.nameEditText.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Toast.makeText(this,"Data saved", Toast.LENGTH_SHORT).show()
        }

        binding.btnView.setOnClickListener{
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")

            if (sharedIdValue == 0 && sharedNameValue.equals("defaultname")) {
                binding.nameTextView.text = "default name: ${sharedNameValue}"
                binding.idTextView.text = "default id: ${sharedIdValue.toString()}"
                Toast.makeText(this,"Data View Empty", Toast.LENGTH_SHORT).show()
            } else {
                binding.nameTextView.setText(sharedNameValue).toString()
                binding.idTextView.text = sharedIdValue.toString()
                Toast.makeText(this, "Data View Shown",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.nameTextView.text = ""
            binding.idTextView.text = ""
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }


    }



}