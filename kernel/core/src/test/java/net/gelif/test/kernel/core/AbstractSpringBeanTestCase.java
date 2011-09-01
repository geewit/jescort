package net.gelif.test.kernel.core;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration(locations={"classpath:spring.xml"})
public abstract class AbstractSpringBeanTestCase extends AbstractJUnit4SpringContextTests
{
    public AbstractSpringBeanTestCase()
    {
    }
}
