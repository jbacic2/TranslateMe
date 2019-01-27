package com.example.joyce.translateme.ui.registration


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.joyce.translateme.R
import kotlinx.android.synthetic.main.fragment_user_registration.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class UserRegistrationFragment : Fragment() {

    private val vm: RegistrationViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        continueButton.setOnClickListener {
            vm.storeUserInfo(usernameEditText.text.toString(), nameEditText.text.toString())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                findNavController().navigate(R.id.permissionsGrantFragment)
            } else {
                findNavController().navigate(R.id.registrationWaitFragment)
            }
        }

        vm.result.observe(this, Observer {
            if (it != null && it.approved) {
                errorText.text = "An error occurred."
            }
        })
    }


}
