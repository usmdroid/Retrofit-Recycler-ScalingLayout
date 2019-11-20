package com.tomreaddle.api_project_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class HeroRecAdapter extends RecyclerView.Adapter<HeroRecAdapter.HeroHolder> {

    public String NAME = "name";
    public String REALNAME = "realname";
    public String TEAM = "team";
    public String FIRSTAPPEARANCE = "firstappearance";
    public String CREATEDBY = "createdby";
    public String PUBLISHER = "publisher";
    public String IMAGEURL = "imageurl";
    public String BIO = "bio";

    List<HeroModel> data;
    Context context;

    public HeroRecAdapter() {
    }

    public HeroRecAdapter(List<HeroModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public HeroHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item , viewGroup , false);
        return new HeroHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroHolder holder, int i) {
        final HeroModel item = data.get(i);

        holder.name.setText(item.getName());
        holder.realname.setText(item.getRealname());
        holder.time.setText(item.getFirstappearance());
        Glide
                .with(context)
                .load(item.getImageurl())
                .into(holder.image);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , open.class);
                intent.putExtra(NAME , item.getName());
                intent.putExtra(REALNAME , item.getRealname());
                intent.putExtra(TEAM , item.getTeam());
                intent.putExtra(FIRSTAPPEARANCE , item.getFirstappearance());
                intent.putExtra(CREATEDBY , item.getCreatedby());
                intent.putExtra(PUBLISHER , item.getPublisher());
                intent.putExtra(IMAGEURL , item.getImageurl());
                intent.putExtra(BIO , item.getBio());
                context.startActivity(intent);
                //Toast.makeText(context, intent.getStringExtra(NAME) + "\n" + intent.getStringExtra(CREATEDBY), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HeroHolder extends RecyclerView.ViewHolder{
        RelativeLayout item;
        ImageView image;
        TextView name;
        TextView realname;
        TextView time;
        public HeroHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_item);
            name = itemView.findViewById(R.id.name_item);
            realname = itemView.findViewById(R.id.realname_item);
            time = itemView.findViewById(R.id.time_item);
            item = itemView.findViewById(R.id.item);

        }
    }
}
