package com.sablab.memotodolist

import android.app.Application
import com.sablab.memotodolist.database.MemoTodoDataBase
import com.sablab.memotodolist.repos.MemoTodoRepo
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class MemoApplication : Application() {
    val scope: CoroutineScope = CoroutineScope(SupervisorJob())
    val database by lazy { MemoTodoDataBase.getDataBaseInstance(this, scope) }
    val repostory by lazy { MemoTodoRepo(database.memoToDoDao()) }
}