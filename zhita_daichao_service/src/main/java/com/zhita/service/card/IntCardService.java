package com.zhita.service.card;

import java.util.List;

import com.zhita.model.manage.CreditCard;

public interface IntCardService {
	//后台管理---查询所有信用卡信息，含分页
	public List<CreditCard> queryAllCard(Integer page,Integer pagesize);
    //后台管理---用于获取总页数
	public int pageCount();
    //后台管理---用于获取模糊查询总页数
	public int pageCountByLike(String title);
    //后台管理---模糊查询信用卡信息,并且有分页功能
	public List<CreditCard> queryByLike(String title,Integer page,Integer pagesize);
    //后台管理---添加信用卡信息
	public int addAll(CreditCard record);
    //后台管理---通过信用卡id，查询信用卡信息
	public CreditCard selectByPrimaryKey(Integer id);
    //后台管理---通过删除按钮，改变当前银行卡的假删除状态，将状态改为删除
	public int upaFalseDel(Integer id);
    //后台管理---修改信用卡状态为开启
    public int upaStateOpen(Integer id);
    //后台管理---修改信用卡状态为关闭
    public int upaStateClose(Integer id);
    //后台管理---通过传过来的信用卡对象，对当前对象进行修改保存
    public int updateCreditCard(CreditCard creditCard);
}
