
DROP TABLE IF EXISTS `coins`;
CREATE TABLE `coins` (
  `id` varchar(255) NOT NULL,
  `coin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;


INSERT INTO `coins` (`id`, `coin`) VALUES
  ('4169868951', 10),
  ('6621876564', 45),
  ('0068131062', 54),
  ('2534892940', 5345),
  ('2542003092', 645),
  ('2076048790', 6564),
  ('7833528552', 8),
  ('5858179630', 4),
  ('7807240504', 23),
  ('7589780480', 1);

DROP TABLE IF EXISTS `creatorlevels`;
CREATE TABLE `creatorlevels` (
  `id` varchar(255) NOT NULL,
  `exp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO `creatorlevels`(`id`, `exp`) VALUES
    ('edac9fad-c77f-4761-a647-34e35e881ca3', 234);



DROP TABLE IF EXISTS `subscribers`;
CREATE TABLE `subscribers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_creator` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`id_user`,`id_creator`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO `subscribers` VALUES
    (1,3,'edac9fad-c77f-4761-a647-34e35e881ca3'),
    (2,1,'edac9fad-c77f-4761-a647-34e35e881ca3'),
    (3,3,'edac9fad-c77f-4761-a647-34e35e881ca3'),
    (4,3,'edac9fad-c77f-4761-a647-34e35e881ca3'),
    (5,4,'edac9fad-c77f-4761-a647-34e35e881ca3'),
    (6,2,'edac9fad-c77f-4761-a647-34e35e881ca3'),
    (7,4,'901e9489-8417-4cdf-9e7c-b0313f57662e'),
    (8,2,'901e9489-8417-4cdf-9e7c-b0313f57662e'),
    (9,2,'901e9489-8417-4cdf-9e7c-b0313f57662e'),
    (10,2,'901e9489-8417-4cdf-9e7c-b0313f57662e');


DROP TABLE IF EXISTS `userlevels`;
CREATE TABLE `userlevels` (
  `id` int(11) NOT NULL,
  `exp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO `userlevels` (`id`, `exp`) VALUES
    (1,472);




DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging`
(
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    IP VARCHAR(16) NOT NULL,
    endpoint VARCHAR(255) NOT NULL,
    request VARCHAR(255) NOT NULL,
    requested_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);
