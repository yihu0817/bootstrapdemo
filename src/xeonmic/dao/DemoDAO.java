package xeonmic.dao;

import xeonmic.vo.demo;

import java.util.List;

public interface DemoDAO {
    //��ӷ���
    public boolean doCreate(demo demo);

    //ɾ������
    public boolean doDelete(int id);

    //�޸ķ���
    public boolean doChange(demo demo);

    //��ѯ����
    public List<demo> doSearch(String keys);
}