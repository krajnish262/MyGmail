package com.example.rajnish.mygmail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,RecycleViewItemTochHelperListener {
    Toolbar toolbar;
    RecyclerView dataRecyclerView;
    GmailAdapter gmailAdapter;
    ArrayList<MyGmail> dataList;
    DatabaseHelper databaseHelper;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        dataRecyclerView = findViewById(R.id.mailDataList);
        fetchingData();

        databaseHelper = new DatabaseHelper(this);

        layout = findViewById(R.id.mainActivityLayout);

    }


    private void fetchingData() {
        dataList = new ArrayList<>();
        dataList.add(new MyGmail("Virat kohli", "Cricketer", "No 1 batsman", R.drawable.kohli, date()));
        dataList.add(new MyGmail("Chris Gayle", "Batsman", "No 2 Batsman", R.drawable.sachin, date()));
        dataList.add(new MyGmail("M.S.Dhoni", "Wkeeper", "No 1 Wkeeper", R.drawable.msd, date()));
        dataList.add(new MyGmail("M.S.Dhoni", "Cricketer", "No 4 Batsman", R.drawable.ms, date()));
        dataList.add(new MyGmail("M.S.Dhoni", "Mr Cool", "No 1 Batsman", R.drawable.mahender, date()));
        dataList.add(new MyGmail("Virat kohli", "Cricketer", "No 1 Batsman", R.drawable.kohli, date()));
        dataList.add(new MyGmail("Virat kohli", "Cricketer", "No 1 Batsman", R.drawable.kohli, date()));
        dataList.add(new MyGmail("Virat kohli", "Cricketer", "No 1 Batsman", R.drawable.kohli, date()));
        dataList.add(new MyGmail("Virat kohli", "Cricketer", "No 1 Batsman", R.drawable.kohli, date()));
        dataList.add(new MyGmail("Virat kohli", "Cricketer", "No 1 Batsman", R.drawable.kohli, date()));

        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        gmailAdapter = new GmailAdapter(this, dataList);
        dataRecyclerView.setAdapter(gmailAdapter);
        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecycleViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(dataRecyclerView);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_for_main, menu);
        MenuItem menuItem = menu.findItem(R.id.searchingBtn);
        android.support.v7.widget.SearchView searchView1 = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem);
        searchView1.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    public void setActionMode(boolean isActionMode) {
        if (isActionMode) {
            toolbar.getMenu().clear();
            toolbar.inflateMenu(R.menu.menu_delete);
            toolbar.setTitle("Delete or Trash");
            toolbar.setBackgroundColor(Color.parseColor("#363636"));
        }

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteBtn:
                Iterator<MyGmail> iterator = dataList.iterator();
                while (iterator.hasNext()) {
                    MyGmail c = iterator.next();
                    if (c.isSelected()) {
                        iterator.remove();
                        databaseHelper.insertData(c);
                        gmailAdapter.notifyDataSetChanged();

                    }
                }
                return true;

            case R.id.trashBtn:
                startActivity(new Intent(this, TrashActivity.class));
                return true;

            case R.id.nameAsc:
                sortBynameAsc();
                return true;

            case R.id.nameDesc:
                sortBynameDesc();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortBynameAsc() {
        Collections.sort(dataList, new NameSorting());
        gmailAdapter.notifyDataSetChanged();
    }

    private void sortBynameDesc() {
        Collections.sort(dataList, new NameSorting());
        Collections.reverse(dataList);
        gmailAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<MyGmail> newList = new ArrayList<>();
        for (MyGmail model : dataList) {
            String name = model.getFrom().toLowerCase();
            String subject = model.getSubject().toLowerCase();
            if (name.contains(newText) || subject.contains(newText)) {
                newList.add(model);
            }
        }
        gmailAdapter.setFilter(newList);
        return false;

    }

    String date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof GmailAdapter.Viewholder) {

            String name = dataList.get(viewHolder.getAdapterPosition()).getFrom();

            final MyGmail deletedItem = dataList.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            gmailAdapter.removeItem(deleteIndex);

            Snackbar snackbar = Snackbar.make(layout, name + " Remove from the list ", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gmailAdapter.restoreItem(deleteIndex, deletedItem);

                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

        }

    }
}
