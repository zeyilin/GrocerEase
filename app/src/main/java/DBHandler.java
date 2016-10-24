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
    private static final String DATABASE_NAME = "grocerEaseData";

    // Contacts table name
    private static final String TABLE_RECIPES = "recipes";
    //private static final String TABLE_INGREDIENTS = "ingredients";
    //private static final String TABLE_RECIPES_INGREDIENTS = "recipes_ingredients";

    //Common Column names
    private static final String KEY_ID = "id";

    //RECIPES TABLE column names
    private static final String KEY_TITLE = "title";
    private static final String KEY_CUISINES = "cuisines";
    private static final String KEY_MINUTES = "minutes";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_IMAGE_TYPE = "image_type";
    private static final String KEY_INSTRUCTIONS = "instructions";
    private static final String KEY_SERVINGS = "servings";
    private static final String KEY_POPULAR = "popular";
    private static final String KEY_HEALTHY = "healthy";
    private static final String KEY_VEGETARIAN = "vegetarian";
    private static final String KEY_VEGAN = "vegan";
    private static final String KEY_GLUTEN_FREE = "gluten_free";
    private static final String KEY_DAIRY_FREE = "dairy_free";
    private static final String KEY_CHEAP = "cheap";

    //Table Create Statements
    //Recipes Table create statement
    private static final String CREATE_TABLE_RECIPES = "CREATE TABLE "
            + TABLE_RECIPES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE
            + " TEXT," + KEY_CUISINES + " TEXT," + KEY_MINUTES + " INTEGER," +
            KEY_IMAGE + " TEXT," + KEY_IMAGE_TYPE + " TEXT," + KEY_INSTRUCTIONS +
            " TEXT," + KEY_SERVINGS + " INTEGER," + KEY_POPULAR + " BOOLEAN," +
            KEY_HEALTHY + " BOOLEAN," + KEY_VEGETARIAN + " BOOLEAN," + KEY_VEGAN + "BOOLEAN,"
            + KEY_GLUTEN_FREE + " BOOLEAN," + KEY_DAIRY_FREE + " BOOLEAN," + KEY_CHEAP + " BOOLEAN,"
            + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_RECIPES);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);

    }

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

}
