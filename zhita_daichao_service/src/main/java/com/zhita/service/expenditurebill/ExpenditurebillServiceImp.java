package com.zhita.service.expenditurebill;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhita.dao.manage.ExpenditureBillMapper;
import com.zhita.model.manage.ExpenditureBill;

@Service
public class ExpenditurebillServiceImp implements IntexpenditurebillService{
	@Autowired
	private ExpenditureBillMapper expenditureBillMapper;
	
    //添加支出账单信息
    public int addExpenditureBill(ExpenditureBill expenditureBill) {
    	int count=expenditureBillMapper.addExpenditureBill(expenditureBill);
    	return count;
    }
    //通过时间模糊查询   当前用户  当前月 所有支出金额总和
    public BigDecimal queryTotalMoney(Integer userid,String time) {
    	BigDecimal sum=expenditureBillMapper.queryTotalMoney(userid, time);
    	return sum;
    }
    
    //通过时间模糊查询出当前用户  当前月 每一天  的支出详细信息
    public List<ExpenditureBill> queryAllByTimeLike(Integer userid,String time){
    	List<ExpenditureBill> list=expenditureBillMapper.queryAllByTimeLike(userid, time);
    	return list;
    }
    
    //通过时间模糊查询出当前用户  当前月 每一天  的支出总和
    public BigDecimal querySumByTimeLike(Integer userid,String time) {
    	BigDecimal sum=expenditureBillMapper.querySumByTimeLike(userid, time);
    	return sum;
    }
}
