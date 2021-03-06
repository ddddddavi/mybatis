package com.zwwhnly.mybatisaction.mapper;

import com.zwwhnly.mybatisaction.model.*;
import com.zwwhnly.mybatisaction.type.Enabled;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class SysUserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = sysUserMapper.selectById(1L);
            Assert.assertNotNull(sysUser);

            Assert.assertEquals("admin", sysUser.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            List<SysUser> sysUserList = sysUserMapper.selectAll();

            Assert.assertNotNull(sysUserList);
            Assert.assertTrue(sysUserList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            List<SysRole> sysRoleList = sysUserMapper.selectRolesByUserId(1L);

            Assert.assertNotNull(sysRoleList);
            Assert.assertTrue(sysRoleList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserInfo("test info");
            // ????????????????????????????????????????????????byte?????????
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());

            // ??????????????????result????????????SQL???????????????
            int result = sysUserMapper.insert(sysUser);
            // ?????????1?????????
            Assert.assertEquals(1, result);
            // id???null,?????????id?????????????????????????????????id??????
            Assert.assertNull(sysUser.getId());
        } finally {
            // ????????????????????????????????????????????????
            // ?????????sqlSessionFactory.openSession()?????????????????????
            // ?????????????????????commit???????????????????????????
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUseGeneratedKeys() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserInfo("test info");
            // ????????????????????????????????????????????????byte?????????
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());

            // ??????????????????result????????????SQL???????????????
            int result = sysUserMapper.insertUseGeneratedKeys(sysUser);
            // ?????????1?????????
            Assert.assertEquals(1, result);
            // ??????id??????,??????id??????null
            Assert.assertNotNull(sysUser.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUseSelectKey() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserInfo("test info");
            // ????????????????????????????????????????????????byte?????????
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());

            // ??????????????????result????????????SQL???????????????
            int result = sysUserMapper.insertUseSelectKey(sysUser);
            // ?????????1?????????
            Assert.assertEquals(1, result);
            // ??????id??????,??????id??????null
            Assert.assertNotNull(sysUser.getId());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = sysUserMapper.selectById(1L);

            Assert.assertEquals("admin", sysUser.getUserName());

            sysUser.setUserName("admin_test");
            sysUser.setUserEmail("admin_test@mybatis.tk");
            sysUser.setUserInfo("test info");
            // ????????????????????????????????????????????????byte?????????
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUser.setCreateTime(new Date());

            // ??????????????????result????????????SQL???????????????
            int result = sysUserMapper.updateById(sysUser);
            // ?????????1?????????
            Assert.assertEquals(1, result);

            sysUser = sysUserMapper.selectById(1L);
            Assert.assertEquals("admin_test", sysUser.getUserName());
            Assert.assertEquals("admin_test@mybatis.tk", sysUser.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = sysUserMapper.selectById(1L);
            Assert.assertNotNull(sysUser);

            // ??????????????????result????????????SQL???????????????
            int result = sysUserMapper.deleteById(1L);
            // ?????????1?????????
            Assert.assertEquals(1, result);

            Assert.assertNull(sysUserMapper.selectById(1L));

            SysUser sysUser2 = sysUserMapper.selectById(1001L);
            Assert.assertNotNull(sysUser2);

            // ?????????1?????????
            Assert.assertEquals(1, sysUserMapper.deleteBySysUser(sysUser2));

            Assert.assertNull(sysUserMapper.selectById(1001L));
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysRole> sysRoleList = sysUserMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);

            Assert.assertNotNull(sysRoleList);
            Assert.assertTrue(sysRoleList.size() > 0);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserAndRole() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            sysUser.setId(1L);
            SysRole sysRole = new SysRole();
            sysRole.setEnabled(Enabled.enabled);

            List<SysRole> sysRoleList = sysUserMapper.selectRolesByUserAndRole(sysUser, sysRole);

            Assert.assertNotNull(sysRoleList);
            Assert.assertTrue(sysRoleList.size() > 0);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            // ?????????????????????
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> sysUserList = sysUserMapper.selectByUser(query);
            Assert.assertTrue(sysUserList.size() > 0);

            // ??????????????????
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            sysUserList = sysUserMapper.selectByUser(query);
            Assert.assertTrue(sysUserList.size() > 0);

            // ?????????????????????????????????
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            sysUserList = sysUserMapper.selectByUser(query);
            // ???????????????????????????????????????????????????????????????????????????0
            Assert.assertTrue(sysUserList.size() == 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUserWhere() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            // ?????????????????????
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> sysUserList = sysUserMapper.selectByUserWhere(query);
            Assert.assertTrue(sysUserList.size() > 0);

            // ??????????????????
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            sysUserList = sysUserMapper.selectByUserWhere(query);
            Assert.assertTrue(sysUserList.size() > 0);

            // ?????????????????????????????????
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            sysUserList = sysUserMapper.selectByUserWhere(query);
            // ???????????????????????????????????????????????????????????????????????????0
            Assert.assertTrue(sysUserList.size() == 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            // ??????id=1?????????
            sysUser.setId(1L);
            // ????????????
            sysUser.setUserEmail("test@mybatis.tk");

            int result = sysUserMapper.updateByIdSelective(sysUser);
            Assert.assertEquals(1, result);

            // ??????id=1?????????
            sysUser = sysUserMapper.selectById(1L);
            // ????????????????????????????????????????????????????????????
            Assert.assertEquals("admin", sysUser.getUserName());
            Assert.assertEquals("test@mybatis.tk", sysUser.getUserEmail());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelectiveSet() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            // ??????id=1?????????
            sysUser.setId(1L);
            // ????????????
            sysUser.setUserEmail("test@mybatis.tk");

            int result = sysUserMapper.updateByIdSelectiveSet(sysUser);
            Assert.assertEquals(1, result);

            // ??????id=1?????????
            sysUser = sysUserMapper.selectById(1L);
            // ????????????????????????????????????????????????????????????
            Assert.assertEquals("admin", sysUser.getUserName());
            Assert.assertEquals("test@mybatis.tk", sysUser.getUserEmail());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertSelective() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            sysUser.setUserName("test-selective");
            sysUser.setUserPassword("123456");
            sysUser.setUserInfo("test info");
            sysUser.setCreateTime(new Date());

            sysUserMapper.insertSelective(sysUser);

            // ???????????????????????????
            sysUser = sysUserMapper.selectById(sysUser.getId());
            // ??????????????????userEmail,????????????????????????????????????
            Assert.assertEquals("test@mybatis.tk", sysUser.getUserEmail());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            // ???id??????
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser sysUser = sysUserMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(sysUser);

            // ??????userName??????
            query.setId(null);
            sysUser = sysUserMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(sysUser);

            // id ??? userName ?????????
            query.setUserName(null);
            sysUser = sysUserMapper.selectByIdOrUserName(query);
            Assert.assertNull(sysUser);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);

            List<SysUser> userList = sysUserMapper.selectByIdList(idList);
            Assert.assertEquals(2, userList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdArray() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            Long[] longArray = new Long[2];
            longArray[0] = 1L;
            longArray[1] = 1001L;

            List<SysUser> userList = sysUserMapper.selectByIdArray(longArray);
            Assert.assertEquals(2, userList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            List<SysUser> sysUserList = new ArrayList<SysUser>();
            for (int i = 0; i < 2; i++) {
                SysUser sysUser = new SysUser();
                sysUser.setUserName("test" + i);
                sysUser.setUserPassword("123456");
                sysUser.setUserEmail("test@mybatis.tk");

                sysUserList.add(sysUser);
            }

            int result = sysUserMapper.insertList(sysUserList);

            for (SysUser sysUser : sysUserList) {
                System.out.println(sysUser.getId());
            }

            Assert.assertEquals(2, result);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", 1L);
            map.put("user_email", "test@mybatis.tk");
            map.put("user_password", "12345678");

            Assert.assertEquals(1, sysUserMapper.updateByMap(map));

            SysUser sysUser = sysUserMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk", sysUser.getUserEmail());
            Assert.assertEquals("12345678", sysUser.getUserPassword());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            // ??????????????????1001???????????????????????????????????????1?????????
            SysUserExtend sysUserExtend = sysUserMapper.selectUserAndRoleById(1001L);

            Assert.assertNotNull(sysUserExtend);
            Assert.assertNotNull(sysUserExtend.getSysRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdResultMap() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUserExtend sysUserExtend = sysUserMapper.selectUserAndRoleByIdResultMap(1001L);
            Assert.assertNotNull(sysUserExtend);
            Assert.assertNotNull(sysUserExtend.getSysRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUserExtend sysUserExtend = sysUserMapper.selectUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(sysUserExtend);

            System.out.println("??????sysUserExtend.equals(null)");
            sysUserExtend.equals(null);

            System.out.println("??????sysUserExtend.getSysRole()");
            Assert.assertNotNull(sysUserExtend.getSysRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            List<SysUserExtend> sysUserList = sysUserMapper.selectAllUserAndRoles();
            System.out.println("????????????" + sysUserList.size());
            for (SysUserExtend sysUser : sysUserList) {
                System.out.println("????????????" + sysUser.getUserName());
                for (SysRoleExtend sysRoleExtend : sysUser.getSysRoleList()) {
                    System.out.println("????????????" + sysRoleExtend.getRoleName());
                    for (SysPrivilege sysPrivilege : sysRoleExtend.getSysPrivilegeList()) {
                        System.out.println("????????????" + sysPrivilege.getPrivilegeName());
                    }
                }
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRolesSelect() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUserExtend sysUserExtend = sysUserMapper.selectAllUserAndRolesSelect(1L);
            System.out.println("????????????" + sysUserExtend.getUserName());
            for (SysRoleExtend sysRoleExtend : sysUserExtend.getSysRoleList()) {
                System.out.println("????????????" + sysRoleExtend.getRoleName());
                for (SysPrivilege sysPrivilege : sysRoleExtend.getSysPrivilegeList()) {
                    System.out.println("????????????" + sysPrivilege.getPrivilegeName());
                }
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            sysUser.setId(1L);
            sysUserMapper.selectUserById(sysUser);

            Assert.assertNotNull(sysUser.getUserName());
            System.out.println("????????????" + sysUser.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserPage() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userName", "ad");
            params.put("offset", 0);
            params.put("limit", 10);

            List<SysUser> sysUserList = sysUserMapper.selectUserPage(params);
            Long total = (Long) params.get("total");
            System.out.println("?????????" + total);
            for (SysUser sysUser : sysUserList) {
                System.out.println("????????????" + sysUser.getUserName());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertAndDelete() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

            SysUser sysUser = new SysUser();
            sysUser.setUserName("test1");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserInfo("test info");
            sysUser.setHeadImg(new byte[]{1, 2, 3});
            sysUserMapper.insertUserAndRoles(sysUser, "1,2");

            Assert.assertNotNull(sysUser.getId());
            Assert.assertNotNull(sysUser.getCreateTime());

            // ???????????????????????????
            sysUserMapper.deleteUserById(sysUser.getId());
        } finally {
            sqlSession.close();
        }
    }
}
