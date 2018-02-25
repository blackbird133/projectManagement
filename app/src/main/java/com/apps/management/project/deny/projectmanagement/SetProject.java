package com.apps.management.project.deny.projectmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SetProject extends AppCompatActivity implements View.OnClickListener {
    EditText etTitleInput, etDateDeadline, etReminderDuration, etMemberInvited;
    Button btnAdd, btnLanjut;
    LinearLayout llMemberAdded;
    DatePickerDialog date;

    public static ArrayList<String> memberList = new ArrayList<String>();
    Calendar mCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_project);

        etTitleInput = (EditText) findViewById(R.id.etTitleInput);
        etDateDeadline = (EditText) findViewById(R.id.etDateDeadline);
        etReminderDuration = (EditText) findViewById(R.id.etReminderDuration);
        etMemberInvited = (EditText) findViewById(R.id.etMemberInvited);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnLanjut = (Button) findViewById(R.id.btnLanjut);
        llMemberAdded = (LinearLayout) findViewById(R.id.llMemberAdded);
        TextView tvMemberAdded = new TextView(this);


        btnAdd.setOnClickListener(this);
        btnLanjut.setOnClickListener(this);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };

        etDateDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SetProject.this, date, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberTask.class);

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("memberArray", memberList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                String etMemberEmptiness = etMemberInvited.getText().toString();
                if (etMemberEmptiness.matches("") || etMemberEmptiness.length() <= 5) {
                    Toast.makeText(this, "Input the member name!", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "Test" + etMemberEmptiness, Toast.LENGTH_SHORT).show();
                } else {
                    llMemberAdded.addView(memberAdded(etMemberInvited.getText().toString()));
                    memberList.add(etMemberEmptiness);
//                    Toast.makeText(this, "member: " + memberList, Toast.LENGTH_SHORT).show();
                }
                etMemberInvited.setText("");
                break;
        }
    }

    private TextView memberAdded(String member) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final TextView tvMemberAdded = new TextView(this);
        tvMemberAdded.setLayoutParams(lparams);
        tvMemberAdded.setText(member);
        tvMemberAdded.setTextSize(14);
        return tvMemberAdded;
    }

    private void updateLabel() {
        String mFormat = "dd MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);

        etDateDeadline.setText(sdf.format(mCalendar.getTime()));
    }
}
