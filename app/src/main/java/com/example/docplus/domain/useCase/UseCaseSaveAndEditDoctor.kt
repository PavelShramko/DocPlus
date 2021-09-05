package com.example.docplus.domain.useCase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.docplus.domain.Doctor
import com.example.docplus.domain.DoctorRepository
import kotlinx.coroutines.*
import javax.inject.Inject

private val empty = Doctor(
    id = 0,
    type = "",
    name = "",
    time = ""
)

class UseCaseSaveAndEditDoctor @Inject constructor (
    private val repository: DoctorRepository/*,
    private val scope: CoroutineScope*/
    ) {

    private val edited = MutableLiveData(empty)

    private val scope = CoroutineScope(Dispatchers.Main.immediate + Job())

    fun save() {
        scope.launch {
            withContext(Dispatchers.IO) {
                edited.value?.let {
                    repository.save(it)
                }
            }
            // Попробовать через дефолтный поток с поствелью
            edited.postValue(empty)
        }
    }

    fun updateContentById(
        id: Long,
        type: String,
        name: String,
        time: String
    ) = scope.launch {
        withContext(Dispatchers.IO) {
            repository.updateContentById(id, type, name, time/*, visits*/)
        }
    }

    fun changeContent(type: String, name: String, time: String) {
        val editType = type.trim()
        val editName = name.trim()
        val edinTime = time.trim()
        if (edited.value?.type == editType &&
            edited.value?.name == editName &&
            edited.value?.time == edinTime
        ) {
            return
        }
        edited.value = edited.value?.copy(type = editType, name = editName, time = edinTime)
    }
}