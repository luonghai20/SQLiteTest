package com.haixl.sqlitetest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.haixl.sqlitetest.R;
import com.haixl.sqlitetest.model.student;

import java.util.ArrayList;

public class listViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<student> students;

    public listViewAdapter(Context context, ArrayList<student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        private TextView txtName;
        private TextView txtClass;
        private ImageButton imgMenu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listview,parent,false);
            viewHolder.txtName=convertView.findViewById(R.id.tvName);
            viewHolder.txtClass=convertView.findViewById(R.id.tvClass);

            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.txtName.setText(students.get(position).getTen());
        viewHolder.txtClass.setText(students.get(position).getLop());

        return convertView;
    }
}
