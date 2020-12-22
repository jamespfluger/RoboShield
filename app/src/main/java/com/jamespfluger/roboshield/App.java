package com.jamespfluger.roboshield;

import android.app.Application;
import android.content.ContextWrapper;

import com.jamespfluger.roboshield.lists.Item;
import com.jamespfluger.roboshield.utils.Constants;
import com.jamespfluger.roboshield.utils.PrefsUtil;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;

public class App extends Application {
    public static String PACKAGE_NAME;

    @Override
    public void onCreate() {
        super.onCreate();
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        PACKAGE_NAME = getBaseContext().getPackageName();

        initializePreferences();
    }

    private void initializePreferences() {
        if (PrefsUtil.getList(Constants.AREA_CODE_LIST_KEY) == null)
            PrefsUtil.putList(Constants.AREA_CODE_LIST_KEY, new ArrayList<Item>());
        if (PrefsUtil.getList(Constants.ALLOW_LIST_KEY) == null)
            PrefsUtil.putList(Constants.ALLOW_LIST_KEY, new ArrayList<Item>());
        if (PrefsUtil.getList(Constants.BLOCK_LIST_KEY) == null)
            PrefsUtil.putList(Constants.BLOCK_LIST_KEY, new ArrayList<Item>());
    }
}
