/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import com.utils.DataSourceUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UserComment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *用户评论模块
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
        String username=(String) session.getAttribute("username");   //从session里面获取用户名
        String pid =request.getParameter("pid");
        String comment=request.getParameter("comment");
        String jsonstr=null;                        //用于写json数据
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource()); //连接数据库   
        String sql ="insert into comment value(?,?,?,?);"; //sql语句
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String  time=df.format(new Date());       // new Date()为获取当前系统时间
                
        System.out.println(time);
              /*执行sql语句，利用json向前端传递数据
              * 若无异常则为评论成功，
              *若有异常抛出则失败
              */
        try{              
              qr.update(sql,pid,username,comment,time);
            // qr.update(sql,"11","admin","1111111",time);  //测试
              jsonstr="{\"state\":1,\"message\":\""+"评论成功"+"\"}";;
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
        }catch(Exception e)
        {
              jsonstr="{\"state\":0,\"message\":\""+"评论失败"+"\"}";;
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
