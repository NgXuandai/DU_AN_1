package com.example.du_an_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.database.DbHelper;

public class Forgot extends AppCompatActivity {

    EditText edt_username,edt_phone;
    Button btn_forgot;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        edt_username = findViewById(R.id.edt_username);
        edt_phone = findViewById(R.id.edt_sdt);
        btn_forgot = findViewById(R.id.btn_forgot);
        dbHelper = new DbHelper(this);

        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edt_username.getText().toString();
                String phone = edt_phone.getText().toString();

                if (username.isEmpty() || phone.isEmpty()) {
                    showDialog("Lỗi", "Vui lòng nhập đầy đủ thông tin");
                } else {
                    if (DocDL(username, phone)) {
                        showDialog("Mời nhập mật khẩu mới", "");
                    } else {
                        showDialog("Lỗi", "Tên người dùng hoặc số điện thoại không đúng!");
                    }
                }
            }
        });
    }
    public boolean DocDL(String hoten,String phone){
        SQLiteDatabase sqLiteDatabase =  dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE maDN = ? AND sDT = ? LIMIT 2", new String[]{hoten, phone});
        if (cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }


    }
    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Forgot.this);
        LayoutInflater inflater = ((Activity) this).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_newpass, null);
        builder.setView(view);

        EditText edt_newpass = view.findViewById(R.id.edt_newpass);
        Button btndone = view.findViewById(R.id.btn_doimk);

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpass = edt_newpass.getText().toString();
                updateNewPass(newpass);
                Toast.makeText(Forgot.this, "Update mật khẩu thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Forgot.this, Login.class));

                AlertDialog dialog = builder.create();
                dialog.dismiss();
            }
        });
        builder.setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }

    private void updateNewPass(String newPass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matKhau", newPass);
        int rowsAffected = sqLiteDatabase.update("User", values, null, null);
        if (rowsAffected > 0) {
            showDialog("Thông báo", "Cập nhật mật khẩu thành công!");
        } else {
            showDialog("Lỗi", "Cập nhật mật khẩu thất bại!");
        }
    }
}