--
-- Table structure for table user
--
-- Passwords are encrypted using BCrypt
--
-- Passwords generated at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: test123
--
--

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  email varchar(50) NOT NULL,  
  password char(80) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for user
--

INSERT INTO user VALUES 
	(1,'maris','maris@inbox.lv', '$2a$10$qDaGJcqWrEsxzUqMCULGJ.eZAL3SIY.yBQKnQGMsuHuj.iGE94dRy'),
	(2,'adina','adina@inbox.lv', '$2a$10$qDaGJcqWrEsxzUqMCULGJ.eZAL3SIY.yBQKnQGMsuHuj.iGE94dRy'),
	(3,'emma','emma@inbox.lv', '$2a$10$qDaGJcqWrEsxzUqMCULGJ.eZAL3SIY.yBQKnQGMsuHuj.iGE94dRy');
	
--
-- Creating table for role
--

DROP TABLE IF EXISTS role;
	
CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for role
--

INSERT INTO role VALUES 
('ROLE_APPLICANT'),('ROLE_MANAGER'),('ROLE_ADMIN');

--
-- Table structure for table users_roles
--

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  
  PRIMARY KEY (user_id, role_id),
  
  KEY FK_ROLE_idx (role_id),
  
  CONSTRAINT FK_USER_01 FOREIGN KEY (user_id) 
  REFERENCES user (id) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT FK_ROLE_02 FOREIGN KEY (role_id) 
  REFERENCES role (id) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table users_roles
--

INSERT INTO users_roles (user_id, role_id)
VALUES 
(1, 1),
(1, 3),
(2, 1),
(2, 2),
(3, 1);