package ca.esystem.bridges.service;

import ca.esystem.bridges.domain.RecoveryToken;

public interface RecoveryTokenService {
	  public RecoveryToken queryRecoveryToken(RecoveryToken recoverytoken);
	  public int insertRecoveryToken(RecoveryToken recoverytoken);
}
