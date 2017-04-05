package traindge.masandroidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RegOptionPage extends AppCompatActivity implements View.OnClickListener {

    private Button parentReg;
    private Button studentReg;
    private Button teacherReg;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_option_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        parentReg = (Button) findViewById(R.id.parentReg);
        studentReg = (Button) findViewById(R.id.studentReg);
        teacherReg = (Button) findViewById(R.id.teacherReg);

        studentReg.setOnClickListener(this);
        parentReg.setOnClickListener(this);
         parentReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
