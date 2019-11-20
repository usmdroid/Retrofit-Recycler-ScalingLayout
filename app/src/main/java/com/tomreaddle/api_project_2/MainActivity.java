package com.tomreaddle.api_project_2;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RelativeLayout infoBlock;
    RecyclerView recyclerView;
    ConstraintLayout mainactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainactivity = findViewById(R.id.mainactivity);
        recyclerView = findViewById(R.id.recyclerview);
        infoBlock = findViewById(R.id.infoBlock);

        isConnect();
        retrec();

    }

    public void retrec(){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Interface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interface api = retrofit.create(Interface.class);
        Call<List<HeroModel>> call = api.getData();
        call.enqueue(new Callback<List<HeroModel>>() {
            @Override
            public void onResponse(Call<List<HeroModel>> call, Response<List<HeroModel>> response) {
                List<HeroModel> heroes =   response.body();


                recyclerView.setAdapter(new HeroRecAdapter(heroes , MainActivity.this));

            }

            @Override
            public void onFailure(Call<List<HeroModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error to connect internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void isConnect() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            infoBlock.setVisibility(View.INVISIBLE);
        } else {
            mainactivity.setBackgroundColor(Color.WHITE);
            infoBlock.setVisibility(View.VISIBLE);
        }
    }

}

