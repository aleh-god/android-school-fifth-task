package by.godevelopment.fifthtask.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.godevelopment.fifthtask.R
import by.godevelopment.fifthtask.commons.TAG
import by.godevelopment.fifthtask.domain.GeographicPointModel
import by.godevelopment.fifthtask.domain.GetAllGeographicDataUseCase
import by.godevelopment.fifthtask.domain.StringHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllGeographicDataUseCase: GetAllGeographicDataUseCase,
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
            getAllGeographicDataUseCase()
                .onStart {
                    Log.i(TAG, "viewModelScope.launch: .onStart")
                    UiState(
                        isFetchingData = true
                    )
                }
                .catch { exception ->
                    Log.i(TAG, "TableViewViewModel: .catch ${exception.message}")
                    _uiState.value = UiState(
                        isFetchingData = false
                    )
                    delay(1000)
                    _uiEvent.emit(stringHelper.getString(R.string.alert_error_loading))
                }
                .collect {
                    Log.i(TAG, "viewModelScope.launch: .collect = ${it.size}")
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