package kz.validol.hacknu.auth.domain

import io.reactivex.Observable
import kz.validol.hacknu.auth.data.AuthRepository
import kz.validol.hacknu.entities.LoginResponse

class LoginInteractor(
    private val authRepository: AuthRepository
) : LoginUseCase {

    override fun execute(
        loginEntity: LoginEntity
    ): Observable<LoginResponse> {
        return authRepository.login(
            username = loginEntity.username!!,
            password = loginEntity.password!!
        )
    }
}