package ca.esystem.bridges.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ca.esystem.framework.util.CommonUtil;
import ca.esystem.bridges.domain.SignupForm;
import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.Business_Category;
import ca.esystem.bridges.domain.RecoveryToken;
import ca.esystem.bridges.service.SignupService;
import ca.esystem.bridges.sysio.EmailSender;
import ca.esystem.bridges.dao.UserDao;
import ca.esystem.bridges.dao.UserProfileDao;
import ca.esystem.bridges.dao.MembershipDao;
import ca.esystem.bridges.dao.AddressDao;
import ca.esystem.bridges.dao.ContactDao;
import ca.esystem.bridges.dao.SysSequenceDao;
import ca.esystem.bridges.dao.TicketDao;
import ca.esystem.bridges.dao.RecoveryTokenDao;

/**
 * Process Signup Form
 * 
 * @author Larry
 *
 */

@Service("SignupService")
public class SignupServiceImpl implements SignupService {
	@Resource
	private UserDao userDao;

	@Resource
	private UserProfileDao userProfileDao;

	@Resource
	private MembershipDao membershipDao;

	@Resource
	private AddressDao addressDao;

	@Resource
	private ContactDao contactDao;

	@Resource
	private SysSequenceDao sysSequenceDao;

	@Resource
	private TicketDao ticketDao;
	
	@Resource
	private RecoveryTokenDao recoveryTokenDao;

	@Resource(name = "GlobalProperties")
	private Properties globalproperties;

    @Resource(name = "EmailSender")
    private EmailSender emailSender;
	
	private BCryptPasswordEncoder encoder;

	public SignupServiceImpl() {
		encoder = new BCryptPasswordEncoder();
	}

	public boolean clickFirstNext(SignupForm signupForm)throws IllegalStateException, IOException{
		boolean result = false;
		String email = signupForm.getUser().getEmail();
		if(CommonUtil.isNotEmptyStr(email)){
			signupForm.getBusinessProfile().setEmail(email);
		}
		
		String phone = signupForm.getUser().getPhone();
		if(CommonUtil.isNotEmptyStr(phone)){
			signupForm.getBusinessProfile().setPhone(phone);
		}

		String ownername = signupForm.getUser().getFirstName()+" "+signupForm.getUser().getLastName();
		if(CommonUtil.isNotEmptyStr(ownername)){
			signupForm.getBusinessProfile().setOwner_name(ownername);
		}	
				
		String wechat = signupForm.getContact().getContact_value();
		if(CommonUtil.isNotEmptyStr(wechat)){
			signupForm.getBusinessProfile().setWechat(wechat);
		}
		result = true;
		return result;
	}
	
