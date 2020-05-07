package kz.validol.hacknu.auth.domain

import io.reactivex.Observable
import kz.validol.hacknu.entities.LoginResponse

interface LoginUseCase {

    fun execute(
        loginEntity: LoginEntity
    ): Observable<LoginResponse>
}