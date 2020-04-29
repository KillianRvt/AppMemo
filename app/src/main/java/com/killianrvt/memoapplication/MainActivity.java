package com.killianrvt.memoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.killianrvt.memoapplication.R.id.liste_memo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(liste_memo);
        final Button button1 = (Button) findViewById(R.id.boutonOK);
        final EditText editText = (EditText) findViewById(R.id.editText);

        // à ajouter pour de meilleures performances :
        recyclerView.setHasFixedSize(true);

        // layout manager, décrivant comment les items sont disposés :
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AppDatabaseHelper.getDatabase(this).memosDAO().getListeMemos();

        // récupérer une liste de memos :
        final List<MemoDTO> listeMemosDTO = AppDatabaseHelper.getDatabase(this).memosDAO().getListeMemos();


        // contenu d'exemple :
        /*final List<MemoDTO> listMemos = new ArrayList<>();
        listMemos.add(new MemoDTO("Memo 1"));
        listMemos.add(new MemoDTO("Memo 2"));
        listMemos.add(new MemoDTO("Memo 3"));
        listMemos.add(new MemoDTO("Memo 4"));
        listMemos.add(new MemoDTO("Memo 5"));
        listMemos.add(new MemoDTO("Memo 6"));
        listMemos.add(new MemoDTO("Memo 7"));
        listMemos.add(new MemoDTO("Memo 8"));
        listMemos.add(new MemoDTO("Memo 9"));
        listMemos.add(new MemoDTO("Memo 10"));
        listMemos.add(new MemoDTO("Memo 11"));
        listMemos.add(new MemoDTO("Memo 12"));
    */
        // adapter :
        final MemoAdapter memoAdapter = new MemoAdapter(listeMemosDTO);
        recyclerView.setAdapter(memoAdapter);
        memoAdapter.notifyDataSetChanged();

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //listMemos.add(0,new MemoDTO(editText.getText().toString()));
                listeMemosDTO.add(new MemoDTO(editText.getText().toString()));
                MemoDTO memoDTO = new MemoDTO(editText.getText().toString());
                AppDatabaseHelper.getDatabase(v.getContext()).memosDAO().insert(memoDTO);
                Toast toast = Toast.makeText(v.getContext(), "Votre mémo a bien été ajouté!", Toast.LENGTH_SHORT);
                toast.show();
                List<MemoDTO> listeMemosDTO = AppDatabaseHelper.getDatabase(v.getContext()).memosDAO().getListeMemos();
                memoAdapter.notifyDataSetChanged();
            }

        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String valeur = preferences.getString("last", "Aucun");
        Toast toast = Toast.makeText(this, "Dernier item cliqué : "+valeur, Toast.LENGTH_SHORT);
        toast.show();



    }
}
