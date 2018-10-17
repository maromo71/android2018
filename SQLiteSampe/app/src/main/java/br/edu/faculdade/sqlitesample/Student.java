package br.edu.faculdade.sqlitesample;

/**
 * Created by maromo on 05/07/2017.
 */

public class Student {
    // Rótulo para nome da tabela
    public static final String TABLE = "Student";

    // Rótulo para os nomes das colunas da Tabela
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_email = "email";
    public static final String KEY_age = "age";

    // Campos de dados para manter os dados
    private int student_ID;
    private String name;
    private String email;
    private int age;

    public int getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(int student_ID) {
        this.student_ID = student_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
