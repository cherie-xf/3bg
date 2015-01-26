package ca.esystem.bridges.dao;

import org.springframework.stereotype.Repository;
import ca.esystem.bridges.domain.RecoveryToken;

@Repository
public interface RecoveryTokenDao {
  public RecoveryToken queryRecoveryToken(RecoveryToken recoverytoken);
  public int insertRecoveryToken(RecoveryToken recoverytoken);
}
