package by.gsu.epamlab.utils;

import com.google.gson.Gson;

public class GsonFactory {
    private static Gson ourInstance = new Gson();

    public static Gson getInstance() {
        return ourInstance;
    }

    private GsonFactory() {
    }
}
