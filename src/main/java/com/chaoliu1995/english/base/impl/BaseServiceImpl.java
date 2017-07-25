package com.chaoliu1995.english.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaoliu1995.english.base.BaseService;

import tk.mybatis.mapper.common.Mapper;

@Service("baseService")
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
    protected Mapper<T> mapper;
	
	public BaseServiceImpl(){ }
	
	@Override
    public T getById(Integer id){
    	return mapper.selectByPrimaryKey(id);
    }
	
	@Override
	public List<T> listByCol(T model) {
		return mapper.select(model);
	}
	
	@Override
	public int getCountByCol(T model) {
		return mapper.selectCount(model);
	}
	
	@Override
	public T getOne(T model) {
		return mapper.selectOne(model);
	}
	
	@Override
    public int insert(T model){
    	return mapper.insert(model);
    }
	
	@Override
    public int update(T model){
    	return mapper.updateByPrimaryKey(model);
    }
	
	@Override
    public int delete(Integer id){
    	return mapper.deleteByPrimaryKey(id);
    }
	
	@Override
    public int delete(T model){
    	return mapper.delete(model);
    }
	
	@Override
	public List<T> listAll(){
		return mapper.selectAll();
	}
}
