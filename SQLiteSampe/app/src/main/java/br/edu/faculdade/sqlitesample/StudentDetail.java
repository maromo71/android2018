package br.edu.faculdade.sqlitesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentDetail extends AppCompatActivity
        implements View.OnClickListener {


    Button btnSave, btnDelete;
    Button btnClose;
    EditText editTextName;
    EditText editTextEmail;
    EditText editTextAge;
    private int _Student_Id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        btnSave =  findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnClose =  findViewById(R.id.btnClose);
        editTextName =  findViewById(R.id.editTextName);
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextAge =  findViewById(R.id.editTextAge);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        _Student_Id = 0;
        Intent intent = getIntent();
        _Student_Id = intent.getIntExtra("student_Id", 0);
        StudentDao dao = new StudentDao(this);
        Student student = new Student();
        student = dao.getStudentById(_Student_Id);
        editTextAge.setText(String.valueOf(student.getAge()));
        editTextName.setText(student.getName());
        editTextEmail.setText(student.getEmail());
    }

    /**
     * Called when a view has been clicked.
     *
     * @param view The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)) {
            StudentDao dao = new StudentDao(this);
            Student student = new Student();
            student.setAge(Integer.parseInt(editTextAge.getText().toString()));
            student.setEmail(editTextEmail.getText().toString());
            student.setName(editTextName.getText().toString());
            student.setStudent_ID(_Student_Id);
            if (_Student_Id == 0) {
                _Student_Id = dao.insert(student);
                Toast.makeText(this, "Novo Aluno incluído", Toast.LENGTH_SHORT).show();
            } else {
                dao.update(student);
                Toast.makeText(this, "Registro do Aluno atualizado com sucesso", Toast.LENGTH_SHORT).show();
            }
        } else if (view == findViewById(R.id.btnDelete)) {
            StudentDao dao = new StudentDao(this);
            dao.delete(_Student_Id);
            Toast.makeText(this, "Registro do Aluno excluído da lista", Toast.LENGTH_SHORT).show();
            finish();
        } else if (view == findViewById(R.id.btnClose)) {
            finish();
        }
    }
}

