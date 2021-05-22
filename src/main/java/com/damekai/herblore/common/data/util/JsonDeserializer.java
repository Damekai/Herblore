package com.damekai.herblore.common.data.util;

import com.google.gson.JsonObject;

public abstract class JsonDeserializer<T>
{
    public abstract T fromJson(JsonObject jsonObject);
}
