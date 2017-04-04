package traindge.masandroidproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParentRegistrationActivity extends AppCompatActivity {

    private EditText et_parent;
    private EditText etStudentName;
    private EditText etParentMoile;
    private EditText etParentEmail_id;
    private EditText etClgName;
    private Button btn_parentSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_parent = (EditText)findViewById(R.id.et_parent);
        etStudentName = (EditText)findViewById(R.id.etStudentName);
        etParentMoile = (EditText)findViewById(R.id.etParentMoile);
        etParentEmail_id = (EditText)findViewById(R.id.etParentEmail_id);
        etClgName = (EditText)findViewById(R.id.etClgName);
        btn_parentSubmit = (Button) findViewById(R.id.btn_parentSubmit);
    }

}
