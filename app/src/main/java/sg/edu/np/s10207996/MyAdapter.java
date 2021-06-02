package sg.edu.np.s10207996;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<User> data;
    public MyAdapter(Context context, ArrayList<User> input) { data = input; }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.myrecyclerview,
                parent,
                false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User userlist = data.get(position);
        holder.text1.setText(userlist.getName());
        holder.text2.setText((userlist.getDescription()));
        if(userlist.getName().endsWith("7")){
            holder.bigImage.setImageResource(R.mipmap.ic_launcher_round);
            holder.bigImage.setVisibility(View.VISIBLE);
        }
        else{
            holder.bigImage.setVisibility(View.GONE);
        }
        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(holder.image.getContext())
                        .setTitle("Profile")
                        .setMessage(userlist.getName())
                        .setNegativeButton("Close", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int i){
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("View", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int i){
                                Bundle extras = new Bundle();
                                extras.putInt("id", userlist.getId());
                                extras.putString("name", userlist.getName());
                                extras.putString("description", userlist.getDescription());
                                extras.putBoolean("value", userlist.getFollowed());
                                Intent in = new Intent(holder.image.getContext(), MainActivity.class);
                                in.putExtras(extras);
                                holder.image.getContext().startActivity(in);
                            }
                        })
                        .setNegativeButton("Close", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2;
        ImageView image, bigImage;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            text1 = itemView.findViewById(R.id.textView2);
            text2 = itemView.findViewById(R.id.textView4);
            image = itemView.findViewById(R.id.imageView1);
            bigImage = itemView.findViewById(R.id.imageView2);
        }
    }
}
