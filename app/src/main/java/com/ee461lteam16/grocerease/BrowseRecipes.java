package com.ee461lteam16.grocerease;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.R.id.list;

public class BrowseRecipes extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    SignInButton signInButton;
    Button signOutButton;
    TextView statusTextView;
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private SharedPreferences grocereasePrefs;
    private static boolean isLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_browse_recipes);

        final ArrayList<Recipe> recipeList = getRecipes();

        ArrayAdapter<Recipe> adapter =
                new ArrayAdapter<Recipe>(this, R.layout.recipe_list, R.id.Recipe_title, recipeList) {

                    // Called to map each data element to a view within the list
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);
                        TextView title = (TextView) view.findViewById(R.id.Recipe_title);
                        TextView minutes = (TextView) view.findViewById(R.id.minutes);
                        TextView servings = (TextView) view.findViewById(R.id.servings);
                        ImageView image = (ImageView) view.findViewById(R.id.Recipe_icon);

                        final Recipe recipe = (Recipe) this.getItem(position);


                        title.setText(recipe.getTitle());
                        minutes.setText(recipe.getReadyInString());
                        servings.setText(recipe.getServingsString());
                        Picasso.with(view.getContext()).load(recipe.getImageURL()).placeholder(view.getContext().getResources().getDrawable(android.R.drawable.star_on)).into(image);
//placeholder(context.getResources().getDrawable(R.drawable.default_person_image)).error(context.getResources().getDrawable(R.drawable.default_person_image))
                        return view;
                    }
                };

        // Find the list and attach the ArrayAdapter to it
        ListView listView = (ListView)findViewById(list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(view.getContext(), RecipeDetails.class);
                intent.putExtra("currentRecipe", recipeList.get(position));
                startActivity(intent);

            }
        });

        //TEST CODE
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_google_signin);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* Fragment Activity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

//        grocereasePrefs = PreferenceManager.getDefaultSharedPreferences(this);

        statusTextView = (TextView) findViewById(R.id.status_textview);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);

    }

    public ArrayList<Recipe> getRecipes(){

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

        try{
            JSONObject json = new JSONObject(loadJSONFromAsset());
            String jsonStr = json.toString();

            Configuration conf = Configuration.builder()
                    .mappingProvider(new JacksonMappingProvider())
                    .jsonProvider(new JacksonJsonProvider())
                    .build();

            TypeRef<ArrayList<Recipe>> type = new TypeRef<ArrayList<Recipe>>(){};

            //On first load, display all recipes (no filters applied)
            recipeList = JsonPath
                    .using(conf)
                    .parse(jsonStr)
                    .read("$.recipes[*][*]", type);

        } catch (JSONException e){


        }

        return recipeList;

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream is = null;
            is = assetManager.open("recipes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return json;
        }
        return json;
    }

    //TEST CODE BELOW
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.signOutButton:
                signOut();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            statusTextView.setText("Hello, " + acct.getDisplayName());
            // Signed in successfully, move to browse recipes
//            isLoggedIn = true;
//            grocereasePrefs.edit().putBoolean("isLoggedIn", isLoggedIn).commit();
//            Intent intent = new Intent(this, BrowseRecipes.class);
//            startActivity(intent);
//            finish();
        } else {
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                statusTextView.setText("Signed Out");
            }
        });
    }
}