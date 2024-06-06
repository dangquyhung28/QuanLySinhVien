package com.example.quanlysinhvien;

import java.io.Serializable;

public class ThiSinh implements Serializable {
    String Sbd;
    String HoTen;
    float Toan;
    float Ly;
    float Hoa;

    public String getSbd() {
        return Sbd;
    }

    public void setSbd(String sbd) {
        Sbd = sbd;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public float getToan() {
        return Toan;
    }

    public void setToan(float toan) {
        Toan = toan;
    }

    public float getLy() {
        return Ly;
    }

    public void setLy(float ly) {
        Ly = ly;
    }

    public float getHoa() {
        return Hoa;
    }

    public void setHoa(float hoa) {
        Hoa = hoa;
    }

    public ThiSinh(String sbd, String hoTen, float toan, float ly, float hoa) {
        this.Sbd = sbd;
        this.HoTen = hoTen;
        this.Toan = toan;
        this.Ly = ly;
        this.Hoa = hoa;
    }
    public float tongDiem(){
        float tongDiem = Toan+Ly+Hoa;
        return tongDiem;
    }
    public float diemTB(){
        float tb = (Toan+Ly+Hoa)/3;
        return tb;
    }


}
