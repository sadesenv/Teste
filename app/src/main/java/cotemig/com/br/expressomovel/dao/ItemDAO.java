package cotemig.com.br.expressomovel.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import cotemig.com.br.expressomovel.Entidades.Item;
import cotemig.com.br.expressomovel.Entidades.Usuario;

/**
 * Created by pedro on 08/06/16.
 */
public class ItemDAO {

    private DataBaseHelper helper;

    public ItemDAO(Context context) { helper = new DataBaseHelper(context); }


    public void insere(Item item){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = getContentValuesToInsert(item);

        db.insert("item", null, values);

        db.close();
    }

    @NonNull
    private ContentValues getContentValuesToInsert(Item item) {
        ContentValues values = new ContentValues();
        values.put("descricao", item.getDescricao());
        values.put("dataRetirada", item.getDataRetirada());
        values.put("dataEntrega", item.getDataEntrega());
        values.put("localRetirada", item.getLocalRetirada());
        values.put("localEntrega", item.getLocalEntrega());
        values.put("idCliente", item.getIdCliente());


        return values;
    }

    @NonNull
    private ContentValues getContentValuesFromitem(Item item) {
        ContentValues values = new ContentValues();
        values.put("idItem", item.getIdItem());
        values.put("descricao", item.getDescricao());
        values.put("dataRetirada", item.getDataRetirada());
        values.put("dataEntrega", item.getDataEntrega());
        values.put("localRetirada", item.getLocalRetirada());
        values.put("localEntrega", item.getLocalEntrega());
        values.put("idEntregador", item.getIdEntregador());
        values.put("idCliente", item.getIdCliente());
        values.put("preco", item.getPreco());

        return values;
    }

    public ArrayList<Item> getListaItensCliente(Long id) {

        String sql = "select * from item where idCliente = " + id;

        ArrayList<Item> itens = new ArrayList<Item>();
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            Item item = new Item();
            item.setIdItem(cursor.getLong(cursor.getColumnIndex("idItem")));
            item.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            item.setDataRetirada(cursor.getString(cursor.getColumnIndex("dataRetirada")));
            item.setDataEntrega(cursor.getString(cursor.getColumnIndex("dataEntrega")));
            item.setLocalRetirada(cursor.getString(cursor.getColumnIndex("localRetirada")));
            item.setLocalEntrega(cursor.getString(cursor.getColumnIndex("localEntrega")));
            item.setIdEntregador(cursor.getLong(cursor.getColumnIndex("idEntregador")));
            item.setIdCliente(cursor.getLong(cursor.getColumnIndex("idCliente")));
            item.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));

            itens.add(item);



        }
        cursor.close();

        db.close();

        return itens;
    }


    public ArrayList<Item> getListaItensEntregador(Long id) {

        String sql = "select * from item where idCliente = " + id + " or idEntregador = " + id + " or idEntregador is null";

        ArrayList<Item> itens = new ArrayList<Item>();
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        while(cursor.moveToNext()){
            Item item = new Item();
            item.setIdItem(cursor.getLong(cursor.getColumnIndex("idItem")));
            item.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            item.setDataRetirada(cursor.getString(cursor.getColumnIndex("dataRetirada")));
            item.setDataEntrega(cursor.getString(cursor.getColumnIndex("dataEntrega")));
            item.setLocalRetirada(cursor.getString(cursor.getColumnIndex("localRetirada")));
            item.setLocalEntrega(cursor.getString(cursor.getColumnIndex("localEntrega")));
            item.setIdEntregador(cursor.getLong(cursor.getColumnIndex("idEntregador")));
            item.setIdCliente(cursor.getLong(cursor.getColumnIndex("idCliente")));
            item.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));

            itens.add(item);



        }
        cursor.close();

        db.close();

        return itens;
    }


    public void deletar(Item item){

        SQLiteDatabase db = helper.getWritableDatabase();

        String[] param = {String.valueOf(item.getIdItem())};

        db.delete("item", "idItem = ?", param);

        db.close();

    }

    public void atualiza(Item item) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = getContentValuesFromitem(item);

        String[] param = {String.valueOf(item.getIdItem())};

        db.update("item", values, "idItem = ?", param);

        db.close();
    }

    public void aceitarEntrega(Item item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idItem", item.getIdItem());
        values.put("idEntregador", item.getIdEntregador());
        String[] param = {String.valueOf(item.getIdItem())};
        db.update("item", values, "idItem = ?", param);
        db.close();
    }

    public void cancelarEntrega(Item item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idItem", item.getIdItem());
        values.put("idEntregador", 0);
        String[] param = {String.valueOf(item.getIdItem())};
        db.update("item", values, "idItem = ?", param);
        db.close();
    }
}
