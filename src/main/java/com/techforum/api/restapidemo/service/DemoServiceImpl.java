package com.techforum.api.restapidemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techforum.api.restapidemo.beans.Billionair;
import com.techforum.api.restapidemo.repository.DemoRepository;

@Service
public class DemoServiceImpl implements DemoService{

	@Autowired
	DemoRepository demoRepository;
	
	@Override
	public List<Billionair> getAllBillionairs() {
		return demoRepository.getAllBillionairs();
	}

	@Override
	public Billionair getBillionairById(Integer billionair) {
		return demoRepository.getBillionairById(billionair);
	}

	@Override
	public Billionair addBillionair(Billionair billionair) {
		return demoRepository.addBillionair(billionair);
	}

	@Override
	public boolean deleteBillionair(Integer billionairId) {
		return demoRepository.deleteBillionair(billionairId);
	}


}
