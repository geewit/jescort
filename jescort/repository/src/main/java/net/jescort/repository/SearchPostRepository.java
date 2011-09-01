package net.jescort.repository;

import net.jescort.domain.forum.Post;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin@gelif.net
 * Date: 11-8-23
 * Time: 下午12:50
 */
public interface SearchPostRepository
{
    public List<Post> searchPosts(String keywords);
	public boolean createIndex();
}
