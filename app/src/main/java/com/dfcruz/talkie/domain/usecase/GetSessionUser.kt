package com.dfcruz.talkie.domain.usecase

import com.dfcruz.talkie.domain.User
import javax.inject.Inject

class GetSessionUser @Inject constructor() {

    operator fun invoke(): User {
        return User()
    }
}