package cotemig.com.br.expressomovel;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import cotemig.com.br.expressomovel.Entidades.Item;
import cotemig.com.br.expressomovel.Entidades.Usuario;

/**
 * Created by pedro on 08/06/16.
 * <p/>
 * Classe responsável pela manipulação dos componentes da tela de cadastro
 *
 * @see CadastrarUsuarioActivity
 */
public class CadastroItemHelper {

    private EditText txtDescricao;
    private EditText txtDataEntrega;
    private EditText txtDataRetirada;
    private EditText txtLocalEntrega;
    private EditText txtLocalRetirada;

    private Item item;
    private Context context;

    public CadastroItemHelper(CadastrarItemActivity activity) {
        context = activity.getBaseContext();
        txtDescricao = (EditText) activity.findViewById(R.id.cadastrar_item_descricao);
        txtDataEntrega = (EditText) activity.findViewById(R.id.cadastrar_item_data_entrega);
        txtDataRetirada = (EditText) activity.findViewById(R.id.cadastrar_item_data_retirada);
        txtLocalEntrega = (EditText) activity.findViewById(R.id.cadastrar_item_local_entrega);
        txtLocalRetirada = (EditText) activity.findViewById(R.id.cadastrar_item_local_retirada);

        txtLocalRetirada.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus

                }
            }
        });

        txtLocalEntrega.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // code to execute when EditText loses focus
                }
            }
        });

        this.item = new Item();
    }

    public Item getItemFromForm() {
        item.setDescricao(txtDescricao.getText().toString());
        item.setDataEntrega(txtDataEntrega.getText().toString());
        item.setDataRetirada(txtDataRetirada.getText().toString());
        item.setLocalEntrega(txtLocalEntrega.getText().toString());
        item.setLocalRetirada(txtLocalRetirada.getText().toString());


        return item;
    }

    public void preencheFormulario(Item item) {
        txtDescricao.setText(item.getDescricao());
        txtDataEntrega.setText(item.getDataEntrega());
        txtDataRetirada.setText(item.getDataRetirada());
        txtLocalEntrega.setText(item.getLocalEntrega());
        txtLocalRetirada.setText(item.getLocalRetirada());

        this.item = item;
    }
}
