package com.supmti.sos_application

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.supmti.sos_application.core.navigation.SOSNavigation
import com.supmti.sos_application.core.ui.theme.SOSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SOSTheme {
                SOSNavigation()
            }
        }
    }
}
