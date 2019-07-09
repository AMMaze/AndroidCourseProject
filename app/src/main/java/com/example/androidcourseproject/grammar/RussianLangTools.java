package com.example.androidcourseproject.grammar;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RussianLangTools {

    private static final Set<Character> vowels = new HashSet<Character>(Arrays.asList('а', 'ы', 'у', 'я', 'е', 'а', 'и', 'е', 'и', 'о', 'ю', 'э'));

    static public String getPredicativeCaseForCity (@NonNull String country, @NonNull String city) {
        StringBuilder res = new StringBuilder(city);

//        for(int i = 0; i < city.length(); i++) {
//            if(!Character.UnicodeBlock.of(city.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
//                return "";
//            }
//        }

        if (country.equals("Россия")) {
            if (city.endsWith("о")) {
                return city;
            } else if (city.endsWith("и") || city.endsWith("ы")) {
                res.setLength(res.length() - 1);
                res.append("ах");
            } else if (vowels.contains(res.charAt(res.length() - 1))) {
                res.setLength(res.length() - 1);
                res.append("е");
            } else {
                res.append("е");
            }
        } else {
            if (!city.contains(" ") && !vowels.contains(res.charAt(res.length() - 1))) {
                res.append("е");
            }
        }

        return res.toString();
    }
}
