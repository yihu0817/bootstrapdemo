package xeonmic.dao.proxy;


import xeonmic.dao.DemoDAO;
import xeonmic.dao.impl.DemoDAOImpl;
import xeonmic.dbc.DatabaseConnection;
import xeonmic.vo.demo;

import java.util.List;

public class DemoDAOProxy implements DemoDAO {
    private DatabaseConnection dbc = null;
    private DemoDAO ddao = null;

    public DemoDAOProxy() {
        this.dbc = new DatabaseConnection();
        this.ddao = new DemoDAOImpl(this.dbc.getConnection());
    }

    @Override
    public boolean doCreate(demo demo) {
        boolean flag = false;
        flag = this.ddao.doCreate(demo);
        this.dbc.close();
        return flag;
    }

    @Override
    public List<demo> doSearch(String keys) {
        // TODO Auto-generated method stub
        List<demo> all = null;
        all = this.ddao.doSearch(keys);
        this.dbc.close();
        return all;
    }

    @Override
    public boolean doDelete(int id) {
        boolean flag = false;
        flag = this.ddao.doDelete(id);
        this.dbc.close();
        return flag;
    }

    @Override
    public boolean doChange(demo demo) {
        boolean flag = false;
        flag = this.ddao.doChange(demo);
        this.dbc.close();
        return flag;
    }

}
