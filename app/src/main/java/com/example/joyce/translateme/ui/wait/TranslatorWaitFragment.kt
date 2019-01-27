package com.example.joyce.translateme.ui.wait


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.joyce.translateme.R
import com.example.joyce.translateme.data.models.Role
import com.example.joyce.translateme.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_translator_wait.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class TranslatorWaitFragment : Fragment() {

    private val vm: MainViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_translator_wait, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.otherUser.observe(this, Observer {
            if (it != null) {
                findNavController().navigate(R.id.translatorMapFragment)
            }
        })

        vm.connectSocket(Role.TRANSLATOR)
        vm.getLocationUpdates()

        cancelButton.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onStop() {
        super.onStop()
        vm.stopLocationUpdates()
    }


    override fun onResume() {
        super.onResume()
        vm.getLocationUpdates()
    }

}
