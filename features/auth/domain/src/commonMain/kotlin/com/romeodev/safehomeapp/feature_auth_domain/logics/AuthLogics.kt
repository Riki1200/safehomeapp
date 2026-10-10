package com.romeodev.safehomeapp.feature_auth_domain.logics

data class AuthLogics(
    val signIn: SignInLogic,
    val signUp: SignUpLogic,
    val socialSignIn: SocialSignInLogic,
    val validateEmail: ValidateEmailLogic,
    val validatePassword: ValidatePasswordLogic,
)
