package com.dsoft.mytestapplication

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dsoft.mytestapplication.util.Notifier
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeNotifier()
    }

    private fun observeNotifier() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Notifier.notificationChannel.collectLatest {
                    showDialog(it)
                }
            }
        }
    }

    private fun showDialog(message: String) {
        // Create the object of AlertDialog Builder class
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        // Set the message show for the Alert time
        builder.setMessage(message)
        // Set Alert Title
        builder.setTitle("Alert!")
        builder.setPositiveButton("Ok") { dialog: DialogInterface?, _: Int ->
            dialog?.dismiss()
        }
        // Create the Alert dialog
        val alertDialog: AlertDialog = builder.create()
        // Show the Alert Dialog box
        alertDialog.show()
    }
}