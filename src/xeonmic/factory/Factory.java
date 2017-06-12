package xeonmic.factory;

import xeonmic.dao.DemoDAO;
import xeonmic.dao.proxy.DemoDAOProxy;

public class Factory {
	public static DemoDAO getDemoDAOInstance(){
		return new DemoDAOProxy();
	}
}
