/**
 * Created by pascalequeralt on 11/5/16.
 */

public class Recipe {

    int id;
    String title;
    String cuisines;
    int minutes;
    String image;
    String image_type;
    String instructions;
    int servings;
    boolean popular;
    boolean healthy;
    boolean vegetarian;
    boolean vegan;
    boolean gluten_free;
    boolean dairy_free;
    boolean cheap;

    public Recipe(){

    }

    public Recipe(int id, String title, String cuisines, int minutes, String image, String image_type, String instructions, int servings, boolean popular, boolean healthy, boolean vegetarian, boolean vegan, boolean gluten_free, boolean dairy_free, boolean cheap) {
        this.id = id;
        this.title = title;
        this.cuisines = cuisines;
        this.minutes = minutes;
        this.image = image;
        this.image_type = image_type;
        this.instructions = instructions;
        this.servings = servings;
        this.popular = popular;
        this.healthy = healthy;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.gluten_free = gluten_free;
        this.dairy_free = dairy_free;
        this.cheap = cheap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGluten_free() {
        return gluten_free;
    }

    public void setGluten_free(boolean gluten_free) {
        this.gluten_free = gluten_free;
    }

    public boolean isDairy_free() {
        return dairy_free;
    }

    public void setDairy_free(boolean dairy_free) {
        this.dairy_free = dairy_free;
    }

    public boolean isCheap() {
        return cheap;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }
}
