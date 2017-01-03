/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.qlmb.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import vn.com.qlmb.model.Ve;
import vn.com.qlmb.view.TraveFrm;

/**
 *
 * @author hoangvinh
 */
public class TraveCtr {

    private TraveFrm tracveform;
    private Connection connect;

    public TraveCtr() {
        this.connect = getConnect();
        this.tracveform = new TraveFrm();
        tracveform.setVisible(true);
        this.tracveform.getBtnSeach().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickSeach();
            }
        });
        this.tracveform.getBtnTrave().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clickTrave();
            }
        });
    }

    public void clickTrave() {
        String idVe = getListSelect();
        System.out.println(idVe);
        if (!"".equalsIgnoreCase(idVe)) {
            List<Ve> lve = seachVe(idVe);
            for (Ve v : lve) {
                if (v.getMa().equalsIgnoreCase(idVe)) {
                    System.out.println(v.getKieu());
                    if (v.getKieu().equalsIgnoreCase("tra lai")) {
                        JOptionPane.showMessageDialog(null, "Vé này đã được trả lại !",
                                "Thông báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        calculateMoney(v);
                    }
                }
            }
        }
    }
    public float calculateMoney(Ve v){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp banve = v.getGioBan();
        long tt = timestamp.getTime() - banve.getTime();
        System.out.println(tt/3600);
        return 0f;
    }
    public boolean updateSttVe(String id,float gia){
        boolean t = false;
        String sql = "update ve set ve.gia = ? , ve.kieu = 'tra lai' where ve.idVe = '"+id+"'";
        System.out.println(sql);
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setFloat(1, gia);
            int x = ps.executeUpdate();
            if(x > 0){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TraveCtr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }
    public Connection getConnect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/qlmaybay", "root", "hoangvinh93");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TraveCtr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void clickSeach() {
        String key = this.tracveform.getTxtSeach().getText();
        List<Ve> listVe = seachVe(key);
        this.tracveform.resetData();
        this.tracveform.gennerateData(listVe);
    }

    public String getListSelect() {
        DefaultTableModel model = (DefaultTableModel) this.tracveform.getTbnSeach().getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 9) != null && (boolean) model.getValueAt(i, 9)) {
                return (String) model.getValueAt(i, 1);
            }
        }
        return "";
    }

    public List<Ve> seachVe(String key) {
        List<Ve> result = new ArrayList<>();
        String sql = "SELECT bt1.*,hangve.ten FROM (SELECT distinct ve.idVe,s1.ten as sanbaydi,s2.ten as sanbayden,chuyenbay.giodi,chuyenbay.gioden,chuyenbay.loaimaybay,ve.gia,chuyenbay.idChuyenBay,ve.kieu,ve.thoiGian FROM \n"
                + "	ve inner join chuyenbay on ve.ChuyenBay_idChuyenBay = chuyenbay.idChuyenBay \n"
                + "    inner join hangve on hangve.ChuyenBay_idChuyenBay = chuyenbay.idChuyenBay\n"
                + "    inner join changbay on chuyenbay.idChuyenBay = changbay.id_chuyenbay\n"
                + "	inner join sanbay s1 on changbay.id_sanbaydi = s1.idSanbay\n"
                + "	inner join sanbay s2 on changbay.id_sanbayden = s2.idSanbay\n"
                + "   ) as bt1\n"
                + "	inner join hangve on hangve.idHangVe = bt1.idChuyenBay\n"
                + "    where idVe like '%" + key + "%'";
        try {
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ResultSet re = ps.executeQuery();
            while (re.next()) {
                Ve temp = new Ve();
                temp.setMa(re.getString("idVe"));
                temp.setSanBayDi(re.getString("sanbaydi"));
                temp.setSanBayDen(re.getString("sanbayden"));
                temp.setGioDi(re.getTimestamp("giodi"));
                temp.setGioDen(re.getTimestamp("gioden"));
                temp.setMayBay(re.getString("loaimaybay"));
                temp.setGiaVe(re.getFloat("gia"));
                temp.setHangVe(re.getString("ten"));
                temp.setKieu(re.getString("kieu"));
                temp.setGioBan(re.getTimestamp("thoiGian"));
                result.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TraveCtr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
        TraveCtr trave = new TraveCtr();
    }
}
