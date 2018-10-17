package br.edu.faculdade.sqlitesample;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by maromo on 05/07/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    //Número da versão para atualizar o banco
    //Cada vez que precisar Adicionar ou Editar a tabela, você precisa mudar o número
    // da versão.
    private static final int DATABASE_VERSION = 4;

    // Nome do Banco de Dados
    private static final String DATABASE_NAME = "students.db";

    //Necessário um construtor padrão
    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Todas as tabelas necessárias para o programa devem ser criadas aqui
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Student.TABLE  + "("
                + Student.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Student.KEY_name + " TEXT, "
                + Student.KEY_age + " INTEGER, "
                + Student.KEY_email + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Apgar as tabelas velhas, se existentes, todos os dados serão apagados!!!
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Student.TABLE);
        // Criar tabelas novamente
        onCreate(sqLiteDatabase);
    }
}
