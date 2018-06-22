/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

import com.utils.DataSourceUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

/**
 *
 * @author LIGUANG
 */
public class ViewAllIndent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *管理员查看所有订单
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");    //设置接收编码
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String adminname=(String)session.getAttribute("adminname");

        String jsonstr=null;           //用于写json
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());//连接数据库
        String sql ="select * from indent order by ctime desc;";//sql语句

        if(adminname==null)
        {
        jsonstr="{'state':0,\"message\":\""+"非管理员禁止操作该页面"+"\"}";
        JSONObject json = JSONObject.fromObject(jsonstr);
        response.getWriter().println(json);
        }
        else
        {
        /*
        *从数据库中查询所有订单
        *MapListHandler, 将查询结果的每一条记录封装map集合,将每一个map集合放入list中返回
        *然后转换成json数据
        */
        
        List<Map<String, Object>> mapList1 = qr.query(sql, new MapListHandler());
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("AllIndent", mapList1);       
        response.getWriter().println(jsonObject2);
        }
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
            Logger.getLogger(ViewAllIndent.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ViewAllIndent.class.getName()).log(Level.SEVERE, null, ex);
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
