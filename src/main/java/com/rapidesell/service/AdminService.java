package com.rapidesell.service;

import com.rapidesell.dao.AdminDao;
import com.rapidesell.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;

    public Admin save(Admin admin) {
        return adminDao.save(admin);
    }

    public Admin findByEmailidAndPassword(String emailId, String password) {
        return adminDao.findByEmailidAndPassword(emailId,password);
    }
}
