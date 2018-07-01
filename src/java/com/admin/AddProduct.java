/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin;

import com.utils.DataSourceUtils;
import com.utils.UUIDUtils;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author LIGUANG
 */
public class AddProduct extends HttpServlet {
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
            throws ServletException, IOException, SQLException, FileUploadException {
        request.setCharacterEncoding("UTF-8");    //设置接收编码
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String adminname=(String)session.getAttribute("adminname"); //从session里面获取管理员名
        int pid=(int) (System.currentTimeMillis());                 //商品id
        String cid=null;                                            //商品类别id
//        String pname=null;
        String jsonstr=null;           //用于写json
         //1.创建磁盘文件工厂
        DiskFileItemFactory factory=new DiskFileItemFactory();
        //2.创建核心对象
        ServletFileUpload upload = new ServletFileUpload(factory);  
        //3.解析请求
        List<FileItem> list = upload.parseRequest(request);
        String[] str=new String[5];
        String fileName = "noimg.png";
        int i=0;
        for (FileItem fi:list)
        {
            String fieldName=fi.getFieldName();
                //a.判断是否为上传组件
            if(fi.isFormField())
            {
                str[i]=fi.getString("utf-8");
                i++;
            }
            else
            {
             //获取文件名字
                if(fi.getName()!=""){
                    fileName=fi.getName();
                }
                //获取文件内容
                InputStream is =fi.getInputStream();
                //创建一个输出流
               FileOutputStream os= new FileOutputStream(new File(request.getSession().getServletContext().getRealPath("/images/")+fileName));
               //拷贝流
               IOUtils.copy(is, os);
               //释放资源，
               os.close();
               is.close();
               //删除临时文件
               fi.delete();
            }
        }
            String pname=str[0];
            String price=str[1];
            String cname=str[2];
            String store=str[3];
            String description=str[4];
            String pimage="images/"+fileName;
        
        if(adminname==null)
        {
        jsonstr="{'state':0,\"message\":\""+"非管理员禁止操作该页面"+"\"}";
        JSONObject json = JSONObject.fromObject(jsonstr);
        response.getWriter().println(json);
        }
        else
        { 
            if("化妆用品".equals(cname))
            {
            cid="1";
            }else if("生活用品".equals(cname))
            {
            cid="2";
            }else if("书籍办公".equals(cname))
            {
            cid="3";  
            }else if("数码产品".equals(cname))
            {
            cid="4";
            }else if("衣服配饰".equals(cname))
            {
            cid="5";
            }else if("运动用品".equals(cname))
            {
            cid="6";
            }
            else if("游戏周边".equals(cname))
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
//              jsonstr="{\"state\":1,\"message\":\""+"添加成功"+"\"}";   //"{\"message\":\""+message+"\"}"
              /*
              *上传文件
              */
//        //1.创建磁盘文件工厂
//        DiskFileItemFactory factory=new DiskFileItemFactory();
//        
//        //2.创建核心对象
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        
//        //3.解析请求
//        List<FileItem> list = upload.parseRequest(request);
//        
//        for (FileItem fi:list)
//        {
//                //a.判断是否为上传组件
//            if(!fi.isFormField())
//            {
//                //获取文件名字
//                String fileName=fi.getName();
//                //获取文件内容
//                InputStream is =fi.getInputStream();
//                //创建一个输出流
//               FileOutputStream os= new FileOutputStream(new File("C:\\C语言\\JavaWeb网上商城资料\\"+fileName));
//               //拷贝流
//               IOUtils.copy(is, os);
//               //释放资源，
//               os.close();
//               is.close();
//               //删除临时文件
//               fi.delete();
//            }
//        }
              
//              JSONObject json = JSONObject.fromObject(jsonstr);
              response.getWriter().println("<script>alert('添加成功');location.href='admin.html'</script>");
        }catch(Exception e)
        {
//              jsonstr="{\"state\":2,\"message\":\""+"添加失败"+"\"}";   //"{\"message\":\""+message+"\"}"
//              JSONObject json = JSONObject.fromObject(jsonstr);
//              response.getWriter().println(json);
            response.getWriter().println("<script>alert('添加失败，请联系管理员');location.href='addProduct.html'</script>");
              System.out.println(e);
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
        } catch (FileUploadException ex) {
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
        } catch (FileUploadException ex) {
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

}
