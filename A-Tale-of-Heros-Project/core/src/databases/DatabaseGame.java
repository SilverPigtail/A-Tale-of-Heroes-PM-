package databases;

/***
 * This interface creates the bind between the android's database classes and the function that
 * allows the program to access the database in the core.
 * load -> load function that allows the program to recover the stepammount of the 'gamedb2' table of the database
 * save -> save function that allows the program to save the stepammount in the 'gamedb2' table of the database
 */
public interface DatabaseGame {
    public int load();
    public void save(int stepAmmount);
}
