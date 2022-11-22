package com.techforum.api.restapidemo.repository;

import java.util.List;

import com.techforum.api.restapidemo.beans.Billionair;

public interface DemoRepository {

	List<Billionair> getAllBillionairs();

	Billionair getBillionairById(Integer billionair);

	Billionair addBillionair(Billionair billionair);

	boolean deleteBillionair(Integer billionairId);

}
