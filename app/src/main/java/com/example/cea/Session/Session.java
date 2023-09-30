package com.example.cea.Session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private String fileName = "user_data";
    private String user_id = "user_id";
    private String email = "email";
    private String name = "name";
    private String lastname = "lastname";
    private String mobile = "mobile";
    private String password = "password";
    private String address = "address";
    private String pincode = "pincode";
    private String image = "image";
    private String walletAmount = "walletAmount";
    private String dob = "dob";
    private String anniversary = "anniversary";
    private String whatsappNumber = "whatsappNumber";
    private String type = "type";
    private String introview = "introview";
    private String referCode = "referCode";
    public static String cordinate = "cordinate";
    public static String pas = "pas";

    Context context;
    public SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public Session(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


    public void setEmail(String em) {
        editor.putString(email, em);
        editor.commit();
    }

    public void setWalletAmount(String em) {
        editor.putString(walletAmount, em);
        editor.commit();
    }

    public void setReferCode(String em) {
        editor.putString(referCode, em);
        editor.commit();
    }



    public void setPas(String em) {
        editor.putString(pas, em);
        editor.commit();
    }


    public void setIsIntroViewed(boolean isView) {
        editor.putBoolean(introview, isView);
        editor.commit();
    }


    public boolean getIsIntroViewed() {
        return sharedPreferences.getBoolean(introview, false);
    }

    public void setAnniversary(String em) {
        editor.putString(anniversary, em);
        editor.commit();
    }

    public void setDob(String em) {
        editor.putString(dob, em);
        editor.commit();
    }

    public void setImage(String em) {
        editor.putString(image, em);
        editor.commit();
    }

    public void setWhatsappNumber(String em) {
        editor.putString(whatsappNumber, em);
        editor.commit();
    }


    public void setAddress(String em) {
        editor.putString(address, em);
        editor.commit();
    }

    public void setPincode(String em) {
        editor.putString(pincode, em);
        editor.commit();
    }


    public void setType(String em) {
        editor.putString(type, em);
        editor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(email, "");
    }
    public String getPas() {
        return sharedPreferences.getString(pas, "");
    }

    public String getWalletAmount() {
        return sharedPreferences.getString(walletAmount, "0");
    }

    public String getWhatsappNumber() {
        return sharedPreferences.getString(whatsappNumber, "");
    }

    public String getAnniversary() {
        return sharedPreferences.getString(anniversary, "");
    }

    public String getPincode() {
        return sharedPreferences.getString(pincode, "all");
    }

    public String getImage() {
        return sharedPreferences.getString(image, "");
    }

    public String getDob() {
        return sharedPreferences.getString(dob, "");
    }

    public String getAddress() {
        return sharedPreferences.getString(address, "");
    }

    public String getType() {
        return sharedPreferences.getString(type, "");
    }

    public String getReferCode() {
        return sharedPreferences.getString(referCode, "");
    }


    public void setUser_id(String id) {
        editor.putString(user_id, id);
        editor.commit();
    }


    public void setMobile(String mob) {
        editor.putString(mobile, mob);
        editor.commit();
        editor.apply();
    }

    public void setPassword(String mob) {
        editor.putString(password, mob);
        editor.commit();
        editor.apply();
    }

    public String getMobile() {
        return sharedPreferences.getString(mobile, "");
    }

    public String getPassword() {
        return sharedPreferences.getString(password, "");
    }


    public void setName(String mob) {
        editor.putString(name, mob);
        editor.commit();
        editor.apply();
    }

    public void setLastname(String mob) {
        editor.putString(lastname, mob);
        editor.commit();
        editor.apply();
    }


    public String getUserId() {
        return sharedPreferences.getString(user_id, "all");
    }


    public String getName() {
        return sharedPreferences.getString(name, "");
    }

    public String getLastname() {
        return sharedPreferences.getString(lastname, "");
    }


    public void logOut() {
        editor.clear();
        editor.commit();
        editor.apply();
    }

}
