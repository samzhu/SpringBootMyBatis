package com.sam.modules.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sam.modules.dao.UserinfoMapper;
import com.sam.modules.entity.Userinfo;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserinfoService extends BaseService<Userinfo, Long>{

	@Autowired
	private UserinfoMapper userinfoMapper;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int save(Userinfo record) {
		record.setTimecreate(new Date(System.currentTimeMillis()));
		return userinfoMapper.insert(record);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int updateSelective(Userinfo record) {
		return userinfoMapper.updateByPrimaryKeySelective(record);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int updateByReplace(Userinfo record) {
		return userinfoMapper.updateByPrimaryKey(record);
	}

	public Userinfo get(Long serno) {
		return userinfoMapper.selectByPrimaryKey(serno);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int delete(Long serno) {
		return userinfoMapper.deleteByPrimaryKey(serno);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void cutsdel(Long serno) throws Exception{
		userinfoMapper.deleteByPrimaryKey(serno);
		if(serno > 10){
			throw new Exception("xx");
		}
	}

}
