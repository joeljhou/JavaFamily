package com.mayikt.entity;

import com.mayikt.annotation.MayiktName;

/**
 * @author zhouyu
 * @create 2021-05-27 1:37
 */
@MayiktName
public class UserEntity {
    private String userName;
    private Integer useAge;

    @MayiktName
    public String pubUserName;

    public UserEntity(String userName, Integer useAge) {
        System.out.println("执行有参构造函数userName:" + userName + "," + useAge);
        this.userName = userName;
        this.useAge = useAge;
    }

    @MayiktName
    public UserEntity() {
        System.out.println("无参构造函数...");
    }

    @Deprecated
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUseAge() {
        return useAge;
    }

    public void setUseAge(Integer useAge) {
        this.useAge = useAge;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userName='" + userName + '\'' +
                ", useAge=" + useAge +
                '}';
    }

    @MayiktName
    public void testMethod(){
        System.out.println("testMethod...");
    }

    private Integer sumMethod(Integer a,Integer b){
        return a + b;
    }

}
