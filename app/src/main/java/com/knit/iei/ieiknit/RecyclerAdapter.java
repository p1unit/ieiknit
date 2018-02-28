package com.knit.iei.ieiknit;

/**
 * Created by puneet on 14/2/18.
 */

import android.app.Application;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.MyHoder>{
    List<Post> list;
    Context context;

    public RecyclerAdapter(List<Post> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Post mylist = list.get(position);
        holder.name.setText(mylist.getTitle());
        holder.email.setText(mylist.getDesc());
        holder.setImg(mylist.getImage());
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    class MyHoder extends RecyclerView.ViewHolder{
         TextView name,email,image;
        ImageView img;
        View mview;

        public MyHoder(View itemView) {
            super(itemView);
            mview=itemView;
            name =  (TextView)itemView.findViewById(R.id.post_title);
            email= (TextView) itemView.findViewById(R.id.post_text);

        }

        public void setImg(final String image){

            img =(ImageView)mview.findViewById(R.id.post_image);
//            Picasso.with(mview.getContext()).load(image).into(img);

            Picasso.with(mview.getContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(img, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(mview.getContext()).load(image).into(img);
                }
            });

        }
    }

}

