package com.lxd.myacc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.lxd.myacc.main.DiariesFragment;
import com.lxd.myacc.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initFragment();
    }

    private void initFragment() {

        DiariesFragment diariesFragment = getDiariesFragment();
        if(diariesFragment == null){
            diariesFragment = new DiariesFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),diariesFragment, R.id.container);
            diariesFragment.setViewModel(ViewModelProviders.of(this).get(DiariesViewModel.class));
        }

    }

    private DiariesFragment getDiariesFragment() {
        return (DiariesFragment) getSupportFragmentManager().findFragmentById(R.id.container);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    
    
}