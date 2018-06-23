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
        
        HttpSession session = request.getSession(true);
        String username=(String) session.getAttribute("username");   //从session里面获取用户名
        String jsonstr=null;                                 //用于写json
        
        if(username==null)
        {
            jsonstr="{\"state\":0,\"message\":\""+"用户未登录"+"\"}";
            JSONObject json = JSONObject.fromObject(jsonstr);
            response.getWriter().println(json);
        }
        else
        {
        //Integer.parseInt() 把String转换为int
        String indentid=UUIDUtils.getId();       //生成订单id
        String pid=request.getParameter("pid");   //获取商品id
        String pname=null;                      //获取商品名字
        String price=null;
        String counts=request.getParameter("counts");   //获取商品数量
//        String counts="1";   //获取商品数量测试
        String store=null;
        String money=null;                               //获取钱
        
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource()); //连接数据库 
        String sql1 ="select * from product where pid=?"; //sql语句
        String sql2 ="select * from user where username=?"; //sql语句
        String sql3 ="update user set money=? where username=?"; //sql语句
        Map<String, Object> map = qr.query(sql1, new MapHandler(),pid);
        Map<String, Object> map1 = qr.query(sql2, new MapHandler(),username);
        pname=map.get("pname").toString();
        price=map.get("price").toString();
        money=map1.get("money").toString();
        store=map.get("store").toString();
        float  cprice=Float.parseFloat(price)*Float.parseFloat(counts);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String  ctime=df.format(new Date());       // new Date()为获取当前系统时间
        String indentsta="待发货";  

        int new_store=Integer.parseInt(store)-Integer.parseInt(counts);
        if(new_store<=0)
        {
            jsonstr="{\"state\":5,\"message\":\""+"你选的商品"+pname+"属于缺货状态"+"\"}";
            JSONObject json = JSONObject.fromObject(jsonstr);
            response.getWriter().println(json);
        }
        else
        {
        if(cprice-Float.parseFloat(money)<=0)
        {
               float newmoney=Float.parseFloat(money)-cprice;   //计算差额
               String sql4 ="insert into indent value(?,?,?,?,?,?,?,?);"; //sql语句
               String sql5 ="update product set store=? where pid=?";     //更新库存
               int newstore=Integer.parseInt(store)-Integer.parseInt(counts);
              /*执行sql语句，利用json向前端传递数据
              * 若无异常则为购买商品成功，
              *
              */
        try{              
              qr.update(sql4,indentid,username,pid,pname,counts,cprice,ctime,indentsta);
              //qr.update(sql,indentid,"11","1","电吹风","3","39.00",ctime,indentsta);  //测试
              qr.update(sql3,Float.toString(newmoney),username);
              qr.update(sql5,Integer.toString(newstore),pid);
              jsonstr="{\"state\":1,\"message\":\""+"购买成功"+"\"}";
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
        }catch(Exception e)
        {
            
              jsonstr="{\"state\":2,\"message\":\""+"数据库异常，请联系管理员"+"\"}";
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
       }
        }
        else
        {
            jsonstr="{\"state\":3,\"message\":\""+"余额不足，请充值"+"\"}";
            JSONObject json = JSONObject.fromObject(jsonstr);
            response.getWriter().println(json);
        }
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
