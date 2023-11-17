package montacer.elfazazi.ejerc2examen1eval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import montacer.elfazazi.ejerc2examen1eval.databinding.ActivityAddCorredorBinding;
import montacer.elfazazi.ejerc2examen1eval.modelos.Corredor;

public class AddCorredorActivity extends AppCompatActivity {

    private ActivityAddCorredorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddCorredorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddCorredor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddCorredor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Corredor corredor = crearCorredor();

                if (corredor == null){
                    Toast.makeText(AddCorredorActivity.this, R.string.faltandatos, Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("CORREDOR", corredor);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private Corredor crearCorredor() {
        if (binding.txtNombreAddCorredor.getText().toString().isEmpty() || binding.txtTiempoAddCorredor.getText().toString().isEmpty() ||
                binding.txtDistanciaAddCorredor.getText().toString().isEmpty()){
            return null;
        }

        Corredor corredor = new Corredor(binding.txtNombreAddCorredor.getText().toString(),
                Float.valueOf(binding.txtTiempoAddCorredor.getText().toString()),
                Float.valueOf(binding.txtDistanciaAddCorredor.getText().toString()));

        return  corredor;
    }
}