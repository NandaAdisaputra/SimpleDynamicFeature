package com.nandaadisaputra.dynamicfeature.core

class UserRepository(private val session: SessionManager) {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(session: SessionManager): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(session)
            }
    }

    fun loginUser(username: String) {
        session.createLoginSession()
        session.saveToPreference(SessionManager.KEY_USERNAME, username)
    }

    fun getUser() = session.getFromPreference(SessionManager.KEY_USERNAME)

    fun isUserLogin() = session.isLogin

    fun logoutUser() = session.logout()
}