package com.example.mhike;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "M-Hike.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create Table users(username Text primary key,password Text)");
        myDB.execSQL("create Table Hike(Hike_Name Text Primary Key, Location Text, Distance Text, Date Text, Level Text, Suitability Text, Attraction Text, Parking Text, Description Text)");
        myDB.execSQL("create Table Observation(Hike_Name Text primary key,Date Text, Fauna Text, Flora Text, Vegetation Text, Weather Text, Trail Text, Comment Text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");

    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("Users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkusernamePassword(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where username = ? and password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {

            return true;
        } else {
            return false;
        }
    }

    public void insertHikeData(String Hike_Name, String Location, String Distance, String Date, String Level, String Suitability, String Attraction, String Parking, String Description) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Hike_Name", Hike_Name);
        contentValues.put("Location", Location);
        contentValues.put("Distance", Distance);
        contentValues.put("Date", Date);
        contentValues.put("Level", Level);
        contentValues.put("Suitability", Suitability);
        contentValues.put("Attraction", Attraction);
        contentValues.put("Parking", Parking);
        contentValues.put("Description", Description);
        long result = myDB.insert("Hike", null, contentValues);

    }

    public void insertObservation(String HikeName, String Date, String Fauna, String Flora, String Vegetation, String Weather, String Trail, String Comment) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Hike_Name", HikeName);
        contentValues.put("Date", Date);
        contentValues.put("Flora", Flora);
        contentValues.put("Fauna", Fauna);
        contentValues.put("Vegetation", Vegetation);
        contentValues.put("Weather", Weather);
        contentValues.put("Trail", Trail);
        contentValues.put("Comment", Comment);
        long result = myDB.insert("Observation", null, contentValues);

    }

    public String getDetails(String o1) {


        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor;

        //String[] columns = new String[]{"Hike_Name", "Date", "Fauna", "Flora", "Vegetation", "Weather", "Trail", "Comment"};


        //cursor = myDB.query("Observation", columns, null, null, null, null, null);
        cursor = myDB.rawQuery("SELECT Hike_Name, Fauna, Flora FROM Observation", new String[]{});

        StringBuilder resultText = new StringBuilder();

        if (cursor != null) {
            cursor.moveToFirst();
        }

        do {
            String HName = cursor.getString(0);
            String Fn = cursor.getString(2);
            String Fl = cursor.getString(3);

            resultText.append("Hike Name : " + HName + "Fauna: " + Fn + "Flora: " + Fl);
        } while (cursor.moveToNext());

String resultText1 = resultText.toString();

        /*cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String HName = cursor.getString(0);
            String Fn = cursor.getString(1);
            String Fl = cursor.getString(2);
            String Vg = cursor.getString(3);
            String W = cursor.getString(4);
            String T = cursor.getString(5);
            String C = cursor.getString(6);


            resultText += "Hike Name :" + HName + "\n" + "Fauna : " + Fn + " " + Fl + " " + Vg + " " + W + " " + T + " " + C + "\n";

            cursor.moveToNext();*/

        return resultText1;
    }

}



