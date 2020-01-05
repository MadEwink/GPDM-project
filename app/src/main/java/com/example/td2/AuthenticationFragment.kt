package com.example.td2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.authentication_fragment.view.*

class AuthenticationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.authentication_fragment, container, false)

        view.button_signup.setOnClickListener { findNavController().navigate(R.id.action_authenticationFragment_to_signupFragment) }
        view.button_login.setOnClickListener { findNavController().navigate(R.id.action_authenticationFragment_to_loginFragment) }

        return view
    }
}