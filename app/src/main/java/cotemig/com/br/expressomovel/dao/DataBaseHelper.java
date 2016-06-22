package cotemig.com.br.expressomovel.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pedro on 14/06/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int dataBaseVersion = 1;
    private static final String dataBaseName = "ExpressMovelDB";

    public DataBaseHelper(Context context) {

        super(context, dataBaseName, null, dataBaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table usuario(id_usuario integer primary key AUTOINCREMENT NOT NULL, " +
                "nome text not null, " +
                "endereco text, " +
                "bairro text, " +
                "cidade text, " +
                "email text, " +
                "telefone text, " +
                "tipo_perfil text," +
                "tipo_veiculo text," +
                "placa text);";

        db.execSQL(sql);

        sql = "create table item(" +
                "idItem INTEGER primary key AUTOINCREMENT NOT NULL, " +
                "descricao text not null, " +
                "dataRetirada text not null, " +
                "dataEntrega text not null, " +
                "localRetirada text not null, " +
                "localEntrega text not null, " +
                "idEntregador int, " +
                "idCliente INTEGER, " +
                "preco float);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists usuario";
        db.execSQL(sql);

        sql = "drop table if exists item";
        db.execSQL(sql);

        onCreate(db);

        db.execSQL(sql);
    }

}
