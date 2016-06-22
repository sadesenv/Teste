package cotemig.com.br.expressomovel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import cotemig.com.br.expressomovel.Entidades.Item;
import cotemig.com.br.expressomovel.Entidades.Usuario;
import cotemig.com.br.expressomovel.dao.ItemDAO;
import cotemig.com.br.expressomovel.dao.UsuarioDAO;


public class ListarItensActivity extends AppCompatActivity {

    private RecyclerView listaItens;
    private RecyclerView.Adapter itemAdapter;

    Long id = 0l;

    ArrayList<Item> itens = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_itens);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaItens = (RecyclerView) findViewById(R.id.listaItens);
        listaItens.setLayoutManager(new LinearLayoutManager(this));
        listaItens.setHasFixedSize(true);


        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        Button acionaFormularioCadastro = (Button) findViewById(R.id.lista_itens_adicionar);

        if (acionaFormularioCadastro != null) {
            acionaFormularioCadastro.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                Intent vaiProFormulario = new Intent(ListarItensActivity.this, CadastrarItemActivity.class);
                                                                startActivity(vaiProFormulario);
                                                            }
                                                        }
            );
        }


    }

    private String getPerfil() {
        UsuarioDAO dao = new UsuarioDAO(this);
        return dao.getPerfil();

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

        switch (id) {

            case R.id.alterar_perfil:

                Intent intent = new Intent(ListarItensActivity.this, CadastrarUsuarioActivity.class);

                UsuarioDAO dao = new UsuarioDAO(ListarItensActivity.this);

                Usuario usuario = dao.getDadosCadastrais();

                Toast.makeText(ListarItensActivity.this, "Alterar perfil de " + usuario.getNome(), Toast.LENGTH_SHORT).show();

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

    private long getIdUsuario() {
        UsuarioDAO dao = new UsuarioDAO(this);
        return dao.getId();

    }

    private void carregaItens() {
        ItemDAO dao = new ItemDAO(this);
        this.itens.clear();
        this.itens = dao.getListaItensCliente(getIdUsuario());

        itemAdapter = new ListarItensAdapter(ListarItensActivity.this, itens);

        listaItens.setAdapter(itemAdapter);
    }
}