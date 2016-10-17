#!/usr/bin/env

import unirest
import sys

def scrapeRandRecipe(key):
    h = {
      "X-Mashape-Key": key,
      "Accept": "application/json"
    }
    response = unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?limitLicense=true&number=1",
      headers=h
    )

    return response.raw_body

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
        if n > 50:
            print "Danger too many recipes!!!! You can't afford that"
            return

        for i in range(n):
            recipes.append(scrapeRandRecipe(key))

    saveRecipes(recipes, 'recipes.txt')
    print 'Recipes saved'

if __name__ == "__main__":
    main()
