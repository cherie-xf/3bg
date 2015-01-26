--
-- Alter tables scripts from '3bdb'.
--
use 3bdb;
SET foreign_key_checks = 0;

ALTER TABLE `member` ADD `status` TINYINT NOT NULL DEFAULT '0' COMMENT '0.inactive 1.active 2.suspend' AFTER `modified_at`;
UPDATE `ticket_source_type` SET `type_name` = '用户账号及会员' WHERE `ticket_source_type`.`type_code` = 'RGST';

ALTER TABLE `service_order` ADD `sub_status_id` VARCHAR(4) NULL DEFAULT '00' AFTER `status_id`, ADD `processed_at` DATETIME NULL AFTER `sub_status_id`;

UPDATE `3bdb`.`recommend_level` SET `recommend_level_name` = '一星' WHERE `recommend_level`.`recommend_level_id` = 1;
UPDATE `3bdb`.`recommend_level` SET `recommend_level_name` = '二星' WHERE `recommend_level`.`recommend_level_id` = 2;
UPDATE `3bdb`.`recommend_level` SET `recommend_level_name` = '三星' WHERE `recommend_level`.`recommend_level_id` = 3;
INSERT INTO `3bdb`.`recommend_level` (`recommend_level_id`, `recommend_level_name`, `description`) VALUES ('4', '四星', NULL), ('5', '五星', NULL);


CREATE TABLE IF NOT EXISTS `3bdb`.`content_class` (
  `class_id` VARCHAR(16) NOT NULL,
  `class_name` VARCHAR(45) NULL,
  PRIMARY KEY (`class_id`))
ENGINE = InnoDB;

INSERT INTO `content_class` (`class_id`, `class_name`) VALUES
('CN01', 'News'),
('CN02', 'About'),
('CN03', 'Legal');


CREATE TABLE IF NOT EXISTS `3bdb`.`content` (
  `sid` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NULL,
  `brief` VARCHAR(255) NULL,
  `class_id` VARCHAR(16) NOT NULL,
  `uri` VARCHAR(255) NULL,
  `meta_keyword` VARCHAR(128) NULL,
  `meta_desc` VARCHAR(255) NULL,
  `status` varchar(4) DEFAULT '10',
  `created_at` DATETIME NULL,
  `created_by` INT NULL,
  `modified_at` DATETIME NULL,
  `modified_by` INT NULL,
  `is_deleted` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`sid`),
  INDEX `fk_content_content_class1_idx` (`class_id` ASC),
  CONSTRAINT `fk_content_content_class1`
    FOREIGN KEY (`class_id`)
    REFERENCES `3bdb`.`content_class` (`class_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET foreign_key_checks = 1;