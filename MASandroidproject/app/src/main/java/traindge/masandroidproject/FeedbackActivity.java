package traindge.masandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

<<<<<<< HEAD
public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
=======

public class FeedbackActivity extends AppCompatActivity {
>>>>>>> 44a1e5f47702afdb1b82c35f37227abc810df8c4

    private TextView tvfeedbackMsg;
    private EditText etSubjectFeedback;
    private Button btnFeedbackSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feddback);

<<<<<<< HEAD
        // object create
        etSubjectFeedback = (EditText) findViewById(R.id.etSubjectFeedback);
        tvfeedbackMsg = (TextView) findViewById(R.id.tvfeedbackMsg);
        btnFeedbackSend = (Button) findViewById(R.id.btnFeedbackSend);

        //create onclicklistener
        btnFeedbackSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
=======
        EditText tvfeedbackMsg=(EditText)findViewById(R.id.tvfeedbackMsg);
        btn_feedback_send = (Button) findViewById(R.id.btn_feedback_send);
        //create onclicklistener

        // our code
        EditText et_subjectfeedback=findViewById(R.id.et_subjectfeedback);
>>>>>>> 44a1e5f47702afdb1b82c35f37227abc810df8c4

    }
    EditText et_subjectfeedback=findViewById(R.id.et_subjectfeedback);
}
