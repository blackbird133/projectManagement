package com.apps.management.project.deny.projectmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView ivMyProject, ivSetProject, ivChatting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivMyProject = (ImageView)findViewById(R.id.ivMyProject);
        ivSetProject = (ImageView)findViewById(R.id.ivSetProject);
        ivChatting = (ImageView)findViewById(R.id.ivChatting);

        ivMyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MyProject.class);
                startActivity(i);
                finish();
            }
        });

        ivSetProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SetProject.class);
                startActivity(i);
                finish();
            }
        });

        ivChatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Chatting.class);
                startActivity(i);
                finish();
            }
        });
    }
}
