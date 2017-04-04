package traindge.masandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity {

    private Button btn_feedback_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feddback);
        EditText et_subjectfeedback=(EditText)findViewById(R.id.et_subjectfeedback);
        EditText tvfeedbackMsg=(EditText)findViewById(R.id.tvfeedbackMsg);
        btn_feedback_send = (Button) findViewById(R.id.btn_feedback_send);
        //create onclicklistener
    }
}
