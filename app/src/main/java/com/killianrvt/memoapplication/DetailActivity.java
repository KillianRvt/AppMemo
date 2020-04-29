package com.killianrvt.memoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // fragment :
        DetailFragment fragment = new DetailFragment();
        String libelle = getIntent().getStringExtra("libelle");
        Bundle bundle = new Bundle();
        bundle.putString("libelle", libelle);
        fragment.setArguments(bundle);

        // fragment manager :
        FragmentManager fragmentManager = getSupportFragmentManager();

        // transaction :
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.detailActivity, fragment, "fragmentDetail");
        fragmentTransaction.commit();


    }
}
