package com.example.joyce.translateme.ui.registration


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.joyce.translateme.R
import com.example.joyce.translateme.ui.MainActivity
import org.koin.android.viewmodel.ext.android.sharedViewModel


class RegistrationWaitFragment : Fragment() {

    private val vm: RegistrationViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_wait, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.saveUser()

        vm.result.observe(this, Observer {
            if (it == null || !it.approved) {
                findNavController().navigate(R.id.userRegistrationFragment)
            } else {
                requireContext().startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        })
    }

}
