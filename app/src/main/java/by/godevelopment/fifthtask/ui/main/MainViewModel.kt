package by.godevelopment.fifthtask.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.fifthtask.R
import by.godevelopment.fifthtask.commons.TAG
import by.godevelopment.fifthtask.domain.helpers.StringHelper
import by.godevelopment.fifthtask.domain.models.GeographicPointModel
import by.godevelopment.fifthtask.domain.usecase.PrepareListOfPointsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prepareListOfPointsUseCase: PrepareListOfPointsUseCase,
    private val stringHelper: StringHelper
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState?> = MutableStateFlow(null)
    val uiState: StateFlow<UiState?> = _uiState

    private val _uiEvent  = MutableSharedFlow<String>(0)
    val uiEvent: SharedFlow<String> = _uiEvent

    init {
        fetchGeographicData()
    }

    fun fetchGeographicData() {
        viewModelScope.launch {
            prepareListOfPointsUseCase()
                .onStart {
                    Log.i(TAG, "viewModelScope.launch: .onStart")
                    UiState(
                        isFetchingData = true
                    )
                }
                .catch { exception ->
                    Log.i(TAG, "viewModelScope.catch ${exception.message}")
                    _uiState.value = UiState(
                        isFetchingData = false
                    )
                    delay(1000)
                    _uiEvent.emit(stringHelper.getString(R.string.alert_error_loading))
                }
                .collect {
                    Log.i(TAG, "viewModelScope.collect = ${it.size}")
                    _uiState.value = null
                    _uiState.value = UiState(
                        data = it
                    )
                }
        }
    }

    data class UiState(
        val isFetchingData: Boolean = false,
        val data: List<GeographicPointModel> = listOf()
    )
}