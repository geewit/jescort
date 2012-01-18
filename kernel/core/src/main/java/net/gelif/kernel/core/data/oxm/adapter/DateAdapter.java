package net.gelif.kernel.core.data.oxm.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date>
{
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public Date unmarshal(String date) throws Exception
    {
        return dateFormat.parse(date);
    }

    public String marshal(Date date) throws Exception
    {
        return dateFormat.format(date);
    }
}
