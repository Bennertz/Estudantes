package sp.senai;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referência da lista a partir da resource id
        // ListView
        ListView lvEstudantes = findViewById(R.id.lvEstudantes);

        // Criação de lista de estudantes
        String[] nomes = {"Estudante 1", "Estudante 2", "Estudante 3", "Estudante 4", "Estudante 5", "Estudante 6"};
        List<String> estudantes = new ArrayList<>(Arrays.asList(nomes));

        // Criação de contexto, recurso para apresentar a lista, fonte de daods
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estudantes);

        // Indicar os dados por trás da ListView
        lvEstudantes.setAdapter(adapter);

        lvEstudantes.setOnItemClickListener((parent, view, position, id) -> {
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
            builder.setPositiveButton("Sim", (arg0, arg1) -> {
                estudantes.remove(estudanteSelecionado);
                adapter.notifyDataSetChanged();
            });
            // Botão Não
            builder.setNegativeButton("Não", (dialog, which) -> Toast.makeText(MainActivity.this, "O estudante " + estudanteSelecionado + " não foi deletado =)", Toast.LENGTH_SHORT).show());
            alerta = builder.create();
            // Apresentar na tela
            alerta.show();
        });
    }
}