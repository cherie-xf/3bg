insert into service_status(status_id,status,description) values
('10','草稿',NULL),
('20','上架',NULL),
('30','下架',NULL),
('40','删除',NULL);

insert into service_unit(unit_id,unit,description) values
('HOUR','每小时',NULL),
('DAY','每天',NULL),
('WEEK','每周',NULL),
('LB','每磅',NULL),
('KG','每千克',NULL),
('TONE','每吨',NULL),
('KM','每千米',NULL),
('SQFT','每平方尺',NULL),
('SQM','每平方米',NULL);

INSERT INTO `recommend_level` (`recommend_level_id`, `recommend_level_name`, `description`) VALUES
('0','无',NULL),
('1','低',NULL),
('2','中',NULL),
('3','高',NULL);

INSERT INTO `member_type` (`type_code`, `type_name`, `description`, `icon`) VALUES
('B1','普通服务商',NULL,NULL),
('B2','注册服务商',NULL,NULL),
('B3','认证服务商',NULL,NULL),
('C1','普通会员',NULL,NULL),
('C2','VIP会员',NULL,NULL),
('S1','普通客服',NULL,NULL),
('S2','客服主管',NULL,NULL),
('S3','系统管理员',NULL,NULL);

insert into service_order_status values
('10','待Bid',NULL,NULL),
('20','已Bid',NULL,NULL),
('30','已Unbid',NULL,NULL),
('40','已完成',NULL,NULL),
('50','已取消',NULL,NULL),
('60','已评估',NULL,NULL);

INSERT INTO `ticket_source_type` (`type_code`, `type_name`, `description`) VALUES
('EMAIL', '客户邮件', NULL),
('ORDER', '客户订单', NULL),
('PHONE', '客户来电', NULL);

INSERT INTO `ticket_status` (`status_code`, `status_name`, `status_desc`, `priority`) VALUES
('10', '等待处理', NULL, 1),
('20', '正在处理', NULL, 2),
('30', '主管处理', NULL, 3),
('40', '处理完成', NULL, 4),
('50', '取消处理', NULL, 5);

INSERT INTO `contact_type` (`type_code`, `type_name`, `type_class`,`description`, `icon`) VALUES
('10EM', 'Email', 'EM', NULL,'/images/icon-social-mail-24.png'),
('15CL', 'Cell', 'PN', NULL,'/images/icon-social-viber-24.png'),
('20PN', 'Phone', 'PN', NULL,'/images/icon-social-phone-24.png'),
('25FX', 'Fax', 'PN', NULL,'/images/icon-social-print-24.png'),
('30WC', 'WeChat', 'SC', NULL,'/images/icon-social-wechat-24.png'),
('35QQ', 'QQ', 'SC', NULL,'/images/icon-social-qq-24.png'),
('40FB', 'FaceBook', 'SC', NULL,'/images/icon-social-facebook-24.png');

INSERT INTO `country` (`country_code`, `country`) VALUES
('CAN','Canada'),
('CHN','China'),
('USA','United State');

INSERT INTO `city` (`city_code`, `city`, `country_code`, `priority`) VALUES
('CT01', 'Vancouver', 'CAN', 10),
('CT02', 'Richmond', 'CAN', 10),
('CT03', 'Burnaby', 'CAN', 10),
('CT04', 'New West', 'CAN', 10),
('CT05', 'Surrey', 'CAN', 10),
('CT06', 'Delta', 'CAN', 10),
('CT07', 'Coquitlam', 'CAN', 10),
('CT08', 'Port Coquitlam', 'CAN', 10),
('CT09', 'Port Moody', 'CAN', 10),
('CT10', 'West Vancouver', 'CAN', 10),
('CT11', 'North Vancouver', 'CAN', 10),
('CT12', 'White Rock', 'CAN', 10),
('CT13', 'Maple Ridge', 'CAN', 10),
('CT14', 'Langly', 'CAN', 10),
('CT15', 'Fraser Valley Area', 'CAN', 10),
('CT16', 'Sunshine Coast', 'CAN', 10);

INSERT INTO `languages` (`language_code`, `language_name`) VALUES
('10MAN', '国语'),
('20CAN', '粤语'),
('30ENG', 'English');

