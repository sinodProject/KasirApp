package com.example.kasirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.kasirapp.databinding.ActivityUpdateUserBinding

class UpdateUser : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var userViewModel: UserViewModel
    var idJob : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModel::class.java]


        supportActionBar?.hide()

        binding.updateUserIbBack.setOnClickListener {
            startActivity(Intent(this, DataUser::class.java ))
            finish()
        }

        val id = intent.getIntExtra("id", 0)
        var job = intent.getStringExtra("jobdesc")
        val spinner = binding.updateUserSpJobdesc
        val name = binding.updateUserEtName
        val userName = binding.updateUserEtUsername
        val password = binding.updateUserEtPassword

        idJob = when (job) {
            "Admin" ->{
                1
            }
            "Kasir" -> {
                2
            }
            else -> {
                3
            }

        }


        name.setText(intent.getStringExtra("name"))
        userName.setText(intent.getStringExtra("userName"))
        password.setText(intent.getStringExtra("password"))

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_job,
            android.R.layout.simple_spinner_item
        ).also{ adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.setSelection(idJob)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long){
                spinner.setSelection(p2)
                job = spinner.selectedItem.toString()
            }

            override  fun onNothingSelected(p0: AdapterView<*>?){

            }
        }


        binding.updateUserBtnSave.setOnClickListener {
            val newName = name.text.toString()
            val newUserName = userName.text.toString()
            val newPassword = password.text.toString()

            when{

                newName.isEmpty() -> alert()
                newUserName.isEmpty() -> alert()
                newPassword.isEmpty() -> alert()
                job == "pilih" -> alert()

                else -> {
                    val updatedUser = User(name = newName, job = job.toString(), username = newUserName , password = newPassword, image = 0)
                    updatedUser.id = id
                    userViewModel.updateUser(updatedUser)
                    startActivity(Intent(this, DataUser::class.java))
                    finish()


                }
            }
        }
    }

    private fun alert(){
        Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
    }
}