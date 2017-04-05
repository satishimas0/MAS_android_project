package traindge.masandroidproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_SIGN_IN = 7283;
    public static final String TAG = "LoginActivity";
    private TextView tvUserName;
    private TextView tv_passord;
    private Button btn_login;
    private ImageView img_google_sign_in;
    private Button btn_forgt_password;
    private Button btn_signup;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //create object
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tv_passord = (TextView) findViewById(R.id.tv_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        img_google_sign_in = (ImageView) findViewById(R.id.img_google_sign_in);
        btn_forgt_password = (Button) findViewById(R.id.btn_forgt_password);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        mAuth = FirebaseAuth.getInstance();

        img_google_sign_in = (ImageView) findViewById(R.id.img_google_sign_in);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // important part
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent homeIntent = new Intent(LoginActivity.this, FeedbackActivity.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };

        //final line
        img_google_sign_in.setOnClickListener(this);


    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);//ctrl+alt+f
        startActivityForResult(signInIntent, RC_SIGN_IN); //ctrl +Alt+C
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            // Google Sign In failed, update UI appropriately
            // ...
        }
    }

    // Pass the activity result back to the Facebook SDK
    //mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_google_sign_in:
                signIn();
                break;
            case R.id.btn_signup:
                showOptions();
                break;
        }

    }

    private void showOptions() {
        String[] options = new String[]{"Student", "Teacher", "Parent"};
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Select")
                .setAdapter(new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_list_item_1, options), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //// TODO: 4/5/2017 Student registration
                                Intent subIntent=new Intent(LoginActivity.this,TeacherRegistrationActivity.class);
                                startActivity(subIntent);
                                break;
                            case 1:
                           subIntent = new Intent(LoginActivity.this, StudentRegistrationActivity.class);
                                startActivity(subIntent);

                                break;
                            case 2:
                                subIntent = new Intent(LoginActivity.this, ParentRegistrationActivity.class);
                                startActivity(subIntent);
                                break;
                        }
                    }
                }).create();
        dialog.show();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}


