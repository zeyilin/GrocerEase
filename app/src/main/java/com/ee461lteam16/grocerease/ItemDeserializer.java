package com.ee461lteam16.grocerease;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Created by pascalequeralt on 11/14/16.
 */

public class ItemDeserializer extends StdDeserializer<Recipe> {

    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Recipe deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        long id = (node.get("id")).longValue();
        String title = node.get("title").asText();
        int minutes = (Integer) (node.get("readyInMinutes")).numberValue();
        int servings = (Integer) (node.get("servings")).numberValue();

        return new Recipe(id, title, minutes, servings);

    }

}
