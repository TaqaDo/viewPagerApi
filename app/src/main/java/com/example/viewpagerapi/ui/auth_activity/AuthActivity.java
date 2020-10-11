package com.example.viewpagerapi.ui.auth_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.viewpagerapi.R;
import com.example.viewpagerapi.data.local.PreferenceUtils;
import com.example.viewpagerapi.data.model.AuthModel;
import com.example.viewpagerapi.data.network.auth_service.AuthApi;
import com.example.viewpagerapi.data.network.auth_service.AuthService;
import com.example.viewpagerapi.ui.main.MainActivity;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    private EditText editLogin;
    private EditText editPassword;
    private Button btnSing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        checkForSkip();
        init();
        onClicks();

    }

    private void checkForSkip() {
        if (!PreferenceUtils.getUser().isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void onClicks() {
        btnSing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " );
                authorization();
            }
        });
    }


    private void authorization() {
        String login = editLogin.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String header = Credentials.basic(login, password);

        PreferenceUtils.saveUser(header);

        AuthService.getClient().auth(header).enqueue(new Callback<AuthModel>() {
            @Override
            public void onResponse(Call<AuthModel> call, Response<AuthModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("TAG", "onResponse: " + response );
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<AuthModel> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t );
            }
        });
    }

    private void init() {
        editLogin = findViewById(R.id.edit_login);
        editPassword = findViewById(R.id.edit_password);
        btnSing = findViewById(R.id.btn_sign_in);

    }
}