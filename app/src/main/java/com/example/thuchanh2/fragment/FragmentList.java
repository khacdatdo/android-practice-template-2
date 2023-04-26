package com.example.thuchanh2.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thuchanh2.R;
import com.example.thuchanh2.UpdateActivity;
import com.example.thuchanh2.adapter.KhoaHocAdapter;
import com.example.thuchanh2.database.KhoaHocDAO;
import com.example.thuchanh2.model.KhoaHoc;

import java.util.List;

public class FragmentList extends Fragment implements KhoaHocAdapter.ListItemListener {
    ListView listView;
    KhoaHocDAO db;
    KhoaHocAdapter adapter;
    List<KhoaHoc> khoaHocs;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        listView = v.findViewById(R.id.list_listItem);
        db = new KhoaHocDAO(getContext());

        khoaHocs = db.getAll();
        adapter = new KhoaHocAdapter(getContext(), khoaHocs);
        adapter.setListItemListener(this);
        listView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onItemClicked(View v, int position) {
        Intent intent = new Intent(getContext(), UpdateActivity.class);
        KhoaHoc khoaHoc = khoaHocs.get(position);
        intent.putExtra("item", khoaHoc);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        khoaHocs.clear();
        khoaHocs.addAll(db.getAll());
        adapter.notifyDataSetChanged();
    }
}
