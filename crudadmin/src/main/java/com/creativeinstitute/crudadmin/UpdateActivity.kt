package com.creativeinstitute.crudadmin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.creativeinstitute.crudadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.updateButton.setOnClickListener{
            val referencePhone  = binding.referencePhone.text.toString()
            val updateName  = binding.updateName.text.toString()
            val updateOperator  = binding.updateOperator.text.toString()
            val updateLocation  = binding.updateLocation.text.toString()

            updateData(referencePhone, updateName, updateOperator, updateLocation)
        }
    }

    private fun updateData(phone: String, name: String, operator: String, location: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Phone Directory")
        val user = mapOf<String, String>("name" to name, "operator" to operator, "location" to location)
        databaseReference.child(phone).updateChildren(user).addOnSuccessListener {
            binding.referencePhone.text.clear()
            binding.updateName.text.clear()
            binding.updateOperator.text.clear()
            binding.updateLocation.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }
    }

}