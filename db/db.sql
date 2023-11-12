
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
    ('4169868951', 103),
      ('6621876564', 3459),
      ('0068131062', 325),
      ('2534892940', 645),
      ('2542003092', 745),
      ('2076048790', 8567),
      ('7833528552', 7456),
      ('5858179630', 25456),
      ('7807240504', 6734),
      ('7589780480', 1);



DROP TABLE IF EXISTS `subscribers`;
CREATE TABLE `subscribers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_creator` varchar(255) NOT NULL,
  PRIMARY KEY (`id`,`id_user`,`id_creator`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO `subscribers` VALUES
    (1,3,'4169868951'),
    (2,1,'6621876564'),
    (3,3,'0068131062'),
    (4,3,'2534892940'),
    (5,4,'2542003092'),
    (6,2,'2076048790'),
    (7,4,'7833528552'),
    (8,2,'5858179630'),
    (9,2,'7807240504'),
    (10,2,'7589780480');


DROP TABLE IF EXISTS `userlevels`;
CREATE TABLE `userlevels` (
  `id` int(11) NOT NULL,
  `exp` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

INSERT INTO `userlevels` (`id`, `exp`) VALUES
    (2,100),
    (3,7594),
    (4,32894),
    (1,72);




DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging`
(
    id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    IP VARCHAR(16) NOT NULL,
    endpoint VARCHAR(255) NOT NULL,
    requested_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO `logging` (`id`, `description`, `ip`, `endpoint`, `requested_at`) VALUES
  (1, 'coba log', '127.0.0.1', 'localhost.com', '2008-01-01 00:00:01');
