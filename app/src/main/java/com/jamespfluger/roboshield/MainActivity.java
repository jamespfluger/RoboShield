package com.jamespfluger.roboshield;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.jamespfluger.roboshield.lists.ItemAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void editItemEntry(View view) {
        ItemAdapter editAdapter = getItemAdapterFromParent(view);
    }

    public void deleteItemEntry(View view) {
        ItemAdapter deleteAdapter = getItemAdapterFromParent(view);
    }

    private ItemAdapter getItemAdapterFromParent(View view) {
        ViewParent parentAscendor = view.getParent();

        // Get the first RecyclerView parent from our alert
        while (parentAscendor != null && !(parentAscendor instanceof RecyclerView)) {
            parentAscendor = parentAscendor.getParent();
        }

        // As long as we found it, make sure it's using our ItemAdapter
        if (parentAscendor != null) {
            RecyclerView recyclerView = (RecyclerView) parentAscendor;
            if (recyclerView.getAdapter() instanceof ItemAdapter) {
                return (ItemAdapter) recyclerView.getAdapter();
            }
        }

        return null;
    }
}