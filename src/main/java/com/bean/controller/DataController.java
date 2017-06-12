package main.java.com.bean.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DataController {
    @RequestMapping("dataServlet.do")
    public String GetDepartment(int limit, int offset, String departmentname, String statu) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < 50; i++) {
            Map jsonObject2 = new HashMap();
            jsonObject2.put("id", i);
            jsonObject2.put("Name", "部门" + i);
            jsonObject2.put("ParentName", "上级部门" + i);
            jsonObject2.put("Level", "部门级别" + i);
            jsonObject2.put("Desc", "描述" + i);
            jsonObject.putAll(jsonObject2);
        }
        return jsonObject.toString();
    }
}
