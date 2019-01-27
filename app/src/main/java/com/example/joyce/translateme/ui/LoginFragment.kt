package com.example.joyce.translateme.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.joyce.translateme.R
import com.example.joyce.translateme.data.models.Role
import com.example.joyce.translateme.ui.registration.RegistrationActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class LoginFragment : Fragment() {

    private val vm: MainViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        translatorButton.setOnClickListener {
            findNavController().navigate(R.id.translatorWaitFragment)
        }
        userButton.setOnClickListener {
            findNavController().navigate(R.id.userWaitFragment)
        }

        signOutButton.setOnClickListener {
            vm.signOut()

            requireActivity().finish()
            startActivity(Intent(requireContext(), RegistrationActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        // If we get back here, we should be offline.
        vm.connectSocket(Role.OFFLINE)
    }

}
