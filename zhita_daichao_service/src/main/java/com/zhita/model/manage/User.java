package com.zhita.model.manage;

import java.io.Serializable;

//用户表
public class User implements Serializable{
    private Integer id;//用户id

    private Integer sourceId;//渠道来源id

    private String nickname;//昵称

    private String phone;//手机号

    private Integer dayfen;//当日分布系数

    private String registrationtime;//注册开始时间
    
    private String registrationtime1;//注册结束时间

    private String name;//姓名

    private Integer age;//年龄

    private String idcard;//身份证号
    
    private Source source;//关联渠道表,多个用户属于一个渠道的
    

    public User(Integer id, Integer sourceId, String nickname, String phone, Integer dayfen, String registrationtime, String name, Integer age, String idcard) {
        this.id = id;
        this.sourceId = sourceId;
        this.nickname = nickname;
        this.phone = phone;
        this.dayfen = dayfen;
        this.registrationtime = registrationtime;
        this.name = name;
        this.age = age;
        this.idcard = idcard;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getDayfen() {
        return dayfen;
    }

    public void setDayfen(Integer dayfen) {
        this.dayfen = dayfen;
    }

    public String getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(String registrationtime) {
        this.registrationtime = registrationtime == null ? null : registrationtime.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getRegistrationtime1() {
		return registrationtime1;
	}

	public void setRegistrationtime1(String registrationtime1) {
		this.registrationtime1 = registrationtime1;
	}
    
}