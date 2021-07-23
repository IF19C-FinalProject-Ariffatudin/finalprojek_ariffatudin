package com.firdaus.pengingattugas.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.firdaus.pengingattugas.R;
import com.firdaus.pengingattugas.database.MataKuliah;

import java.util.List;


public class DetailMatakuliahListAdapter extends BaseAdapter {

    private Context tContext;
    private List<MataKuliah> tMatakuliahList;

    public DetailMatakuliahListAdapter(Context tContext, List<MataKuliah> tMatakuliahList) {
        this.tContext = tContext;
        this.tMatakuliahList = tMatakuliahList;
    }

    @Override
    public int getCount() {
        return tMatakuliahList.size();
    }

    @Override
    public Object getItem(int position) {
        return tMatakuliahList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewDetail = View.inflate(tContext, R.layout.item_detail_matakuliah_list, null);

        TextView dinputMK = (TextView) viewDetail.findViewById(R.id.mkCode);
        TextView dnamaMK = (TextView) viewDetail.findViewById(R.id.namaMataKuliah);
        TextView dnamaDosen = (TextView) viewDetail.findViewById(R.id.namaDosen);
        TextView dnamaRuangan = (TextView) viewDetail.findViewById(R.id.listRuangan);
        TextView dwaktuMK = (TextView) viewDetail.findViewById(R.id.listWaktu);

        dinputMK.setText(tMatakuliahList.get(position).getKodeMK());
        dnamaMK.setText(tMatakuliahList.get(position).getNamaMK());
        dnamaDosen.setText(tMatakuliahList.get(position).getDosen());
        dnamaRuangan.setText(tMatakuliahList.get(position).getRuangMK());
        dwaktuMK.setText(tMatakuliahList.get(position).getWaktu());

        return viewDetail;
    }
}
