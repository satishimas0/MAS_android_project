package traindge.masandroidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

public class ParentRegistrationActivity extends AppCompatActivity {

    private EditText et_parent;
    private EditText etStudentName;
    private EditText etParentMoile;
    private EditText etParentEmail_id;
    private EditText etClgName;
    private Button btn_parentSubmit;
    private EditText etParentName;
    private EditText etParentEmail;
    private Button btnParentSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etParentName = (EditText)findViewById(R.id.etParentName);
        etStudentName = (EditText)findViewById(R.id.etStudentName);
        etParentMoile = (EditText)findViewById(R.id.etParentMobile);
        etParentEmail = (EditText)findViewById(R.id.etParentEmail);
        etClgName = (EditText)findViewById(R.id.etClgName);
        btnParentSubmit = (Button) findViewById(R.id.btnParentSubmit);
    }

}
