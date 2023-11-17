package montacer.elfazazi.ejerc2examen1eval.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import montacer.elfazazi.ejerc2examen1eval.R;
import montacer.elfazazi.ejerc2examen1eval.modelos.Corredor;

public class CorredorAdapter extends RecyclerView.Adapter<CorredorAdapter.CorredorVH> {

    private List<Corredor> objects;
    private int resource;
    private Context context;

    public CorredorAdapter(List<Corredor> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public CorredorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View corredorView = LayoutInflater.from(context).inflate(resource, null);
        corredorView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
    );
        return  new CorredorVH(corredorView);
    }

    @Override
    public void onBindViewHolder(@NonNull CorredorVH holder, int position) {
        Corredor corredor = objects.get(position);

        holder.lbNombre.setText(corredor.getNombre());
        holder.lbTiempo.setText(String.valueOf(corredor.getTiempo()));
        holder.lbDistancia.setText(String.valueOf(corredor.getDistancia()));

        holder.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar(corredor).show();
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(corredor).show();
            }
        });


    }

    private AlertDialog editar(Corredor corredor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.seguroeditar);
        builder.setCancelable(false);

        View corredorModel =
                LayoutInflater.from(context).inflate(R.layout.activity_add_corredor, null);
        EditText txtNombre = corredorModel.findViewById(R.id.txtNombreAddCorredor);
        EditText txtTiempo = corredorModel.findViewById(R.id.txtTiempoAddCorredor);
        EditText txtDistancia = corredorModel.findViewById(R.id.txtDistanciaAddCorredor);
        Button btnCancelar = corredorModel.findViewById(R.id.btnCancelarAddCorredor);
        Button btnCrear = corredorModel.findViewById(R.id.btnCrearAddCorredor);
        builder.setView(corredorModel);

        txtNombre.setText(corredor.getNombre());
        txtDistancia.setText(String.valueOf(corredor.getDistancia()));
        txtTiempo.setText(String.valueOf(corredor.getTiempo()));

        btnCrear.setEnabled(false);
        btnCancelar.setEnabled(false);

        builder.setNegativeButton(R.string.no, null);
        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (txtNombre.getText().toString().isEmpty() ||
                        txtDistancia.getText().toString().isEmpty() ||
                        txtTiempo.getText().toString().isEmpty()){

                    Toast.makeText(context, R.string.faltandatos, Toast.LENGTH_SHORT).show();
                }else {
                    corredor.setNombre(txtNombre.getText().toString());
                    corredor.setDistancia(Float.valueOf(txtDistancia.getText().toString()));
                    corredor.setTiempo(Float.valueOf(txtTiempo.getText().toString()));

                    notifyItemChanged(objects.indexOf(corredor));
                }
            }
        });

        return  builder.create();
    }

    private AlertDialog borrar(Corredor corredor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.seguroborrarr);
        builder.setCancelable(false);

        builder.setNegativeButton(R.string.no, null);
        builder.setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = objects.indexOf(corredor);
                objects.remove(corredor);
                notifyItemRemoved(position);
            }
        });
        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class CorredorVH extends RecyclerView.ViewHolder{
        TextView lbNombre, lbTiempo, lbDistancia;
        Button btnBorrar, btnEditar;
        CardView fila;

        public CorredorVH(@NonNull View itemView) {
            super(itemView);



            lbNombre = itemView.findViewById(R.id.lbNombreViewModel);
            lbTiempo = itemView.findViewById(R.id.lbTiempoViewModel);
            lbDistancia = itemView.findViewById(R.id.lbDistanciaViewModel);
            btnBorrar = itemView.findViewById(R.id.btnBorrarViewModel);
            btnEditar = itemView.findViewById(R.id.btnEditarViewModel);
            fila = itemView.findViewById(R.id.cvFilaCorredorView);
        }
    }
}
