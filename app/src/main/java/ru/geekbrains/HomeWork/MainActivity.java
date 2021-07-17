package ru.geekbrains.HomeWork;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import ru.geekbrains.cityheraldry.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, (R.string.open),
                (R.string.close));
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (navigateFragment(id)) {
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
    }

    private boolean navigateFragment(int id) {
        switch (id) {
            case R.id.search:
                Toast.makeText(MainActivity.this, "Поиск", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.favorite:
                Toast.makeText(MainActivity.this, "Избранное", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.smt_1:
                Toast.makeText(MainActivity.this, "Что-то №1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.smt_2:
                Toast.makeText(MainActivity.this, "Что-то №2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.smt_3:
                Toast.makeText(MainActivity.this, "Что-то №3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(MainActivity.this, "О приложении", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (navigateFragment(id)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}