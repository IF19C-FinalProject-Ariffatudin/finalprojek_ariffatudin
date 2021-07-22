package com.firdaus.pengingattugas.fragment;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.firdaus.pengingattugas.DetailActivity;
import com.firdaus.pengingattugas.R;
import com.firdaus.pengingattugas.adapter.MatakuliahListAdapter;
import com.firdaus.pengingattugas.database.MataKuliah;
import com.firdaus.pengingattugas.database.QueryMK;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class KamisFragment extends Fragment {

    private ListView listView;
    private MatakuliahListAdapter adapterMatakuliah;
    private List<MataKuliah> mMatakuliah;
    private QueryMK db;

    public KamisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kamis, container, false);

        listView = view.findViewById(R.id.dataKamis);

        db = new QueryMK(getContext());
        mMatakuliah = new ArrayList<>();
        db.open();
        mMatakuliah = db.DataJadwalKuliah("'Thursday'");
        db.close();

        adapterMatakuliah = new MatakuliahListAdapter(getContext(), mMatakuliah);
        listView.setAdapter(adapterMatakuliah);
        adapterMatakuliah.notifyDataSetChanged();

        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String select = mMatakuliah.get(position).getKodeMK();
                String selectHari = mMatakuliah.get(position).getHariMK();
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("kodeMK", select);
                intent.putExtra("hariMK", selectHari);
                startActivity(intent);
            }
        });
        return view;
    }

}
