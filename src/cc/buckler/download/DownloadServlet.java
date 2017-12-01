package cc.buckler.download;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRemoteUser();
        req.getRemoteHost();
        req.getRemoteAddr();
        req.getRemotePort();
        req.getLocalAddr();
        req.getLocalPort();

        //获得请求文件名
        String filename = req.getParameter("filename");
        System.out.println(filename);

        //设置文件MIME类型
        resp.setContentType(getServletContext().getMimeType(filename));
        //设置Content-Disposition
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename);
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = getServletContext().getRealPath("/download/" + filename);
        System.out.println(fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = resp.getOutputStream();

        //写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }

        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
