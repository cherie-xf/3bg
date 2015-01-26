SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `3bdb` ;
CREATE SCHEMA IF NOT EXISTS `3bdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `3bdb` ;

-- -----------------------------------------------------
-- Table `3bdb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`user` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(64) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `first_name` VARCHAR(20) NULL,
  `last_name` VARCHAR(20) NULL,
  `preferred_name` VARCHAR(45) NULL,
  `password` VARCHAR(128) NULL,
  `salt` VARCHAR(15) NULL,
  `enabled` TINYINT(1) NULL,
  `account_expired` DATETIME NULL,
  `account_locked` TINYINT(1) NULL,
  `credentials_expired` TINYINT(1) NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT false,
  `status` TINYINT NULL DEFAULT 0 COMMENT '0.inactive 1.active 2.locked',
  `user_type` TINYINT NULL DEFAULT 0 COMMENT '0.web user 1.mis user ',
  `created_by` INT NULL,
  `created_at` DATETIME NULL,
  `modified_by` INT NULL,
  `modified_at` DATETIME NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `3bdb`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`category` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`category` (
  `category_id` VARCHAR(16) NOT NULL,
  `category_name` VARCHAR(45) NULL,
  `english_name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `parent_id` VARCHAR(16) NULL,
  `leaf` TINYINT(1) NULL DEFAULT false,
  `enable` TINYINT(1) NULL DEFAULT true,
  `priority` INT NULL DEFAULT 20,
  `cssclass` VARCHAR(45) NULL,
  `created_at` DATETIME NULL,
  `created_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `modified_by` INT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`category_id`),
  INDEX `category_fk_parent_idx` (`parent_id` ASC),
  CONSTRAINT `category_fk_parent`
    FOREIGN KEY (`parent_id`)
    REFERENCES `3bdb`.`category` (`category_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_status` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_status` (
  `status_id` VARCHAR(8) NOT NULL,
  `status` VARCHAR(45) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_unit` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_unit` (
  `unit_id` VARCHAR(8) NOT NULL,
  `unit` VARCHAR(45) NULL,
  `description` VARCHAR(128) NULL,
  PRIMARY KEY (`unit_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`recommend_level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`recommend_level` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`recommend_level` (
  `recommend_level_id` INT NOT NULL,
  `recommend_level_name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`recommend_level_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`member_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`member_type` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`member_type` (
  `type_code` VARCHAR(8) NOT NULL,
  `type_name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `icon` VARCHAR(128) NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`member` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`member` (
  `member_id` VARCHAR(10) NOT NULL COMMENT 'Start with C, B or S, then plus 7 digitals of  number',
  `user_id` INT NULL,
  `type_code` VARCHAR(8) NULL,
  `credit` INT NULL,
  `valid_from` DATE NULL COMMENT '生效日期',
  `valid_to` DATE NULL COMMENT '失效日期',
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `status` TINYINT NULL DEFAULT 0 COMMENT '0.inactive 1.active 2.suspend',
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`member_id`),
  INDEX `membership_fk_user_idx` (`user_id` ASC),
  INDEX `membership_fk_type_idx` (`type_code` ASC),
  CONSTRAINT `member_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `3bdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `member_fk_type`
    FOREIGN KEY (`type_code`)
    REFERENCES `3bdb`.`member_type` (`type_code`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_product` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_product` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `service_title` VARCHAR(255) NULL,
  `cover_img` VARCHAR(255) NULL COMMENT 'a thumb image of cover',
  `category_id` VARCHAR(16) NOT NULL,
  `member_id` VARCHAR(10) NOT NULL,
  `face_negotiable` TINYINT(1) NULL DEFAULT false,
  `basic_price` FLOAT NULL,
  `unit_price` FLOAT NULL,
  `unit_id` VARCHAR(8) NOT NULL,
  `tax_included` TINYINT(1) NULL,
  `gst_rate` FLOAT NULL,
  `pst_rate` FLOAT NULL,
  `description` TEXT NULL,
  `warrant` TEXT NULL,
  `status_id` VARCHAR(8) NOT NULL,
  `recommend_level_id` INT NOT NULL DEFAULT 0,
  `meta_keywords` VARCHAR(45) NULL,
  `meta_desc` VARCHAR(512) NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `modified_by` INT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  INDEX `service_fk_idx` (`category_id` ASC),
  PRIMARY KEY (`service_id`),
  INDEX `status_id_fk_idx` (`status_id` ASC),
  INDEX `fk_service_service_unit1_idx` (`unit_id` ASC),
  INDEX `fk_service_recommend_level1_idx` (`recommend_level_id` ASC),
  INDEX `fk_service_member1_idx` (`member_id` ASC),
  CONSTRAINT `service_fk_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `3bdb`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `service_fk_status`
    FOREIGN KEY (`status_id`)
    REFERENCES `3bdb`.`service_status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_service_unit1`
    FOREIGN KEY (`unit_id`)
    REFERENCES `3bdb`.`service_unit` (`unit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_recommend_level1`
    FOREIGN KEY (`recommend_level_id`)
    REFERENCES `3bdb`.`recommend_level` (`recommend_level_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `3bdb`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_order_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_order_status` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_order_status` (
  `status_id` VARCHAR(4) NOT NULL,
  `status_name` VARCHAR(45) NULL,
  `description` VARCHAR(256) NULL,
  `icon` VARCHAR(256) NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_order` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_order` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `service_id` INT NOT NULL,
  `service_title` VARCHAR(255) NULL,
  `cover_img` VARCHAR(255) NULL COMMENT 'a thumb image of cover',
  `face_negotiable` TINYINT(1) NULL DEFAULT false,
  `basic_price` FLOAT NULL,
  `unit_price` FLOAT NULL,
  `unit_id` VARCHAR(10) NULL,
  `unit_quantity` INT NULL,
  `tax_included` TINYINT(1) NULL DEFAULT 0,
  `gst_rate` FLOAT NULL,
  `pst_rate` FLOAT NULL,
  `warrant` TEXT NULL,
  `business_member_id` VARCHAR(10) NULL,
  `business_name` VARCHAR(255) NULL,
  `requirement` TEXT NULL,
  `user_id` INT NULL,
  `customer_member_id` VARCHAR(10) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `cell` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `postcode` VARCHAR(7) NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `status_id` VARCHAR(4) NOT NULL,
  `sub_status_id` VARCHAR(4) NULL DEFAULT '00',
  `processed_at` DATETIME NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`order_id`),
  INDEX `order_fk_service_idx` (`service_id` ASC),
  INDEX `fk_order_order_status1_idx` (`status_id` ASC),
  CONSTRAINT `order_fk_service`
    FOREIGN KEY (`service_id`)
    REFERENCES `3bdb`.`service_product` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_order_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `3bdb`.`service_order_status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`role` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`role` (
  `role_id` INT NOT NULL,
  `rolename` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`user_role` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `role_fk_idx` (`role_id` ASC),
  CONSTRAINT `user_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `3bdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `3bdb`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`ticket_source_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`ticket_source_type` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`ticket_source_type` (
  `type_code` VARCHAR(5) NOT NULL,
  `type_name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`ticket_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`ticket_status` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`ticket_status` (
  `status_code` VARCHAR(5) NOT NULL,
  `status_name` VARCHAR(45) NULL,
  `status_desc` VARCHAR(256) NULL,
  `priority` INT NULL DEFAULT 5,
  PRIMARY KEY (`status_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`ticket`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`ticket` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`ticket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `user_id` INT NULL,
  `status_code` VARCHAR(4) NOT NULL,
  `type_code` VARCHAR(8) NOT NULL,
  `source_id` INT NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `close_by` INT NULL,
  `close_at` TIMESTAMP NULL,
  `processor_id` INT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `ticket_fk_source_idx` (`type_code` ASC),
  INDEX `fk_ticket_ticket_status1_idx` (`status_code` ASC),
  INDEX `ticket_fk_user_idx` (`user_id` ASC),
  CONSTRAINT `ticket_fk_source`
    FOREIGN KEY (`type_code`)
    REFERENCES `3bdb`.`ticket_source_type` (`type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ticket_ticket_status1`
    FOREIGN KEY (`status_code`)
    REFERENCES `3bdb`.`ticket_status` (`status_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`contact_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`contact_type` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`contact_type` (
  `type_code` VARCHAR(8) NOT NULL,
  `type_name` VARCHAR(45) NULL,
  `type_class` VARCHAR(8) NULL COMMENT '\'PN\':Telephone\n\'EM\':Email\n\'SC\':Social Media ' ,  
  `description` TEXT NULL,
  `icon` VARCHAR(128) NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`contact` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type_code` VARCHAR(8) NULL,
  `contact_value` VARCHAR(1024) NULL,
  `user_id` INT NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL,
  `modified_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `type_id_fk_idx` (`type_code` ASC),
  INDEX `contact_fk_user_idx` (`user_id` ASC),
  CONSTRAINT `contact_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `3bdb`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `contact_fk_type`
    FOREIGN KEY (`type_code`)
    REFERENCES `3bdb`.`contact_type` (`type_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`history` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `component_workflow_id` INT NULL,
  `workflow_state_id` INT NULL,
  `history_key` INT NULL,
  `history_value` TEXT NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `is_archived` TINYINT(1) NULL DEFAULT false,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`ticket_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`ticket_order` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`ticket_order` (
  `ticket_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`ticket_id`, `order_id`),
  INDEX `order_id_fk_idx` (`order_id` ASC),
  CONSTRAINT `ticket_order_fk_ticket`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `3bdb`.`ticket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ticket_order_fk_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `3bdb`.`service_order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_order_rate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_order_rate` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_order_rate` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NULL,
  `attitude` FLOAT NULL,
  `time` FLOAT NULL,
  `price` FLOAT NULL,
  `quality` FLOAT NULL,
  `comment` TEXT NULL,
  `rated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `rate_fk_order_idx` (`order_id` ASC),
  CONSTRAINT `rate_fk_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `3bdb`.`service_order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`media`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`media` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`media` (
  `media_id` INT NOT NULL AUTO_INCREMENT,
  `thumb` VARCHAR(256) NULL,
  `bigpic` VARCHAR(256) NULL,
  `title` VARCHAR(128) NULL,
  `ext_name` VARCHAR(5) NULL,
  `description` VARCHAR(255) NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL,
  `modified_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT false,
  PRIMARY KEY (`media_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`media_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`media_type` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`media_type` (
  `type_code` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(1024) NULL,
  PRIMARY KEY (`type_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`country` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`country` (
  `country_code` VARCHAR(8) NOT NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`country_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`city` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`city` (
  `city_code` VARCHAR(8) NOT NULL,
  `city` VARCHAR(45) NULL,
  `country_code` VARCHAR(8) NULL,
  `priority` INT NULL DEFAULT 10,
  PRIMARY KEY (`city_code`),
  INDEX `city_fk_country_idx` (`country_code` ASC),
  CONSTRAINT `city_fk_country`
    FOREIGN KEY (`country_code`)
    REFERENCES `3bdb`.`country` (`country_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_area` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_area` (
  `service_id` INT NOT NULL,
  `city_code` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`service_id`, `city_code`),
  INDEX `fk_service_area_city1_idx` (`city_code` ASC),
  CONSTRAINT `service_area_fk_vc`
    FOREIGN KEY (`service_id`)
    REFERENCES `3bdb`.`service_product` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_area_city1`
    FOREIGN KEY (`city_code`)
    REFERENCES `3bdb`.`city` (`city_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `3bdb`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`address` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `address` TEXT NULL,
  `district` VARCHAR(45) NULL,
  `city_code` VARCHAR(8) NULL,
  `postal_code` VARCHAR(45) NULL,
  `user_id` INT NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL,
  `modified_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`address_id`),
  INDEX `address_fk_city_idx` (`city_code` ASC),
  INDEX `address_fk_user_idx` (`user_id` ASC),
  CONSTRAINT `address_fk_city`
    FOREIGN KEY (`city_code`)
    REFERENCES `3bdb`.`city` (`city_code`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `address_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `3bdb`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`business_profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`business_profile` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`business_profile` (
  `member_id` VARCHAR(10) NOT NULL,
  `business_number` VARCHAR(45) NULL,
  `business_name` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `owner_name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `tax_number` VARCHAR(45) NULL,
  `wcb` VARCHAR(45) NULL,
  `business_start` DATE NULL,
  `logo` VARCHAR(255) NULL,
  `support_doc` VARCHAR(255) NULL,
  `agree_term` TINYINT(1) NULL,
  `recommend_level_id` INT NOT NULL,
  `publication` TINYINT(1) NULL DEFAULT 0,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  `is_person` TINYINT(1) NULL DEFAULT 0 COMMENT '0.personal 1.entreprenier',
  `website` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `wechat` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  `quick_respond` TINYINT(1) NULL,
  `referal_info` VARCHAR(1024) NULL,
  `contract_date` DATE NULL,
  INDEX `fk_business_profile_member1_idx` (`member_id` ASC),
  UNIQUE INDEX `member_id_UNIQUE` (`member_id` ASC),
  PRIMARY KEY (`member_id`),
  INDEX `fk_business_profile_recommend_level1_idx` (`recommend_level_id` ASC),
  CONSTRAINT `fk_business_profile_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `3bdb`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_business_profile_recommend_level1`
    FOREIGN KEY (`recommend_level_id`)
    REFERENCES `3bdb`.`recommend_level` (`recommend_level_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`business_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`business_category` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`business_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(10) NOT NULL,
  `category_id` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_business_category_category1_idx` (`category_id` ASC),
  INDEX `fk_business_category_business_profile1_idx` (`member_id` ASC),
  CONSTRAINT `fk_business_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `3bdb`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_business_category_business_profile1`
    FOREIGN KEY (`member_id`)
    REFERENCES `3bdb`.`business_profile` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `3bdb`.`languages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`languages` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`languages` (
  `language_code` VARCHAR(8) NOT NULL,
  `language_name` VARCHAR(45) NULL,
  `icon` VARCHAR(128) NULL,
  PRIMARY KEY (`language_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`recovery_token`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`recovery_token` ;

CREATE  TABLE IF NOT EXISTS `3bdb`.`recovery_token` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `token` VARCHAR(128) NOT NULL ,
  `user_id` INT NOT NULL ,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  INDEX `token_fk_user_idx` (`user_id` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `token_fk_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `3bdb`.`user` (`user_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `3bdb`.`category_tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`category_tag` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`category_tag` (
  `tag_id` INT NOT NULL AUTO_INCREMENT,
  `tag` VARCHAR(45) NULL,
  `category_id` VARCHAR(16) NULL,
  `created_at` TIMESTAMP NULL,
  `created_by` INT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`tag_id`),
  INDEX `tag_fk_category_idx` (`category_id` ASC),
  CONSTRAINT `tag_fk_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `3bdb`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`search_missed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`search_missed` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`search_missed` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `keyword` VARCHAR(255) NULL,
  `searched_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NULL,
  `user_ip_address` VARCHAR(45) NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `search_missed_fk_user_idx` (`user_id` ASC),
  CONSTRAINT `search_missed_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `3bdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;






-- -----------------------------------------------------
-- Table `3bdb`.`Ticket_Reply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`ticket_reply` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`ticket_reply` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reply_msg` TEXT NULL,
  `reply_by` INT NULL,
  `replier_name` VARCHAR(45) NULL,
  `reply_time` TIMESTAMP NULL,
  `ticket_id` INT NOT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_Ticket_Reply_ticket1_idx` (`ticket_id` ASC),
  CONSTRAINT `fk_Ticket_Reply_ticket1`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `3bdb`.`ticket` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`history_archive`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`history_archive` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`history_archive` (
  `id` INT NOT NULL,
  `component_workflow_id` INT NULL,
  `workflow_state_id` INT NULL,
  `history_key` INT NULL,
  `history_value` TEXT NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL,
  `archived_by` INT NULL,
  `archived_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`service_schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`service_schedule` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`service_schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `servicetime` DATETIME NULL,
  `selected` TINYINT(1) NULL DEFAULT false,
  PRIMARY KEY (`id`),
  INDEX `fk_service_schedule_service_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_service_schedule_service_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `3bdb`.`service_order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`user_profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`user_profile` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`user_profile` (
  `user_id` INT NOT NULL,
  `icon_url` VARCHAR(512) NULL,
  `description` TEXT NULL,
  `gender` CHAR NULL COMMENT 'M: male,  F:female',
  `birthday` DATE NULL,
  `blood_type` VARCHAR(2) NULL COMMENT 'A,B,AB,O',
  `preferred_language` VARCHAR(8) NULL,
  `passport_number` VARCHAR(45) NULL,
  `chinese_id_number` VARCHAR(45) NULL,
  `driver_license` VARCHAR(45) NULL,
  `profession` TEXT NULL,
  `health` TEXT NULL,
  `family_info` TEXT NULL,
  `car_info` TEXT NULL,
  `pet_info` TEXT NULL,
  `property_info` TEXT NULL,
  `news_letter` TINYINT(1) NULL DEFAULT 1,
  `sms_contact` TINYINT(1) NULL DEFAULT 1,
  `additional` TEXT NULL,
  `created_at` TIMESTAMP NULL,
  `created_by` INT NULL,
  `modified_at` TIMESTAMP NULL,
  `modified_by` INT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  INDEX `user_fk_preferred_lang_idx` (`preferred_language` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `profile_fk_preferred_lang0`
    FOREIGN KEY (`preferred_language`)
    REFERENCES `3bdb`.`languages` (`language_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `profile_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `3bdb`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `3bdb`.`blog_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`blog_status` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`blog_status` (
  `status_code` VARCHAR(5) NOT NULL,
  `status_name` VARCHAR(45) NOT NULL,
  `status_desc` VARCHAR(256) NULL,
  `priority` INT NULL,
  PRIMARY KEY (`status_code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`blog_class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`blog_class` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`blog_class` (
  `class_id` VARCHAR(5) NOT NULL,
  `class_name` VARCHAR(45) NULL,
  PRIMARY KEY (`class_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`blog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`blog` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`blog` (
  `blog_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(128) NULL,
  `content` TEXT NULL,
  `meta_keyword` VARCHAR(256) NULL,
  `meta_dec` VARCHAR(256) NULL,
  `class_id` VARCHAR(5) NOT NULL,
  `blog_status` VARCHAR(5) NOT NULL DEFAULT 10,
  `created_at` TIMESTAMP NULL,
  `created_by` INT NOT NULL,
  `modified_at` TIMESTAMP NULL,
  `modified_by` INT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`blog_id`),
  UNIQUE INDEX `blog_id_UNIQUE` (`blog_id` ASC),
  INDEX `fk_blog_blog_status_idx` (`blog_status` ASC),
  INDEX `fk_blog_blog_class_idx` (`class_id` ASC),
  CONSTRAINT `fk_blog_blog_status`
    FOREIGN KEY (`blog_status`)
    REFERENCES `3bdb`.`blog_status` (`status_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_blog_blog_class`
    FOREIGN KEY (`class_id`)
    REFERENCES `3bdb`.`blog_class` (`class_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `3bdb`.`user_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `3bdb`.`user_history` (
  `history_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `content` VARCHAR(512) NULL,
  `creater_name` VARCHAR(45) NULL,
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`history_id`),
  INDEX `user_history_fk_user_idx` (`user_id` ASC),
  CONSTRAINT `user_history_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `3bdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`init_business_profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`init_business_profile` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`init_business_profile` (
  `member_id` VARCHAR(10) NOT NULL,
  `business_number` VARCHAR(45) NULL,
  `business_name` VARCHAR(255) NULL,
  `description` TEXT NULL,
  `owner_name` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `tax_number` VARCHAR(45) NULL,
  `wcb` VARCHAR(45) NULL,
  `business_start` DATE NULL,
  `logo` VARCHAR(255) NULL,
  `support_doc` VARCHAR(255) NULL,
  `agree_term` TINYINT(1) NULL,
  `recommend_level_id` INT NOT NULL,
  `publication` TINYINT(1) NULL DEFAULT 0,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  `is_person` TINYINT(1) NULL DEFAULT 0 COMMENT '0.personal 1.entreprenier',
  `created_by` INT NULL,
  `created_at` TIMESTAMP NULL,
  `ip_address` VARCHAR(45) NULL,
  `website` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `address` VARCHAR(255) NULL,
  `wechat` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  `quick_respond` TINYINT(1) NULL,
  `referal_info` VARCHAR(1024) NULL,
  `contract_date` DATE NULL,
  INDEX `fk_init_business_profile_member1_idx` (`member_id` ASC),
  UNIQUE INDEX `init_member_id_UNIQUE` (`member_id` ASC),
  PRIMARY KEY (`member_id`),
  INDEX `fk_init_business_profile_recommend_level1_idx` (`recommend_level_id` ASC),
  CONSTRAINT `fk_init_business_profile_recommend_level1`
    FOREIGN KEY (`recommend_level_id`)
    REFERENCES `3bdb`.`recommend_level` (`recommend_level_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `3bdb`.`business_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `3bdb`.`init_business_category` ;

CREATE TABLE IF NOT EXISTS `3bdb`.`init_business_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `member_id` VARCHAR(10) NOT NULL,
  `category_id` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_init_business_category_category1_idx` (`category_id` ASC),
  INDEX `fk_init_business_category_business_profile1_idx` (`member_id` ASC),
  CONSTRAINT `fk_init_business_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `3bdb`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_init_business_category_business_profile1`
    FOREIGN KEY (`member_id`)
    REFERENCES `3bdb`.`init_business_profile` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

--
-- sequence generator
--
DROP TABLE IF EXISTS `3bdb`.`sys_seq` ;
CREATE  TABLE IF NOT EXISTS `3bdb`.`sys_seq` (
  `seq_name` VARCHAR(4) NOT NULL ,
  `seq_value` INT NOT NULL ,
  PRIMARY KEY (`seq_name`) )
ENGINE = InnoDB;

insert into sys_seq(seq_name,seq_value) values ('B',1000);
insert into sys_seq(seq_name,seq_value) values ('C',1000);
insert into sys_seq(seq_name,seq_value) values ('S',1000);

DROP FUNCTION IF EXISTS getNextSeq;
DELIMITER $$
create function getNextSeq(seqName varchar(4)) returns int unsigned
begin
    update sys_seq set seq_value = (@next_value:= seq_value+1) where seq_name = seqName;
    return @next_value;
end;
$$
DELIMITER ;

DROP TABLE IF EXISTS `3bdb`.`sms_message` ;
CREATE TABLE IF NOT EXISTS `3bdb`.`sms_message` (
  `phone` varchar(20) NOT NULL,
  `message_code` int(11) NOT NULL,
  `message_text` text NOT NULL,
  `created_at` datetime DEFAULT NULL
) ENGINE=InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
