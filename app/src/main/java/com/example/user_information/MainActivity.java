package com.example.user_information;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    TextView femail;
    ListView listView;
    Button add, update;
    EditText editText;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    String item;
    Integer indexVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        add = findViewById(R.id.add);
//        editText=findViewById(R.id.edittext);
//        listView=findViewById(R.id.listview);
//        update=findViewById(R.id.update);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String names=editText.getText().toString();
//                arrayList.add(names);
//                listView.setAdapter(arrayAdapter);
//                arrayAdapter.notifyDataSetChanged();
//                editText.getText().clear();
//
//            }
//
//        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                           int position, long id) {
//                // TODO Auto-generated method stub
//
//                arrayList.remove(position);
//
//                arrayAdapter.notifyDataSetChanged();
//
//                Toast.makeText(MainActivity.this, "User Deleted", Toast.LENGTH_LONG).show();
//
//                return true;
//            }
//
//        });
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                item=adapterView.getItemAtPosition(i).toString()+"   selected";
//                indexVal=i;
//                Toast.makeText(MainActivity.this, item, Toast.LENGTH_SHORT).show();
//
//
//
//
//            }
//        });


        femail = findViewById(R.id.femail);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = firebaseStorage.getReference();


        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        AddNewUser addNewUser = new AddNewUser();
        LogOut logOut = new LogOut();
        ManageVisitor manageVisitor = new ManageVisitor();
        ListAll listAll = new ListAll();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addnewuser:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, addNewUser).commit();
                        drawerLayout.closeDrawer(navigationView);

                        break;
                    case R.id.logout:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, logOut).commit();
                        drawerLayout.closeDrawer(navigationView);
                        FirebaseAuth.getInstance().signOut();
                        break;
                    case R.id.managevisitor:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, manageVisitor).commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.listall:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, listAll).commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    @Override
    public void onBackPressed() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            } else {
                super.onBackPressed();
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}