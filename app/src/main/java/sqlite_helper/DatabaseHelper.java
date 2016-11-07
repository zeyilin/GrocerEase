package sqlite_helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pascalequeralt on 10/17/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "grocerEaseData";

    // Contacts table name
    private static final String TABLE_RECIPE = "recipes";
    private static final String TABLE_INGREDIENT = "ingredients";
    private static final String TABLE_RECIPE_INGREDIENT = "recipes_ingredients";

    //Common Column names
    private static final String KEY_ID = "id";

    //RECIPE TABLE column names
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

    //INGREDIENT TABLE column names
    private static final String KEY_NAME = "name";
    private static final String KEY_AISLE = "aisle";

    //RECIPE_INGREDIENT column name
    private static final String KEY_RECIPE_ID = "recipe_id";
    private static final String KEY_INGREDIENT_ID = "ingredient_id";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_UNITS = "units";
    private static final String KEY_INGREDIENT_STRING = "ingredient_string";

    //Table Create Statements
    //RECIPE TABLE create statement
    private static final String CREATE_TABLE_RECIPE = "CREATE TABLE "
            + TABLE_RECIPE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE
            + " TEXT," + KEY_CUISINES + " TEXT," + KEY_MINUTES + " INTEGER," +
            KEY_IMAGE + " TEXT," + KEY_IMAGE_TYPE + " TEXT," + KEY_INSTRUCTIONS +
            " TEXT," + KEY_SERVINGS + " INTEGER," + KEY_POPULAR + " BOOLEAN," +
            KEY_HEALTHY + " BOOLEAN," + KEY_VEGETARIAN + " BOOLEAN," + KEY_VEGAN + "BOOLEAN,"
            + KEY_GLUTEN_FREE + " BOOLEAN," + KEY_DAIRY_FREE + " BOOLEAN," + KEY_CHEAP + " BOOLEAN"
            + ")";

    //INGREDIENT TABLE create statement
    private static final String CREATE_TABLE_INGREDIENT = "CREATE TABLE "
            + TABLE_INGREDIENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT," + KEY_AISLE+ " TEXT"
            + ")";

    //RECIPE_INGREDIENT create statement
    private static final String CREATE_TABLE_RECIPE_INGREDIENT = "CREATE TABLE "
            + TABLE_RECIPE_INGREDIENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_RECIPE_ID + " INTEGER," + KEY_INGREDIENT_ID + " INTEGER,"
            + KEY_QUANTITY + " DOUBLE," + KEY_UNITS + " TEXT," + KEY_INGREDIENT_STRING + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_RECIPE);
        db.execSQL(CREATE_TABLE_INGREDIENT);
        db.execSQL(CREATE_TABLE_RECIPE_INGREDIENT);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENT);

        //create new tables
        onCreate(db);

    }




}
