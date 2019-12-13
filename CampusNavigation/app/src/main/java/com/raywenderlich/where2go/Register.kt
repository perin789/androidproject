package com.example.myapplication3


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class Register : Fragment() , View.OnClickListener {

    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    private fun createAccount(email: String, password: String) {
        Log.d(ContentValues.TAG, "createAccount:$email")


        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //   updateUI(null)
                }

                // ...
            }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.emailCreateAccountButton -> createAccount(fieldEmail.text.toString(), fieldPassword.text.toString())

        }
    }


}
