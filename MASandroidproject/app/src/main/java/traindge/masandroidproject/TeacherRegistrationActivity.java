package traindge.masandroidproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TeacherRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "TeacherRegistration";
    private EditText etTchrName;
    private EditText etTchrMobile;
    private EditText etTchrEmail_id;
    private EditText etClgName;
    private EditText etHqualification;
    private Button btnTchrSubmit;
    private EditText et_tchrPassword;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //create object
        etTchrName = (EditText) findViewById(R.id.etTchrName);
        etTchrMobile = (EditText) findViewById(R.id.etTchrMobile);
        etTchrEmail_id = (EditText) findViewById(R.id.etTchrEmail_id);
        etClgName = (EditText) findViewById(R.id.etClgName);
        etHqualification = (EditText) findViewById(R.id.etHqualification);
        btnTchrSubmit = (Button) findViewById(R.id.btnTchrSubmit);
        et_tchrPassword = (EditText) findViewById(R.id.et_TchrPassword);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



        btnTchrSubmit.setOnClickListener(this);
    }

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

    @Override
    public void onClick(View v) {

        final String email = etTchrEmail_id.getText().toString();
        String password = et_tchrPassword.getText().toString();
        final String TeacherName= etTchrName.getText().toString();
        final String college= etClgName.getText().toString();
        final String HQualification= etHqualification.getText().toString();
        final String mobileNumber= etTchrMobile.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(TeacherRegistrationActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        String uid = task.getResult().getUser().getUid();
                        HashMap<String, String> usermap= new HashMap<String, String>();
                        usermap.put("email",email);
                        usermap.put("mobile",mobileNumber);
                        usermap.put("name",TeacherName);
                        usermap.put("college",college);
                        usermap.put("qualification",HQualification);
                        FirebaseDatabase.getInstance().getReference("users").child(uid).setValue(usermap, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                Toast.makeText(TeacherRegistrationActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
    }
}
