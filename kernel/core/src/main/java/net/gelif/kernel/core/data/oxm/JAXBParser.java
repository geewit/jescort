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
