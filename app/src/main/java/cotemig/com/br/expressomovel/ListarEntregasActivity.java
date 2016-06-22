package cotemig.com.br.expressomovel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import cotemig.com.br.expressomovel.Entidades.Item;
import cotemig.com.br.expressomovel.Entidades.Usuario;
import cotemig.com.br.expressomovel.dao.ItemDAO;
import cotemig.com.br.expressomovel.dao.UsuarioDAO;


public class ListarEntregasActivity extends AppCompatActivity {

    private RecyclerView listaEntregas;
    private RecyclerView.Adapter itemAdapter;

    Long id = 0l;

    ArrayList<Item> itens = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_entregas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaEntregas = (RecyclerView) findViewById(R.id.listaEntregas);
        listaEntregas.setHasFixedSize(true);
        listaEntregas.setLayoutManager(new LinearLayoutManager(this));
    }

    private String getPerfil() {
        UsuarioDAO dao = new UsuarioDAO(this);
        return dao.getPerfil();
    }

    private long getIdUsuario() {
        UsuarioDAO dao = new UsuarioDAO(this);
        return dao.getId();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {

            case R.id.alterar_perfil:

                Intent intent = new Intent(ListarEntregasActivity.this, CadastrarUsuarioActivity.class);

                UsuarioDAO dao = new UsuarioDAO(ListarEntregasActivity.this);

                Usuario usuario = dao.getDadosCadastrais();

                Toast.makeText(ListarEntregasActivity.this, "Alterar perfil de " + usuario.getNome(), Toast.LENGTH_SHORT).show();

                intent.putExtra("usuario", usuario);

                startActivity(intent);

                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaItens();
    }

    private void carregaItens() {
        ItemDAO dao = new ItemDAO(this);
        this.itens.clear();
        this.itens = dao.getListaItensEntregador(getIdUsuario());

        itemAdapter = new ListarEntregasAdapter(ListarEntregasActivity.this, itens, getIdUsuario());

        listaEntregas.setAdapter(itemAdapter);
    }
}