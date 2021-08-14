package com.example.docplus.domain.useCase

import com.example.docplus.domain.DoctorRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class UseCaseRemove @Inject constructor (
    private val repository: DoctorRepository/*,
    private val scope: CoroutineScope*/
) {

    val scope = CoroutineScope(Dispatchers.Default + Job())

    fun removeById(id: Long) {
        scope.launch {
            withContext(Dispatchers.IO) {
                repository.removeById(id)
            }
        }
    }
}
