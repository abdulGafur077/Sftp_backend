CREATE TABLE AuthenticationDetails (
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT ,
	username varchar(200) NOT NULL,
    password varchar(200) NOT NULL
);

CREATE TABLE AuditTrail 
(
	id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	module varchar(1000) NOT NULL,
	message LONGTEXT NOT NULL,
	performedBy varchar(1000) NOT NULL,
	actionPerformed varchar(1000) NOT NULL,
	auditValue LONGTEXT NULL,
	actionTime datetime NOT NULL
);

CREATE TABLE applicationdetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  appName varchar(200) NOT NULL,
  shortName varchar(100) NOT NULL,
  description varchar(200) DEFAULT NULL,
  isActive char(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);


CREATE TABLE capabilitiesdetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  shortName varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  isActive char(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);


CREATE TABLE divisiondetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  divName varchar(200) NOT NULL,
  shortName varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  isActive char(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE roledetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  roleName varchar(200) NOT NULL,
  shortName varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  isActive char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
);

CREATE TABLE userdetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  firstName varchar(200) DEFAULT NULL,
  lastName varchar(200) DEFAULT NULL,
  userName varchar(200) DEFAULT NULL,
  divisionId int(11) DEFAULT NULL,
  isActive char(1) DEFAULT NULL,
  isDeleted char(1) DEFAULT NULL,
  createdon datetime DEFAULT NULL,
  createdby int(11) DEFAULT NULL,
  updatedon datetime DEFAULT NULL,
  updatedby int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY userName_UNIQUE (userName),
  KEY fk_division_id_idx (divisionId),
  CONSTRAINT fk_division_id FOREIGN KEY (divisionId) REFERENCES divisiondetails (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE usergroupdetails (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(200) NOT NULL,
  shortName varchar(100) NOT NULL,
  description varchar(2000) DEFAULT NULL,
  isActive char(1) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE mappingusergrouprole (
  id int(11) NOT NULL AUTO_INCREMENT,
  usergroupId int(11) NOT NULL,
  roleId int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_ugr_usergroup_id_idx (usergroupId),
  KEY fk_ugr_role_id_idx (roleId),
  CONSTRAINT fk_ugr_role_id FOREIGN KEY (roleId) REFERENCES roledetails (id) ON UPDATE CASCADE,
  CONSTRAINT fk_ugr_usergroup_id FOREIGN KEY (usergroupId) REFERENCES usergroupdetails (id) ON UPDATE CASCADE
);

CREATE TABLE mappinguserapplcation (
  id int(11) NOT NULL AUTO_INCREMENT,
  userId int(11) NOT NULL,
  applicationId int(11) NOT NULL,
  roleId int(11) NOT NULL,
  approvalStatus char(1) DEFAULT 0,
  PRIMARY KEY (id),
  KEY fk_user_id_idx (userId),
  KEY fk_app_id_idx (applicationId),
  KEY fk_role_id_idx (roleId),
  CONSTRAINT fk_app_id FOREIGN KEY (applicationId) REFERENCES applicationdetails (id) ON UPDATE CASCADE,
  CONSTRAINT fk_role_id FOREIGN KEY (roleId) REFERENCES roledetails (id) ON UPDATE CASCADE,
  CONSTRAINT fk_user_id FOREIGN KEY (userId) REFERENCES userdetails (id) ON UPDATE CASCADE
);

CREATE TABLE mappingrolecapabilities (
  id int(11) NOT NULL AUTO_INCREMENT,
  roleId int(11) NOT NULL,
  capabilitiesId int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY fk_role_id_idx (roleId),
  KEY fk_capabilities_id_idx (capabilitiesId),
  CONSTRAINT fk_rc_capacities_id FOREIGN KEY (capabilitiesId) REFERENCES capabilitiesdetails (id) ON UPDATE CASCADE,
  CONSTRAINT fk_rc_role_id FOREIGN KEY (roleId) REFERENCES roledetails (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
