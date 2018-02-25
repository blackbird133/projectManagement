package com.apps.management.project.deny.projectmanagement;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MemberTask extends AppCompatActivity {
    int memberTotal;
    int memberID = 0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_task);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ArrayList<String> memberArray = bundle.getStringArrayList("memberArray");

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_emergency_call);

//        Toast.makeText(this, "member: " + memberArray.get(0), Toast.LENGTH_SHORT).show();

        memberTotal = memberArray.size();
        final String array[] = new String[memberTotal];

        for (int i = 0; i < memberTotal; i++) {
            array[i] = memberArray.get(i);

            LinearLayout linearLayout1 = new LinearLayout(this);
            linearLayout1.setBackgroundResource(R.drawable.member_task_background);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            linearLayout1.setId(i);
            linearLayout1.setPadding(20, 20, 20, 20);

            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout1.setWeightSum(6F);
            linearLayout1.setLayoutParams(linearLayoutParams);
            linearLayoutParams.setMargins(0, 20, 0, 0);

            TextView name = new TextView(this);
            name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            name.setTypeface(null, Typeface.BOLD);
            name.setText(array[i]);
            linearLayout1.addView(name);
            linearLayout.addView(linearLayout1);

            final int finalI = i;

            linearLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(MemberTask.this, "ID: " + array[finalI], Toast.LENGTH_SHORT).show();

                    // Create custom dialog object
                    final Dialog dialog = new Dialog(MemberTask.this);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.dialog_set_task);
                    // Set dialog title
                    dialog.setTitle("Custom Dialog");

                    // set values for custom dialog components - text, image and button
                    TextView text = (TextView) dialog.findViewById(R.id.memberName);
                    text.setText(array[finalI]);

                    final LinearLayout llMemberTask = (LinearLayout) dialog.findViewById(R.id.llMemberTask);
                    final EditText etTask = (EditText) dialog.findViewById(R.id.etMemberTask);
                    Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);

                    btnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String resetTask = etTask.getText().toString();

                            if (resetTask.matches("") || resetTask.length() <= 5) {
                                Toast.makeText(getApplicationContext(), "Input the member Task!", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "Test" + etMemberEmptiness, Toast.LENGTH_SHORT).show();
                            } else {
                                llMemberTask.addView(memberTask(etTask.getText().toString()));
//                                memberList.add(etMemberEmptiness);
//                    Toast.makeText(this, "member: " + memberList, Toast.LENGTH_SHORT).show();
                            }
                            etTask.setText("");
                        }
                    });


                    dialog.show();

                    Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
                    btnSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });


                }
            });

        }

    }

    private TextView memberTask(String member) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView tvTaskAdded = new TextView(this);
        tvTaskAdded.setLayoutParams(lparams);
        tvTaskAdded.setText(member);
        tvTaskAdded.setTextSize(14);
        return tvTaskAdded;
    }


}
