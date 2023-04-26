package com.example.thuchanh2.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thuchanh2.R;
import com.example.thuchanh2.UpdateActivity;
import com.example.thuchanh2.adapter.KhoaHocAdapter;
import com.example.thuchanh2.database.KhoaHocDAO;
import com.example.thuchanh2.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment implements KhoaHocAdapter.ListItemListener {
    SearchView searchView;
    Spinner spinner;
    Button searchButton;
    ListView listView;
    List<KhoaHoc> khoaHocs = new ArrayList<>();
    KhoaHocDAO db;
    KhoaHocAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        searchButton = v.findViewById(R.id.search_btnSubmit);
        spinner = v.findViewById(R.id.spinner);
        searchView = v.findViewById(R.id.search_searchView);
        listView = v.findViewById(R.id.search_listItem);

        db = new KhoaHocDAO(getContext());
        adapter = new KhoaHocAdapter(getContext(), khoaHocs);
        adapter.setListItemListener(this);
        listView.setAdapter(adapter);

        // Search Button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = String.valueOf(searchView.getQuery());
                int index = (int) spinner.getSelectedItemId();

                khoaHocs.clear();
                khoaHocs.addAll(db.search(key, index));
                adapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    @Override
    public void onItemClicked(View v, int position) {
        Intent intent = new Intent(getContext(), UpdateActivity.class);
        KhoaHoc khoaHoc = khoaHocs.get(position);
        intent.putExtra("item", khoaHoc);
        startActivity(intent);
    }

    // Load lại fragment sau khi add
    @Override
    public void onResume() {
        super.onResume();
        khoaHocs.clear();
        adapter.notifyDataSetChanged();
    }
}
