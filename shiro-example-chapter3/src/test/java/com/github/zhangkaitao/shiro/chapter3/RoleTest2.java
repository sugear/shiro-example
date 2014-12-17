package com.github.zhangkaitao.shiro.chapter3;

import junit.framework.Assert;
import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-26
 * <p>
 * Version: 1.0
 */
public class RoleTest2 extends BaseTest {

    @Test
    public void testHasRole() {
        login("classpath:shiro-role.ini", "wang", "123");
        //判断拥有角色：role1
        Assert.assertTrue(subject().hasRole("role1"));

        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(false, result[1]);
        Assert.assertEquals(false, result[2]);

    }

    @Test
    public void testCheckRole1() {
        login("classpath:shiro-role.ini", "wang", "123");
        //断言拥有角色：role1
        subject().checkRole("role1");

    }

    @Test(expected = UnauthorizedException.class)//期望抛出异常
    public void testCheckRole13() {
        login("classpath:shiro-role.ini", "wang", "123");

        //断言拥有角色：role1 and role3 因为role3实际是没有的，会失败抛出异常
        subject().checkRoles("role1", "role3");
    }
    


}
