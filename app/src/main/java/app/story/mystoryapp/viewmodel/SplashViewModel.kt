package app.story.mystoryapp.viewmodel

import androidx.lifecycle.ViewModel
import app.story.mystoryapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    fun getAuthToken(): Flow<String?> = authRepository.getAuthToken()

}