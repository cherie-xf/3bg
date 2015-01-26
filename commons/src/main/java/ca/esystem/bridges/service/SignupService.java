package ca.esystem.bridges.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.esystem.bridges.domain.SignupForm;
import ca.esystem.framework.service.BasicService;

/**
 * 
 * @author Larry
 *
 */
public interface SignupService{
  public boolean clickFirstNext(SignupForm signupForm)throws Exception;
  public boolean submitSignupForm(SignupForm signupForm)throws Exception;
}
