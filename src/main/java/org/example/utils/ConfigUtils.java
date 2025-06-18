package org.example.utils;

import com.zebrunner.carina.utils.R;

public class ConfigUtils {
    public static String getOrDefault(String key, String defaultValue) {
        String val = R.CONFIG.get(key);
        return (val == null || val.isEmpty()) ? defaultValue : val;
    }
}

