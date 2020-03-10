package services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/***
 * This class creates the toast service that appears when you hit the start game button.
 */
public class AlertService extends Service {


    /***
     * This function cast the service in the game menu activity class.
     * @param intent -> The intent that cast the toast that says that the game is starting
     * @param flags -> flags of the start command
     * @param startId -> the initial ID of the function
     * @return -> The service itself with the toast
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Toast.makeText(this, "Starting game...", Toast.LENGTH_LONG).show();
        return Service.START_NOT_STICKY;
    }


    /***
     * This function binds services, but we don't have any other service, so, it returns nothing
     * @param intent -> the intent service
     * @return -> null object
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
