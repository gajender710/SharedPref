package com.example.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared_Prefss {

    private Context context;
    private SharedPreferences s;
    public static final  String SHARED_PREFS = "sharedPrefs";
    public static final  String SWITCH1="switch1";

    public Shared_Prefss(Context context) {
        s= context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        this.context=context;
    }
    public void saveData(String savedVariableName, String Value) {
        SharedPreferences.Editor editor=s.edit();
        editor.putString(savedVariableName,Value);
        editor.apply();
        //Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
    public void saveBoolean(String savedVariableName, boolean value) {
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(savedVariableName, value);
        editor.apply();
    }

    public String loadStringData(String savedVariableName) {
        return s.getString(savedVariableName, null);
    }

    public boolean loadBooleanData(String savedVariableName) {
        return s.getBoolean(savedVariableName, false);
    }


}
