/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package net.jescort.repository.impl;

import net.jescort.repository.FileRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 12-3-17
 * Time: 上午7:11
 */
public class FileRepositoryImpl implements FileRepository
{
    private transient final static Log logger = LogFactory.getLog(FileRepositoryImpl.class);

    private ResourceLoader resourceLoader;
    private String absolutePath;

    public void init() throws Exception
    {
        final Resource resource = resourceLoader.getResource("/");
        absolutePath = resource.getFile().getAbsolutePath();
        logger.debug("absolutePath == " + absolutePath);
        File file = new File(absolutePath);
        file.mkdirs();
    }

    @Override
    public String getAbsolutePath()
    {
        return absolutePath;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader)
    {
        this.resourceLoader = resourceLoader;
    }
}
