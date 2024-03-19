package com.example.du_an_1.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1.database.DbHelper;
import com.example.du_an_1.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    SQLiteDatabase db;
    public  UserDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public List<User> getAll() {
        String sql = "SELECT * FROM User";
        return getData(sql);
    }
    public long insert(User obj) {
        ContentValues values = new ContentValues();
        values.put("maDN", obj.getMaDN());
        values.put("matKhau", obj.getMatKhau());
        values.put("hoTen", obj.getHoTen());
        values.put("sDT", obj.getsDt());
        values.put("vaiTro", obj.getVaiTro());
        return db.insert("User", null, values);
    }

    public int checkLogin(String id, String password){
        String sql =" SELECT  * FROM User WHERE maDN=? AND matKhau=?";
        List<User> list = getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }

        return 1;
    }

    @SuppressLint("Range")
    private List<User> getData(String sql, String... selectionArgs) {
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            User obj = new User();
            obj.setMaUser(cursor.getInt(cursor.getColumnIndex("maUser")));
            obj.setMaDN(cursor.getString(cursor.getColumnIndex("maDN")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setsDt(cursor.getString(cursor.getColumnIndex("sDT")));
            obj.setVaiTro(cursor.getInt(cursor.getColumnIndex("vaiTro")));
            list.add(obj);
        }
        return list;
    }
    int madn;
    public String getMaND(String vt) {
        try {
            Cursor cursor = db.rawQuery("SELECT maUser FROM User WHERE maDN=?", new String[] {String.valueOf(vt)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    madn = Integer.parseInt(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return String.valueOf(madn);
    }
    String maDNTV;
    public String getMDNTV(String maTV) {
        try {
            Cursor cursor = db.rawQuery("SELECT maDN FROM User WHERE hoTen=?", new String[] {String.valueOf(maTV)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    maDNTV = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return maDNTV;
    }
}