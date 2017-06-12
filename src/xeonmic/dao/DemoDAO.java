package xeonmic.dao;

import xeonmic.vo.demo;

import java.util.List;

public interface DemoDAO {
    //添加方法
    public boolean doCreate(demo demo);

    //删除方法
    public boolean doDelete(int id);

    //修改方法
    public boolean doChange(demo demo);

    //查询方法
    public List<demo> doSearch(String keys);
}