#!/usr/bin/env

import sys
import json

def main():
    ingredients = []

    data = json.loads(open('copy.json').read());
    string = str(data)
    string.lstrip("{\n\"recipes\": [")
    string.rstrip("}")
    print(string)

    # for recipes in obj
    #     for  in recipe
    #         newobj = { id: innerrecipe.id, name: innerrecipe.name}
    #         ingredients.add(newobj)
    #
    # print(ingredients)

    # saveIngredients(ingredients, 'ingredients.txt')
    # print 'Recipes saved'

if __name__ == "__main__":
    main()
