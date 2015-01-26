package ca.esystem.bridges.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ca.esystem.bridges.domain.RecoveryToken;
import ca.esystem.bridges.service.RecoveryTokenService;
import ca.esystem.bridges.dao.RecoveryTokenDao;

@Service("RecoveryTokenService")
public class RecoveryTokenServiceImpl implements RecoveryTokenService{
	
	@Resource
	RecoveryTokenDao recoveryTokenDao;
	
	public RecoveryToken queryRecoveryToken(RecoveryToken recoverytoken) {
		return recoveryTokenDao.queryRecoveryToken(recoverytoken);
	}

	public int insertRecoveryToken(RecoveryToken recoverytoken) {
		return recoveryTokenDao.insertRecoveryToken(recoverytoken);
	}
}
