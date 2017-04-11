package com.kadam.yogesh.g_gis.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kadam.yogesh.g_gis.R;

public class AddProjectActivity extends AppCompatActivity {

    EditText project_et;
    Button add_project_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        project_et = (EditText) findViewById(R.id.et_project);
        add_project_btn = (Button) findViewById(R.id.btn_add_project);

        add_project_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (project_et.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter Project name", Toast.LENGTH_LONG).show();
                } else {
                    Intent add_server = new Intent(AddProjectActivity.this, AddServerActivty.class);
                    add_server.putExtra("project_name", project_et.getText().toString());
                    startActivity(add_server);
                }
            }
        });
    }
}
