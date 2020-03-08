package databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/***
 * This class creates the database that the game will use.
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    public DBOpenHelper(Context c, int version){

        super(c, "mainGameDB", null, version);
    }

    /***
     * In this method we create the only table that the game uses. As you can see, the only parameter
     * that the table has is the ammount of steps.
     * @param db -> The database of the game
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table gamedb2(stepammount int(50) primary key)");
    }

    /***
     * This function deletes the previous table. We need this because the previous database had a
     * mistake in the parameters
     * @param db -> The database of the game
     * @param oldVersion -> The previous version of the database
     * @param newVersion -> The new version of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table gamedb1");
    }
}
