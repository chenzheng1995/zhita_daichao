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
	public List<CreditCard> queryAllCard(Integer page) {
		List<CreditCard> list=creditCardMapper.queryAllCard(page);
		return list;
	}
	//后台管理---用于获取总页数
	@Override
	public int pageCount() {
		int count=creditCardMapper.pageCount();
		return count;
	}
	//后台管理---模糊查询信用卡信息,并且有分页功能
	@Override
	public List<CreditCard> queryByLike(String title, Integer page) {
		List<CreditCard> list=creditCardMapper.queryByLike(title, page);
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
    //后台管理---通过删除按钮，改变当前银行卡的假删除状态，将状态改为删除
    public int upaFalseDel(Integer id) {
    	int selnum=creditCardMapper.upaFalseDel(id);
    	return selnum;
    }
}
