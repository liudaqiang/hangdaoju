package com.hangdaoju.core.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hangdaoju.core.Page;  
  
//import cn.sunsharp.alibaba.core.Page;  
  
public interface BaseMongoDao<T> {  
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> findAll();
    /** 
     * 通过条件查询实体(集合) 
     *  
     * @param query 
     */  
    public List<T> find(Query query) ;  
  
    /** 
     * 通过一定的条件查询一个实体 
     *  
     * @param query 
     * @return 
     */  
    public T findOne(Query query) ;  
  
    /** 
     * 通过条件查询更新数据  每次只更新一条
     *  
     * @param query 
     * @param update 
     * @return 
     */  
    public void update(Query query, Update update) ; 
    /**
     * 通过条件查询更新数据  全部更新
     * 
     * @param query
     * @param update
     */
    public void updateMulti(Query query,Update update);
    /** 
     * 保存一个对象到mongodb 
     *  
     * @param entity 
     * @return 
     */  
    public T save(T entity) ;  
  
    /** 
     * 通过ID获取记录 
     *  
     * @param id 
     * @return 
     */  
    public T findById(String id) ;  
  
    /** 
     * 通过ID获取记录,并且指定了集合名(表的意思) 
     *  
     * @param id 
     * @param collectionName 
     *            集合名 
     * @return 
     */  
    public T findById(String id, String collectionName) ;  
      
    /** 
     * 分页查询 
     * @param page 
     * @param query 
     * @return 
     */  
    public Page<T> findPage(Page<T> page,Query query);  
      
    /** 
     * 求数据总和 
     * @param query 
     * @return 
     */  
    public long count(Query query);  
    /**
     * 根据条件删除数据
     */
    public void delete(T entity);
    /**
     * 查询后删除
     * @param entity
     * @return
     */
    public T findAndDelete(Query query);
}  