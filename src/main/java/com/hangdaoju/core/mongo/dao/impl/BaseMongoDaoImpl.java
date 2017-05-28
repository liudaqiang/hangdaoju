package com.hangdaoju.core.mongo.dao.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hangdaoju.core.Page;
import com.hangdaoju.core.mongo.dao.BaseMongoDao;
import com.hangdaoju.util.ReflectionUtils;  
  
public  class BaseMongoDaoImpl<T> implements BaseMongoDao<T>{  
	@Autowired
	private MongoTemplate mongoTemplate;
	
    //private static final int DEFAULT_SKIP = 0;  
    //private static final int DEFAULT_LIMIT = 200;  
      
    /** 
     * spring mongodb　集成操作类　 
     */  
	
	/**
	 * 查询所有
	 */
	@Override
	public List<T>findAll(){
		return mongoTemplate.findAll(this.getEntityClass());
	}
	/**
	 * 条件查询
	 */
    @Override  
    public List<T> find(Query query) {  
        return mongoTemplate.find(query, this.getEntityClass());  
    }  
    /**
     * 查询一条数据
     */
    @Override  
    public T findOne(Query query) {  
        return mongoTemplate.findOne(query, this.getEntityClass());  
    }  
    /**
     * 根据id查询数据
     */
    @Override  
    public T findById(String id) {  
        return mongoTemplate.findById(id, this.getEntityClass());  
    }  
    @Override  
    public T findById(String id, String collectionName) {  
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);  
    }  
    /**
     * 分页条件查询
     */
    @Override  
    public Page<T> findPage(Page<T> page,Query query){  
        long count = this.count(query);  
        page.setTotal(count);  
        int pageNumber = page.getPageNumber();  
        int pageSize = page.getPageSize();  
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);  
        List<T> rows = this.find(query);  
        page.setRows(rows);  
        return page;  
    }  
    
    /**
     * 修改数据(仅修改一条)
     */
    @Override  
    public void update(Query query, Update update) {  
        mongoTemplate.findAndModify(query, update, this.getEntityClass());  //最多更新一次文档
    }  
    /**
     * 修改所有符合条件的数据
     */
	@Override
	public void updateMulti(Query query, Update update) {
		mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}  
	/**
	 * 保存数据
	 */
    @Override  
    public T save(T entity) {  
        mongoTemplate.insert(entity);  
        return entity;  
    }  
    /**
     * 查询返回list长度
     */
    @Override  
    public long count(Query query){  
        return mongoTemplate.count(query, this.getEntityClass());  
    }  
    /**
     * 根据id删除
     */
    @Override
    public void delete(T entity){
    	mongoTemplate.remove(entity);
    }
    /**
     * 查询后删除
     */
    @Override
    public T findAndDelete(Query query){
    	return mongoTemplate.findAndRemove(query, this.getEntityClass());
    }
    /** 
     * 获取需要操作的实体类class 
     *  
     * @return 
     */  
    private Class<T> getEntityClass(){  
        return ReflectionUtils.getSuperClassGenricType(getClass());  
    }
}  
