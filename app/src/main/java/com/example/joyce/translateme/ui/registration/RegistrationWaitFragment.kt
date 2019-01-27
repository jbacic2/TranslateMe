package com.example.joyce.translateme.ui.registration


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joyce.translateme.R
import com.example.joyce.translateme.ui.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


class RegistrationWaitFragment : Fragment() {

    private val vm: RegistrationViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration_wait, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.saveUser()

        requireContext().startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }

}
