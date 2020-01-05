package com.example.td2

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.td2.network.Api
import com.example.td2.network.LoginForm
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val coroutineScope = MainScope()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        view.login_button.setOnClickListener { onLoginButtonClick() }

        return view
    }

    private fun onLoginButtonClick() {
        val email = login_email.text.toString()
        if (email == "")
        {
            Toast.makeText(context, "No email", Toast.LENGTH_LONG).show()
            return
        }
        val password = login_password.text.toString()
        if (password == "")
        {
            Toast.makeText(context, "No password", Toast.LENGTH_LONG).show()
            return
        }
        val loginForm = LoginForm(email, password)
        coroutineScope.launch {
            val  response = Api.INSTANCE.userService.login(loginForm)
            if (response.isSuccessful)
            {
                PreferenceManager.getDefaultSharedPreferences(this@LoginFragment.context).edit {
                    putString(SHARED_PREF_TOKEN_KEY, response.body()?.token)
                }
                val intent = Intent(this@LoginFragment.context,MainActivity::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(context, "Could not log in", Toast.LENGTH_LONG).show()
            }
        }
    }
}