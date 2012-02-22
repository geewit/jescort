package net.jescort.repository.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.gelif.kernel.core.data.domain.PageableFactory;
import net.gelif.modules.bbcode.BBProcessorFactory;
import net.gelif.modules.bbcode.TextProcessor;
import net.jescort.domain.forum.*;
import net.jescort.domain.user.User;
import net.jescort.persistence.dao.*;
import net.jescort.repository.EscortRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Service("escortRepository")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class EscortRepositoryImpl implements EscortRepository
{
    protected transient final static Log logger = LogFactory.getLog(EscortRepositoryImpl.class);

    public static TextProcessor processor = BBProcessorFactory.getInstance().create();

    @Resource(name = "attachmentDao")
    private AttachmentDao attachmentDao;

    @Resource(name = "forumDao")
    private ForumDao forumDao;

    @Resource(name = "topicDao")
    private TopicDao topicDao;

    @Resource(name = "postDao")
    private PostDao postDao;

    @Resource(name = "postEditDao")
    private PostEditDao postEditDao;

    @Resource(name = "categoryDao")
    private CategoryDao categoryDao;

    @Override
    public Category findCategory(final Integer categoryId)
    {
        return categoryDao.findOne(categoryId);
    }

    @Override
    public List<Category> findCategories()
    {
        return categoryDao.findAll();
    }

    @Override
    public void createForum(final Forum forum)
    {
        forumDao.save(forum);
    }

    @Override
    public void updateForum(final Forum forum)
    {
        forumDao.save(forum);
    }

    @Override
    public Forum getForum(final Integer forumId)
    {
        return forumDao.findOne(forumId);
    }

    @Override
    public boolean forumExists(final Integer forumId)
    {
        return forumDao.exists(forumId);
    }

    @Override
    public Topic getTopic(final Integer topicId)
    {
        Topic topic = topicDao.findOne(topicId);
        return topic;
    }

    @Override
    public Topic getTopicWithBBCodeToHtml(final Integer topicId)
    {
        Topic topic = topicDao.findOne(topicId);
        topic.getRootPost().setContent(processor.process(topic.getRootPost().getContent()));
        return topic;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createTopic(final Topic topic)
    {
        topicDao.save(topic);
        int forumId = topic.getForumId();
        forumDao.increaseTopics(forumId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createTopic(final Topic topic, final HttpServletRequest request)
    {
        final int forumId = topic.getForumId();
        forumDao.increaseTopics(forumId);
        Post rootPost = topic.getRootPost();
        //rootPost.setCreatedate(Calendar.getInstance());
        List<Attachment> attachments = uploadAttachments(request);
        rootPost.setAttachments(attachments);
        topic.setRootPost(rootPost);
        topicDao.save(topic);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateTopic(final Topic topic)
    {
        topicDao.save(topic);
    }

    @Override
    public Page<Topic> findTopicsByForum(final Integer forumId, final Pageable pageable)
    {
        List<Topic> topics = topicDao.findByForum(forumId, pageable);
        Page<Topic> page = new PageImpl<Topic>(topics);
        return page;
    }

    @Override
    public Page<Topic> findTopicsByForumWithBBCodeToHtml(final Integer forumId, final Pageable pageable)
    {
        List<Topic> topics = topicDao.findByForum(forumId, pageable);
        for(Topic topic : topics)
        {
            Post rootPost = topic.getRootPost();
            String content = rootPost.getContent();
            topic.getRootPost().setContent(processor.process(content));
        }
        Page<Topic> page = new PageImpl<Topic>(topics);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ModelAndView topicView(final Integer topicId, final Integer pageNo, final Integer pageSize, final ModelAndView mav)
    {
        Topic topic = topicDao.findOne(topicId);
        topic.getRootPost().setContent(processor.process(topic.getRootPost().getContent()));
        Pageable pageable = PageableFactory.create(pageNo, pageSize);
        List<Post> posts = postDao.findByTopicId(topicId, pageable);
        for(Post post : posts)
        {
            post.setContent(processor.process(post.getContent()));
        }
        final long totalPages = postDao.countByTopicId(topicId);
        Page<Post> page = new PageImpl<Post>(posts, pageable, totalPages);
        topicDao.increaseViews(topicId);
        mav.addObject("topic", topic);
        mav.addObject("posts", page);
        return mav;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void replyTopic(final Post post, final HttpServletRequest request)
    {
        //post.setCreatedate(Calendar.getInstance());
        List<Attachment> attachments = uploadAttachments(request);
        int topicId = post.getTopicId();
        topicDao.replyTopic(topicId, postId);
        Topic topic = topicDao.findOne(topicId);
        int forumId = topic.getForumId();
        forumDao.increaseReplys(forumId);

        post.setAttachments(attachments);
        postDao.save(post);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createPost(final Post post)
    {
        //post.setCreatedate(Calendar.getInstance());
        postDao.save(post);
    }

    @Override
    public Post getPost(final Integer postId)
    {
        return postDao.findOne(postId);
    }

    @Override
    public Post getPostWithBBCodeToHtml(final Integer postId)
    {
        Post post = postDao.findOne(postId);
        post.setContent(processor.process(post.getContent()));
        return post;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updatePost(final Post post, final User editor)
    {
        post.setEdits(post.getEdits() + 1);
        postDao.save(post);
        if (!postEditDao.exists(post.getId()))
        {
            PostEdit edit = new PostEdit();
            edit.setId(post.getId());
            edit.setEditor(editor);
            //edit.setEditdate(Calendar.getInstance());
            postEditDao.save(edit);
        }
    }

    @Override
    public Post quotePost(final Integer postId)
    {
        Post post = postDao.findOne(postId);
        Post quotePost = new Post();
        StringBuffer sb = new StringBuffer();
        sb.append("[quote=");
        sb.append(post.getPoster().getUsername());
        sb.append("]");
        sb.append(post.getContent());
        sb.append("[/quote]");
        quotePost.setContent(sb.toString());
        return quotePost;
    }

    @Override
    public long countPostsByTopicId(final Integer topicId)
    {
        return postDao.countByTopicId(topicId);
    }

    @Override
    public long rownumberPostsByTopicIdAndId(final Integer topicId, final Integer postId)
    {
        return postDao.rownumberPostsByTopicIdAndPostId(topicId, postId);
    }

    @Override
    public Page<Post> findPostsByTopicWithBBCodeToHtml(final Integer topicId, final Pageable pageable)
    {
        List<Post> posts = postDao.findByTopicId(topicId, pageable);
        for(Post post : posts)
        {
            post.setContent(processor.process(post.getContent()));
        }
        Page<Post> page = new PageImpl<Post>(posts);
        return page;
    }

    @Override
    public PostEdit getPostEdit(Integer id)
    {
        return postEditDao.findOne(id);
    }

    @Override
    public Attachment findAttachment(final Integer attachmentId)
    {
        return attachmentDao.findOne(attachmentId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveAttachment(final Attachment attachment)
    {
        attachmentDao.save(attachment);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Attachment> uploadAttachments(final HttpServletRequest request)
    {
        final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        final Map<String, MultipartFile> multipartFiles = multipartRequest.getFileMap();
        final List<Attachment> attachments = new ArrayList<Attachment>();
        for(final MultipartFile multipartFile : multipartFiles.values())
        {
            executor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Attachment attachment = new Attachment();
                        attachment.setOriginalName(multipartFile.getOriginalFilename());
                        attachment.setSize(multipartFile.getSize());
                        //attachment.setCreatedate(Calendar.getInstance());
                        attachment.setContentType(multipartFile.getContentType());
                        AttachmentData attachmentData = new AttachmentData();
                        attachmentData.setContent(multipartFile.getBytes());
                        attachment.setAttachmentData(attachmentData);
                        attachments.add(attachment);
                    } catch (IOException e)
                    {
                        logger.debug(e.toString());
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
                        } catch (InterruptedException ex)
                        {
                            executor.shutdownNow();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });
        }
        attachmentDao.save(attachments);
        return attachments;
    }

    @Override
    public List<Attachment> findAttachmentsByUser(Integer userId)
    {
        return attachmentDao.findByUser(userId);
    }

    @Override
    public List<Attachment> findAttachmentsByUser(Integer userId, final Integer pageNo, final Integer pageSize)
    {
        return attachmentDao.findByUser(userId, pageNo, pageSize);
    }

    @Override
    public ModelAndView attachmentView(final Integer userId, final Integer pageNo, final Integer pageSize, final ModelAndView mav)
    {
        Pageable pageable = PageableFactory.create(pageNo, pageSize);
        List<Attachment> attachments = attachmentDao.findByUser(userId, pageable);
        final long totalPages = attachmentDao.countByUserId(userId);
        Page<Attachment> page = new PageImpl<Attachment>(attachments, pageable, totalPages);
        mav.addObject("attachments", page);
        return mav;
    }
}
