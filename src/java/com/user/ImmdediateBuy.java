/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.user;

import com.utils.DataSourceUtils;
import com.utils.UUIDUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ImmdediateBuy extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *直接购买模块，添加到订单
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException,SQLException {
        request.setCharacterEncoding("UTF-8");    //设置接收编码
        response.setContentType("text/html;charset=UTF-8");
        
        //Integer.parseInt() 把String转换为int
        String indentid=UUIDUtils.getId();       //生成订单id
        String username=request.getParameter("username");   //获取用户名字
        String pid=request.getParameter("pid");   //获取商品id
        String pname=request.getParameter("pname");   //获取商品名字
        String counts=request.getParameter("counts");   //获取商品数量
        //计算总价格
        String  cprice=Integer.toString(Integer.parseInt(request.getParameter("price"))*Integer.parseInt(request.getParameter("counts")));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String  ctime=df.format(new Date());       // new Date()为获取当前系统时间
        String indentsta="待发货";
        String jsonstr=null;                        //用于写json数据
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource()); //连接数据库   
        String sql ="insert into indent value(?,?,?,?,?,?,?,?);"; //sql语句
              /*执行sql语句，利用json向前端传递数据
              * 若无异常则为购买商品成功，
              *若有异常抛出则添加失败 
              */
        try{              
              qr.update(sql,indentid,username,pid,pname,counts,cprice,ctime,indentsta);
              //qr.update(sql,indentid,"11","1","电吹风","3","39.00",ctime,indentsta);  //测试
              jsonstr="{'state':1,'cprice':"+cprice+"}";
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
        }catch(Exception e)
        {
            
              jsonstr="{'state':0}";
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
            Logger.getLogger(ImmdediateBuy.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ImmdediateBuy.class.getName()).log(Level.SEVERE, null, ex);
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
