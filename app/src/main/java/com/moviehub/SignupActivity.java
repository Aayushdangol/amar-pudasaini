package com.moviehub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moviehub.url.url;

import Interface.MovieAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private EditText etName,etUsername,etRegisterPassword, etAddress,etContact;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        etName=findViewById( R.id.etName );
        etUsername=findViewById( R.id.etUsername );
        etRegisterPassword=findViewById( R.id.etRegisterPassword );
        etAddress=findViewById( R.id.etAddress );
        etContact=findViewById( R.id.etContact );
        btnRegister=findViewById( R.id.btnRegister );

        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    register();
                }
            }
            private void register() {
                MovieAPI movieAPI = url.getInstance().create(MovieAPI.class);
                String full_name= etName.getText().toString();
                String email= etUsername.getText().toString();
                String password= etRegisterPassword.getText().toString().trim();
                String address= etAddress.getText().toString().trim();
                String contact= etContact.getText().toString().trim();

                Call<Void> usersCall = movieAPI.registerUser(full_name,email,address,contact,password);
                usersCall.enqueue( new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "code " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(SignupActivity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                } );
            }

        } );

    }

    private boolean validate() {
        boolean checkvalidate=true;
        if(TextUtils.isEmpty(etName.getText().toString())){
            etName.setError("Full Name is required");
            etName.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(etUsername.getText().toString())){
            etUsername.setError("Email is required");
            etUsername.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(etRegisterPassword.getText().toString())){
            etRegisterPassword.setError("Password is required");
            etRegisterPassword.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(etAddress.getText().toString())){
            etAddress.setError("Address is required");
            etAddress.requestFocus();
            checkvalidate=false;
        }
        if(TextUtils.isEmpty(etContact.getText().toString())){
            etContact.setError("Contact is required");
            etContact.requestFocus();
            checkvalidate=false;
        }
        return  checkvalidate;
    }


}
