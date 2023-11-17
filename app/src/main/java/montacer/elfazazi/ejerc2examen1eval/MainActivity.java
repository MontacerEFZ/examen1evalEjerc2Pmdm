package montacer.elfazazi.ejerc2examen1eval;

import android.content.Intent;
import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import montacer.elfazazi.ejerc2examen1eval.adapter.CorredorAdapter;
import montacer.elfazazi.ejerc2examen1eval.databinding.ActivityMainBinding;
import montacer.elfazazi.ejerc2examen1eval.modelos.Corredor;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherAddCorredor;
    private ArrayList<Corredor> listaCorredores;
    private CorredorAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaCorredores = new ArrayList<>();
        inicializarLauncher();

        adapter = new CorredorAdapter(listaCorredores, R.layout.corredor_view_model, MainActivity.this);
        layoutManager = new GridLayoutManager(this, 1);

        binding.contentMain.container.setAdapter(adapter);
        binding.contentMain.container.setLayoutManager(layoutManager);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            launcherAddCorredor.launch(new Intent(MainActivity.this, AddCorredorActivity.class));
            }
        });
    }

    private void inicializarLauncher() {
        launcherAddCorredor = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
           if (result.getResultCode() == RESULT_OK){
               if (result.getData() != null && result.getData().getExtras() != null){
                   Corredor corredor = (Corredor) result.getData().getExtras().getSerializable("CORREDOR");
                   listaCorredores.add(0, corredor);
                   adapter.notifyItemInserted(0);
               }
           }else {
               Toast.makeText(MainActivity.this, R.string.accioncancelada, Toast.LENGTH_SHORT).show();
           }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("LIST", listaCorredores);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        listaCorredores.addAll((ArrayList<Corredor>) savedInstanceState.getSerializable("LIST"));
        adapter.notifyItemRangeInserted(0, listaCorredores.size());
    }
}