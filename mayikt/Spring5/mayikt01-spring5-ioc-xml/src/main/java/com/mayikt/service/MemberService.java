package com.mayikt.service;

import com.mayikt.dao.MemberDao;
import com.mayikt.dao.MemberDaoImpl;

/**
 * @author 周宇
 * @create 2021-07-23 18:31
 */
public class MemberService {

    /**
     * 使用到属性注入的方式
     */
    private MemberDao memberDao;
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void addMember() {
        //// 原始的方式
        //new MemberDaoImpl().addMember();
        System.out.println(">MemberService.addMember()<");
        memberDao.addMember();
    }

}
