


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

import com.utils.DataSourceUtils;
import com.utils.UUIDUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

/**
 *
 * @author LIGUANG
 */
@MultipartConfig(location="D:\\images\\")   //上传文件的存放路径
public class AddProduct extends HttpServlet {
     private String fileNameExtractorRegex="filename=\".+\"";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *添加商品
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
        String adminname=(String)session.getAttribute("adminname"); //从session里面获取管理员名
        int pid=(int) (System.currentTimeMillis());                 //商品id
        String pname=request.getParameter("pname");                 //商品名字
//      String pname="1651651";                 //商品名字测试
        String price=request.getParameter("price");                 //商品价格
        String cid=null;                                            //商品类别id
        String cname=request.getParameter("cname");                 //类别名称
//      String cname="游戏周边";                 //类别名称测试
        String store=request.getParameter("store");                 //库存
        String description=request.getParameter("description");        //商品描述
        String pimage="images/"+pname+".jpg";
        String jsonstr=null;           //用于写json
        
        if(adminname==null)
        {
        jsonstr="{'state':0,\"message\":\""+"非管理员禁止操作该页面"+"\"}";
        JSONObject json = JSONObject.fromObject(jsonstr);
        response.getWriter().println(json);
        }
        else
        { 
            if(cname=="化妆用品")
            {
            cid="1";
            }else if(cname=="生活用品")
            {
            cid="2";
            }else if(cname=="书籍办公")
            {
            cid="3";  
            }else if(cname=="数码产品")
            {
            cid="4";
            }else if(cname=="衣服配饰")
            {
            cid="5";
            }else if(cname=="运动用品")
            {
            cid="6";
            }
            else if(cname=="游戏周边")
            {
            cid="7";
            }
        QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource()); //连接数据库   
        String sql ="insert into product values(?,?,?,?,?,?,?,?);"; //sql语句
              /*执行sql语句，利用json向前端传递数据
              * 若无异常则插入成功，
              *
              */
        try{              
              qr.update(sql,Integer.toString(pid),pname,price,cid,cname,store,description,pimage);
//              qr.update(sql,Integer.toString(pid),pname,"15616",cid,cname,"1561","1616516",pimage);
              jsonstr="{\"state\":1,\"message\":\""+"添加成功"+"\"}";   //"{\"message\":\""+message+"\"}"
              PrintWriter out = response.getWriter();
        try {
            String path=this.getServletContext().getRealPath("/");
            for(Part p:request.getParts())
            {
               if(p.getContentType().contains("image"))
               {
                    String fname=getFileName(p);
                    p.write(fname);
               } 
            }
            }
        finally
        {
            out.close();
        } 
              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println(json);
        }catch(Exception e)
        {
              jsonstr="{\"state\":2,\"message\":\""+"添加失败"+"\"}";   //"{\"message\":\""+message+"\"}"
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
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
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

        private String getFileName(Part part) {
        String contentDesc=part.getHeader("content-disposition");
        String fileName=null;
        Pattern pattern=Pattern.compile(fileNameExtractorRegex);
        Matcher matcher=pattern.matcher(contentDesc);
        if(matcher.find()){
            fileName=matcher.group();
            fileName=fileName.substring(10, fileName.length()-1);
        }
        return fileName;
    }
}
