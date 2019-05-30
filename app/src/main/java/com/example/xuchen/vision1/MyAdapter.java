package com.example.xuchen.vision1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<Cloth> mClothList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cImage;
        TextView cName;
        public ViewHolder(View view){
            super(view);
            cImage=(ImageView)view.findViewById(R.id.cloth_image);
            //cName=(TextView)view.findViewById(R.id.cloth_name);
        }
    }
    public MyAdapter(List<Cloth>clothList){
        mClothList=clothList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cloth_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Cloth cloth=mClothList.get(position);
        holder.cImage.setImageResource(cloth.getImageId());
        //holder.cName.setText(cloth.getName());
    }

    @Override
    public int getItemCount(){
        return mClothList.size();
    }
}
