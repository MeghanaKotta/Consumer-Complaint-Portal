package com.tickethandling.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.tickethandling.constants.Queries;
import com.tickethandling.dto.PieChartDataCountResponseDTO;

@Service
public class JdbcQueryService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<PieChartDataCountResponseDTO> getTheTicketsPieChartData(int depid, int orgid) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("depid", depid);
		parameters.put("orgid", orgid);
		return namedParameterJdbcTemplate.query(Queries.PIE_CHART_COUNT_QUERY, parameters,
				new BeanPropertyRowMapper<>(PieChartDataCountResponseDTO.class));
	}

}
