package com.zhita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/notifyUrl")
public class notifyUrlDemo {
    /**
     * 回调接口示例
     * @link http://localhost:8080/callback
     * @param request request
     * @return 回调成功信息，默认返回success
     */
    @RequestMapping(value = "/callback", consumes = "multipart/form-data", method = RequestMethod.POST)
    public String callback(MultipartHttpServletRequest request) {
	 String search_id = request.getParameter("search_id");
     String outUniqueId = request.getParameter("outUniqueId");
     String userId = request.getParameter("userId");
     String state = request.getParameter("state");
     String account = request.getParameter("account");
     String accountType = request.getParameter("accountType");
     System.out.println("接收到的参数：\nsearch_id为："
             + search_id + "\noutUniqueId为：" + outUniqueId
             + "\nuserId为：" + userId + "\nstate为：" + state
             + "\naccount为：" + account + "\naccountType为：" + accountType);
     return "success";
}
}