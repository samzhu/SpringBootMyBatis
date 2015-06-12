package com.sam.modules.service;

import java.io.Serializable;

/**
 * 
 * @author samzhu
 *
 * @param <T> 實體物件
 * @param <ID> ID類型 ex:String,Long,Integer
 */
public abstract class BaseService<T,ID extends Serializable> {
	/**
	 * 儲存
	 * @param record
	 * @return
	 */
	public abstract int save(T record);
	/**
	 * 更新有值的部分
	 * @param record
	 * @return
	 */
	public abstract int updateSelective(T record);
	/**
	 * 更新所有值
	 * @param record
	 * @return
	 */
	public abstract int updateByReplace(T record);
	/**
	 * 取出主鍵物件
	 * @param id
	 * @return
	 */
	public abstract T get(ID id);
	/**
	 * 依照主鍵刪除
	 * @param id
	 * @return
	 */
	public abstract int delete(ID id);
}

