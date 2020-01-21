GRANT ALL PRIVILEGES ON grocerymanagementapp.* TO 'groot'@'localhost';
USE grocerymanagementapp;
CREATE TABLE ITEM(
    id INT NOT NULL auto_increment, 
    name VARCHAR(200) NOT NULL,
    purchase_date DATE NOT NULL,
    storage_state VARCHAR(18) NULL DEFAULT NULL,
    shelf_life VARCHAR(30) NULL DEFAULT NULL,
    expiry_date DATE,
    PRIMARY KEY (id)
);

--------------------------------------------------

ALTER TABLE grocerymanagementapp.item
ADD date_today DATE;
--------------------------------------------------
ALTER TABLE grocerymanagementapp.item MODIFY COLUMN date_today DATE NOT NULL;

-------------------------------------------------
ALTER TABLE `grocerymanagementapp`.`item` 
CHANGE COLUMN `date_today` `purchase_date` DATE NOT NULL ;

ALTER TABLE `grocerymanagementapp`.`item` 
CHANGE COLUMN `expiry` `shelf_life` VARCHAR(30) NULL DEFAULT NULL ;


ALTER TABLE grocerymanagementapp.item
ADD expiry_date DATE;

ALTER TABLE grocerymanagementapp.item
ADD storage_state VARCHAR(18) NULL DEFAULT NULL;


---------------------------------------------------

CREATE TABLE `grocerymanagementapp`.`repository_items` (
  `rItemId` INT NOT NULL,
  `rtemName` VARCHAR(45) NOT NULL,
  `rDate_U_P` VARCHAR(45) NULL COMMENT 'Repository Item\'s UNOPENED PANTRY date',
  `rDate_O_P` VARCHAR(45) NULL COMMENT 'Repository Item\'s OPENED PANTRY date',
  `rDate_U_F` VARCHAR(45) NULL COMMENT 'Repository Item\'s UNOPENED Freezer date',
  `rDate_O_F` VARCHAR(45) NULL COMMENT 'Repository Item\'s OPENED Freezer date',
  `rDate_U_R` VARCHAR(45) NULL COMMENT 'Repository Item\'s UNOPENED REFRIGERATED date',
  `rDate_O_R` VARCHAR(45) NULL COMMENT 'Repository Item\'s OPENED REFRIGERATED date',
  PRIMARY KEY (`rItemId`));
  
  -----------------------------------------------
  CREATE TABLE `grocerymanagementapp`.`repo_items` (
  `r_item_id` INT(11) NOT NULL AUTO_INCREMENT,
  `r_item_name` VARCHAR(45) NOT NULL ,
  `r_refigerate_date` VARCHAR(45) NULL DEFAULT NULL ,
  `r_pantry_date` VARCHAR(45) NULL DEFAULT NULL ,
  `r_freeze_date` VARCHAR(45) NULL DEFAULT NULL ,
  PRIMARY KEY (`r_item_id`));
-----------------------------------------

  ALTER TABLE `grocerymanagementapp`.`item` 
CHANGE COLUMN `name` `name` VARCHAR(200) NOT NULL ;
-----------------------------------------
ALTER TABLE `grocerymanagementapp`.`item` 
ADD COLUMN `refrigerate_date` VARCHAR(45) NULL DEFAULT NULL AFTER `expiry_date`,
ADD COLUMN `pantry_date` VARCHAR(45) NULL DEFAULT NULL AFTER `refrigerate_date`,
ADD COLUMN `freezer_date` VARCHAR(45) NULL DEFAULT NULL AFTER `pantry_date`,
CHANGE COLUMN `shelf_life` `longevity` VARCHAR(30) NULL DEFAULT NULL ;

  