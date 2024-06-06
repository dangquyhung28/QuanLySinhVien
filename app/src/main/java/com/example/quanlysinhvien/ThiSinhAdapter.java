package com.example.quanlysinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ThiSinhAdapter extends ArrayAdapter<ThiSinh> {
    public ThiSinhAdapter(@NonNull Context context, int resource, @NonNull ThiSinh[] objects) {
        super(context, resource, objects);
    }

    public ThiSinhAdapter(@NonNull Context context, int resource, @NonNull List<ThiSinh> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.items,null);

        }
        ThiSinh ts = getItem(position);
        if(ts!=null){
            TextView tv0 = (TextView) v.findViewById(R.id.txtSBD);
            TextView tv1 = (TextView) v.findViewById(R.id.textViewHoTen);
            TextView tv2 = (TextView) v.findViewById(R.id.textViewTxtTongDiem);
            tv0.setText(ts.Sbd);
            tv1.setText(ts.HoTen);
            tv2.setText(String.valueOf(ts.tongDiem()));
        }
        return v;
    }
}
