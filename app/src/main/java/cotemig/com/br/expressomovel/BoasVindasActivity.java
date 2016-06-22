package cotemig.com.br.expressomovel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cotemig.com.br.expressomovel.Entidades.Usuario;
import cotemig.com.br.expressomovel.dao.UsuarioDAO;

public class BoasVindasActivity extends Activity {

    private static int SPLASH_TIME_OUT = 1000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boasvindas);
        new Handler().postDelayed(new Runnable() {
     /* * Exibindo splash com um timer. */

                                      @Override
                                      public void run() {
                                          UsuarioDAO dao = new UsuarioDAO(BoasVindasActivity.this);

                                          Usuario user = dao.getDadosCadastrais();

                                          Intent i;

                                          // Se diferente de null já possui cadastro
                                          if (user != null) {

                                              if ("N".equals(user.getTipoPerfil())) {
                                                  i = new Intent(BoasVindasActivity.this, ListarItensActivity.class);
                                                  BoasVindasActivity.this.startActivity(i);
                                              } else {
                                                  i = new Intent(BoasVindasActivity.this, ListarEntregasActivity.class);
                                                  BoasVindasActivity.this.startActivity(i);
                                              }
                                          } else {
                                              i = new Intent(BoasVindasActivity.this, CadastrarUsuarioActivity.class);
                                              BoasVindasActivity.this.startActivity(i);
                                          }
                                          finish();

                                      }
                                  },
                SPLASH_TIME_OUT);

    }


}

