package databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainDatabase implements DatabaseGame {

    private DBOpenHelper oHelper;

    public MainDatabase(Context c) {
        oHelper = new DBOpenHelper(c, 1);
    }

    @Override
    public int load() {

        SQLiteDatabase dBase = oHelper.getWritableDatabase();
        Cursor c = dBase.query("gamedb2", null, null, null, null, null, null);

        if(c.moveToFirst()) {
            return c.getInt(c.getColumnIndex("stepammount"));
        } else {
            return 0;
        }




    }

    @Override
    public void save(int sta) {

        SQLiteDatabase dBase = oHelper.getWritableDatabase();
        Cursor c = dBase.query("gamedb2", null, null, null, null, null, null);

        ContentValues cValues = new ContentValues();
        cValues.put("stepammount", sta);


        if(c.moveToFirst()) {
            dBase.update("gamedb2", cValues, null, null);

        } else {
            dBase.insert("gamedb2", null, cValues);
        }

        c.close();
        dBase.close();
    }
}
