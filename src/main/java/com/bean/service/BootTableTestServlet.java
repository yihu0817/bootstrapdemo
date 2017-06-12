package main.java.com.bean.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BootTableTestServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        JSONObject jsonObj = new JSONObject();
        // 根据jqGrid对JSON的数据格式要求给jsonObj赋值
        jsonObj.put("page", 1);                // 当前页
        jsonObj.put("total", 1);        // 总页数
        jsonObj.put("records", 4);        // 总记录数
        // 定义rows，存放数据
        JSONArray rows = new JSONArray();
        // 放入4条数据
        for (int i = 1; i < 5; i++) {
            // 存放一条记录的对象
            JSONObject cell = new JSONObject();
            cell.put("id", i);
            cell.put("Name", "部门" + i);
            cell.put("ParentName", "上级部门" + i);
            cell.put("Level", "部门级别" + i);
            cell.put("Desc", "描述" + i);
            // 将该记录放入rows中
            rows.add(cell);
        }
        // 将rows放入json对象中
        jsonObj.put("rows", rows);
        // 自控制台打印输出，以检验json对象生成是否正确
        System.out.println("要返回的json对象：\n" + jsonObj.toString());
        // 设置字符编码
        resp.setCharacterEncoding("GBK");
        // 返回json对象（通过PrintWriter输出）
        resp.getWriter().print(jsonObj);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
