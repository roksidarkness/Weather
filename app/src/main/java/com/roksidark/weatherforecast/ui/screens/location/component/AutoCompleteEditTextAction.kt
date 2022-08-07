package com.roksidark.weatherforecast.ui.screens.location.component

sealed class AutoCompleteEditTextAction {
    object OnDoneClick : AutoCompleteEditTextAction()
    object OnClearClick : AutoCompleteEditTextAction()

    data class OnQueryChange(val query: String) : AutoCompleteEditTextAction()
    data class OnItemSelected<T>(val selectedItem: T) : AutoCompleteEditTextAction()
}