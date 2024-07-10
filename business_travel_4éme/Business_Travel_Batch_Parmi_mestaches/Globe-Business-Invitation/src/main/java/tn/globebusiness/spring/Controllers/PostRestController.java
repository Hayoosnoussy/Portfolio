package tn.globebusiness.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Comment;
import tn.globebusiness.spring.Entities.Likee;
import tn.globebusiness.spring.Entities.Post;
import tn.globebusiness.spring.Entities.Share;
import tn.globebusiness.spring.Repositories.LikeRepositoy;
import tn.globebusiness.spring.Services.IPostService;

@RestController
public class PostRestController  {
	@Autowired
	IPostService ips;
	@Autowired 
	LikeRepositoy lr;
	@PostMapping("addPost")
	public void addPost(@RequestParam("idEmployee") Long idEmployee, @RequestBody Post post){
		ips.addPost(idEmployee,post);	
	}
	@GetMapping("selectPost")
	public List<Post> selectPost(){
		return ips.selectPost();
	}
	@DeleteMapping("deletePost")
	public void deletePost(@RequestParam("idPost") Long idPost,@RequestParam("idEmployee") Long idEmployee){
		ips.deletePost(idPost,idEmployee);
	}
	@PutMapping("editPost")
	public void editPost(@RequestBody Post post){
		ips.editPost(post);	
	}
	
	@GetMapping("getMyPosts")
	public List<Post> getMyPosts(@RequestParam("idEmployee") Long idEmployee){
		return ips.getMayPosts(idEmployee);
	}
	
	@PostMapping("addLikeDislike")
	public void addLikeDislike(@RequestParam("idEmployee") Long idEmployee,@RequestParam("idPost")Long idPost){
		ips.addLikeDislike(idEmployee, idPost);
	}
	@GetMapping("getLike")
	public Likee getLike(@RequestParam("idLike") Long idLike){
		return lr.findById(idLike).orElse(new Likee());
	}
	
	@GetMapping("likeNumberByPost")
	public int likeNumberByPost(@RequestParam("idPost") Long idPost){
		return ips.likeNumberByPost(idPost);
	}
	@GetMapping("postMaxLike")
	public Post postMaxLike(){
		return ips.postMaxLike();
	}
	
	@GetMapping("trierPostByDate")
	public List<Post> trierPostByDate(){
		return ips.trierPostByDate();
	}
	
	@GetMapping("trierPostByNbrLike")
	public List<Post> trierPostByNbrLike(){
		return ips.trierPostByDate();
	}
	
	@PostMapping("addComment")
	public Comment addComment(@RequestParam("idEmployee") Long idEmployee,@RequestParam("idPost") Long idPost,@RequestBody Comment comm)     {
		return ips.addComment(idEmployee, idPost,comm);
	}
	
	@DeleteMapping("deleteComment")
	public Comment deleteComment(@RequestParam("idComment")Long idComment,@RequestParam Long idEmployee){
		return ips.deleteComment(idComment, idEmployee);
	}
	@PutMapping("updateComment")
	public Comment updateComment(@RequestParam("idComment") Long idComment,@RequestParam("idEmployee") Long idEmployee,@RequestBody Comment comm){
		return ips.updateComment(idComment, idEmployee, comm);
	}
	
	
	@PostMapping("sharePost")
	public Share sharePost(@RequestParam("idEmployee1")Long idEmployee1,@RequestParam("idEmployee2") Long idEmployee2,@RequestParam("idPost") Long idPost){
		return ips.sharePost(idEmployee1, idEmployee2, idPost);
	}
	
	@GetMapping("myFriendPost")
	public List<Post> myFriendPost(@RequestParam("idEmployee2")Long idEmployee2){
		return ips.myFriendPost(idEmployee2);
	}
	
	
	
}