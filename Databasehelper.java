package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Databasehelper extends SQLiteOpenHelper {
    private static String DB_name = "user.db";
    private static int DB_VERSION = 1;
    public static final String TABLE_NAME = "user";
    public static final String col_1 = "name";
    public static final String col_2 = "email";
    public static final String col_3 = "credit";

    public Databasehelper(Context context) {
        super(context, DB_name, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists user(name text,email text,credit integer)");
        db.execSQL("insert into user values('gayathri','gayathri@gmail.com',100) ");
        db.execSQL("insert into user values('savithri','savithri@gmail.com',100) ");
        db.execSQL("insert into user values('dhathri','dhathri@gmail.com',100) ");
        db.execSQL("insert into user values('gangothri','gangothri@gmail.com',100) ");
        db.execSQL("insert into user values('pavitra','pavitra@gmail.com',100) ");
        db.execSQL("insert into user values('mythri','mythri@gmail.com',100) ");
        db.execSQL("insert into user values('chaythri','chaythri@gmail.com',100) ");
        db.execSQL("insert into user values('thrilok','thrilok@gmail.com',100) ");
        db.execSQL("insert into user values('theres','theres@gmail.com',100) ");
        db.execSQL("insert into user values('rinu','rinu@gmail.com',100) ");
        db.execSQL("insert into user values('gayathri','gayathri@gmail.com',100) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor listcontents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

    /* public  Cursor info(String str)
      { String[] pro={col_1,col_2,col_3};
         // String[] par=new String[]{str};
          String s=(col_1+"LIKE ?");
          String[] sel={str};
          SQLiteDatabase db=this.getWritableDatabase();
          Cursor cursors=db.query(TABLE_NAME,pro,s,sel,null,null,null);
          return cursors;
      }*/
    public void updatetable(String name, String mail, int credit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_1, name);
        contentValues.put(col_2, mail);
        contentValues.put(col_3, credit);
        db.update(TABLE_NAME, contentValues, "name is ? ", new String[]{name});
        /*SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("UPDATE  "+TABLE_NAME+" SET "+col_3+" = "+credit+" WHERE "+col_2+" like "+mail);
*/

    }

   /* public ArrayList<String> get() {
        ArrayList<String> list=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        String sel="SELECT * FROM "+ TABLE_NAME ;
        Cursor cur=db.rawQuery(sel,null);
        if(cur.getCount()>0){
            while (cur.moveToNext()){
                String pname=cur.getString(cur.getColumnIndex("name"));
                list.add(pname);
            }
        }
       return list;
    }*/
}