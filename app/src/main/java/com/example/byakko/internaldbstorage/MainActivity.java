package com.example.byakko.internaldbstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etTel;
    Button btnAgregar, btnMostrar;
    TextView tvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTel = (EditText)findViewById(R.id.etTel);
        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        btnMostrar = (Button)findViewById(R.id.btnMostrar);
        tvDatos = (TextView)findViewById(R.id.tvDatos);

        btnAgregar.setOnClickListener(this);
        btnMostrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAgregar:
                String number = etTel.getText().toString();
                try{
                    DBHelper dbh = new DBHelper(this);
                    dbh.openDB();
                    long result = dbh.register(number);
                    if(result > 0){
                        Toast.makeText(this, "Registro ingresado con exito", Toast.LENGTH_LONG).show();
                    }
                    dbh.closeDB();
                }catch(Exception e){
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnMostrar:
                try{
                    DBHelper dbh = new DBHelper(this);
                    dbh.openDB();
                    String result = dbh.consult();
                    tvDatos.setText(result);
                    dbh.closeDB();
                }catch(Exception e){
                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
