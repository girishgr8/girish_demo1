package com.example.android.chat_demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.LENGTH_SHORT;

public class Register extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Button reg;
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        firebaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);
        reg=findViewById(R.id.reg);
        username=findViewById(R.id.user);
        password=findViewById(R.id.pass);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String user=username.getText().toString();
                    String pwd=password.getText().toString();
                Toast.makeText(Register.this, user+" "+password, Toast.LENGTH_SHORT).show();
               // Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                    firebaseAuth.createUserWithEmailAndPassword(user,pwd).addOnCompleteListener(Register.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,MainActivity.class));
                            }else
                            {
                                Toast.makeText(Register.this, "Not Registered", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    return;
                }



        });
    }


}
