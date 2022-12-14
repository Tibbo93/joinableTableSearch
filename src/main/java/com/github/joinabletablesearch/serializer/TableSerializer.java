package com.github.joinabletablesearch.serializer;

import com.github.joinabletablesearch.model.Cell;
import com.github.joinabletablesearch.model.Table;
import com.google.gson.*;


import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class TableSerializer implements JsonSerializer<Table> {
    @Override
    public JsonElement serialize(Table table, Type type, JsonSerializationContext jsonSerializationContext) {

        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(table).getAsJsonObject();
        jsonObject.remove("columns");

        List<Cell> cells = table.getColumns().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        jsonObject.add("cells", gson.toJsonTree(cells));

        return jsonObject;
    }
}