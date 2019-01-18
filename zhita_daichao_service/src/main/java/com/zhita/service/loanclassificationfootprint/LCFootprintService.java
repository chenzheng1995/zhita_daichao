package com.zhita.service.loanclassificationfootprint;

import org.springframework.stereotype.Service;

@Service
public interface LCFootprintService {

	int insertfootprint(String footprintName, String userId, long currentTimestamp, String company);

}
