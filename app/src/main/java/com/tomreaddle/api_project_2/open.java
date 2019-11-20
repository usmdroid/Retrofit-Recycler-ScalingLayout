package com.tomreaddle.api_project_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class open extends AppCompatActivity {

    String name , realname , team , firstappearance , createdby , publisher , imageurl , bio;
    ImageView open_image;
    TextView open_createdby , open_time , open_title , open_desc , open_tag;
    HeroRecAdapter data = new HeroRecAdapter();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.open);
        super.onCreate(savedInstanceState);

        name = get(data.NAME);
        realname= get(data.REALNAME);
        team = get(data.TEAM);
        firstappearance = get(data.FIRSTAPPEARANCE);
        createdby = get(data.CREATEDBY);
        publisher = get(data.PUBLISHER);
        imageurl = get(data.IMAGEURL);
        bio = get(data.BIO);

        open_image = findViewById(R.id.open_image);
        open_createdby = findViewById(R.id.open_createby);
        open_time = findViewById(R.id.open_time);
        open_title = findViewById(R.id.open_title);
        open_desc = findViewById(R.id.open_desc);
        open_tag = findViewById(R.id.open_tag);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(team);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        open_title.setText(name);
        open_createdby.setText(createdby);
        open_time.setText(firstappearance);
        open_desc.setText(bio);
        open_tag.setText("#" + realname + "    #" + team + "    #" + createdby + "    #" + publisher);
        Glide
                .with(this)
                .load(imageurl)
                .into(open_image);
        Toast.makeText(this, name , Toast.LENGTH_SHORT).show();

    }

    public String get(String key){
        Intent intent = getIntent();
        return intent.getStringExtra(key);
    }
}
