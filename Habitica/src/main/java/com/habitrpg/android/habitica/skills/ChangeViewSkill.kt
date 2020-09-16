package com.habitrpg.android.habitica.skills

import android.content.Context
import android.content.Intent
import com.habitrpg.android.habitica.ui.activities.*
import com.justai.aimybox.Aimybox
import com.justai.aimybox.api.aimybox.AimyboxRequest
import com.justai.aimybox.api.aimybox.AimyboxResponse
import com.justai.aimybox.core.CustomSkill
import com.justai.aimybox.model.Response

class ChangeViewSkill(private val context: Context): CustomSkill<AimyboxRequest, AimyboxResponse> {

    override fun canHandle(response: AimyboxResponse) = response.action == "changeView"

    override suspend fun onResponse(
            response: AimyboxResponse,
            aimybox: Aimybox,
            defaultHandler: suspend (Response) -> Unit
    ) {
        val intent = when (response.intent) {
            "settings" -> Intent(context, PrefsActivity::class.java)
            "characteristics" -> Intent(context, FixCharacterValuesActivity::class.java)//
            "profile" -> Intent(context, FullProfileActivity::class.java)//
            "about" -> Intent(context, AboutActivity::class.java)
            else -> Intent(context, MainActivity::class.java)
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        aimybox.standby()
        context.startActivity(intent)
    }
}