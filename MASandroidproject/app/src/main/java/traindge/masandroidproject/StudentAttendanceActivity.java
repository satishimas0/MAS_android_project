package traindge.masandroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class StudentAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Report:
                Intent IntentHome = new Intent(StudentAttendanceActivity.this, Report.class);
                startActivity(IntentHome);
                break;
            case R.id.Notice:
                Intent IntentHom = new Intent(StudentAttendanceActivity.this, NoticeSystemActivity.class);
                startActivity(IntentHom);
                break;
            case R.id.about:
                break;
            case R.id.feedback:
                Intent IntentH = new Intent(StudentAttendanceActivity.this, FeedbackActivity.class);
                startActivity(IntentH);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
