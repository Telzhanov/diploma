package kz.validol.hacknu.auth.data

import io.reactivex.Observable
import kz.validol.hacknu.Api
import kz.validol.hacknu.App
import kz.validol.hacknu.entities.LoginResponse

class AuthRepository(private val apiClient: Api) {

    fun login(
        username: String,
        password: String
    ): Observable<LoginResponse> {
        return apiClient.login(
            username = username,
            password = password,
            token = App.fcmDeviceId,
            login = username
        )
    }
}