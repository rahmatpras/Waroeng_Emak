package com.simamat.waroengemak

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class BarangAdapter(val mCtx: Context, val layoutUserId: Int,val barangList: List<Barang>) : ArrayAdapter<Barang>(mCtx,layoutUserId,barangList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutUserId, null)

        val tvName = view.findViewById<TextView>(R.id.tv_item_barang)
        val tvHarga = view.findViewById<TextView>(R.id.tv_item_harga)
        val tvSatuan = view.findViewById<TextView>(R.id.tv_item_satuan)
        val barang = barangList[position]

        tvName.text = barang.namaBarang
        tvHarga.text = barang.hargaBarang
        tvSatuan.text = barang.satuanBarang

        return view
    }

}