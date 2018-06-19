/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.product;

import com.utils.DataSourceUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 *
 * @author LIGUANG
 */
public class AllProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *展示所有商品模块
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");    //设置接收编码
        response.setContentType("text/html;charset=UTF-8");

        String jsonstr=null;           //用于写json
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());//连接数据库
        String sql ="select * from product where cname =?;";//sql语句
        
        /*
        *从数据库中查询所有商品
        *MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
        *然后转换成json数据
        */
        
        List<Map<String, Object>> mapList1 = qr.query(sql, new MapListHandler(),"化妆用品");
        List<Map<String, Object>> mapList2 = qr.query(sql, new MapListHandler(),"生活用品");
        List<Map<String, Object>> mapList3 = qr.query(sql, new MapListHandler(),"书籍办公");
        List<Map<String, Object>> mapList4 = qr.query(sql, new MapListHandler(),"数码产品");
        List<Map<String, Object>> mapList5 = qr.query(sql, new MapListHandler(),"衣服配饰");
        List<Map<String, Object>> mapList6 = qr.query(sql, new MapListHandler(),"运动用品");
        List<Map<String, Object>> mapList7 = qr.query(sql, new MapListHandler(),"游戏周边");
//	for (Map<String, Object> map : mapList) {
//	    JSONObject jsonObject = JSONObject.fromObject(map);
//            JSONObject jsonObject2 = new JSONObject();
//            jsonObject2.put("product", jsonObject);
//            response.getWriter().println(jsonObject2);
//            
//	}
         JSONObject jsonObject2 = new JSONObject();
         jsonObject2.put("Makeup_products", mapList1);   //化妆用品
         jsonObject2.put("daily_use", mapList2);         //生活用品
         jsonObject2.put("Book_office", mapList3);       // 书籍办公
         jsonObject2.put("Digital_products", mapList4);  // 数码产品
         jsonObject2.put("Clothing_Acc", mapList5);      //衣服配饰
         jsonObject2.put("Sports_products", mapList6);   //运动用品
         jsonObject2.put("Game", mapList7);              //游戏周边
         JSONObject jsonObject3 = new JSONObject();
         jsonObject3.put("categroy",jsonObject2);
         
         response.getWriter().println(jsonObject3);
                    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AllProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AllProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
