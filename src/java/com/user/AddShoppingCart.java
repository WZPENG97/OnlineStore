/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

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
public class AddShoppingCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *添加至购物车
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");    //设置接收编码
        response.setContentType("text/html;charset=UTF-8");
        
        //Integer.parseInt() 把String转换为int
        HttpSession session = request.getSession(true);
        String username=(String) session.getAttribute("username");   //从session里面获取用户名
        String jsonstr=null;                        //用于写json数据
        if(username==null)
        {
            jsonstr="{\"state\":0,\"message\":\""+"用户未登录"+"\"}";
            JSONObject json = JSONObject.fromObject(jsonstr);
            response.getWriter().println(json);
        }
        else
        {
        String pid=request.getParameter("pid");   //获取商品id
//      String pname=request.getParameter("pname");   //获取商品名字
//      String price=request.getParameter("price");   //获取商品价格
        String pname=null;                              //获取商品名字
        String price=null;                              //获取商品价格
        String counts=request.getParameter("counts");   //获取商品数量
        
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource()); //连接数据库 
        String sql1="select * from product where pid =?;";      //sql语句用于查询商品数据
        Map<String, Object> map = qr.query(sql1, new MapHandler(),pid);
        pname=map.get("pname").toString();
        price=map.get("price").toString();
        
        String sql2 ="insert into shopping_cart value(?,?,?,?,?);"; //sql语句用于插入到购物车
        
              /*执行sql语句，利用json向前端传递数据
              * 若无异常则为添加至购物车成功，
              *若有异常抛出则添加失败 
              */
        try{              
              qr.update(sql2,username,pid,pname,price,counts);
             // qr.update(sql2,username,"11",pname,price,"3");  //测试
              jsonstr="{\"state\":1,\"message\":\""+"添加成功"+"\"}";
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
        }catch(Exception e)
        {
              jsonstr="{\"state\":2,\"message\":\""+"已添加至购物车，请前往购物车查看"+"\"}";
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
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
