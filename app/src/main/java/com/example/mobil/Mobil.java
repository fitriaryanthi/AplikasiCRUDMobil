package com.example.mobil;

public class Mobil {
    private long id;
    private String nama_mobil;
    private String merk_mobil;
    private String harga_mobil;

    public Mobil ()
    {

    }

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getNama_mobil () {
        return nama_mobil;
    }

    public void setNama_mobil (String nama_mobil) {
        this.nama_mobil = nama_mobil;
    }

    public String getMerk_mobil () {
        return merk_mobil;
    }

    public void setMerk_mobil (String merk_mobil) {
        this.merk_mobil = merk_mobil;
    }

    public String getHarga_mobil () {
        return harga_mobil;
    }

    public void setHarga_mobil (String harga_mobil) {
        this.harga_mobil = harga_mobil;
    }

    public String toString () {
        return " \nNama Mobil :" + nama_mobil +" \nMerk Mobil :" + merk_mobil +" \nHarga :" + harga_mobil;
    }
}
