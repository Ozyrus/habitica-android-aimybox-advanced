package com.habitrpg.android.habitica.data.local

import com.habitrpg.android.habitica.models.FAQArticle

import io.reactivex.Flowable
import io.realm.RealmResults

interface FAQLocalRepository : ContentLocalRepository {
    fun getArticle(position: Int): Flowable<FAQArticle>

    val articles: Flowable<RealmResults<FAQArticle>>
}
