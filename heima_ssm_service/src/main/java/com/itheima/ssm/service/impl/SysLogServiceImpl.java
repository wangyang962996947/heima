package com.itheima.ssm.service.impl;import com.itheima.ssm.dao.ISysLogDao;import com.itheima.ssm.domain.SysLog;import com.itheima.ssm.service.ISysLogService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.List;/** * @auther wangyang * @date 2019/10/25 10:04 PM */@Service@Transactionalpublic class SysLogServiceImpl implements ISysLogService {    @Autowired    private ISysLogDao sysLogDao;    @Override    public void save(SysLog sysLog) throws Exception {        sysLogDao.save(sysLog);    }    @Override    public List<SysLog> findAll() throws Exception {        return sysLogDao.findAll();    }}