/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import com.utils.DataSourceUtils;
import com.utils.UUIDUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;

/**
 *
 * @author LIGUANG
 */
public class UserRegister extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *用户注册模块
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");  //设置接收编码
        response.setContentType("text/html;charset=UTF-8");
        
        String username=request.getParameter("username");   //获取用户名
        String password=request.getParameter("password");   //获取密码
        String sex=request.getParameter("sex");             //获取性别
        String email=request.getParameter("email");         //获取邮件
        String telephone=request.getParameter("telephone"); //获取电话
        String postcode=request.getParameter("postcode");   //获取邮编
        String address=request.getParameter("address");     //获取地址
        String jsonstr=null;     //用于写json
        String money="0";
        
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource()); //连接数据库   
        String sql ="insert into user values(?,?,?,?,?,?,?,?);"; //sql语句
              /*执行sql语句，利用json向前端传递数据
              * 若无异常则为注册成功，
              *若有异常抛出则数据库已有该用户名请重新输入用户名 
              */
        try{              
              qr.update(sql,username,password,sex,email,telephone,postcode,address,money);
              jsonstr="{\"state\":1,\"message\":\""+"注册成功"+"\"}";   //"{\"message\":\""+message+"\"}"
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
        }catch(Exception e)
        {
              jsonstr="{\"state\":0,\"message\":\""+"用户已存在"+"\"}";   //"{\"message\":\""+message+"\"}"
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
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
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
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
