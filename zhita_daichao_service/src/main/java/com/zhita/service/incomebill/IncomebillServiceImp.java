package com.zhita.service.incomebill;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.IncomeBillMapper;
import com.zhita.model.manage.DayBill;
import com.zhita.model.manage.IncomeBill;

@Service
public class IncomebillServiceImp implements IntincomebillService{
	@Autowired
	private IncomeBillMapper incomeBillMapper;
	
    //添加收入账单信息
    public int addIncomeBill(IncomeBill incomeBill) {
    	int count=incomeBillMapper.addIncomeBill(incomeBill);
    	return count;
    }
    //通过时间模糊查询   当前用户  当前月 所有收入金额总和
    public BigDecimal queryTotalMoney(Integer userid,String time) {
    	BigDecimal sum=incomeBillMapper.queryTotalMoney(userid, time);
    	return sum;
    }
    //通过时间模糊查询出当前用户  当前月 每一天  的收入详细信息
    public List<IncomeBill> queryAllByTimeLike(Integer userid,String time){
    	List<IncomeBill> list=incomeBillMapper.queryAllByTimeLike(userid, time);
    	return list;
    }
    //通过时间准确查询出当前用户  当前月 每一天  的收入详细信息
    public List<IncomeBill> queryAllByTime(Integer userid,String time){
    	List<IncomeBill> list=incomeBillMapper.queryAllByTime(userid, time);
    	return list;
    }
    //通过时间模糊查询出当前用户  当前月 每一天  的收入总和
    public List<DayBill> querySumByTimeLike(Integer userid,String time) {
    	List<DayBill> listincomelike=incomeBillMapper.querySumByTimeLike(userid, time);
    	return listincomelike;
    }
}
