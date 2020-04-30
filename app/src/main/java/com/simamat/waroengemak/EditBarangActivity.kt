package com.simamat.waroengemak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class EditBarangActivity : AppCompatActivity() {

    lateinit var etNamaBarang: EditText
    lateinit var etHargaBarang: EditText
    lateinit var etSatuanBarang: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_barang)

        val actionBar = supportActionBar //actionbar
        actionBar!!.title = "Ubah Data Barang" //set actionbar tittle
        //set back button
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        etNamaBarang = findViewById(R.id.et_nama_barang)
        etHargaBarang = findViewById(R.id.et_harga_barang)
        etSatuanBarang = findViewById(R.id.et_satuan_harga_barang)

    }

    private fun saveBarang() {
        val name = etNamaBarang.text.toString().trim()
        val harga = etHargaBarang.text.toString().trim()
        val satuan = etSatuanBarang.text.toString().trim()

        if (name.isEmpty()) {
            etNamaBarang.error = "Tolong isi nama"
            return
        } else if (harga.isEmpty()) {
            etHargaBarang.error = "Tolong isi harga"
            return
        } else if (satuan.isEmpty()) {
            etSatuanBarang.error = "Tolong isi satuan"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("barang")
        val idBarang = ref.push().key.toString()

        val barang = Barang(idBarang, name, harga, satuan)

        ref.child(idBarang).setValue(barang).addOnCompleteListener {
            Toast.makeText(applicationContext, "Sukses Menyimpan barang", Toast.LENGTH_LONG).show()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit_barang, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
//                Toast.makeText(applicationContext, "Save", Toast.LENGTH_SHORT).show()
                saveBarang()
                startActivity(Intent(applicationContext, BarangActivity::class.java))
                onSupportNavigateUp()
                finish()
                true
            }
            R.id.action_delete -> {
                Toast.makeText(applicationContext, "Delete", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

}
