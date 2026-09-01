package com.alzimerahmed.omnitype

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.alzimerahmed.omnitype.manager.CommandManager
import com.alzimerahmed.omnitype.manager.StatsManager

class OmniTypeViewModel(application: Application) : AndroidViewModel(application) {
    val prefs: SharedPreferences = application.getSharedPreferences("settings", Context.MODE_PRIVATE)
    val keyManager = (application as OmniTypeApp).keyManager
    val commandManager = CommandManager(application)
    val statsManager = StatsManager(application)
}
