package net.jescort.repository;

import net.jescort.domain.forum.*;
import net.jescort.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EscortRepository
{
    public Category findCategory(final Integer categoryId);

    public List<Category> findCategories();

    public void createForum(final Forum forum);

    public void updateForum(final Forum forum);

    public Forum getForum(final Integer forumId);

    public boolean forumExists(final Integer forumId);

    public Topic getTopic(final Integer topicId);

    public Topic getTopicWithBBCodeToHtml(final Integer topicId);

    public void createTopic(final Topic topic, final HttpServletRequest request);

    public Page<Topic> findTopicsByForum(final Integer forumId, final Pageable pageable);

    public Page<Topic> findTopicsByForumWithBBCodeToHtml(final Integer forumId, final Pageable pageable);

    public ModelAndView topicView(final Integer id, final Integer pageNo, final Integer pageSize, final ModelAndView mav);

    public void replyTopic(final Post post, final HttpServletRequest request);

    public Post getPost(final Integer postId);

    public Post getPostWithBBCodeToHtml(final Integer postId);

    public void createPost(final Post post);

    public void savePost(final Post post, final User editor);

    public Post quotePost(final Integer postId);

    public long countPostsByTopicId(final Integer topicId);

    public long rownumberPostsByTopicIdAndId(final Integer topicId, final Integer postId);

    public Page<Post> findPostsByTopicWithBBCodeToHtml(final Integer topicId, final Pageable pageable);

    public PostEdit getPostEdit(Integer id);

    public Attachment findAttachment(final Integer attachmentId);

    public void saveAttachment(final Attachment attachment);

    public List<Attachment> uploadAttachments(final HttpServletRequest request);

    public List<Attachment> findAttachmentsByUser(final Integer userId);

    public List<Attachment> findAttachmentsByUser(final Integer userId, final Integer pageNo, final Integer pageSize);

    public ModelAndView attachmentView(final Integer userId, final Integer pageNo, final Integer pageSize, final ModelAndView mav);
}
