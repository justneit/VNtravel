package com.example.animatedonboarding.model


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animatedonboarding.R
import com.example.animatedonboarding.data.AccountData
import com.example.animatedonboarding.data.Destination
import com.example.animatedonboarding.data.Yatch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel: ViewModel() {
    val state = mutableStateOf(listOf<Destination>())
    init {
        getData()
    }
    private fun getData() {
        viewModelScope.launch {
            val destinations = getDesList()
            state.value = destinations
        }
    }
}
suspend fun getDesList(): List<Destination> {
    val db = FirebaseFirestore.getInstance()
    val destinationList = mutableListOf<Destination>()
    try {
        val querySnapshot = db.collection("Des").get().await()
        for (document in querySnapshot.documents) {
            val destination = document.toObject(Destination::class.java)
            destination?.let {
                it.id = document.id
                destinationList.add(it)
            }
        }
    } catch (e: FirebaseFirestoreException) {
        Log.d("error", "getDataFromFireStore: $e")
    }
    return destinationList
}

suspend fun fetchDestinationByName(name: String): Destination? {
    val db = FirebaseFirestore.getInstance()
    val querySnapshot = db.collection("Des")
        .whereEqualTo("name", name)
        .get()
        .await()

    if (!querySnapshot.isEmpty) {
        val document = querySnapshot.documents[0]
        return document.toObject(Destination::class.java)
    }
    return null
}

class SigninViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val _signupState = MutableStateFlow<SignupState>(SignupState.Idle)
    val signupState: StateFlow<SignupState> = _signupState

    fun sendSigninData(email: String, userName: String, pass: String) {
        viewModelScope.launch {
            _signupState.value = SignupState.Loading
            try {
                val authResult = auth.createUserWithEmailAndPassword(email, pass).await()
                val uid = authResult.user?.uid
                val avt: String = "https://t4.ftcdn.net/jpg/02/15/84/43/360_F_215844325_ttX9YiIIyeaR7Ne6EaLLjMAmy4GvPC69.jpg"
                if (uid != null) {
                    val user = hashMapOf(
                        "uid" to uid,
                        "email" to email,
                        "username" to userName,
                        "password" to pass,
                        "avt" to avt
                    )
                    db.collection("account")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            _signupState.value = SignupState.Success
                        }
                        .addOnFailureListener { e ->
                            _signupState.value = SignupState.Error(e.message ?: "Unknown error")
                        }
                } else {
                    _signupState.value = SignupState.Error("UID is null")
                }
            } catch (e: Exception) {
                _signupState.value = SignupState.Error(e.message ?: "Unknown error")
            }
        }
    }

    companion object {
        private const val TAG = "SigninViewModel"
    }
}

sealed class SignupState {
    object Idle : SignupState()
    object Loading : SignupState()
    object Success : SignupState()
    data class Error(val message: String) : SignupState()
}

class LoginViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> get() = _loginState
    private val _userData = MutableLiveData<AccountData?>()
    val userData: LiveData<AccountData?> get() = _userData
    init {
        checkCurrentUser()
    }
    fun checkUserCredentials(email: String, pass: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginState.value = true
                        fetchUserData()
                        onResult(true)
                    } else {
                        _loginState.value = false
                        onResult(false)
                    }
                }
            } catch (e: Exception) {
                _loginState.value = false
                onResult(false)
            }
        }
    }

    private fun fetchUserData() {
        val user = auth.currentUser
        user?.let {
            firestore.collection("account")
                .whereEqualTo("uid", it.uid)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        val document = documents.documents.first() // Lấy tài liệu đầu tiên trong danh sách
                        val userData = document.toObject(AccountData::class.java)
                        _userData.value = userData
                    } else {
                        _userData.value = null
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("LoginViewModel", "Error getting documents: ", exception)
                    _userData.value = null
                }
        } ?: run {
            _userData.value = null
        }
    }
    fun getCurrentUserUid(): String? {
        return auth.currentUser?.uid
    }
    fun checkCurrentUser() {
        val user = auth.currentUser
        _loginState.value = user != null
        if (user != null) {
            fetchUserData()
        } else {
            _userData.value = null
        }
    }
    fun signOut() {
        auth.signOut()
        _loginState.value = false
        _userData.value = null
    }
}

fun addToWishlist(
    db: FirebaseFirestore,
    desItemId: String?,
    iduser: String,
    desTitle: String
) {
    val wishlistItem = hashMapOf(
        "desID" to desItemId,
        "IDuser" to iduser,
        "desTitle" to desTitle
    )
    db.collection("wishlist")
        .add(wishlistItem)
        .addOnSuccessListener {
            Log.d("Firestore", "DocumentSnapshot successfully written!")
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error writing document", e)
        }
}
fun removeFromWishlist(db: FirebaseFirestore, desItemId: String?, iduser: String) {
    db.collection("wishlist")
        .whereEqualTo("desID", desItemId)
        .whereEqualTo("IDuser", iduser)
        .get()
        .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                db.collection("wishlist").document(document.id).delete()
            }
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error removing document", e)
        }
}



class YatchViewModel : ViewModel() {
    val yatch = listOf(
        Yatch(
            yatchLocation = "Ha Long Bay",
            yatchName = "Elite of the Seas",
            yatchInfo = "Launched in 2022 - 35 Rooms",
            imageYatchId = R.drawable.elitesea,
            imageYatchId1 = R.drawable.eots1,
            imageYatchId2 = R.drawable.eots2,
            imageYatchId3 = R.drawable.eots3,
            rate = 5f,
            introduce = R.string.elite_intro,
            cost = 290
        ),
        Yatch(
            yatchLocation = "Ha Long Bay",
            yatchName = "Le Theatre",
            yatchInfo = "Launched in 2019 - 21 Rooms",
            imageYatchId = R.drawable.le_theatre,
            imageYatchId1 = R.drawable.leth1,
            imageYatchId2 = R.drawable.leth2,
            imageYatchId3 = R.drawable.leth3,
            rate = 4.5f,
            introduce = R.string.lethe_intro,
            cost = 150
        ),

        )
}