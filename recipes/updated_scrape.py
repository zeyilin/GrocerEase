#!/usr/bin/env

# dependences:
# -unirest
# -simplejson
# to install, use pip

import unirest
import sys
import simplejson as json

def scrapeRandRecipe(key):
    h = {
      "X-Mashape-Key": key,
      "Accept": "application/json"
    }
    response = unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=1",
      headers=h)

    return response.body

def scrapeRandRecipePretend():
    return open('fake_http_response.txt').read()

def saveRecipes(recipes, file):
    with open(file, "a") as myfile:
        for r in recipes:
            myfile.write(str(r))

def main():
    args = sys.argv
    key = ''
    recipes = []
    if len(args) == 1:
        recipes.append(scrapeRandRecipePretend())
        recipes.append(scrapeRandRecipePretend())

    else:
        if len(args) not in (2,3):
            print 'Usage: scrape.py <key> <optional # of recipes>'
            return

        key = sys.argv[1]
        n = 1
        if len(sys.argv) > 2:
            n = int(sys.argv[2])
        # if n > 50:
        #     print "Danger too many recipes!!!! You can't afford that"
        #     return

        for i in range(n):
            recipes.append(scrapeRandRecipe(key)['recipes'])

    recipe_data = {}
    recipe_data['recipes'] = recipes
    recipe_json_raw = json.dumps(recipe_data, sort_keys=True, indent=2 * ' ')
    saveRecipes(recipe_json_raw, 'updated_recipes.txt')
    saveRecipes(recipe_json_raw, 'updated_recipes.json')

    print 'Recipes saved'

if __name__ == "__main__":
    main()
