package sp.senai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // ListView
    private ListView lvEstudantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referência da lista a partir da resource id
        lvEstudantes = findViewById(R.id.lvEstudantes);

        // Criação de lista de estudantes
        String[] nomes = {"Estudante 1", "Estudante 2", "Estudante 3", "Estudante 4", "Estudante 5", "Estudante 6"};
        List<String> estudantes = new ArrayList<>(Arrays.asList(nomes));

        // Criação de contexto, recurso para apresentar a lista, fonte de daods
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estudantes);

        // Indicar os dados por trás da ListView
        lvEstudantes.setAdapter(adapter);

        lvEstudantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Diálogo de alerta
                AlertDialog alerta;
                // Estudante selecionado da lista
                String estudanteSelecionado = (String) parent.getAdapter().getItem(position);
                //Construtor do alerta
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Título
                builder.setTitle("Atenção");
                // Mensagem
                builder.setMessage("Você quer deletar o registro?");
                // Botão Sim
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        estudantes.remove(estudanteSelecionado);
                        adapter.notifyDataSetChanged();
                    }
                });
                // Botão Não
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "O estudante " + estudanteSelecionado + " não foi deletado =)", Toast.LENGTH_SHORT).show();
                    }
                });
                alerta = builder.create();
                // Apresentar na tela
                alerta.show();
            }
        });
    }
}