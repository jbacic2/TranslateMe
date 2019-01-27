package com.example.joyce.translateme.ui.registration

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.joyce.translateme.R
import org.koin.android.viewmodel.ext.android.viewModel

class RegistrationActivity : AppCompatActivity() {

    private val vm: RegistrationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            findNavController(R.id.nav_host_fragment).navigate(R.id.registrationWaitFragment)
        }
    }
}
