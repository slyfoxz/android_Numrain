package com.mygdx.numrain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class PreferencesDayDream extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);

        getPreferenceManager().setSharedPreferencesName("preferences"); // Don't change name here
        getPreferenceManager().setSharedPreferencesMode(0);

        addPreferencesFromResource(R.xml.preferences);



        Preference more = (Preference) findPreference("more");
        more.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                openUrl(R.string.url_more);

                return true;
            }
        });
    }

    private void openUrl(int i) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse((String) getText(i))));
        } catch (Exception e) {
            toast();
        }
    }

    private void toast() {
        Toast.makeText(this, R.string.settings_toast_unavailable, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {


        return false;
    }

}
