package com.example.thuchanh2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.thuchanh2.R;
import com.example.thuchanh2.model.KhoaHoc;

import java.util.List;

public class KhoaHocAdapter extends ArrayAdapter {
    Context context;
    List<KhoaHoc> khoaHocList;
    private ListItemListener listItemListener;
    public KhoaHocAdapter(@NonNull Context context, @NonNull List list) {
        super(context, R.layout.item, list);

        this.context = context;
        this.khoaHocList = list;
    }

    public void setListItemListener(ListItemListener listItemListener) {
        this.listItemListener = listItemListener;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, null, true);
        TextView tenView = v.findViewById(R.id.item_ten);
        TextView chuyenNganh = v.findViewById(R.id.item_chuyenNganh);
        TextView ngayBatDauView = v.findViewById(R.id.item_ngayBatDau);
        TextView hocPhiView = v.findViewById(R.id.item_hocPhi);
        TextView kichHoatBox = v.findViewById(R.id.item_kichHoat);

        KhoaHoc khoaHoc = khoaHocList.get(position);
        tenView.setText(khoaHoc.getTen());
        chuyenNganh.setText(khoaHoc.getChuyenNganh().getDescription());
        ngayBatDauView.setText(khoaHoc.getNgayBatDau());
        hocPhiView.setText(khoaHoc.getHocPhi());
        kichHoatBox.setText(khoaHoc.getKichHoat() == 1 ? "Đã kích hoạt" : "Chưa kích hoạt");

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemListener.onItemClicked(v, position);
            }
        });
        return v;
    }

    //Item clicked sent to Main
    public interface ListItemListener {
        void onItemClicked(View v, int position);
    }
}
