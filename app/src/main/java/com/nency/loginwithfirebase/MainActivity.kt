package com.nency.loginwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nency.loginwithfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth
//    lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
//        user = FirebaseAuth.getInstance().currentUser!!
//        if (user.uid.isNotEmpty()) {
//            startActivity(Intent(this,HomeActivity::class.java))
//            finish()
//        }

        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        binding.cardLogin.setOnClickListener {

            var email = binding.edtEmail.text.toString()
            var password = binding.edtPassword.text.toString()

            if(email.isNotEmpty()){
                if(email.contains("@") && email.contains(".")){
                    register(email, password)
                }else{
                    Toast.makeText(this, "Enter Valid Email!!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please enter email!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun register(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else {

            }
        }.addOnFailureListener{

            }
        }
}
