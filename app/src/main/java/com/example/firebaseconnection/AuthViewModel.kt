package com.example.firebaseconnection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class AuthViewModel : ViewModel() {

    // Instância do FirebaseAuth usada para autenticar o usuário
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    // Estado interno da autenticação, usado apenas dentro da classe
    private val _authState = MutableLiveData<AuthState>()

    // Estado público da autenticação, exposto para a interface observar
    val authState: LiveData<AuthState> = _authState

    // Ao iniciar o ViewModel, verifica se o usuário já está autenticado
    init {
        checkAuthStatus()
    }

    // Verifica se há um usuário autenticado e atualiza o estado
    fun checkAuthStatus(){
        if(auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    // Realiza o login do usuário com email e senha
    fun login(email: String, password: String){
        // Verifica se os campos estão vazios e emite erro, se necessário
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        // Indica que a autenticação está em andamento
        _authState.value = AuthState.Loading

        // Tenta fazer o login com Firebase Authentication
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    // Realiza o cadastro de um novo usuário com email e senha
    fun signup(email: String, password: String){
        if(email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }

        _authState.value = AuthState.Loading

        // Cria novo usuário usando Firebase
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    // Faz logout do usuário e atualiza o estado para "não autenticado"
    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}

// Representa os diferentes estados que a autenticação pode ter
sealed class AuthState {
    object Authenticated : AuthState() // Usuário autenticado com sucesso
    object Unauthenticated : AuthState() // Usuário não autenticado
    object Loading : AuthState() // Autenticação em andamento
    data class Error(val message: String) : AuthState() // Erro na autenticação com mensagem
}
