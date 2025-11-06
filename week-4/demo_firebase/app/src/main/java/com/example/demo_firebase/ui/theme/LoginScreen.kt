package com.example.demo_firebase.ui

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import coil.compose.rememberAsyncImagePainter


@Composable
fun LoginScreen(navController: NavController, activity: Activity) {
    val auth = FirebaseAuth.getInstance()
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(activity.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(activity, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener { signInTask ->
                if (signInTask.isSuccessful) {
                    auth.currentUser?.let {
                        navController.navigate(
                            "profile/${it.displayName}/${it.email}/${it.photoUrl}"
                        )
                    }
                }
            }
        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

    Button(
        onClick = { launcher.launch(googleSignInClient.signInIntent) },
        modifier = androidx.compose.ui.Modifier.padding(16.dp)
    ) {
        Text("Sign in with Google")
    }
}
