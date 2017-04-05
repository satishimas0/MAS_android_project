package trainedge.firebasedatabasedemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                                        return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:
                    
                    return true;
            }
            return false;
        }

    };
    private EditText etName;
    private EditText etComment;
    private Button btnSend;
    private FirebaseDatabase db;
    private DatabaseReference commentref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //our code
        etName = (EditText) findViewById(R.id.etName);
        etComment = (EditText) findViewById(R.id.etComment);
        btnSend = (Button) findViewById(R.id.btnSend);

        db = FirebaseDatabase.getInstance();
        commentref = FirebaseDatabaseInstance.getreference("comments");

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name= etName.getText().toString();
        String comment= etComment.getText().toString();

        if (name.isEmpty()){
            etName.setError("fill your name");
                return;
                    }
                    if(comment.isEmpty()){
                        etComment.setError("Add comment here");
                        return;
                    }

                //firebase upload
        HashMap<String,String>map=new HashMap<>();
        map.put("msg",comment);
        map.put("user",name);
        commentref.push().setValue(map);


        //you may add onComplementionListener too
        // update ui
        etName.setText("");
        etComment.setText("");
    }

}
