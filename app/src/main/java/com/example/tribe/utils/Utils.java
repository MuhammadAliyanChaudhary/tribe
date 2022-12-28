package com.example.tribe.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

    public SharedPreferences sharedPreferences;
    public static final String MyPREFERENCE  = "MyPrefs";
    public static final String Token = "Token";
    public String username;
    public String password;

    public Utils(Context context){
        sharedPreferences = context.getSharedPreferences(MyPREFERENCE, Context.MODE_PRIVATE);



    }

    public String getToken(){
        return  sharedPreferences.getString(Token,"");
    }


    public void putToken(String token){
        sharedPreferences.edit().putString(Token,token).commit();
    }


    public void logout(){
        sharedPreferences.edit().putString(Token,"logout").commit();
    }

    public boolean isLoggedIn(){
        boolean LoggedIn=true;
        if(getToken().equals("logout")||getToken().isEmpty()){
            LoggedIn=false;
        }
        return LoggedIn;
    }

    public void clearSharedPrefs(){
        sharedPreferences.edit().clear().commit();
    }

    public void SetShowOnboard(boolean val){
        sharedPreferences.edit().putBoolean("showonboardornot",val).commit();
    }
    public boolean showonboard(){
        return sharedPreferences.getBoolean("showonboardornot",true);

    }







}
