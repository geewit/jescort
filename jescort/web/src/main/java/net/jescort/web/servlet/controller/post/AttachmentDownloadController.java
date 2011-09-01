package net.jescort.web.servlet.controller.post;

import net.jescort.domain.forum.Attachment;
import net.jescort.repository.EscortRepository;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-7-31
 * Time: 上午9:59
 */
@Controller
public class AttachmentDownloadController
{
    private transient final Log logger = LogFactory.getLog(AttachmentDownloadController.class);

    @RequestMapping(value = "/attachments/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id")Integer id, HttpServletResponse response)
    {
        try
        {
            Attachment attachment = escortRepository.getAttachment(id);
            response.setHeader("Content-Disposition", "inline;filename=\"" + attachment.getOriginalName() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(attachment.getContentType());
            IOUtils.copy(new ByteArrayInputStream(attachment.getAttachmentData().getContent()), out);
            out.flush();
            out.close();

        } catch (IOException e)
        {
            logger.warn(e.getMessage());
        }
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}
