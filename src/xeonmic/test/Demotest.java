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
			System.out.println("��ӳɹ�");
		}else {
			System.out.println("���ʧ��");
		}
	}
	public static void  doChange(demo demo) {
		if (Factory.getDemoDAOInstance().doChange(demo)) {
			System.out.println("�޸ĳɹ�");
		}else {
			System.out.println("�޸�ʧ��");
		}
	}
	public static void  doDelete(int id) {
		if (Factory.getDemoDAOInstance().doDelete(id)) {
			System.out.println("ɾ���ɹ�");
		}else {
			System.out.println("ɾ��ʧ��");
		}
	}
}
/*
 * ������
		��ӳɹ�
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		SELECT id,name,type,pay,text FROM t_demo 
		demo [id=1, name=Name, type=1, pay=0.98, text=Text]
		�޸ĳɹ�
		SELECT id,name,type,pay,text FROM t_demo WHERE id = 1
		demo [id=1, name=Name, type=1, pay=0.98, text=Change Text]
		ɾ���ɹ�
		SELECT id,name,type,pay,text FROM t_demo 

 * */
