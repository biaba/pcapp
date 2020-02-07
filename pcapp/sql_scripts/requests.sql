--
-- Table structure for table request
--

DROP TABLE IF EXISTS request;

CREATE TABLE request (
  id int(11) NOT NULL AUTO_INCREMENT,
  text varchar(200) NOT NULL,
  status varchar(50) NOT NULL,  
  date_subm DATE,
  user_id int(11) NOT NULL,
  PRIMARY KEY (id),
  
  CONSTRAINT FK_USER_02 FOREIGN KEY (user_id) 
  REFERENCES user (id) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for request
--

INSERT INTO request VALUES 
	(1,'not working','submitted', '2015-12-17', 1),
	(2,'broken','submitted', '2018-12-17', 3),
    (3,'needs better version. Outdated.','submitted', '2018-11-17', 3);
	
--
-- Creating table for computer part
--

DROP TABLE IF EXISTS computer_part;
	
CREATE TABLE computer_part (
  id int(11) NOT NULL AUTO_INCREMENT,
  part_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for computer part
--

INSERT INTO computer_part (part_name) VALUES 
('Central processing unit'),('Motherboard'),('Memory (RAM)'),('Graphics processing unit (GPU)'),('SSD'),('HDD'),('Power supply unit (PSU)');

--
-- Table structure for table requests_computer_parts
--

DROP TABLE IF EXISTS requests_computer_parts;

CREATE TABLE requests_computer_parts (
  request_id int(11) NOT NULL,
  computer_part_id int(11) NOT NULL,
  
  PRIMARY KEY (request_id, computer_part_id),
  
  KEY FK_REQUEST_idx (request_id),
  
  CONSTRAINT FK_REQUEST_01 FOREIGN KEY (request_id) 
  REFERENCES request (id) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT FK_COMPUTER_PART_02 FOREIGN KEY (computer_part_id) 
  REFERENCES computer_part (id) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table users_roles
--

INSERT INTO requests_computer_parts (request_id, computer_part_id)
VALUES 
(1, 1),
(1, 3),
(2, 1),
(2, 2),
(3, 1);