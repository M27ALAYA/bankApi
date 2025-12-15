package com.dlx.bnk.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class Audit implements AuditorAware<String>  {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
	 return Optional.of("SYSTEM");

	}

}
