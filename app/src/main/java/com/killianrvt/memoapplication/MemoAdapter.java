package com.killianrvt.memoapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import cz.msebera.android.httpclient.Header;

import static android.widget.Toast.LENGTH_SHORT;
import static com.killianrvt.memoapplication.R.id.libelle_memo;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoViewHolder> {

    // Liste d'objets métier :
    private List<MemoDTO> listMemo = null;

    // Constructeur
    public MemoAdapter(List<MemoDTO> listMemo) {
        this.listMemo = listMemo;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewMemo = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        return new MemoViewHolder(viewMemo);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {
        holder.getTextViewLibelleMemo().setText(listMemo.get(position).getIntitule());
    }

    @Override
    public int getItemCount() {
        return listMemo.size();
    }

    public class MemoViewHolder extends RecyclerView.ViewHolder {
        // TextView intitulé course :
        public TextView textViewLibelleMemo;

        public TextView getTextViewLibelleMemo() {
            return textViewLibelleMemo;
        }

        // Constructeur :
        public MemoViewHolder(View itemView) {
            super(itemView);
            textViewLibelleMemo = itemView.findViewById(libelle_memo);


            // listener :
            textViewLibelleMemo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final MemoDTO memo = listMemo.get(getAdapterPosition());
                    Toast.makeText(view.getContext(), "Clic sur " + memo.getIntitule(), LENGTH_SHORT).show();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = preferences.edit();

                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("libelle", memo.getIntitule());
                    view.getContext().startActivity(intent);

                    editor.putString("last", memo.getIntitule());
                    editor.apply();

                    // client HTTP :
                    AsyncHttpClient client = new AsyncHttpClient();

                    // paramètres :
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("memo", memo.getIntitule());

                    // appel :
                    client.post("http://httpbin.org./post", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                            String retour = new String(response);

                            // Conversion en un objet Java ayant le même format que le JSON :
                            Gson gson = new Gson();
                            Example example = gson.fromJson(retour, Example.class);

                            Toast.makeText(view.getContext(), "Retour : " + example.getForm().getMemo(), LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                            Log.e("TAG", e.toString());
                        }
                    });
                }
            });
        }


    }


}
