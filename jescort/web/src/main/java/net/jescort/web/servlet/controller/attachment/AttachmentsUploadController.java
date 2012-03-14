package net.jescort.web.servlet.controller.attachment;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.jescort.domain.forum.Attachment;
import net.jescort.repository.EscortRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA. User: admin@gelif.net Date: 11-7-17 Time: 下午6:44
 */
@Controller
@RequestMapping(value = "/upload")
public class AttachmentsUploadController
{
    private transient final Log logger = LogFactory.getLog(AttachmentsUploadController.class);

    private static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setupForm()
    {
        ModelAndView mav = new ModelAndView("fileupload");
        return mav;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String handleFormUpload(HttpServletRequest request)
    {
        escortRepository.uploadAttachments(request);
        return "redirect:/";
    }

    /**
     * When Spring is shutdown (app undeploys) then shutdown thread pool too.
     */
    @PreDestroy
    void shutdownAndAwaitTermination()
    {
        executor.shutdown();
        try
        {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS))
            {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                {
                    logger.error("Pool did not terminate");
                }
            }
        } catch (InterruptedException e)
        {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Resource(name = "escortRepository")
    private EscortRepository escortRepository;
}
