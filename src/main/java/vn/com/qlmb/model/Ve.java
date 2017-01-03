/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.qlmb.model;

import java.sql.Timestamp;

/**
 *
 * @author hoangvinh
 */
public class Ve {
    private String ma;
    private String sanBayDi;
    private String sanBayDen;
    private Timestamp gioDi;
    private Timestamp gioDen;
    private String mayBay;
    private String hangVe;
    private float giaVe;
    private String kieu;
    private Timestamp gioBan;

    public Ve(String ma, String sanBayDi, String sanBayDen, Timestamp gioDi, Timestamp gioDen, String mayBay, String hangVe, float giaVe) {
        this.ma = ma;
        this.sanBayDi = sanBayDi;
        this.sanBayDen = sanBayDen;
        this.gioDi = gioDi;
        this.gioDen = gioDen;
        this.mayBay = mayBay;
        this.hangVe = hangVe;
        this.giaVe = giaVe;
    }

    public Ve() {
    }
    
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getSanBayDi() {
        return sanBayDi;
    }

    public void setSanBayDi(String sanBayDi) {
        this.sanBayDi = sanBayDi;
    }

    public String getSanBayDen() {
        return sanBayDen;
    }

    public void setSanBayDen(String sanBayDen) {
        this.sanBayDen = sanBayDen;
    }

    public Timestamp getGioDi() {
        return gioDi;
    }

    public void setGioDi(Timestamp gioDi) {
        this.gioDi = gioDi;
    }

    public Timestamp getGioDen() {
        return gioDen;
    }

    public void setGioDen(Timestamp gioDen) {
        this.gioDen = gioDen;
    }

    public String getMayBay() {
        return mayBay;
    }

    public void setMayBay(String mayBay) {
        this.mayBay = mayBay;
    }

    public String getHangVe() {
        return hangVe;
    }

    public void setHangVe(String hangVe) {
        this.hangVe = hangVe;
    }

    public float getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(float giaVe) {
        this.giaVe = giaVe;
    }
    public String toString(){
        return ma+" "+sanBayDi+" "+sanBayDen+" "+mayBay+" "+gioDi+" "+gioDen;
    }

    public String getKieu() {
        return kieu;
    }

    public void setKieu(String kieu) {
        this.kieu = kieu;
    }

    public Timestamp getGioBan() {
        return gioBan;
    }

    public void setGioBan(Timestamp gioBan) {
        this.gioBan = gioBan;
    }
    
    
}
