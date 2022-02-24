package com.titoufu.lista_palestras;

import static com.titoufu.lista_palestras.R.color.azulEscuro;
import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.titoufu.lista_palestras.util.DateCustom;
import com.titoufu.lista_palestras.util.Palestra;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<News> newsArrayList;
    MyAdapter myAdapter;
    RelativeLayout relativeLayout;
    private final int[] numList = {4, 10, 20};
    private boolean firstTime = true;
    private final String[] titulo = {"PALESTRAS DA SEMANA",
            "PALESTRAS DO MÊS",
            "PALESTRAS DO BIMESTRE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.id_layoutItemMaster);
        recyclerView = findViewById(R.id.id_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        newsArrayList = new ArrayList<>();
        myAdapter = new MyAdapter(this, newsArrayList);
        recyclerView.setAdapter(myAdapter);
        customizaActionBar(titulo[0]);
        if (firstTime) montaLista(numList[0]);
        firstTime = false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.id_semana:
                customizaActionBar(titulo[0]);
                montaLista(numList[0]);
                //   Toast.makeText(getApplicationContext(), "Semana", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_mes:
                customizaActionBar(titulo[1]);
                montaLista(numList[1]);
                //   Toast.makeText(getApplicationContext(), "Mes", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_bimestre:
                customizaActionBar(titulo[2]);
                montaLista(numList[2]);
                //   Toast.makeText(getApplicationContext(), "Bimestre", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void montaLista(int numAdiante) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("palestra");
        myRef.orderByChild("numero").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int dataAtual = parseInt(DateCustom.dataAtualLong());
                int i = 0;
                newsArrayList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Palestra palestra = data.getValue(Palestra.class);
                    int dataPalestra = parseInt(palestra.getNumero());
                    if ((dataPalestra >= dataAtual) && (i < numAdiante)) {
                        String diaSemana = DateCustom.diaDaSemana(palestra.getData());
                        News news = new News(palestra.getData().substring(6, 10),
                                palestra.getData().substring(0, 5),
                                "(" + palestra.getOrador() + ")",
                                '\u0022' +palestra.getTema()+ '\u0022' , palestra.getReferencia(), diaSemana);
                        newsArrayList.add(news);
                        i++;
                    }
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Erro ao ler no Banco de Dados", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void customizaActionBar(String tituloActionBar) {
        final ActionBar actionBar = getSupportActionBar();
      //  actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.color.azulEscuro))));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor( "#FF3F8EFF")));
        // abar.setBackgroundDrawable(getResources().getDrawable(R.drawable.));//line under the action bar
        View viewActionBar = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        TextView textviewTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);
        textviewTitle.setText(tituloActionBar);
        actionBar.setCustomView(viewActionBar, params);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false); // não mostra seta de navegação a esquerda se false
        actionBar.setHomeButtonEnabled(false);
    }
}