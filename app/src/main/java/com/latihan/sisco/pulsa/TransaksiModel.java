package com.latihan.sisco.pulsa;



public class TransaksiModel {
    private int id;
    private String code;
    private String nohp;

    public TransaksiModel() {

    }

    public TransaksiModel(String code, String nohp) {
        this.code = code;
        this.nohp = nohp;
    }

    public TransaksiModel(int id, String code, String nohp) {
        this.id = id;
        this.code = code;
        this.nohp = nohp;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}