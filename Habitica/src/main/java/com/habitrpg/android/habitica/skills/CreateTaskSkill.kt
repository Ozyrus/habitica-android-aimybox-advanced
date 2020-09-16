package com.habitrpg.android.habitica.skills

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.habitrpg.android.habitica.models.tasks.Task
import com.habitrpg.android.habitica.ui.activities.TaskFormActivity
import com.justai.aimybox.Aimybox
import com.justai.aimybox.api.aimybox.AimyboxRequest
import com.justai.aimybox.api.aimybox.AimyboxResponse
import com.justai.aimybox.core.CustomSkill
import com.justai.aimybox.model.Response
import java.util.HashMap

class CreateTaskSkill(private val context: Context): CustomSkill<AimyboxRequest, AimyboxResponse> {


    override fun canHandle(response: AimyboxResponse) = response.action == "createTask"

    override suspend fun onResponse(
            response: AimyboxResponse,
            aimybox: Aimybox,
            defaultHandler: suspend (Response) -> Unit
    ) {
        val intent = Intent(context, TaskFormActivity::class.java)
        val additionalData = HashMap<String, Any>()
        val type = response.intent
        additionalData["viewed task type"] = when (type) {
            "habit" -> Task.TYPE_HABIT
            "daily" -> Task.TYPE_DAILY
            "todo" -> Task.TYPE_TODO
            "reward" -> Task.TYPE_REWARD
            else -> ""
        }
        val bundle = Bundle()
        bundle.putString(TaskFormActivity.TASK_TYPE_KEY, type)
        if (response.data != null) {
            bundle.putString("activity_name", response.data?.get("taskName")?.asString)
            bundle.putString("activity_description", response.data?.get("taskDescription")?.asString)
            response.data?.get("taskSentiment")?.asBoolean?.let { bundle.putBoolean("sentiment", it) } // no not have null problems
            bundle.putString("activity_difficulty", response.data?.get("taskDifficulty")?.asString)

        } else {
            bundle.putString("activity_name", type)
            bundle.putString("activity_description", "Описание")
            bundle.putBoolean("sentiment", true)
            bundle.putString("activity_difficulty", "hard")
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtras(bundle)
        aimybox.standby()
        context.startActivity(intent)
    }
}