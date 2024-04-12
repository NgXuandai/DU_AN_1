package com.example.du_an_1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.database.DbHelper;
import com.example.du_an_1.model.Food;

import java.util.ArrayList;

public class ThongkeDao {
    DbHelper dbHelper;
    public ThongkeDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Food>getTop10(){
        ArrayList<Food> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ctdh.maFood,food.tenFood,COUNT(ctdh.maFood)FROM CHI_TIET_DON_HANG ctdh, FOOD food WHERE ctdh.maFood = food.maFood GROUP BY ctdh.maFood, food.maFood ORDER BY COUNT(ctdh.maFood) DESC LIMIT 10", null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do {
                 list.add(new Food(cursor.getString(0),cursor.getString(1),cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
