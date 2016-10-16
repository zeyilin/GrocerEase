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

def main():
    args = sys.argv
    key = ''
    if len(sys.argv) not in (2,3):
        print 'Usage: scrape.py <key> <optional # of recipes>'
        return

    key = sys.argv[1]
    n = 1
    if len(sys.argv) > 2:
        n = int(sys.argv[2])
    recipies = ''
    for i in range(n):
        print scrapeRandRecipe(key)

    print recipies


if __name__ == "__main__":
    main()
