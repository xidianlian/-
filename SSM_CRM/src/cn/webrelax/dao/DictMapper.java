package cn.webrelax.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.webrelax.pojo.BaseDict;



public interface DictMapper {
	public List<BaseDict> findDictByTypeCode(String typeCode) ;
}
