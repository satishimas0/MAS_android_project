package trainedge.settingdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import static trainedge.settingdemo.R.id.buttonPanel;
import static trainedge.settingdemo.R.id.clearSetting;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, TextWatcher {


    private EditText editdefault;
    private Switch switchoff;
    private Button clearsetting;
    private Switch switchcloudsyn;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // our code start here
        // object creation

        editdefault = (EditText) findViewById(R.id.etdefemail);
        switchoff = (Switch) findViewById(R.id.switchwifioff);
        clearsetting = (Button) findViewById(R.id.clearSetting);
        switchcloudsyn = (Switch) findViewById(R.id.switchcloudsyn);
        //shared preference object
        pref = getSharedPreferences("setting_pref", MODE_PRIVATE);
        //listener
        clearsetting.setOnClickListener(this);
        switchoff.setOnCheckedChangeListener(this);
        switchcloudsyn.setOnCheckedChangeListener(this);
        editdefault.addTextChangedListener(this);


    }

    @Override
    public void onClick(View view) {
        //clear setting
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences.Editor editor = pref.edit();
        switch (compoundButton.getId()) {
            case R.id.switchwifioff:
                //code
                editor.putBoolean("wifi_option", b);
                break;
            case R.id.switchcloudsyn:

                //<code></code>
                editor.putBoolean("cloud_option", b);
                break;

        }
        editor.apply();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//keep it empty
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//keep it empty
    }

    @Override
    //saving email address
    public void afterTextChanged(Editable editable) {
SharedPreferences.Editor editor=pref.edit();

        String email= editable.toString();
                if (email.isEmpty()&&email.length()<10 && !email.contains("0")){
                    editdefault.setError("please give default email ");
                    return;
                }
                editor.putString("def_email",email);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"setting saved",Toast.LENGTH_SHORT).show();
    }
}