import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pascalequeralt on 10/17/16.
 */

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "recipeInfo";
    // Contacts table name
    private static final String TABLE_RECIPES = "recipes";
    // Shops Table Columns names
    private static final String KEY_ID = "recipe_id";
    private static final String KEY_TITLE = "recipe_title";
    private static final String KEY_DESCRIPTION = "recipe_description";
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_RECIPES + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
        + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
    // Creating tables again
            onCreate(db);
    }


    //Adding a new recipes
    public void addRecipe(){

    }

}
