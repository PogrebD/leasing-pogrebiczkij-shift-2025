package com.pogreb.leasingshift.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pogreb.leasingshift.main.ui.MainScreen
import com.pogreb.leasingshift.ui.theme.LeasingShiftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeasingShiftTheme {
                MainScreen()
            }
        }
    }
}