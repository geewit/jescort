package net.jescort.test.persistence.dao.jpa;

import org.junit.Test;

public class UserDaoTest// extends AbstractJpaDaoTestCase
{
    /*
    @Resource
    private UserDao userDao;
    
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }
    */
    @Test
    public void testGetUserInvalid() throws Exception
    {
        /*
        try
        {
            userDao.findOne(-1);
            fail("'bad username' found in database, failing test...");
        }
        catch(EntityNotFoundException e)
        {
            assertNotNull(e);
        }
        */
    }

    @Test
    public void testGetUser() throws Exception
    {
        /*
        User auth = userDao.findOne(-1);
        assertNotNull(auth);
        */
    }

    @Test
    public void testGetUserPassword() throws Exception
    {
        /*
        User auth = userDao.findOne(-1);
        String password = userDao.findPasswordByUsername(auth.getUsername());
        assertNotNull(password);
        logger.debug("password: " + password);
        */
    }

    @Test
    public void testUpdateUser() throws Exception
    {
        /*
        User auth = userDao.findOne(-1);
        userDao.save(auth);
        
        super.endTransaction();
        
        //verify that violation occurs when adding new auth with same username
        auth.setId(0);
        
        super.startNewTransaction();
        try
        {
            userDao.save(auth);
            fail("saveUser didn't throw EntityExistsException");
        }
        catch(EntityExistsException e)
        {
            logger.debug("expected exception: " + e.getMessage());
            assertNotNull(e);
        }
        */
    }

    @Test
    public void addAndRemoveUser() throws Exception
    {
        /*
        User auth = new User();
        auth.setPassword("testpass");
        auth.setName(new Name("Huang", "Fang"));
        auth = userDao.save(auth);
        
        assertNotNull(auth.getId());
        assertEquals("123456", auth.getPassword());
        
        userDao.delete(auth.getId());
        
        try
        {
            userDao.findOne(auth.getId());
            fail("getUser didn't throw DataAccessException");
        }
        catch(EntityNotFoundException e)
        {
            assertNotNull(e);
        }
        */
    }

    @Test
    public void userExists() throws Exception
    {
        /*
        boolean b = userDao.exists(-1);
        super.assertTrue(b);
        */
    }

    @Test
    public void testUserNotExists() throws Exception
    {
        /*
        boolean b = userDao.exists(-111);
        super.assertFalse(b);
        */
    }
}
