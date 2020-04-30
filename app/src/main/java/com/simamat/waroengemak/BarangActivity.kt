package com.simamat.waroengemak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_barang.*

class BarangActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var barangList: MutableList<Barang>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barang)

        val actionBar = supportActionBar //actionbar
        actionBar!!.title = "Pengaturan Emak" //set actionbar tittle
        //set back button
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        listView = findViewById(R.id.lv_harga_barang)

        fab_add_barang.setOnClickListener {
            startActivity(Intent(this, EditBarangActivity::class.java))
        }

        barangList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("barang")

        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    for (h in p0.children) {
                        val barang = h.getValue(Barang::class.java)
                        barangList.add(barang!!)
                    }

                    val adapter = BarangAdapter(applicationContext, R.layout.item_daftar_harga, barangList)
                    listView.adapter = adapter
                }
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }
}
