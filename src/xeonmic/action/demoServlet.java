package xeonmic.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import xeonmic.factory.Factory;
import xeonmic.vo.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Servlet implementation class demoServlet
 */
public class demoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public demoServlet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        int rows = Integer.valueOf(request.getParameter("rows")); //每页中显示的记录行数
        int page = Integer.valueOf(request.getParameter("page")); //当前的页码
        String sord = request.getParameter("sord");//排序方式
        String sidx = request.getParameter("sidx");//排序列名
        Boolean search = (request.getParameter("_search").equals("true")) ? true : false;//是否用于查询请求

        List<demo> allList = new LinkedList<demo>();//返回结果集

        String keys = "";//查询条件字符串

        if (search) {
            keys = " WHERE ";
            String filters = request.getParameter("filters");
            System.out.println(filters);
            //"{"groupOp":"AND","rules":[{"field":"id","op":"eq","data":"1"},{"field":"type","op":"ew","data":"2"}]}"
            JSONObject jsonObject = JSONObject.fromObject(filters);
            String groupOp = "AND";//每个规则之间的关系（and/or）
            if (jsonObject.getString("groupOp") != null && !"".equals(jsonObject.getString("groupOp"))) {
                if (jsonObject.getString("groupOp").equals("OR")) {
                    groupOp = "OR";
                }
            }

            JSONArray rulesjson = jsonObject.getJSONArray("rules");

            for (int z = 0; z < rulesjson.size(); z++) {
                Object t = rulesjson.get(z);
                JSONObject rulejson = JSONObject.fromObject(t);
                String field = rulejson.getString("field");
                String op = rulejson.getString("op");
                int optype = 0;
                if (!StringUtils.isBlank(op)) {
                    if (op.equals("eq")) {
                        optype = 1;
                    } else if (op.equals("ne")) {
                        optype = 2;
                    } else if (op.equals("ne")) {
                        optype = 2;
                    } else if (op.equals("li")) {
                        optype = 3;
                    } else if (op.equals("le")) {
                        optype = 4;
                    } else if (op.equals("gt")) {
                        optype = 5;
                    } else if (op.equals("ge")) {
                        optype = 6;
                    } else if (op.equals("bw")) {
                        optype = 7;
                    } else if (op.equals("bu")) {
                        optype = 8;
                    } else if (op.equals("ew")) {
                        optype = 9;
                    } else if (op.equals("en")) {
                        optype = 10;
                    } else if (op.equals("cn")) {
                        optype = 11;
                    } else if (op.equals("nc")) {
                        optype = 12;
                    } else if (op.equals("in")) {
                        optype = 13;
                    } else if (op.equals("ni")) {
                        optype = 14;
                    }
                }
                String data = rulejson.getString("data");
                String string = "";
                switch (optype) {
                    case 1://相等
                        string = " = '" + data + "' ";
                        break;
                    case 2://不相等
                        string = " <> '" + data + "' ";
                        break;
                    case 3://小于
                        string = " < '" + data + "' ";
                        break;
                    case 4://小于等于
                        string = " <= '" + data + "' ";
                        break;
                    case 5://大于
                        string = " > '" + data + "' ";
                        break;
                    case 6://大于等于
                        string = " >= '" + data + "' ";
                        break;
                    case 7://在...之间
                    {
                        if (data.split(",").length == 2) {
                            string = " BETWEEN '" + data.split(",")[0] + "' AND '" + data.split(",")[1] + "' ";
                        } else {
                            string = " = '" + data + "' ";//数据错误时处理
                        }
                    }

                    break;
                    case 8://不在...之间
                    {
                        if (data.split(",").length == 2) {
                            string = " NOT BETWEEN '" + data.split(",")[0] + "' AND '" + data.split(",")[1] + "' ";
                        } else {
                            string = " <> '" + data + "' ";//数据错误时处理
                        }
                    }
                    break;
                    case 9://以...结束
                        string = " LIKE '%" + data + "' ";
                        break;
                    case 10://不以...结束
                        string = " NOT LIKE '%" + data + "' ";
                        break;
                    case 11://包含
                        string = " LIKE '%" + data + "%' ";
                        break;
                    case 12://不包含
                        string = " NOT LIKE '%" + data + "%' ";
                        break;
                    case 13://在
                    {
                        string = " IN ( ";
                        String[] datas = data.split(",");
                        for (int i = 0; i < datas.length; i++) {
                            string += " '" + datas[i] + "' ";
                            if (i != datas.length - 1) {
                                string += ",";
                            } else {
                                string += " ) ";
                            }
                        }
                    }
                    break;
                    case 14://不在
                    {
                        string = " NOT IN ( ";
                        String[] datas = data.split(",");
                        for (int i = 0; i < datas.length; i++) {
                            string += " '" + datas[i] + "' ";
                            if (i != datas.length - 1) {
                                string += ",";
                            } else {
                                string += " ) ";
                            }
                        }
                    }
                    break;
                    default:
                        op = null;
                        System.out.println("OP符号错误");//OP符号错误
                }
                if (op != null) {
                    if (z == rulesjson.size() - 1) {
                        keys += " " + field + " " + string + " ";
                    } else {
                        keys += " " + field + " " + string + " " + groupOp + " ";
                    }

                }
            }

            //allList = Factory.getDemoDAOInstance().doSearch(keys);
        }//else {
        //allList = Factory.getDemoDAOInstance().doSearch("");
        //}


        if (sidx != null && !"".equals(sidx)) {
            System.out.println(sidx);
            keys += " ORDER BY " + sidx;
            System.out.println("sord=" + sord);
            if (!sord.equals("asc")) {
                keys += " DESC ";
            }
        }


        System.out.println(keys);
        allList = Factory.getDemoDAOInstance().doSearch(keys);


        //分页部分


        int total = 0;
        total = (allList.size() % rows == 0) ? (allList.size() / rows) : ((allList.size() / rows) + 1);
        int j = 0;
        int m = (page - 1) * rows;
        int n = (page - 1) * rows + rows;
        JSONArray jArray = new JSONArray();
        for (j = m; j < allList.size() && j < n; j++) {
            jArray.add(JSONObject.fromObject(allList.get(j)));
        }


//		List<String> colnames = new LinkedList<String>();
//		colnames.add("");
//		colnames.add("出库单号");
//		colnames.add("出库类型");
//		colnames.add("总金额");
//		colnames.add("申请人（单位）");
//		colnames.add("备注");
//		JSONArray colnamesjson = JSONArray.fromObject(colnames);


        JSONObject jjson = new JSONObject();
        jjson.accumulate("page", page);
        jjson.accumulate("total", total);
        jjson.accumulate("records", allList.size());
        jjson.accumulate("rows", jArray);
        //jjson.accumulate("colNames", colnamesjson);
        //jjson.accumulate("colModel", jArray);
        System.out.println(jjson.toString());
        //	request.setAttribute("servlet", "demoServlet");
        response.getWriter().write(jjson.toString());
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doGet(request, response);
    }

}
