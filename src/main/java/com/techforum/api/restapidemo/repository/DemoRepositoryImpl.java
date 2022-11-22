package com.techforum.api.restapidemo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.techforum.api.restapidemo.beans.Billionair;

@Repository
public class DemoRepositoryImpl implements DemoRepository{

	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert simpleJdbcInsert;
	
	public DemoRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("billionaires").usingGeneratedKeyColumns("id");
	}

	@Override
	public List<Billionair> getAllBillionairs() {
		List<Billionair> persons = jdbcTemplate.query("SELECT * FROM BILLIONAIRES",
				(rs, rowNum) -> {
					Billionair b = new Billionair();
					b.setId(rs.getLong("id"));
					b.setFirstName(rs.getString("firstName"));
					b.setLastName(rs.getString("lastName"));
					b.setCareer(rs.getString("career"));
					return b;
		});
		return persons;
	}

	@Override
	public Billionair getBillionairById(Integer billionairId) {
		Billionair person = jdbcTemplate.queryForObject("SELECT * FROM BILLIONAIRES WHERE ID = ? ", new Object[] {billionairId}, 
				(rs, rowNum) -> {
					Billionair b = new Billionair();
					b.setId(rs.getLong("id"));
					b.setFirstName(rs.getString("firstName"));
					b.setLastName(rs.getString("lastName"));
					b.setCareer(rs.getString("career"));
					return b;
		});
		return person;
	}

	@Override
	public Billionair addBillionair(Billionair billionair) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("firstName", billionair.getFirstName());
		parameters.put("lastName", billionair.getLastName());
		parameters.put("career", billionair.getCareer());
		Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
		billionair.setId(new Long(String.valueOf(newId)));
		return billionair;
	}

	@Override
	public boolean deleteBillionair(Integer billionairId) {
		return jdbcTemplate.update("DELETE FROM BILLIONAIRES WHERE ID = ? ", billionairId) == 1;
	}

}
