--
-- Drop tables scripts from '3bdb'. Local dev environment use only.
--
use 3bdb;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS `business_category`;

DROP TABLE IF EXISTS `business_profile`;

DROP TABLE IF EXISTS `category`;

DROP TABLE IF EXISTS `category_tag`;

DROP TABLE IF EXISTS `city`;

DROP TABLE IF EXISTS `contact`;

DROP TABLE IF EXISTS `contact_type`;

DROP TABLE IF EXISTS `country`;

DROP TABLE IF EXISTS `history`;

DROP TABLE IF EXISTS `history_archive`;

DROP TABLE IF EXISTS `languages`;

DROP TABLE IF EXISTS `media`;

DROP TABLE IF EXISTS `member`;

DROP TABLE IF EXISTS `member_type`;

DROP TABLE IF EXISTS `recommend_level`;

DROP TABLE IF EXISTS `role`;

DROP TABLE IF EXISTS `service_product`;

DROP TABLE IF EXISTS `service_area`;

DROP TABLE IF EXISTS `service_order`;

DROP TABLE IF EXISTS `service_order_rate`;

DROP TABLE IF EXISTS `service_order_status`;

DROP TABLE IF EXISTS `service_status`;

DROP TABLE IF EXISTS `service_unit`;

DROP TABLE IF EXISTS `sys_user`;

DROP TABLE IF EXISTS `ticket_reply`;

DROP TABLE IF EXISTS `ticket`;

DROP TABLE IF EXISTS `ticket_source_type`;

DROP TABLE IF EXISTS `ticket_status`;

DROP TABLE IF EXISTS `user`;

DROP TABLE IF EXISTS `user_profile`;

DROP TABLE IF EXISTS `user_role`;

DROP TABLE IF EXISTS `service`;

DROP TABLE IF EXISTS `search_missed`;

DROP TABLE IF EXISTS `blog`;

DROP TABLE IF EXISTS `blog_status`;

DROP TABLE IF EXISTS `blog_class`;

DROP TABLE IF EXISTS `user_history`;

DROP TABLE IF EXISTS `sms_message`;

SET foreign_key_checks = 1;