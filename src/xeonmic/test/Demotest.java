package xeonmic.test;


import xeonmic.factory.Factory;
import xeonmic.vo.demo;

import java.util.LinkedList;
import java.util.List;

public class Demotest {
	public static void main(String[] args) {
		demo demo1 = new demo();
		demo1.setName("Name");
		demo1.setPay(0.98);
		demo1.setType(1);
		demo1.setText("Text");
		doCreate(demo1);
		doSearch(null);
		
		if (doSearch(null)!=null&&!doSearch(null).isEmpty()) {
			demo1 = doSearch("").get(0);
			demo1.setText("Change Text");
			doChange(demo1);
			doSearch("WHERE id = "+demo1.getId());
			
			doDelete(demo1.getId());
			doSearch(null);
		}
		
		
		
	}
	
	public static List<demo> doSearch(String keys) {
		List<demo> allDemos = new LinkedList<demo>();
		allDemos = Factory.getDemoDAOInstance().doSearch(keys);
		for (demo demo : allDemos) {
			System.out.println(demo.toString());
		}
		return allDemos;
	}
	public static void  doCreate(demo demo) {
		if (Factory.getDemoDAOInstance().doCreate(demo)) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
	}
	public static void  doChange(demo demo) {
		if (Factory.getDemoDAOInstance().doChange(demo)) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
	}
	public static void  doDelete(int id) {
		if (Factory.getDemoDAOInstance().doDelete(id)) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}
}
/*
 * 输出结果
		添加成功
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		修改成功
		SELECT id,name,type,pay,text FROM t_demo WHERE id = 1
		demo [id=1, name=Name, type=1, pay=0.98, text=Change Text]
		删除成功
		SELECT id,name,type,pay,text FROM t_demo 

 * */
