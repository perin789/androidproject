package com.android.example.campusnavigation


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import android.content.Intent


/**
 * A simple [Fragment] subclass.
 */
class Login : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var t: TextView
    lateinit var Login: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var ly:View? = inflater.inflate(R.layout.fragment_login, container, false)


        Login = ly?.findViewById(R.id.emailCreateAccountButton)!!

        Login.setOnClickListener {
            view!!.findNavController().navigate(ActionOnlyNavDirections(R.id.action_login2_to_mapsActivity))

        }
//
//        Login.setOnClickListener {
//            val intent = Intent(this, MapsActivity::class.java)
//            startActivity(intent);
//        }
        return ly
    }


    private fun signIn(email: String, password: String) {
        Log.d(ContentValues.TAG, "signIn:$email")




        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("chirag", "signInWithEmail:success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("hello", "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }

                // [START_EXCLUDE]


                // [END_EXCLUDE]
            }
        // [END sign_in_with_email]
    }

//    override fun onClick(v: View) {
//        val i = v.id
//        when (i) {
//            R.id.emailCreateAccountButton -> signIn(fieldEmail.text.toString(), fieldPassword.text.toString())
//
//
//        }
//    }


}