	public boolean submitSignupForm(SignupForm signupForm)
			throws IllegalStateException, IOException {
		boolean result = false;

		String password = encoder.encode(signupForm.getUser().getPassword());
		signupForm.getUser().setPassword(password);
		signupForm.getUser().setCreated_at(new Date());
		userDao.insert(signupForm.getUser());
		int userid = signupForm.getUser().getId();
		signupForm.getUserProfile().setCreatedAt(new Date());
		signupForm.getUserProfile().setCreatedBy(userid);
		signupForm.getUserProfile().setUserId(userid);
		userProfileDao.insert(signupForm.getUserProfile());

		if (CommonUtil
				.isNotEmptyStr(signupForm.getContact().getContact_value())) {
			signupForm.getContact().setCreated_by(userid);
			signupForm.getContact().setCreated_at(new Date());
			signupForm.getContact().setUser_id(userid);
			contactDao.insert(signupForm.getContact());
		}

		if (CommonUtil.isNotEmptyStr(signupForm.getAddress().getAddress())
				|| CommonUtil.isNotEmptyStr(signupForm.getAddress()
						.getPostal_code())) {
			signupForm.getAddress().setCreated_by(userid);
			signupForm.getAddress().setCreated_at(new Date());
			signupForm.getAddress().setUser_id(userid);
			addressDao.insert(signupForm.getAddress());
		}

		if (CommonUtil.isNotEmptyStr(signupForm.getMembership().getType_code())) {
			// automatically generate member id
			String membertype = signupForm.getMembership().getType_code();
			String seqName = membertype.substring(0,1);
			int seqValue = sysSequenceDao.queryNextVal(seqName);
			String memberNumber = CommonUtil.addZeroBeforeNumber(7, ""
					+ seqValue);
			String memberid = seqName + memberNumber;
			signupForm.getMembership().setMember_id(memberid);
			signupForm.getMembership().setCreated_by(userid);
			signupForm.getMembership().setCreated_at(new Date());
			signupForm.getMembership().setUser_id(userid);
			membershipDao.insert(signupForm.getMembership());

			// only business membership has business profile
			if (seqName.equalsIgnoreCase("B")) {
				// deal with attachment
				String rootdir = "";
				String os = System.getProperty("os.name").toLowerCase();
				if (os.indexOf("linux") >= 0 || os.indexOf("unix") >= 0
						|| os.indexOf("mac") >= 0) {
					rootdir = globalproperties
							.getProperty("DocumentRoot.Linux");
				} else {
					rootdir = globalproperties
							.getProperty("DocumentRoot.Windows");
				}
				String attachdir = globalproperties
						.getProperty("Folder.Attach");

				CommonsMultipartFile supportFile = signupForm
						.getBusinessProfile().getSupport_doc_file();
				if (supportFile != null && !supportFile.isEmpty()) {
					String fileName = supportFile.getOriginalFilename();
					String uploadFileName = rootdir + attachdir + "/" + userid
							+ "/" + fileName;
					File uploadFile = new File(uploadFileName);
					if (!uploadFile.exists()) {
						uploadFile.mkdirs();
					}
					supportFile.transferTo(uploadFile);
					signupForm.getBusinessProfile().setSupport_doc(
							attachdir + "/" + userid + "/" + fileName);
				}
				signupForm.getBusinessProfile().setMember_id(memberid);
				membershipDao.insertBusinessProfile(signupForm.getBusinessProfile());

				// deal with business category list
				String categoryids = signupForm.getBusinessProfile()
						.getCategoryids();
				if (CommonUtil.isNotEmptyStr(categoryids)) {
					String[] categoryArray = categoryids.split(",");
					for (int i = 0; i < categoryArray.length; i++) {
						if (CommonUtil.isNotEmptyStr(categoryArray[i])) {
							Business_Category category = new Business_Category();
							category.setCategory_id(categoryArray[i]);
							category.setMember_id(memberid);
							membershipDao.insertBusinessCategory(category);
						}
					}
				}
			}

			// send ticket of membership application to customer representatives
			Ticket memberApplication = new Ticket();
			String ticketTitle = "用户" + signupForm.getUser().getLastName()
					+ " " + signupForm.getUser().getFirstName() + "申请成为"
					+ signupForm.getMembership().getType_code() + "会员";
			memberApplication.setTitle(ticketTitle);
			String description = "会员ID: "
					+ signupForm.getMembership().getMember_id() + "<br>"
					+ "姓名: " + signupForm.getUser().getLastName()
					+ signupForm.getUser().getLastName() + "<br>" + "电话: "
					+ signupForm.getUser().getPhone() + "<br>" + "Email:"
					+ signupForm.getUser().getEmail() + "<br>" + "日期: "
					+ signupForm.getMembership().getCreated_at().toString();
			memberApplication.setDescription(description);
			memberApplication.setStatus_code("10");
			memberApplication.setType_code("RGST");
			memberApplication.setCreated_at(new Date());
			memberApplication.setCreated_by(userid);
			memberApplication.setUser_id(userid);
			memberApplication.setEmail(signupForm.getUser().getEmail());
			memberApplication.setPhone(signupForm.getUser().getPhone());
			ticketDao.insert(memberApplication);
		}

		//create the account activation code and send it to user's email
		RecoveryToken recoverytoken = new RecoveryToken();
		String plaincode = CommonUtil.getRandomString(6);
		String encycode = encoder.encode(plaincode);
		recoverytoken.setUser_id(userid);
		recoverytoken.setToken(encycode);
		recoverytoken.setCreated_at(new Date());
		recoveryTokenDao.insertRecoveryToken(recoverytoken);
		int id=recoverytoken.getId();
		String activeurl = globalproperties.getProperty("DomainName")+"/web/signup/activeaccount.html?id="+id
				         + "&token="+encycode;
		
		emailSender.sendRegisterMail(signupForm.getUser().getEmail(), signupForm.getUser().getFirstName(), "注册确认信", activeurl);

		result = true;
		return result;
	}
}
