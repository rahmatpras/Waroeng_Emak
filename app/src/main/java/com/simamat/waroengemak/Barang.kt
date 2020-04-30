package com.simamat.waroengemak

class Barang(
    val idBarang: String,
    val namaBarang: String,
    val hargaBarang: String,
    val satuanBarang: String
) {
    constructor() : this("", "", "", "") {

    }
}