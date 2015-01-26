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
('EMAL', '客户邮件', NULL),
('ORDR', '客户订单', NULL),
('PHNE', '客户来电', NULL),
('RGST', '用户账号及会员', NULL);


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

--
-- Dumping data for table `blog_status`
--
INSERT INTO `blog_status` (`status_code`, `status_name`, `status_desc`, `priority`) VALUES
('10', '草稿', NULL, NULL),
('20', '发布', NULL, NULL),
('30', '不发布', NULL, NULL);

INSERT INTO `blog_class` (`class_id`, `class_name`) VALUES
('1', '生活'),
('2', '娱乐');

--
-- initialize admin account
--
INSERT INTO `user` (`user_id`,`email`, `phone`, `first_name`, `last_name`, `preferred_name`, `password`, `salt`, `enabled`, `account_expired`, `account_locked`, `credentials_expired`, `is_deleted`, `status`, `user_type`, `created_by`, `created_at`) VALUES
(1,'admin@3bridges.net', '6047778888', 'Qiao', 'San', '', '$2a$10$so1RkGcuarKYC4kHLYjI9uKwUBfk1i2CKcoHBTQtANVcSongNZYji', NULL, 1, NULL, 0, 0, 0, 1, 0, 1, '2014-12-30 11:05:01');


INSERT INTO `category` (`category_id`, `category_name`, `english_name`, `description`, `parent_id`, `leaf`, `enable`, `priority`, `cssclass`, `created_at`, `created_by`, `modified_at`, `modified_by`, `is_deleted`) VALUES
('01', '日常生活', 'Living', '', NULL, 0, 1, 20, '', '2014-12-01 11:42:12', 1, '2014-12-31 00:43:12', 1, 0),
('0101', '窗帘缝纫', '', '', '01', 0, 1, 20, '', '2014-12-01 11:43:20', 1, '2014-12-31 00:47:56', 1, 0),
('010101', '布艺', '', '', '0101', 1, 1, 20, '', '2014-12-01 11:43:40', 1, '2014-12-31 00:52:03', 1, 0),
('010102', '罗马杆', '', '', '0101', 1, 1, 20, '', '2014-12-01 11:43:56', 1, '2014-12-31 00:52:30', 1, 0),
('010103', '滑轨', '', '', '0101', 1, 1, 20, '', '2014-12-30 16:53:06', 1, '2014-12-31 00:53:48', 1, 0),
('010104', '百叶窗', '', '', '0101', 1, 1, 20, '', '2014-12-30 16:53:19', 1, '2014-12-31 00:53:54', 1, 0),
('0102', '家庭旅馆', '', '', '01', 0, 1, 20, '', '2014-12-01 11:44:13', 1, '2014-12-31 00:48:17', 1, 0),
('010201', '住宿', '', '', '0102', 1, 1, 20, '', '2014-12-01 11:44:29', 1, '2014-12-31 00:54:51', 1, 0),
('010202', '民宿', '', '', '0102', 1, 1, 20, '', '2014-12-01 11:44:44', 1, '2014-12-31 00:56:35', 1, 0),
('0103', '宠物相关', NULL, NULL, '01', 0, 1, 20, NULL, '2014-12-30 16:48:44', 1, NULL, NULL, 0),
('010301', '动物医疗', NULL, NULL, '0103', 0, 1, 20, NULL, '2014-12-30 16:56:18', 1, NULL, NULL, 0),
('0104', '婚庆服务', NULL, NULL, '01', 0, 1, 20, NULL, '2014-12-30 16:49:09', 1, NULL, NULL, 0),
('0105', '外币汇兑', NULL, NULL, '01', 0, 1, 20, NULL, '2014-12-30 16:49:54', 1, NULL, NULL, 0),
('0106', '画框装裱', NULL, NULL, '01', 0, 1, 20, NULL, '2014-12-30 16:50:10', 1, NULL, NULL, 0),
('0107', '接送服务', NULL, NULL, '01', 0, 1, 20, NULL, '2014-12-30 16:50:21', 1, NULL, NULL, 0),
('0108', '搬运卸货', NULL, NULL, '01', 0, 1, 20, NULL, '2014-12-30 16:50:37', 1, NULL, NULL, 0),
('010801', '搬家', '', '', '0108', 1, 1, 20, '', '2014-12-30 17:01:21', 1, '2014-12-31 01:02:02', 1, 0),
('0109', '家居清洁', NULL, NULL, '01', 0, 1, 20, NULL, '2014-12-30 16:50:48', 1, NULL, NULL, 0),
('02', '休闲娱乐', '', '', NULL, 0, 1, 20, '', '2014-12-01 11:42:27', 1, '2014-12-31 00:43:57', 1, 0),
('03', '教育培训', '', '', NULL, 0, 1, 20, '', '2014-12-01 11:43:05', 1, '2014-12-31 00:45:09', 1, 0),
('04', '汽车相关', 'car service', '', NULL, 0, 1, 20, '', '2014-12-01 11:46:17', 1, '2014-12-31 00:46:04', 1, 0),
('05', '房屋相关', '', '', NULL, 0, 1, 20, '', '2014-12-04 15:50:47', 1, '2014-12-31 00:46:29', 1, 0),
('06', '商业服务', 'Business', '', NULL, 0, 1, 20, '', '2014-12-04 15:51:03', 1, '2014-12-31 00:46:34', 1, 0);
