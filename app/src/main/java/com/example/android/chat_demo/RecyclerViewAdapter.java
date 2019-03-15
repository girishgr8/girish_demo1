package com.example.android.chat_demo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

   Context mContext;
   List<Expense> mData;
   Dialog myDialog;


    public RecyclerViewAdapter(Context mContext, List<Expense> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v;
       v= LayoutInflater.from(mContext).inflate(R.layout.item_expense,parent, false);
       final MyViewHolder vHolder = new MyViewHolder(v);

       myDialog = new Dialog(mContext);
       myDialog.setContentView(R.layout.dialog_expense);
       myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


       vHolder.item_expense.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TextView dialog_name_tv= (TextView) myDialog.findViewById(R.id.dialog_name_id);
               TextView dialog_amt_tv= (TextView) myDialog.findViewById(R.id.dialog_amount_id);
               ImageView dialog_expense_image= (ImageView) myDialog.findViewById(R.id.dialog_img);

               dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
               dialog_amt_tv.setText(mData.get(vHolder.getAdapterPosition()).getAmount());
               dialog_expense_image.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

               Toast.makeText(mContext,"Please enter amount spent on "+String.valueOf(mData.get(vHolder.getAdapterPosition()).getName()),Toast.LENGTH_SHORT).show();
               myDialog.show();
           }
       });
        return vHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_amount.setText(mData.get(position).getAmount());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_expense;
        private TextView tv_name;
        private  TextView tv_amount;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_expense= (LinearLayout) itemView.findViewById(R.id.expense_item_id);
            tv_name= (TextView) itemView.findViewById(R.id.name_expense);
            tv_amount= (TextView) itemView.findViewById(R.id.amount_expense);
            img =(ImageView) itemView.findViewById(R.id.img_expense);
        }
    }
}
