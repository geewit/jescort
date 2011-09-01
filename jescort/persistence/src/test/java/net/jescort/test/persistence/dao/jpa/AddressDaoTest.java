package net.jescort.test.persistence.dao.jpa;

import org.junit.Test;

public class AddressDaoTest// extends AbstractJpaDaoTestCase
{
    /*
    @Resource
    private AddressDao addressDao;
    
    public void setAddressDao(AddressDao addressDao)
    {
        this.addressDao = addressDao;
    }
    */
    @Test
    public void testGetAddressInvalid() throws Exception
    {
        /*
        try
        {
            addressDao.findOne(0);
            fail("'bad id' found in database, failing test...");
        }
        catch(EntityNotFoundException e)
        {
            assertNotNull(e);
        }
        */
    }
    
    @Test
    public void testGetAddress() throws Exception
    {
        /*
        Address address = addressDao.findOne(-1);
        
        assertNotNull(address);
        */
    }
    
    @Test
    public void testUpdateAddress() throws Exception
    {
        /*
        Address address = addressDao.findOne(-1);
        addressDao.save(address);
        
        super.endTransaction();
        
        address.setId(0);
        
        super.startNewTransaction();
        try
        {
            addressDao.save(address);
            fail("saveAddress didn't throw EntityExistsException");
        }
        catch(EntityExistsException e)
        {
            logger.debug("expected exception: " + e.getMessage());
            assertNotNull(e);
        }
        */
    }
    
    @Test
    public void addAndRemoveAddress() throws Exception
    {
        /*
        Address address = new Address();
        address.setLocationId(11);
        address.setPostalcode("200236");
        address.setStreet("aaa bbb ccc 250");
        address = addressDao.save(address);
        
        assertNotNull(address.getId());
        assertEquals("200236", address.getPostalcode());
        
        addressDao.delete(address.getId());
        
        try
        {
            addressDao.findOne(address.getId());
            fail("getUser didn't throw DataAccessException");
        }
        catch(EntityNotFoundException e)
        {
            assertNotNull(e);
        }
        */
    }
    
    @Test
    public void addressExists() throws Exception
    {
        /*
        boolean b = addressDao.exists(-1);
        super.assertTrue(b);
        */
    }
    
    @Test
    public void testAddressNotExists() throws Exception
    {
        /*
        boolean b = addressDao.exists(-111);
        super.assertFalse(b);
        */
    }
    
}
