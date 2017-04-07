package traindge.masandroidproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import static traindge.masandroidproject.R.id.etStudentEmail;

public class StudentRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etparentName;
    private EditText etClgName;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText etStudentMobile;
    private EditText etStudentName;
    private EditText etStudentClass;
    private Button etStudentPassword;
    private Button btnStudentSubmit;
    private EditText etStudentEmail;

    public StudentRegistrationActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etStudentName = (EditText) findViewById(R.id.etStudentName);
        etparentName = (EditText) findViewById(R.id.etParentName);
        etStudentMobile = (EditText)findViewById(R.id.etStudentMobile);
        etStudentEmail = (EditText)findViewById(R.id.etStudentEmail);
        etClgName = (EditText)findViewById(R.id.etClgName);
        etStudentClass = (EditText)findViewById(R.id.etStudentClass);
        etStudentPassword = (Button) findViewById(R.id.etStudentPassword);
        btnStudentSubmit = (Button) findViewById(R.id.btnStudentSubmit);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //// TODO: 4/5/2017 INTENT
                } else {
                    // User is signed out
                }
                // ...
            }
        };



        btnStudentSubmit.setOnClickListener(this);
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

        final String name = etStudentName.getText().toString();
        final String password = etStudentPassword.getText().toString();
        final String email=etStudentEmail.getText().toString();
        final String college= etClgName.getText().toString();
        final String mobileNumber= etStudentMobile.getText().toString();
        final String studentclass= etStudentClass.getText().toString();
        final String parentName= etparentName.getText().toString();
        final String buttonsubmit=btnStudentSubmit.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(StudentRegistrationActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        String uid = task.getResult().getUser().getUid();
                        HashMap<String, String> usermap= new HashMap<String, String>();
                        usermap.put("email",email);
                        usermap.put("mobile",mobileNumber);
                        usermap.put("name",name);
                        usermap.put("college",college);
                        usermap.put("class",studentclass);
                        usermap.put("parent",parentName);
                        usermap.put("password",password);

                        FirebaseDatabase.getInstance().getReference("users").child(uid).setValue(usermap, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                Toast.makeText(StudentRegistrationActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
    }

}
