package sg.edu.np.s10207996;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context) {
        super(context, "T02DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (NAME TEXT, DESCRIPTION TEXT, ID INTEGER, FOLLOWED INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        onCreate(db);
    }

    public void addUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("description", user.getDescription());
        values.put("id", user.getId());
        values.put("followed", user.getFollowed());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("USERS", null, values);
        db.close();
    }

    public ArrayList<User> getUser(String Name) {
        SQLiteDatabase db = getWritableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM users WHERE name = \"" + name + "\"", null);
        Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
        ArrayList<User> list = new ArrayList<>();
        User u = null;

        while (cursor.moveToNext()) {
            u = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3) > 0
            );
            list.add(u);
        }

        cursor.close();
        db.close();

        return list;
    }

    public void updateUser(User u){
        ContentValues values = new ContentValues();
        values.put("name", u.getName());
        values.put("description", u.getDescription());
        values.put("id", u.getId());
        values.put("followed", u.getFollowed());

        SQLiteDatabase db = getWritableDatabase();
        db.update("USERS", values, "id=?", new String[]{Integer.toString(u.getId())});
        db.close();
    }

//    public ArrayList<User> getUser()
//    {
//        ArrayList<User> list = new ArrayList<>();
//        String query = "SELECT * FROM USERS";
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        try{
//            Cursor cursor = db.rawQuery(query, null);
//            if(cursor.moveToFirst()){
//                do{
//                    User obj = new User(cursor.getString(0),
//                            cursor.getString(1),
//                            cursor.getInt(2),
//                            Boolean.parseBoolean(cursor.getString(3))
//                    );
//                } while (cursor.moveToNext());
//            }
//        }finally {
//            try {db.close();} catch (Exception ignore){}
//        }
//        return list;
//    }
}
