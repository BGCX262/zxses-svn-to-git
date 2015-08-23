package com.zx.core.base.service;   

import com.lcsoft.control.tag.page.pojo.Paginate;

public class BaseService implements IBaseService {
    
    private Paginate paginate = null;

    public Paginate getPaginate() {
	return paginate;
    }

    public void setPaginate(Paginate paginate) {
	this.paginate = paginate;
    }
}