package com.pogreb.leasingshift.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pogreb.leasingshift.feature.main.ui.MainScreen
import com.pogreb.leasingshift.ui.theme.LeasingShiftTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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