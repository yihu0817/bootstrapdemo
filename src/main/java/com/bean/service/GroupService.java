package main.java.com.bean.service;

import main.java.com.bean.dao.GroupDao;
import main.java.com.domain.group.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    GroupDao groupDao;

    public void saveGroup(Group group) {
        groupDao.save(group);
    }

    public void updateGroup(Group group) {
        groupDao.update(group);
    }

    public void deleteGroup(Group group) {
        groupDao.deleteGroup(group.getId());
    }
}
