package traindge.masandroidproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ParentRegistrationActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener, View.OnClickListener {

    public static final String TAG = "parentRegistration";
    private EditText et_parent;
    private EditText etStudentName;
    private EditText etParentMoile;
    private EditText etParentEmail_id;
    private EditText etClgName;
    private Button btn_parentSubmit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Task<AuthResult> authResultTask;
    private OnCompleteListener<AuthResult> task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //create object
        et_parent = (EditText) findViewById(R.id.et_parent);
        etStudentName = (EditText) findViewById(R.id.etStudentName);
        etParentMoile = (EditText) findViewById(R.id.etParentMoile);
        etParentEmail_id = (EditText) findViewById(R.id.etParentEmail_id);
        etClgName = (EditText) findViewById(R.id.etClgName);
        btn_parentSubmit = (Button) findViewById(R.id.btn_parentSubmit);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = (this);


btn_parentSubmit.setOnClickListener(this);

    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            //user is signed in
            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            //user is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {
       final String email = etParentEmail_id.getText().toString();
       final String mobileno = etParentMoile.getText().toString();
      final   String parentname = et_parent.getText().toString();
       final String studentname = etStudentName.getText().toString();
       final String submit = btn_parentSubmit.getText().toString();
       final String college = etClgName.getText().toString();
    Log.e(TAG,email);
        authResultTask = mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, (task)
        Log.d(TAG,"createUserWithEmail:onComplete:"+task.is)
        // If signin fails, display a message to the user. if signin succeed
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if(task.isSuccessful){
            String  uid = task.getResult().getUser().getUid();
                    HashMap<String,String>Usermap=new HashMap<>();
            usermap.put("email",email);
            usermap.put("mobile",mobileno);
            usermap.put("parentname",parentname);
            usermap.put("studentname",studentname);
            usermap.put("college",college);
                    FirebaseDatabase.getInstance().getReference("user").child("parent").child(uid).setValue(usermap,(completionListener))
                } Toast.makeText(ParentRegistrationActivity.this,"Saved",Toast.LENGTH_SHORT).show();


    }
}