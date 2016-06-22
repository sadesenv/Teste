package cotemig.com.br.expressomovel;

import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import cotemig.com.br.expressomovel.Entidades.Usuario;

/**
 * Created by pedro on 08/06/16.
 *
 * Classe responsável pela manipulação dos componentes da tela de cadastro
 * @see CadastrarUsuarioActivity
 *
 */
public class CadastroUsuarioHelper {

    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtBairro;
    private EditText txtCidade;
    private EditText txtTelefone;
    private EditText txtEmail;
    private Switch swtPerfil;
    private TextView txtTipoVeiculo;
    private Spinner spinTipoVeiculo;
    private EditText txtPlaca;

    private Usuario usuario;

    public CadastroUsuarioHelper(CadastrarUsuarioActivity activity) {

        txtNome = (EditText) activity.findViewById(R.id.cadastrar_nome);
        txtEndereco = (EditText) activity.findViewById(R.id.cadastrar_endereco);
        txtBairro = (EditText) activity.findViewById(R.id.cadastrar_bairro);
        txtCidade = (EditText) activity.findViewById(R.id.cadastrar_cidade);
        txtTelefone = (EditText) activity.findViewById(R.id.cadastrar_telefone);
        txtEmail = (EditText) activity.findViewById(R.id.cadastrar_email);
        swtPerfil = (Switch) activity.findViewById(R.id.cadastrar_tipo_perfil);
        txtTipoVeiculo = (TextView) activity.findViewById(R.id.tipoVeiculo);
        spinTipoVeiculo = (Spinner) activity.findViewById(R.id.spinnerTipoVeiculo);
        txtPlaca = (EditText) activity.findViewById(R.id.placa);

        swtPerfil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ocultaComponentes();
            }
        });


        this.usuario = new Usuario();
    }


    public Usuario getUsuarioFromForm(){
        usuario.setNome(txtNome.getText().toString());
        usuario.setEndereco(txtEndereco.getText().toString());
        usuario.setBairro(txtBairro.getText().toString());
        usuario.setCidade(txtCidade.getText().toString());
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setTelefone(txtTelefone.getText().toString());
        usuario.setTipoPerfil(swtPerfil.isChecked()? "S" : "N");
        usuario.setTipoVeiculo(spinTipoVeiculo.getSelectedItem().toString());
        usuario.setPlaca(txtPlaca.getText().toString());

        return usuario;
    }

    public void preencheFormulario(Usuario usuario) {
        txtNome.setText(usuario.getNome());
        txtEndereco.setText(usuario.getEndereco());
        txtBairro.setText(usuario.getBairro());
        txtCidade.setText(usuario.getCidade());
        txtEmail.setText(usuario.getEmail());
        txtTelefone.setText(usuario.getTelefone());

        if("S".equals(usuario.getTipoPerfil())){
            swtPerfil.setChecked(true);
        }

        ArrayAdapter adapter = (ArrayAdapter) spinTipoVeiculo.getAdapter();
        spinTipoVeiculo.setSelection(adapter.getPosition(usuario.getTipoVeiculo()));

        txtPlaca.setText(usuario.getPlaca());

        this.usuario = usuario;

        ocultaComponentes();
    }

    public void ocultaComponentes(){
        if (swtPerfil.isChecked()) {
            txtTipoVeiculo.setVisibility(View.VISIBLE);
            spinTipoVeiculo.setVisibility(View.VISIBLE);
            txtPlaca.setVisibility(View.VISIBLE);
        } else {
            txtTipoVeiculo.setVisibility(View.GONE);
            spinTipoVeiculo.setVisibility(View.GONE);
            txtPlaca.setVisibility(View.GONE);
        }
    }
}
