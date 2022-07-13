package br.com.sailboat.zerotohero.android.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VS, VA> : ViewModel() {
    abstract val viewState: VS
    abstract fun dispatchViewAction(viewAction: VA)
}
