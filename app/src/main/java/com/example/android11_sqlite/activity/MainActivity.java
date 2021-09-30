package com.example.android11_sqlite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android11_sqlite.R;
import com.example.android11_sqlite.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText edName;
    private EditText edDes;
    private EditText edContent;
    private EditText edTotal;
    private EditText edDate;
    private Button btAdd;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {

        edName = findViewById(R.id.edName);
        edDes = findViewById(R.id.edDes);
        edContent = findViewById(R.id.edContent);
        edTotal = findViewById(R.id.edTotal);
        edDate = findViewById(R.id.edDate);
        btAdd = findViewById(R.id.btAdd);

        btAdd.setOnClickListener(this);

    @Override
    public void onClick(View v) {
        if (v == btAdd) {
            onRegister();
        }
    }

    private void onRegister() {

        if (edName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nhập tên chi tiêu", Toast.LENGTH_LONG).show();
            return;
        }

        if (edTotal.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nhập số tiền", Toast.LENGTH_LONG).show();
            return;
        }

        if (edDate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Nhập thời gian", Toast.LENGTH_LONG).show();
            return;
        }

        boolean isAdd = db.addExpense(edName.getText().toString(), edDes.getText().toString(),
                edContent.getText().toString(), edTotal.getText().toString(), edDate.getText().toString(), 1);

        if (isAdd == true) {
            Toast.makeText(this, "Thành công", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, ListUserAct.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Thất bại", Toast.LENGTH_LONG).show();
        }
    }
}