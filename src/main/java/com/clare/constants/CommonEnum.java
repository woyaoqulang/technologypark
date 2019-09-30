package com.clare.constants;


/**
 * 基础枚举常量类
 * @author zhangHao
 * @date 2019-07-31 14:56
 */
public class CommonEnum {

    /**
     *登录方式
     */
    public enum LoginWayEnum{
        //
        Web(1,"PC Web端"),
        WeChat(2,"微信端");

        private Integer id;
        private String name;

        LoginWayEnum(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public static String getName(Integer id){
            for (LoginWayEnum o : LoginWayEnum.values()) {
                if(o.getId().equals(id)){
                    return o.name;
                }
            }
            return "";
        }

        public Integer getId() {
            return id;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * 角色类型
     */
    public enum  RoleType{
        //
        SuperAdmin(1,"超级管理员"),
        Admin(2,"管理员"),
        SuperVip(3,"超级会员"),
        Vip(4,"会员"),
        user(5,"普通用户");

        private Integer id;
        private String name;

        RoleType(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }


}
