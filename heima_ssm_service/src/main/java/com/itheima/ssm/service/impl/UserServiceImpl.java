package com.itheima.ssm.service.impl;import com.itheima.ssm.dao.IUserDao;import com.itheima.ssm.domain.Role;import com.itheima.ssm.domain.UserInfo;import com.itheima.ssm.service.IUserService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.core.authority.SimpleGrantedAuthority;import org.springframework.security.core.userdetails.User;import org.springframework.security.core.userdetails.UserDetails;import org.springframework.security.core.userdetails.UsernameNotFoundException;import org.springframework.security.crypto.password.PasswordEncoder;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.ArrayList;import java.util.List;/** * security 数据库认证 * @auther wangyang * @date 2019/10/23 11:35 PM */@Service("userService")@Transactionalpublic class UserServiceImpl implements IUserService {    @Autowired    private PasswordEncoder passwordEncoder;    @Autowired    private IUserDao userDao;    @Override    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        UserInfo userInfo = null;        try {            userInfo = userDao.findByUsername(username);        } catch (Exception e) {            e.printStackTrace();        }        //处理自己的用户对象封装成UserDetails        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));        User user = new User(userInfo.getUsername(), userInfo.getPassword(),                userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));        return user;    }    /**     * 返回权限集合     * @return     */    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){        List <SimpleGrantedAuthority> list = new ArrayList<>();        for (Role role : roles){            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));        }        return list;    }    @Override    public List<UserInfo> findAll()throws Exception{        return userDao.findAll();    }    @Override    public void saveUserInfo(UserInfo userInfo) throws Exception{        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));        userDao.saveUserInfo(userInfo);    }    @Override    public UserInfo findById(String id)throws Exception {        return userDao.findById(id);    }    @Override    public void addRoleToUser(String userId, String[] roleIds) {        for (String roleId : roleIds){            userDao.addRoleToUser(userId,roleId);        }    }}