/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

import com.utils.DataSourceUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;

/**
 *
 * @author LIGUANG
 */
public class Shipment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");    //设置接收编码
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String adminname=(String)session.getAttribute("adminname"); //从session里面获取管理员名
        String indentid=request.getParameter("indentid");
        String indentsta="配送中";

        String jsonstr=null;           //用于写json
        String sql ="update indent set indentsta=? where indentid=?;";//sql语句

        if(adminname==null)
        {
        jsonstr="{'state':0,\"message\":\""+"非管理员禁止操作该页面"+"\"}";
        JSONObject json = JSONObject.fromObject(jsonstr);
        response.getWriter().println(json);
        }
        else
        { 
         QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource()); //连接数据库 
          try{              
              qr.update(sql,indentsta,indentid); 
//              qr.update(sql,indentsta,"111"); //测试
              jsonstr="{\"state\":1,\"message\":\""+"发货成功"+"\"}";   
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
        }catch(Exception e)
        {
              jsonstr="{\"state\":2,\"message\":\""+"发货失败"+"\"}";   
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
       }
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
