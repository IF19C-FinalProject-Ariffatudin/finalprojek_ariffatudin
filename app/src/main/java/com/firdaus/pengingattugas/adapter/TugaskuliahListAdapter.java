package com.firdaus.pengingattugas.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.firdaus.pengingattugas.R;
import com.firdaus.pengingattugas.database.TugasKuliah;

import java.util.List;


public class TugaskuliahListAdapter extends BaseAdapter {

    private Context bContext;
    private List<TugasKuliah> bTugasKuliah;

    public TugaskuliahListAdapter(Context bContext, List<TugasKuliah> bTugasKuliah) {
        this.bContext = bContext;
        this.bTugasKuliah = bTugasKuliah;
    }

    @Override
    public int getCount() {
        return bTugasKuliah.size();
    }

    @Override
    public Object getItem(int position) {
        return bTugasKuliah.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(bContext, R.layout.item_tugaskuliah_list, null);

        TextView tipeTugas = (TextView) view.findViewById(R.id.inputTipe);
        TextView judulTugas = (TextView) view.findViewById(R.id.titleTugas);
        TextView deskripsiTugas = (TextView) view.findViewById(R.id.deskTugas);
        TextView dateline = (TextView) view.findViewById(R.id.Ddate);
        TextView datelineWaktu = (TextView) view.findViewById(R.id.DWaktu);
        TextView pengingat = (TextView) view.findViewById(R.id.pengingat);

        judulTugas.setText(bTugasKuliah.get(position).getJudulTask());
        tipeTugas.setText(bTugasKuliah.get(position).getTipeTask());
        deskripsiTugas.setText(bTugasKuliah.get(position).getDeskripsiTask());
        dateline.setText(bTugasKuliah.get(position).getD_dateTask());
        datelineWaktu.setText("/ " + bTugasKuliah.get(position).getD_timeTask());
        pengingat.setText(bTugasKuliah.get(position).getPengingatTask());

        return view;
    }
}
