package com.hangdaoju.api;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hangdaoju.core.Page;
import com.hangdaoju.dao.UserDao;
import com.hangdaoju.model.Friends;
import com.hangdaoju.model.UserEntity;
@RestController
@RequestMapping("/demo")
public class DemoCRUD {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/insertFriend")
	public void insertFriend(Friends friends,String userName,String passWord){
		UserEntity user = new UserEntity();
		 user.setUserName(userName);
		 user.setPassWord(passWord);
		List<Friends> list = new ArrayList<Friends>();
		friends.setId(new ObjectId());
		list.add(friends);
		user.setFriends(list);
		userDao.save(user);
	}
	
	
	@RequestMapping(value="/getList")
	public List<UserEntity> getList(){
		Criteria cirteria = new Criteria("userName");
		cirteria.is("小往");//但条件查询
		//Criteria cirteria = new Criteria();
		//cirteria.orOperator(Criteria.where("userName").is("111"),Criteria.where("userName").is("222"));//or查询
		//Criteria cirteria = new Criteria();
		//cirteria.andOperator(Criteria.where("userName").is("111"),Criteria.where("passWord").is("111"));//and查询
		//Criteria cirteria = new Criteria();
		//cirteria.orOperator(Criteria.where("userName").is("111"),Criteria.where("userName").is("222")).andOperator(Criteria.where("passWord").is("222"));//and  or联合查询
		//Pattern pattern = Pattern.compile("^.*11.*$", Pattern.CASE_INSENSITIVE);
		Query query = new Query();
		query.addCriteria(cirteria);
		//query.addCriteria(Criteria.where("userName").regex(pattern));//模糊查询
		//query.with(new PageRequest(1, 1)); //分页
		//query.with(new Sort(Sort.Direction.DESC, "age"));//排序
		return userDao.find(query);
	}
	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping(value="/getPage")
	public Page<UserEntity> getPage(){
		Criteria cirteria = new Criteria("userName"); 
		cirteria.is("bbb");
		Query query = new Query();
		query.addCriteria(cirteria);
		return userDao.findPage(new Page<UserEntity>(), query);
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public void update(){
		/**
		 * 条件update
		 */
		/*Criteria criteria = new Criteria("userName");
		criteria.is("222");
		Update update = new Update().set("userName", "101");
		Query query = new Query();
		query.addCriteria(criteria);
		userDao.updateMulti(query, update);*/
		/**
		 * 条件  向数组插入值
		 */
		/*Criteria criteria = new Criteria("userName");
		criteria.is("101");
		Query query = new Query();
		query.addCriteria(criteria);
		Update update = new Update().push("pushKey", "pushKey");
		userDao.updateMulti(query, update);*/
		/**
		 * 条件 向数组删除某元素
		 */
		/*Criteria criteria = new Criteria("userName");
		criteria.is("101");
		Query query = new Query();
		query.addCriteria(criteria);
		Update update = new Update().pull("pushKey","pushKey");
		userDao.updateMulti(query, update);*/
		/**
		 * 条件 想数组修改某元素
		 */
		/*Criteria c = new Criteria("friends");//查询对应字段
		Criteria d = new Criteria("name");//查询字段中某字段是否为aaa
		d.is("aaa");
		c.elemMatch(d);//加入到后续条件当中
		Query query = new Query(c);
        Update update = new Update().set("friends.$.name", "66666");//向对应查询出来的内容进行修改
        userDao.update(query, update);*/
	}
}
