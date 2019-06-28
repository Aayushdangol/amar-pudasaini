package com.moviehub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moviehub.admin.AdminDashboardActivity;
import com.moviehub.url.url;

import Interface.MovieAPI;
import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=findViewById( R.id.etmail );
        etPassword=findViewById( R.id.etPassword );
        btnLogin=findViewById( R.id.btnLogin );
        btnRegister=findViewById( R.id.btnRegister );

        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( LoginActivity.this, SignupActivity.class );
                startActivity( intent );
            }
        } );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    checkUser();
                }
            }
            private boolean validate() {
                boolean checkvalidate=true;
                if(TextUtils.isEmpty(etUsername.getText().toString())){
                    etUsername.setError("Username is required");
                    etUsername.requestFocus();
                    checkvalidate=false;
                }
                if(TextUtils.isEmpty(etPassword.getText().toString())){
                    etPassword.setError("Password is required");
                    etPassword.requestFocus();
                    checkvalidate=false;
                }
                return  checkvalidate;
            }
            private void checkUser() {
                if(etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")){
                    Intent intent = new Intent( LoginActivity.this, AdminDashboardActivity.class );
                    startActivity( intent );
                }else{
                    MovieAPI movieAPI= url.getInstance().create( MovieAPI.class );
                    String username=etUsername.getText().toString().trim();
                    String password=etPassword.getText().toString().trim();

                    Call<LoginSignupResponse> usersCall = movieAPI.checkUser( username,password );
                    usersCall.enqueue( new Callback<LoginSignupResponse>() {
                        @Override
                        public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Either Username or Password is Incorrect ",Toast.LENGTH_SHORT).show();
                                return;

                            }else{
                                Toast.makeText(LoginActivity.this," "+response.body().getToken(),Toast.LENGTH_SHORT).show();


                                if(response.body().getSuccess()){
                                    url.token=response.body().getToken();
                                    Toast.makeText(LoginActivity.this,"Success ",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginSignupResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this,"Error "+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

                        }
                    } );

                }
            }

        } );
    }


}



