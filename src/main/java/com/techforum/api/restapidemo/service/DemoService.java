package com.techforum.api.restapidemo.service;

import java.util.List;

import com.techforum.api.restapidemo.beans.Billionair;

public interface DemoService {

	List<Billionair> getAllBillionairs();

	Billionair getBillionairById(Integer billionairId);

	Billionair addBillionair(Billionair billionair);

	boolean deleteBillionair(Integer billionairId);

}
