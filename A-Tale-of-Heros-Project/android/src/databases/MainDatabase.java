package databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/***
 * This class models the database of the game, allow it to get the parameters from the database
 * interface.
 * oHleper -> The Database Open Helper that manage the database and the version management
 */
public class MainDatabase implements DatabaseGame {

    private DBOpenHelper oHelper;

    /***
     * This function pass the context of the activity to the open helper of the database
     * @param c -> The context of the current activity
     */
    public MainDatabase(Context c) {
        oHelper = new DBOpenHelper(c, 1);
    }

    /***
     * This function loads the table 'gamedb2' from the database with the pourpose of recover the
     * ammount of steps that the sprite do
     * @return -> setpammount (the ammount of steps that the sprite do)
     */
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

    /***
     * This function saves the step ammount that the character do
     * @param sta -> setp ammount
     */
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
