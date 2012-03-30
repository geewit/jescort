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
package net.gelif.kernel.core.data.oxm;

import org.springframework.oxm.XmlMappingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

public final class JAXBParser
{
    public static <T> String marshal(T object, Class<T>[] clazzes) throws IOException, XmlMappingException
    {
        String result = null;

        try
        {
            JAXBContext jc = JAXBContext.newInstance(clazzes);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            StringWriter writer = new StringWriter();
            marshaller.marshal(object, writer);
            result = writer.toString();
        } catch (JAXBException e)
        {
            throw new RuntimeException("Can't marshal the XML file, error message: " + e.getMessage(), e.getCause());
        }

        return result;
    }

    public static <T extends Object> T unmarshal(String str, Class<T>[] clazzes) throws IOException, XmlMappingException
    {
        StringBuffer xmlBuffer = new StringBuffer(str);
        StreamSource source = new StreamSource(new StringReader(xmlBuffer.toString()));
        return unmarshal(source, clazzes);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Object> T unmarshal(StreamSource source, Class<T>[] clazzes) throws IOException, XmlMappingException
    {
        JAXBContext jaxbContext;
        T object;
        try
        {
            jaxbContext = JAXBContext.newInstance(clazzes);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            object = (T) unmarshaller.unmarshal(source);
        } catch (JAXBException e)
        {
            throw new RuntimeException("Can't unmarshal the XML file, error message: " + e.getMessage(), e.getCause());
        }

        return object;
    }

    public static <T extends Object> T unmarshal(InputStream inputStream, Class<T>[] clazzes) throws IOException, XmlMappingException
    {
        StreamSource source = new StreamSource(inputStream);
        return unmarshal(source, clazzes);
    }
}
