package com.ara.walli;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by r4kia on 4/20/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;
    Context ctx;

    ArrayList<Job> arrayList = new ArrayList<>();
    public RecyclerAdapter(ArrayList<Job>arrayList,Context ctx)
    {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEAD)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout,parent,false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,viewType,ctx,arrayList);

            return recyclerViewHolder;
        }
        else if(viewType ==TYPE_LIST)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,viewType,ctx,arrayList);

            return recyclerViewHolder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        if(holder.viewType==TYPE_LIST)
        {
            Job job = arrayList.get(position-1);
            holder.JName.setText(job.getJname());
            holder.Name.setText(job.getName());

        }




    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView Name,JName;
        int viewType;
        ArrayList<Job> arrayList = new ArrayList<>();
        Context ctx;
        public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<Job> arrayList)
        {
            super(view);
            if(viewType==TYPE_LIST)
            {
                this.arrayList = arrayList;
                this.ctx = ctx;
                view.setOnClickListener(this);
                JName = (TextView) view.findViewById(R.id.jname);
                Name = (TextView) view.findViewById(R.id.name);
                this.viewType = TYPE_LIST;
            }
            else if(viewType==TYPE_HEAD)
            {
                this.viewType=TYPE_HEAD;
            }


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition()-1;
            Job job = this.arrayList.get(position);

            Intent intent = new Intent(this.ctx,JobDetail.class);
            intent.putExtra("d_name",job.getName());
            intent.putExtra("d_jname",job.getJname());
            intent.putExtra("d_description",job.getJdescription());
            intent.putExtra("d_jpay",job.getPay());
            intent.putExtra("d_jlocation",job.getLocation());
            this.ctx.startActivity(intent);
        }
    }
    @Override
    public int getItemViewType(int position){
        if(position==0)
            return TYPE_HEAD;
        return TYPE_LIST;

    }
}

