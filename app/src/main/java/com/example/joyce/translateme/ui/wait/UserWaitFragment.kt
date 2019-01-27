package com.example.joyce.translateme.ui.wait


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.joyce.translateme.R
import com.example.joyce.translateme.data.models.Role
import com.example.joyce.translateme.ui.MainViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class UserWaitFragment : Fragment() {

    private val vm: MainViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_wait, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.connectSocket(Role.USER)

        vm.getLocationUpdates()
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
