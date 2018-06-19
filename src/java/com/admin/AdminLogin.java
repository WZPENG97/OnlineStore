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
import org.apache.commons.dbutils.handlers.MapHandler;

/**
 *
 * @author LIGUANG
 */
public class AdminLogin extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
       request.setCharacterEncoding("UTF-8");    //设置接收编码
        response.setContentType("text/html;charset=UTF-8");
        
        String adminname=request.getParameter("username");  //获取用户名
        String apassword=request.getParameter("password");   //获取密码 
        String jsonstr=null;           //用于写json
        /*
        *创建一个 session 对象用户用来判断用户是否过期
        *过期时间为20分钟
        */
        HttpSession session = request.getSession(true);
        session.setAttribute("adminname", adminname);
      
         QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());//连接数据库
         String sql ="select * from admin where admin=? and apassword=?;";//sql语句

         Map<String, Object> map = qr.query(sql, new MapHandler(),adminname,apassword);
//         Map<String, Object> map = qr.query(sql, new MapHandler(),"admin1","123456");//测试
         JSONObject jsonObject = JSONObject.fromObject(map);//转换为json数据

         /*
         *写json数据返回到前端
         *
         */
         if(jsonObject.size()>0){   //判断是否有数据
              jsonstr="{\"state\":1,\"message\":\""+"登录成功"+"\"}";   //"{\"message\":\""+message+"\"}"
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
             // response.getWriter().println(jsonObject);
             // System.out.println("dao这里");   //测试
         }else{
               jsonstr="{'state':0,\"message\":\""+"用户名或密码错误"+"\"}";
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
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
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
