package com.projects.gungde.learningrealm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.projects.gungde.learningrealm.adapter.ItemAdapter;
import com.projects.gungde.learningrealm.app.RealmApplication;
import com.projects.gungde.learningrealm.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private RecyclerView rvList;
    private FloatingActionButton fab;
    private Realm realm;

    private List<String> arrays = new ArrayList<>();
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

    }

    private void initComponents() {
        realm = Realm.getDefaultInstance();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        setSupportActionBar(toolbar);
        setRecyclerView();
    }

    private void setRecyclerView() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(getResults(),MainActivity.this);
        rvList.setAdapter(adapter);
    }

    private RealmResults<Item> getResults() {
        RealmQuery<Item> query = realm.where(Item.class);
        RealmResults<Item> results = query.findAll();
        return results;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab :
                showAddItemDialog();
                break;
        }
    }

    private void showAddItemDialog() {
        final AddItemDialog dialog = new AddItemDialog();
        dialog.show(getFragmentManager(), dialog.getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
