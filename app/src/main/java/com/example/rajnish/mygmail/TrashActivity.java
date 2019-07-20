package com.example.rajnish.mygmail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Collections;

public class TrashActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView trashRecyclerView;
    TrashAdapter trashAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        trashRecyclerView = findViewById(R.id.trashA);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);


        databaseHelper = new DatabaseHelper(this);
        trashRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        trashAdapter = new TrashAdapter(this, databaseHelper.getData());
        trashRecyclerView.setAdapter(trashAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.date_sorting_trash_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dateAsc:
                return true;
            case R.id.dateDesc:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                Collections.sort(databaseHelper.getData(), new DateSorting());
                trashAdapter.notifyDataSetChanged();
                Collections.reverse(databaseHelper.getData());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}