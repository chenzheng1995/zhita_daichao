package com.zhita.service.card;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.CreditCardMapper;
import com.zhita.model.manage.CreditCard;

@Transactional
@Service(value="cardServiceImp")
public class CardServiceImp implements IntCardService{
	@Resource(name="creditCardMapper")
	private CreditCardMapper creditCardMapper;
	
	public CreditCardMapper getCreditCardMapper() {
		return creditCardMapper;
	}

	public void setCreditCardMapper(CreditCardMapper creditCardMapper) {
		this.creditCardMapper = creditCardMapper;
	}
	
	//后台管理---查询所有信用卡信息，含分页
	@Override
	public List<CreditCard> queryAllCard(Integer page,Integer pagesize) {
		List<CreditCard> list=creditCardMapper.queryAllCard(page,pagesize);
		return list;
	}
	//后台管理---用于获取总页数
	@Override
	public int pageCount() {
		int count=creditCardMapper.pageCount();
		return count;
	}
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String title) {
    	int count=creditCardMapper.pageCountByLike(title);
    	return count;
    }
	//后台管理---模糊查询信用卡信息,并且有分页功能
	@Override
	public List<CreditCard> queryByLike(String title, Integer page,Integer pagesize) {
		List<CreditCard> list=creditCardMapper.queryByLike(title, page,pagesize);
		return list;
	}
	//后台管理---添加信用卡信息
	@Override
	public int addAll(CreditCard record) {
		int selnum=creditCardMapper.addAll(record);
		return selnum;
	}
    //后台管理---通过信用卡id，查询信用卡信息
    public CreditCard selectByPrimaryKey(Integer id) {
    	CreditCard creditCard=creditCardMapper.selectByPrimaryKey(id);
    	return creditCard;
    }
    //后台管理---通过传过来的信用卡对象，对当前对象进行修改保存
    public int updateCreditCard(CreditCard creditCard) {
    	int num=creditCardMapper.updateCreditCard(creditCard);
    	return num;
    }
    
    //后台管理---通过删除按钮，改变当前银行卡的假删除状态，将状态改为删除
    public int upaFalseDel(Integer id) {
    	int num=creditCardMapper.upaFalseDel(id);
    	return num;
    }
    //后台管理---修改信用卡状态为开启
    public int upaStateOpen(Integer id) {
    	int num=creditCardMapper.upaStateOpen(id);
    	return num;
    }
    
    //后台管理---修改信用卡状态为关闭
    public int upaStateClose(Integer id) {
    	int num=creditCardMapper.upaStateClose(id);
    	return num;
    }
}
