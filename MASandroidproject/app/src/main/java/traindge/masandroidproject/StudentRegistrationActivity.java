package traindge.masandroidproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentRegistrationActivity extends AppCompatActivity {

    private EditText etStdNAme;
    private EditText etparentName;
    private EditText etStdMobile;
    private EditText etStdEmail_id;
    private EditText etClgName;
    private EditText etClass;
    private Button btnStdSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etStdNAme = (EditText) findViewById(R.id.etStdName);
        etparentName = (EditText) findViewById(R.id.etparentName);
        etStdMobile = (EditText)findViewById(R.id.etStdMobile);
        etStdEmail_id = (EditText)findViewById(R.id.etStdEmail_id);
        etClgName = (EditText)findViewById(R.id.etClgName);
        etClass = (EditText)findViewById(R.id.etClass);
        btnStdSubmit = (Button) findViewById(R.id.btnStdSubmit);
    }

}
