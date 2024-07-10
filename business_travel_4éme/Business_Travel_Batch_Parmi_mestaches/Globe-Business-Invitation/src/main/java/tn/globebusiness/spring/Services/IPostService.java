package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Comment;
import tn.globebusiness.spring.Entities.Post;
import tn.globebusiness.spring.Entities.Share;

public interface IPostService {
	void addPost(Long idEmployee, Post post);
	List<Post> selectPost();
	void deletePost(Long id,Long idEmployee);
	void editPost(Post post);
	List<Post> getMayPosts(Long idEmployee);
	
	
	void addLikeDislike(Long idEmployee, Long idPost);
	int likeNumberByPost(Long idPost);
	Post postMaxLike();
	List<Post> trierPostByDate();
	List<Post> trierPostByNbrLike();
	
	Comment addComment(Long idEmployee,Long idPost,Comment comment);
	Comment deleteComment(Long idComment,Long idEmployee);
	Comment updateComment(Long idComment,Long idEmployee,Comment comment);
	
	Share sharePost(Long idEmployee1,Long idEmployee2,Long idPost);
	
	List<Post> myFriendPost(Long idEmployee2);
}