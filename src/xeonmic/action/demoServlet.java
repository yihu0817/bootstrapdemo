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

        request.setCharacterEncoding("utf-8");  //���ﲻ���ñ����������
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        int rows = Integer.valueOf(request.getParameter("rows")); //ÿҳ����ʾ�ļ�¼����
        int page = Integer.valueOf(request.getParameter("page")); //��ǰ��ҳ��
        String sord = request.getParameter("sord");//����ʽ
        String sidx = request.getParameter("sidx");//��������
        Boolean search = (request.getParameter("_search").equals("true")) ? true : false;//�Ƿ����ڲ�ѯ����

        List<demo> allList = new LinkedList<demo>();//���ؽ����

        String keys = "";//��ѯ�����ַ���

        if (search) {
            keys = " WHERE ";
            String filters = request.getParameter("filters");
            System.out.println(filters);
            //"{"groupOp":"AND","rules":[{"field":"id","op":"eq","data":"1"},{"field":"type","op":"ew","data":"2"}]}"
            JSONObject jsonObject = JSONObject.fromObject(filters);
            String groupOp = "AND";//ÿ������֮��Ĺ�ϵ��and/or��
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
                    case 1://���
                        string = " = '" + data + "' ";
                        break;
                    case 2://�����
                        string = " <> '" + data + "' ";
                        break;
                    case 3://С��
                        string = " < '" + data + "' ";
                        break;
                    case 4://С�ڵ���
                        string = " <= '" + data + "' ";
                        break;
                    case 5://����
                        string = " > '" + data + "' ";
                        break;
                    case 6://���ڵ���
                        string = " >= '" + data + "' ";
                        break;
                    case 7://��...֮��
                    {
                        if (data.split(",").length == 2) {
                            string = " BETWEEN '" + data.split(",")[0] + "' AND '" + data.split(",")[1] + "' ";
                        } else {
                            string = " = '" + data + "' ";//���ݴ���ʱ����
                        }
                    }

                    break;
                    case 8://����...֮��
                    {
                        if (data.split(",").length == 2) {
                            string = " NOT BETWEEN '" + data.split(",")[0] + "' AND '" + data.split(",")[1] + "' ";
                        } else {
                            string = " <> '" + data + "' ";//���ݴ���ʱ����
                        }
                    }
                    break;
                    case 9://��...����
                        string = " LIKE '%" + data + "' ";
                        break;
                    case 10://����...����
                        string = " NOT LIKE '%" + data + "' ";
                        break;
                    case 11://����
                        string = " LIKE '%" + data + "%' ";
                        break;
                    case 12://������
                        string = " NOT LIKE '%" + data + "%' ";
                        break;
                    case 13://��
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
                    case 14://����
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
                        System.out.println("OP���Ŵ���");//OP���Ŵ���
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


        //��ҳ����


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
//		colnames.add("���ⵥ��");
//		colnames.add("��������");
//		colnames.add("�ܽ��");
//		colnames.add("�����ˣ���λ��");
//		colnames.add("��ע");
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
