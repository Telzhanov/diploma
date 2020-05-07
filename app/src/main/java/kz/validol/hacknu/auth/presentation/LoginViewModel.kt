package kz.validol.hacknu.auth.presentation

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.validol.hacknu.App
import kz.validol.hacknu.auth.domain.LoginEntity
import kz.validol.hacknu.auth.domain.LoginUseCase
import kz.validol.hacknu.entities.LoginResponse

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val loginResponseLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
    private val loginErrorLiveData: MutableLiveData<String> = MutableLiveData()

    fun getLoginResponseLiveData(): LiveData<LoginResponse> = loginResponseLiveData
    fun getLoginErrorLiveData(): LiveData<String> = loginErrorLiveData

    @SuppressLint("CheckResult")
    fun onLoginClicked(
        username: String,
        password: String
    ) {
        loginUseCase.execute(LoginEntity(username, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.code == 0) {
                    loginResponseLiveData.value = it
                    App.user = it.user
                }
            }, {
                loginErrorLiveData.value = it.localizedMessage
            })
    }

}