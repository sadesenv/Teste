package cotemig.com.br.expressomovel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;

import java.util.ArrayList;
import java.util.List;

import cotemig.com.br.expressomovel.Entidades.Usuario;

/**
 * Created by pedro on 08/06/16.
 */
public class UsuarioDAO {

    private DataBaseHelper helper;

    public UsuarioDAO(Context context) {
        helper = new DataBaseHelper(context);
    }


    public void insere(Usuario usuario){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = getContentValuesFromUsuario(usuario);

        db.insert("usuario", null, values);

        db.close();

    }

    @NonNull
    private ContentValues getContentValuesFromUsuario(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("endereco", usuario.getEndereco());
        values.put("bairro", usuario.getBairro());
        values.put("cidade", usuario.getCidade());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());
        values.put("tipo_perfil", usuario.getTipoPerfil());
        values.put("tipo_veiculo", usuario.getTipoVeiculo());
        values.put("placa", usuario.getPlaca());

        return values;
    }

    public Usuario getDadosCadastrais() {

        String sql = "select * from usuario";

        Usuario usuario = null;
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setIdUsuario(cursor.getLong(cursor.getColumnIndex("id_usuario")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            usuario.setBairro(cursor.getString(cursor.getColumnIndex("bairro")));
            usuario.setCidade(cursor.getString(cursor.getColumnIndex("cidade")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            usuario.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            usuario.setTipoPerfil(cursor.getString(cursor.getColumnIndex("tipo_perfil")));
            usuario.setTipoVeiculo(cursor.getString(cursor.getColumnIndex("tipo_veiculo")));
            usuario.setPlaca(cursor.getString(cursor.getColumnIndex("placa")));

        }
        cursor.close();

        db.close();

        return usuario;
    }

    public void deletar(Usuario usuario){

        SQLiteDatabase db = helper.getWritableDatabase();

        String[] param = {String.valueOf(usuario.getIdUsuario())};

        db.delete("usuario", "id_usuario = ?", param);

        db.close();
    }

    public void atualiza(Usuario usuario) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = getContentValuesFromUsuario(usuario);

        String[] param = {String.valueOf(usuario.getIdUsuario())};

        db.update("usuario", values, "id_usuario = ?", param);
        db.close();
    }

    public String getPerfil() {

        String sql = "select tipo_perfil from usuario";

        String perfil = "";

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToNext()){
            perfil = cursor.getString(cursor.getColumnIndex("tipo_perfil"));

        }
        cursor.close();

        db.close();

        return perfil;
    }

    public Long getId() {

        String sql = "select id_usuario from usuario";

        Long id = null;

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToNext()){
            id = cursor.getLong(cursor.getColumnIndex("id_usuario"));

        }
        cursor.close();

        db.close();

        return id;
    }

    public String getNome(Long id) {

        String sql = "select nome from usuario where id_usuario = "+ String.valueOf(id);

        String nome = null;

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToNext()){
            nome = cursor.getString(cursor.getColumnIndex("nome"));

        }
        cursor.close();

        db.close();

        return nome;
    }
}
