CREATE DATABASE  IF NOT EXISTS `rent_a_car_schema` /*!40100 DEFAULT CHARACTER SET greek */;
USE `rent_a_car_schema`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: rent_a_car_schema
-- ------------------------------------------------------
-- Server version	8.0.2-dmr-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customers`
--

-- ολοι οι πινακες υποστηριζουν ελληνικους χαρακτηρες
-- ολα τα triggers επιστρεφουν μηνυμα σφαλματος σε περιπτωση που δεν τηρουνται οι συνθηκες που περιλαμβανουν

-- Δημιουργουμε τον πινακα customers με κυριο κλειδι το customer_id με auto increment ωστε να αυξανονται αυτοματα τα id σε νεους πελατες
-- ολα τα varchars εχουν μεγιστο μηκος 45, ολα τα πεδια πρεπει να εχουν τιμη
-- ολα τα πεδια Unique και το κυριο κλειδι εχουν ευρετηριο

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `customer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,	
  `irs_number` bigint(10) unsigned NOT NULL,				-- περιμενουμε να ειναι μεγαλος θετικος αριθμος
  `social_security_number` int(10) unsigned NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `driver_license` bigint(10) NOT NULL,
  `first_registration` datetime NOT NULL,
  `city` varchar(45) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `street` varchar(45) NOT NULL,
  `street_number` varchar(10) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
  UNIQUE KEY `irs_number_UNIQUE` (`irs_number`),
  UNIQUE KEY `social_security_number_UNIQUE` (`social_security_number`),
  UNIQUE KEY `driver_license_UNIQUE` (`driver_license`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,8688275249,754092455,'Amada','Stanton',297970452282,'2015-02-13 00:00:00','East Brunswick NJ','23666','3rd Avenue','195'),(2,2673147133,194964875,'Charlesetta','Sims',793008555244,'2015-04-26 00:00:00','Lemont IL','15650','Ashley Court','268'),(3,9285872710,342673578,'Jesse','Hunter',575648973029,'2011-06-21 00:00:00','Harlingen TX','44139','Mulberry Court','297'),(4,5344007466,999351523,'Yu','Harrington',483380799200,'2014-03-10 00:00:00','Marlborough MA','77904','Chestnut Street','130'),(5,2946062505,190615983,'Cristobal','Luna',726379183057,'2017-05-05 00:00:00','Andover MA','35640','Spruce Avenue','249'),(6,2728833018,230066151,'Loreta','Carpenter',603749123767,'2014-02-24 00:00:00','Billings MT','60048','Durham Court','217'),(7,9487466523,224531590,'Migdalia','Collins',791241011162,'2017-02-11 00:00:00','Oconomowoc WI','08817','Roberts Road','253'),(8,5704084158,678852472,'Derek','Best',470132484464,'2014-02-03 00:00:00','Wheeling WV','45140','Lantern Lane','237'),(9,4090095300,585220307,'Boyce','Armstrong',709425294282,'2015-03-07 00:00:00','Winchester VA','08234','Jackson Avenue','56'),(10,3338619507,193721697,'Migdalia','Roth',704206241708,'2012-04-08 00:00:00','Apple Valley CA','33009','7th Avenue','24'),(11,8405734546,548548618,'Shaunna','Lutz',686008716977,'2015-02-06 00:00:00','Grosse Pointe MI','12065','Cedar Avenue','88'),(12,1020612695,983280524,'Antonio','Carter',961807089922,'2017-02-23 00:00:00','Reynoldsburg OH','32725','Elm Avenue','105'),(13,2919517878,312254089,'Yan','Beasley',328553561558,'2014-01-20 00:00:00','Altamonte Springs FL','11729','Pennsylvania Avenue','35'),(14,7280075830,954137050,'Christine','Brown',435761331840,'2014-01-03 00:00:00','Lakeville MN','45601','Cambridge Drive','209'),(15,4907442962,901589789,'Dessie','Atkinson',603296418965,'2012-01-03 00:00:00','De Pere WI','11040','Maiden Lane','68'),(16,9762521854,510145179,'Shanae','Taylor',866287064496,'2016-03-02 00:00:00','Basking Ridge NJ','11520','7th Avenue','293'),(17,7449327628,868645570,'Modesta','Bray',875887309128,'2012-03-19 00:00:00','Lakeville MN','44805','Park Place','271'),(18,5239838211,944489073,'Gladis','Walters',636180310014,'2011-02-03 00:00:00','Anchorage AK','50023','Hawthorne Avenue','276'),(19,7165656684,418730374,'Yan','Mayer',385033881854,'2012-06-07 00:00:00','Jackson NJ','20772','Main Street South','101'),(20,5954028205,478681196,'Malia','Quinn',801531063920,'2015-02-01 00:00:00','Prior Lake MN','60193','Lexington Drive','27'),(21,8188784335,688430989,'Drucilla','Webster',676649786214,'2014-03-09 00:00:00','Desoto TX','28376','Spring Street','213'),(22,1675140192,908492123,'Valeri','Colon',653461440061,'2016-05-07 00:00:00','Carrollton GA','29526','Evergreen Drive','226'),(23,2473683662,482426318,'Terrilyn','Lester',401098922645,'2013-03-20 00:00:00','Fort Walton Beach FL','42001','3rd Street West','12'),(24,9708082035,418658109,'Sunny','Padilla',957839935180,'2013-03-25 00:00:00','Clifton Park NY','30117','Laurel Street','296'),(25,2258808806,672012873,'Charita','Gentry',160578474909,'2014-01-27 00:00:00','Benton Harbor MI','60014','7th Street','17'),(26,9702193876,596596441,'Rafael','Mckinney',180933662653,'2016-03-27 00:00:00','Absecon NJ','46368','Cambridge Court','175'),(27,3204070991,149066308,'Loreta','Oneill',373894684883,'2012-04-06 00:00:00','State College PA','07052','Prospect Street','174'),(28,4411422966,188907824,'Gemma','Friedman',322004673031,'2016-01-21 00:00:00','Kansas City MO','03038','Eagle Street','257'),(29,4864495872,651925292,'Margy','Branch',277185890621,'2016-01-06 00:00:00','Little Falls NJ','60134','Locust Street','169'),(30,4351056935,492158464,'Charita','Patrick',827448290756,'2017-06-06 00:00:00','Cleveland TN','02478','Cedar Lane','165');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `customers_view`
--

-- δημιουργουμε αυτο το editable view στο οποιο θα κανουμε αλλαγες απο την εφαρμογη μας

DROP TABLE IF EXISTS `customers_view`;
/*!50001 DROP VIEW IF EXISTS `customers_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `customers_view` AS SELECT 
 1 AS `customer_id`,
 1 AS `irs_number`,
 1 AS `social_security_number`,
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `driver_license`,
 1 AS `first_registration`,
 1 AS `city`,
 1 AS `postal_code`,
 1 AS `street`,
 1 AS `street_number`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `email_addresses_stores`
--

-- Δημιουργουμε τον πινακα email_addresses_stores με κυριο κλειδι το email_id με auto increment ωστε να αυξανονται αυτοματα τα id σε νεα email
-- ενα store μπορει να εχει πολλες εγγραφες στον πινακα αυτο δηλαδη πολλα email
-- ολα τα πεδια Unique και το κυριο κλειδι εχουν ευρετηριο
-- το store_id ειναι foreign key και αναφερεται στο store_id του πινακα stores και ενημερωνεται σε διαγραφη ή αλλαγη μιας εγγραφης στο stores

DROP TABLE IF EXISTS `email_addresses_stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_addresses_stores` (
  `email_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `store_id` int(10) unsigned NOT NULL,
  `email_address` varchar(45) NOT NULL,
  PRIMARY KEY (`email_id`),
  UNIQUE KEY `email_id_UNIQUE` (`email_id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`),
  KEY `email_addresses_stores_fk_idx` (`store_id`),
  CONSTRAINT `email_addresses_stores_fk` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_addresses_stores`
--

--
-- δημιουργουμε ενα trigger τυπου BEFORE INSERT και ενα BEFORE UPDATE τα οποια φροντιζουν να δινεται σωστη μορφη email στον πινακα
--

LOCK TABLES `email_addresses_stores` WRITE;
/*!40000 ALTER TABLE `email_addresses_stores` DISABLE KEYS */;
INSERT INTO `email_addresses_stores` VALUES (1,3000,'123getAcar@gmail.com'),(2,3001,'123get@gmail.com'),(3,3002,'123Acar@hotmail.com'),(4,3003,'most@hotmail.com'),(5,3004,'most_awesome@hotmail.com'),(6,3005,'most_awesome_car@yahoo.com'),(7,3006,'Guru_of_car_rentals@yahoo.com'),(8,3007,'123getAcar@hotmail.com'),(9,3008,'most_awesome_car_rentals@hotmail.com'),(10,3009,'awesome_car_rentals@hotmail.com'),(11,3000,'testEmail@email.com'),(12,3003,'temp1@example.com'),(13,3007,'store@company.net');
/*!40000 ALTER TABLE `email_addresses_stores` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `email_addresses_stores_BEFORE_INSERT` BEFORE INSERT ON `email_addresses_stores` FOR EACH ROW BEGIN
	IF NEW.`email_address` NOT LIKE '%_@%_.__%' THEN
		SIGNAL SQLSTATE VALUE '45000'
			SET MESSAGE_TEXT = '[table:email_addresses_stores] - `email` address is not valid';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `email_addresses_stores_BEFORE_UPDATE` BEFORE UPDATE ON `email_addresses_stores` FOR EACH ROW BEGIN
		IF NEW.`email_address` NOT LIKE '%_@%_.__%' THEN
		SIGNAL SQLSTATE VALUE '45000'
			SET MESSAGE_TEXT = '[table:email_addresses_stores] - `email` address is not valid';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `employees`
--

-- Δημιουργουμε τον πινακα employees με κυριο κλειδι το irs_number 
-- ολα τα πεδια Unique και το κυριο κλειδι εχουν ευρετηριο
-- τα unique πεδια οριζονται ετσι λογω του οτι ειναι μοναδικα στην πραγματικοτητα

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `irs_number` bigint(10) unsigned NOT NULL,
  `social_security_number` int(10) unsigned NOT NULL,
  `driver_license` bigint(10) unsigned DEFAULT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `street` varchar(45) NOT NULL,
  `street_number` varchar(10) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`irs_number`),
  UNIQUE KEY `irs_number_UNIQUE` (`irs_number`),
  UNIQUE KEY `social_security_number_UNIQUE` (`social_security_number`),
  UNIQUE KEY `driver_license_UNIQUE` (`driver_license`)
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1165065830,285708317,921240147736,'Gemma','Jackson','Sycamore Drive','188','12590','Saginaw MI'),(1165758572,575638019,351948486237,'Albert','Tran','Route 6 will','175','07003','Piqua OH'),(1322832573,967000695,722433910455,'Shaunna','Kline','Liberty Street','75','34761','Annandale VA'),(1575909685,448202228,940770164544,'Christine','Haley','Ridge Avenue','171','21740','Paducah KY'),(1741206407,604375178,255926451446,'Charolette','Riggs','Madison Court','281','01420','Berwyn IL'),(1837916754,120221783,439561799718,'Nestor','Strickland','Virginia Avenue','242','44145','Evansville IN'),(1917219356,626702440,405378631715,'Yu','Welch','Main Street West','100','48178','Morrisville PA'),(1966141049,896591062,940130199215,'Michael','Hubbard','Lincoln Street','122','07003','Southaven MS'),(2006356912,217150887,861001944018,'Dessie','York','Cherry Lane','80','13027','Eden Prairie MN'),(2107021272,112025178,192676175583,'Parker','Melendez','Park Street','291','07860','Whitehall PA'),(2119053821,966899574,846380437540,'Kaitlin','Sexton','Chestnut Street','22','45066','Cockeysville MD'),(2215544904,713224393,333093331317,'Christine','Webster','Brandywine Drive','140','38053','Tualatin OR'),(2324933127,272908940,849210273245,'Charita','Mckee','Olive Street','35','45601','Windsor Mill MD'),(2539937004,568462224,614666644288,'Eden','Black','Heather Court','148','02136','Millington TN'),(2552953050,330707788,420186426459,'Amada','Zavala','Route 64','261','29501','Clarkston MI'),(2586308723,912694051,629316766009,'Corinne','Whitaker','Woodland Drive','194','37343','Macungie PA'),(2834204605,580017885,126257800480,'Faustina','Blankenship','Route 41','186','18966','Rockville MD'),(2847939409,599619626,227565462474,'Charita','Dominguez','Route 5','294','01810','Maryville TN'),(2938048580,943213398,668766637298,'Nickie','Ramirez','Valley View Road','44','12010','Laurel MD'),(3028318468,169668933,713601593878,'Abel','Walter','State Street','253','27530','Bridgeton NJ'),(3038656780,483112201,946231645969,'Yu','Perez','Eagle Road','26','06614','Roanoke Rapids NC'),(3156224124,978163694,433854258590,'Dalila','Mosley','East Street','218','50701','Nashville TN'),(3406307655,889333591,743356529179,'Carry','Cook','New Street','272','60187','Green Cove Springs FL'),(3570954653,196277564,393917432394,'Faustina','Hale','Beechwood Drive','38','46368','Sidney OH'),(3628769681,456490097,695995462389,'Shanae','Wu','Sherwood Drive','34','97124','Saint Paul MN'),(3640615726,454160025,949995751394,'Devon','Henderson','Route 29','181','53154','Woodbridge VA'),(3769054732,222531483,206815147722,'Mariam','Shepard','Madison Street','62','30047','Annapolis MD'),(3812701156,926591822,233458630863,'Jesse','Espinoza','4th Street','169','30213','Montclair NJ'),(3853465512,342733922,123947163275,'Hilma','Pierce','Forest Street','43','37312','Morrisville PA'),(3873637988,964080295,626950809375,'Gabriella','Burnett','Belmont Avenue','239','13027','Derby KS'),(3986540199,972465440,380414513213,'Ewa','Howard','Willow Drive','111','32547','Rockledge FL'),(4289831081,225255370,819364388154,'Malia','Rhodes','Route 6 will','90','15146','Downingtown PA'),(4337208959,524696554,431381591124,'Corinne','Barron','Mulberry Court','158','02132','Willingboro NJ'),(4353063036,707845487,362205814113,'Shanae','Chang','Lexington Drive','68','06489','Wappingers Falls NY'),(4567626504,715355934,956092902643,'Loris','Duffy','Aspen Drive','137','15146','Yuma AZ'),(4646251290,393270474,202646942054,'Leticia','Nixon','Summit Avenue','280','34787','Bethpage NY'),(4672589224,190104443,139935323500,'Hilma','Compton','5th Street West','209','21136','Lilburn GA'),(4957595344,433012221,312081840857,'Cordelia','Shaw','Andover Court','83','47802','Little Rock AR'),(4989119383,111564526,394051354190,'Ivelisse','Sanchez','Fairview Road','286','07501','North Wales PA'),(5289428656,676006951,549449542407,'Boyce','Shepherd','Jackson Street','185','28104','Mcminnville TN'),(5328622135,531161688,715914621984,'Migdalia','Carroll','Route 4','288','37421','Wheeling WV'),(5374858026,242993353,570474676318,'Renae','Arellano','Lake Street','55','11377','Clayton NC'),(5581657218,537754587,201533359763,'Loreta','Goodwin','Route 10','169','32159','Fullerton CA'),(5787781979,254696461,973174067863,'Michael','Medina','Parker Street','200','93035','Mount Holly NJ'),(5856038261,454468028,469911306282,'Delinda','Strickland','Manor Drive','160','11375','Clover SC'),(6166652264,867379695,851294851769,'Tran','Duffy','2nd Street','259','67037','Danville VA'),(6188555091,260600101,158544204906,'Nestor','Sweeney','Holly Drive','248','75126','Carrollton GA'),(6309652985,987845867,630647383580,'Elenore','Bryant','John Street','68','32708','Salem MA'),(6341148139,666358848,131466068795,'Gustavo','Raymond','Briarwood Drive','140','37803','Winchester VA'),(6344570781,715065730,505839894094,'Peter','Pennington','Atlantic Avenue','44','60101','Lake In The Hills IL'),(6434736286,151269486,381365390955,'Yu','Mcbride','Pleasant Street','119','48329','Oviedo FL'),(6458643760,259630750,353764425538,'Cordelia','Pruitt','Augusta Drive','98','19038','Blackwood NJ'),(6471876059,864043076,180562555889,'Bobby','Lyons','Orchard Avenue','237','54115','Chesterfield VA'),(6524311213,500614305,855023512389,'Parker','Pitts','Park Street','100','38637','Salem MA'),(6536522756,553482469,625723220648,'Nestor','Bryan','Hanover Court','250','34761','Linden NJ'),(6629012060,510872330,892344055154,'Shanae','Melton','Mill Road','103','23832','Clover SC'),(6835632574,823493151,644249606620,'Charolette','Spears','Myrtle Avenue','14','97124','Cheshire CT'),(6904284705,793395297,255725867183,'Marquitta','Buckley','Hill Street','202','02136','Fullerton CA'),(6933598590,814054306,534557462667,'Betsy','Owen','Linden Street','240','38053','Englewood NJ'),(7025710825,406147785,526152690376,'Kendall','Roberson','Windsor Drive','215','60169','Winona MN'),(7216317287,938150935,957473648322,'Parker','Washington','Locust Lane','9','55347','Green Cove Springs FL'),(7223886886,402970864,211513296644,'Janita','Heath','4th Street South','171','29621','Waterloo IA'),(7291136612,649432134,955070139430,'Corine','Horne','5th Street West','80','02893','Jamaica NY'),(7356572705,297800576,447899828553,'Gustavo','Larsen','Mill Street','17','29577','Trenton NJ'),(7450196837,746246082,322790958728,'Sunny','Ruiz','Church Street','234','60193','Massapequa NY'),(7540495086,207788700,770151168892,'Faustina','Mcgee','Sycamore Drive','205','95060','Holyoke MA'),(7598505993,662543699,741215922936,'Adriane','Rubio','Penn Street','87','08046','Pensacola FL'),(7603857531,549170005,746829061452,'Yen','Hull','2nd Street North','229','07726','Alliance OH'),(7694714800,502220647,826714186905,'Modesta','Atkinson','Rose Street','228','43551','Egg Harbor Township NJ'),(7908323919,455379726,682370964634,'Yu','Beasley','Brandywine Drive','89','60156','Ames IA'),(7919858173,856086046,290190694697,'Dean','Bruce','Windsor Drive','161','11520','Butler PA'),(8021506701,802894837,152827176546,'Diamond','Mora','Lincoln Avenue','55','26003','Richmond Hill NY'),(8076750092,432944017,669840568139,'Elenore','Lowe','Cedar Lane','109','48348','Englishtown NJ'),(8202042999,989608263,509970816582,'Hilma','Crawford','Willow Drive','35','11354','La Crosse WI'),(8315854470,341412224,999920191379,'Devon','Cain','Devon Court','234','48601','Saint Augustine FL'),(8352500035,151351143,784952309895,'Salley','Lowe','Morris Street','297','08859','Champlin MN'),(8373664849,178198933,430417986745,'Sheba','Mcgee','Deerfield Drive','169','84010','Helotes TX'),(8429702015,497688365,476693712547,'Mariam','Byrd','Primrose Lane','136','90403','Clarkston MI'),(8526176699,311035448,197251046979,'Concha','Ballard','State Street','287','20850','Goldsboro NC'),(8583227491,555977714,171924494186,'Diamond','Harrison','Vine Street','44','60440','Hopkins MN'),(8635641772,763934822,857252891383,'Eden','Pope','Virginia Avenue','25','19083','Massapequa NY'),(8639454916,977955327,675312334238,'Diamond','Andrade','Cambridge Court','29','08527','Lemont IL'),(8651752471,933287231,861189290475,'Bobby','Atkins','Jackson Street','153','54115','Holyoke MA'),(8697452276,406448027,768675611769,'Terese','Barr','5th Street East','16','38637','Middletown CT'),(8711825121,635463656,171291150693,'Jesse','Carney','6th Street North','41','95060','Corona NY'),(8739362771,582289879,752485902602,'Joesph','Lynn','Route 4','170','33771','Glenside PA'),(8751065832,776016048,408587773080,'Winona','Carpenter','Linden Street','275','18966','West Orange NJ'),(8862311299,365777968,855433592494,'Aisha','Archer','Jackson Avenue','82','33010','Port Jefferson Station NY'),(8880826355,513573068,804904954426,'Gertha','Petersen','Sunset Drive','256','10002','Olive Branch MS'),(8905039882,306678702,904508887997,'Devon','Frye','Route 32','194','11377','Merrimack NH'),(8942667394,403152871,472437942959,'Jesse','Gomez','Ivy Lane','23','07052','Port Saint Lucie FL'),(9087972142,800288233,205866284667,'Cristobal','Cooper','Park Drive','249','55372','Massillon OH'),(9129576852,534809308,817032277702,'Betsy','Christensen','Parker Street','18','64151','Superior WI'),(9154045162,479982495,932135713657,'Gemma','Hopkins','Valley View Drive','264','21114','Norfolk VA'),(9418906948,986035167,977257355160,'Shaunna','Greer','Woodland Road','97','75126','Winter Park FL'),(9559460020,533026377,382365454673,'Migdalia','Thomas','James Street','186','75126','Lenoir NC'),(9613092079,726525628,149325133086,'Kam','Lyons','Canterbury Court','125','11377','Newton NJ'),(9634305646,570104099,677567916207,'Yu','Manning','Prospect Avenue','280','15701','Rochester NY'),(9692593435,712541404,737690792244,'Willa','Bonilla','Magnolia Avenue','191','60134','Taylors SC'),(9796005491,747923714,572552822299,'Marylynn','West','Lafayette Street','140','37363','Buffalo Grove IL');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `employment_data`
--

--
-- μεσω αυτου του view το οποιο περιλαμβανει τις βασικες πληροφοριες που 
-- χαρακτηριζουν εναν υπαλληλο και τα στοιχεια που αφορουν την θεση του στην εταιρεια
-- προβαλλουμε ολες τις πληροφοριες του στο αντιστοιχο tab "Employment Data" στην εφαρμογη μας
-- 

DROP TABLE IF EXISTS `employment_data`;
/*!50001 DROP VIEW IF EXISTS `employment_data`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `employment_data` AS SELECT 
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `irs_number`,
 1 AS `start_date`,
 1 AS `finish_date`,
 1 AS `position`,
 1 AS `city`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `fuel_type`
--

--
-- ο πινακας fuel_type αναφερει για καθε οχημα της εταιρειας τον τυπο μηχανης που διαθετει
--

DROP TABLE IF EXISTS `fuel_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fuel_type` (
  `license_plate` varchar(10) NOT NULL,
  `fuel_type` varchar(15) NOT NULL,
  PRIMARY KEY (`license_plate`),
  UNIQUE KEY `license_plate_UNIQUE` (`license_plate`),
  CONSTRAINT `fuel_type_vehicles_fk` FOREIGN KEY (`license_plate`) REFERENCES `vehicles` (`license_plate`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fuel_type`
--

LOCK TABLES `fuel_type` WRITE;
/*!40000 ALTER TABLE `fuel_type` DISABLE KEYS */;
INSERT INTO `fuel_type` VALUES ('0DO553','gas'),('12D468','gas'),('19E686','electrical'),('566854','electrical'),('6TF315','electrical'),('8RV384','diesel'),('8ZS429','electrical'),('9XX243','gasoline'),('BFC629','gasoline'),('BFY557','electrical'),('CJT264','diesel'),('CRC435','gasoline'),('DUC744','gas'),('EBF941','gasoline'),('F3E364','electrical'),('FQY662','gas'),('FYE720','gasoline'),('HVW231','gasoline'),('JCE569','electrical'),('JGD403','diesel'),('KXD520','diesel'),('LBT905','electrical'),('MMY807','electrical'),('QZC986','gasoline'),('RWE302','diesel'),('T12278','diesel'),('VVY346','diesel'),('VYC992','electrical'),('W4Q586','diesel'),('WE0615','electrical');
/*!40000 ALTER TABLE `fuel_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_data`
--

--
-- τον πινακα login_data τον δημιουργησαμε προκειμενου να αποθηκευουμε σε αυτον τα username και password 
-- που διαθετει καθε υπαλληλος της εταιριας ωστε να εχει προσβαση στη βαση μεσω της εφαρμογης μας
-- το password ειναι κρυπτογραφημενο σε SHA256 και εχουμε φτιαξει ως παραδειγμα 2 χρηστες της εφαρμογης
-- εναν με username και password "admin" και εναν με "user" με διαφορετικα δικαιωματα ο καθενας
--

DROP TABLE IF EXISTS `login_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_data` (
  `irs_number` bigint(10) unsigned NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`irs_number`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `irs_number_UNIQUE` (`irs_number`),
  CONSTRAINT `login_data_employees_fk` FOREIGN KEY (`irs_number`) REFERENCES `employees` (`irs_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_data`
--

LOCK TABLES `login_data` WRITE;
/*!40000 ALTER TABLE `login_data` DISABLE KEYS */;
INSERT INTO `login_data` VALUES (3028318468,'admin','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918'),(7598505993,'user','04F8996DA763B7A969B1028EE3007569EAF3A635486DDAB211D512C85B9DF8FB');
/*!40000 ALTER TABLE `login_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_transaction`
--

--
-- ο πινακας payment_transaction περιλαμβανει το ποσο και τον τυπο πληρωμης για καθε ενοικιαση 
-- εχει 2 foreign keys που αντιστοιχουν στα 2 primary keys του πινακα vehicles 
-- το payment_amount ειναι δεκαδικος αριθμος με 2 δεκαδικα ψηφια
-- εννοουνται οσα ειπωθηκαν πιο πανω για τα indexes και τα foreign keys
--

DROP TABLE IF EXISTS `payment_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_transaction` (
  `start_date` date NOT NULL,
  `license_plate` varchar(10) NOT NULL,
  `payment_amount` decimal(10,2) unsigned NOT NULL,
  `payment_method` varchar(45) NOT NULL,
  PRIMARY KEY (`start_date`,`license_plate`),
  KEY `payment_transaction_vehicle_fk_idx` (`license_plate`),
  CONSTRAINT `payment_transaction_rents_fk` FOREIGN KEY (`start_date`) REFERENCES `rents` (`start_date`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `payment_transaction_vehicles_fk` FOREIGN KEY (`license_plate`) REFERENCES `vehicles` (`license_plate`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_transaction`
--

LOCK TABLES `payment_transaction` WRITE;
/*!40000 ALTER TABLE `payment_transaction` DISABLE KEYS */;
INSERT INTO `payment_transaction` VALUES ('2017-01-03','HVW231',76.00,'Credit card'),('2017-01-07','BFC629',71.00,'Credit card'),('2017-01-11','JGD403',68.00,'manual labor'),('2017-01-11','MMY807',37.00,'Credit card'),('2017-01-17','BFY557',33.00,'Western Union'),('2017-01-19','6TF315',32.00,'Cryptocurrency'),('2017-01-19','RWE302',63.00,'Western Union'),('2017-01-21','19E686',95.00,'manual labor'),('2017-01-26','12D468',44.00,'manual labor'),('2017-02-05','JCE569',68.00,'Cryptocurrency'),('2017-02-11','9XX243',74.00,'Western Union'),('2017-02-19','T12278',63.00,'Cryptocurrency'),('2017-02-20','KXD520',22.00,'Western Union'),('2017-02-21','WE0615',93.00,'Western Union'),('2017-03-03','DUC744',75.00,'manual labor'),('2017-03-09','QZC986',61.00,'Cryptocurrency'),('2017-03-16','CRC435',72.00,'manual labor'),('2017-03-17','FQY662',54.00,'Cryptocurrency'),('2017-03-19','VVY346',99.00,'Western Union'),('2017-03-21','LBT905',93.00,'Western Union'),('2017-03-27','566854',43.00,'Western Union'),('2017-04-04','F3E364',55.00,'Credit card'),('2017-04-20','0DO553',57.00,'Cryptocurrency'),('2017-04-20','CJT264',50.00,'cash'),('2017-05-03','EBF941',97.00,'Western Union'),('2017-05-12','8ZS429',91.00,'Cryptocurrency'),('2017-05-27','8RV384',86.00,'Credit card'),('2017-06-11','VYC992',71.00,'Credit card'),('2017-06-27','FYE720',48.00,'Cryptocurrency'),('2018-01-03','HVW231',92.00,'Manual labor'),('2018-01-07','BFC629',78.00,'Manual labor'),('2018-01-11','JGD403',83.00,'Cryptocurrency'),('2018-01-11','MMY807',48.00,'Cryptocurrency'),('2018-01-17','BFY557',42.00,'Western Union'),('2018-01-19','6TF315',53.00,'Credit card'),('2018-01-19','RWE302',64.00,'Credit card'),('2018-01-21','19E686',77.00,'Credit card'),('2018-01-26','12D468',61.00,'Western Union'),('2018-02-05','JCE569',67.00,'Western Union'),('2018-02-11','9XX243',60.00,'Western Union'),('2018-02-19','T12278',80.00,'Cryptocurrency'),('2018-02-20','KXD520',50.00,'Credit card'),('2018-02-21','WE0615',88.00,'Western Union'),('2018-03-03','DUC744',65.00,'Western Union'),('2018-03-09','QZC986',71.00,'Credit card'),('2018-03-16','CRC435',52.00,'Western Union'),('2018-03-17','FQY662',97.00,'Western Union'),('2018-03-19','VVY346',97.00,'Cryptocurrency'),('2018-03-21','LBT905',41.00,'cash'),('2018-03-27','566854',27.00,'Credit card'),('2018-04-04','F3E364',82.00,'Manual labor'),('2018-04-20','0DO553',38.00,'Manual labor'),('2018-04-20','CJT264',31.00,'cash'),('2018-05-03','EBF941',32.00,'Cryptocurrency'),('2018-05-12','8ZS429',48.00,'Cryptocurrency'),('2018-05-27','8RV384',44.00,'Manual labor'),('2018-06-11','VYC992',66.00,'Cryptocurrency'),('2018-06-27','FYE720',64.00,'Credit card');
/*!40000 ALTER TABLE `payment_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_numbers_stores`
--

--
-- ο πινακας phone_numbers_stores περιλαμβανει τα τηλεφωνα που αντιστοιχουν σε καθε store
--

DROP TABLE IF EXISTS `phone_numbers_stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_numbers_stores` (
  `phone_numbers_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `store_id` int(10) unsigned NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  PRIMARY KEY (`phone_numbers_id`),
  UNIQUE KEY `phone_numbers_id_UNIQUE` (`phone_numbers_id`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  KEY `phone_numbers_stores_fk_idx` (`store_id`),
  CONSTRAINT `phone_numbers_stores_fk` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_numbers_stores`
--

LOCK TABLES `phone_numbers_stores` WRITE;
/*!40000 ALTER TABLE `phone_numbers_stores` DISABLE KEYS */;
INSERT INTO `phone_numbers_stores` VALUES (1,3005,'2100113188'),(2,3000,'2100726445'),(3,3006,'2102564203'),(4,3003,'2102602216'),(5,3009,'2102658810'),(6,3008,'2102728918'),(7,3002,'2103850027'),(8,3007,'2104312853'),(9,3005,'2105749681'),(10,3001,'2106942270'),(11,3008,'2107893215'),(12,3000,'2107894561'),(13,3004,'2108221196');
/*!40000 ALTER TABLE `phone_numbers_stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rents`
--

--
-- ο πινακας rents περιλαμβανει τις εγγραφες σχετικες με τα ενοικια 
--

DROP TABLE IF EXISTS `rents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rents` (
  `license_plate` varchar(10) NOT NULL,
  `start_date` date NOT NULL,
  `start_location` varchar(45) NOT NULL,
  `finish_location` varchar(45) NOT NULL,
  `finish_date` date NOT NULL,
  `return_state` varchar(1000) NOT NULL DEFAULT '-',
  `customer_id` int(10) unsigned NOT NULL,
  `irs_number` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`license_plate`,`start_date`),
  KEY `rents_start_date_idx` (`start_date`),
  KEY `rents_customers_fk_idx` (`customer_id`),
  KEY `rents_employees_fk_idx` (`irs_number`),
  CONSTRAINT `rents_customers_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rents_employees_fk` FOREIGN KEY (`irs_number`) REFERENCES `employees` (`irs_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rents_vehicles_fk` FOREIGN KEY (`license_plate`) REFERENCES `vehicles` (`license_plate`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rents`
--

-- 
-- εχουμε ενα trigger BEFORE INSERT και ενα BEFORE UPDATE στον πινακα rents 
-- αυτα διασφαλιζουν οτι σε περιπτωση νεας εγγραφης στον πινακα το start date θα εχει την τρεχουσα ημερομηνια και
-- σε περιπτωση update οτι θα κρατηθει ιδια η προηγουμενη start date
-- επισης οτι to finish date αφορα μελλοντικη ημερομηνια σε σχεση με το start date

LOCK TABLES `rents` WRITE;
/*!40000 ALTER TABLE `rents` DISABLE KEYS */;
INSERT INTO `rents` VALUES ('0DO553','2017-04-20','Gastonia NC','Conway SC','2017-10-04','Perfect',5,8373664849),('0DO553','2018-04-20','Gastonia NC','Conway SC','2018-10-04','Perfect',5,8373664849),('12D468','2017-01-26','Conway SC','Gastonia NC','2017-11-05','Slight damages',23,5856038261),('12D468','2018-01-26','Conway SC','Gastonia NC','2018-11-05','Slight Damages',23,5856038261),('19E686','2017-01-21','Snellville GA','Snellville GA','2017-09-06','Slight damages',11,8526176699),('19E686','2018-01-21','Snellville GA','Snellville GA','2018-09-06','Slight Damages',11,8526176699),('566854','2017-03-27','Oak Creek WI','Summerville SC','2017-07-16','Slight damages',8,8905039882),('566854','2018-03-27','Oak Creek WI','Summerville SC','2018-07-16','Slight Damages',8,8905039882),('6TF315','2017-01-19','Snellville GA','Gastonia NC','2017-05-26','Heavily damaged',1,2938048580),('6TF315','2018-01-19','Snellville GA','Gastonia NC','2018-05-26','Heavily Damaged',1,2938048580),('8RV384','2017-05-27','Summerville SC','Summerville SC','2017-11-18','Slight damages',13,9634305646),('8RV384','2018-05-27','Summerville SC','Summerville SC','2018-11-18','Slight Damages',13,9634305646),('8ZS429','2017-05-12','Conway SC','Conway SC','2017-11-09','Perfect',12,4353063036),('8ZS429','2018-05-12','Conway SC','Conway SC','2018-11-09','Perfect',12,4353063036),('9XX243','2017-02-11','Hoffman Estates IL','Oak Creek WI','2017-09-11','Slight damages',22,2107021272),('9XX243','2018-02-11','Hoffman Estates IL','Oak Creek WI','2018-09-11','Slight Damages',22,2107021272),('BFC629','2017-01-07','Elizabeth City NC','Southington CT','2017-11-17','Slight damages',26,7450196837),('BFC629','2018-01-07','Elizabeth City NC','Southington CT','2018-11-17','Slight Damages',26,7450196837),('BFY557','2017-01-17','Summerville SC','Gastonia NC','2017-06-13','Perfect',25,6629012060),('BFY557','2018-01-17','Summerville SC','Gastonia NC','2018-06-13','Perfect',25,6629012060),('CJT264','2017-04-20','Summerville SC','Reynoldsburg OH','2017-10-24','Perfect',15,6629012060),('CJT264','2018-04-20','Summerville SC','Reynoldsburg OH','2018-10-24','Perfect',15,6629012060),('CRC435','2017-03-16','Reynoldsburg OH','Southington CT','2017-10-12','Heavily damaged',28,4989119383),('CRC435','2018-03-16','Reynoldsburg OH','Southington CT','2018-10-12','Heavily Damaged',28,4989119383),('DUC744','2017-03-03','Southington CT','Reynoldsburg OH','2017-09-24','Heavily damaged',29,6536522756),('DUC744','2018-03-03','Southington CT','Reynoldsburg OH','2018-09-24','Heavily Damaged',29,6536522756),('EBF941','2017-05-03','Hoffman Estates IL','Summerville SC','2017-08-20','Perfect',7,4957595344),('EBF941','2018-05-03','Hoffman Estates IL','Summerville SC','2018-08-20','Perfect',7,4957595344),('F3E364','2017-04-04','Oak Creek WI','East Orange NJ','2017-06-20','Perfect',10,8711825121),('F3E364','2018-04-04','Oak Creek WI','East Orange NJ','2018-06-20','Perfect',10,8711825121),('FQY662','2017-03-17','East Orange NJ','Conway SC','2017-10-20','Perfect',17,1322832573),('FQY662','2018-03-17','East Orange NJ','Conway SC','2018-10-20','Perfect',17,1322832573),('FYE720','2017-06-27','Summerville SC','Southington CT','2017-11-09','Heavily damaged',24,3406307655),('FYE720','2018-06-27','Summerville SC','Southington CT','2018-11-09','Heavily Damaged',24,3406307655),('HVW231','2017-01-03','Southington CT','East Orange NJ','2017-07-15','Slight damages',20,8635641772),('HVW231','2018-01-03','Southington CT','East Orange NJ','2018-07-15','Slight Damages',20,3028318468),('JCE569','2017-02-05','Hoffman Estates IL','Reynoldsburg OH','2017-06-10','Perfect',14,7291136612),('JCE569','2018-02-05','Hoffman Estates IL','Reynoldsburg OH','2018-06-10','Perfect',14,7291136612),('JGD403','2017-01-11','Reynoldsburg OH','Snellville GA','2017-11-08','Perfect',21,8352500035),('JGD403','2018-01-11','Reynoldsburg OH','Snellville GA','2018-11-08','Perfect',21,8352500035),('KXD520','2017-02-20','Reynoldsburg OH','Reynoldsburg OH','2017-11-07','Slight damages',16,7919858173),('KXD520','2018-02-20','Reynoldsburg OH','Reynoldsburg OH','2018-11-07','Slight Damages',16,7919858173),('LBT905','2017-03-21','Elizabeth City NC','Reynoldsburg OH','2017-08-22','Heavily damaged',3,9087972142),('LBT905','2018-03-21','Elizabeth City NC','Reynoldsburg OH','2018-08-22','Heavily Damaged',3,9087972142),('MMY807','2017-01-11','East Orange NJ','Hoffman Estates IL','2017-06-01','Heavily damaged',18,2006356912),('MMY807','2018-01-11','East Orange NJ','Hoffman Estates IL','2018-06-01','Heavily Damaged',18,2006356912),('QZC986','2017-03-09','Southington CT','Summerville SC','2017-10-15','Perfect',9,2119053821),('QZC986','2018-03-09','Southington CT','Summerville SC','2018-10-15','Perfect',9,2119053821),('RWE302','2017-01-19','Summerville SC','East Orange NJ','2017-10-19','Slight damages',19,3406307655),('RWE302','2018-01-19','Summerville SC','East Orange NJ','2018-10-19','Slight Damages',19,3406307655),('T12278','2017-02-19','Gastonia NC','Snellville GA','2017-11-04','Heavily damaged',6,4567626504),('T12278','2018-02-19','Gastonia NC','Snellville GA','2018-11-04','Heavily Damaged',6,4567626504),('VVY346','2017-03-19','Oak Creek WI','Summerville SC','2017-11-09','Perfect',4,8905039882),('VVY346','2018-03-19','Oak Creek WI','Summerville SC','2018-11-09','Perfect',4,8905039882),('VYC992','2017-06-11','Summerville SC','Summerville SC','2017-11-19','Heavily damaged',2,8880826355),('VYC992','2018-06-11','Summerville SC','Summerville SC','2018-11-19','Heavily Damaged',2,8880826355),('WE0615','2017-02-21','Hoffman Estates IL','Snellville GA','2017-11-20','Perfect',27,4957595344),('WE0615','2018-02-21','Hoffman Estates IL','Snellville GA','2018-11-20','Perfect',27,4957595344);
/*!40000 ALTER TABLE `rents` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `rents_BEFORE_INSERT` BEFORE INSERT ON `rents` FOR EACH ROW BEGIN
	set new.start_date = curdate();
    if new.finish_date < new.start_date then
			SIGNAL SQLSTATE VALUE '45000'
					SET MESSAGE_TEXT = '[table:rents] - finish date cannot be before start date';
			END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `rents_BEFORE_UPDATE` BEFORE UPDATE ON `rents` FOR EACH ROW BEGIN
	set new.start_date = old.start_date;
    if new.finish_date < new.start_date then
			SIGNAL SQLSTATE VALUE '45000'
					SET MESSAGE_TEXT = '[table:rents] - finish date cannot be before start date';
			END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `rents_with_payments_customers_vehicles_and_employees`
--

--
-- το view αυτο ενωνει στηλες απο τους πινακας rent, payment_transaction, customers, vehicles, employees
-- μεσω αυτου προβαλλονται πληροφοριες για τις ενοικιασεις στην εφαρμογη μας
-- περιλαμβανονται πληροφοριες για το ονομα του πελατη και του υπαλληλου καθως και για το οχημα
--

DROP TABLE IF EXISTS `rents_with_payments_customers_vehicles_and_employees`;
/*!50001 DROP VIEW IF EXISTS `rents_with_payments_customers_vehicles_and_employees`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `rents_with_payments_customers_vehicles_and_employees` AS SELECT 
 1 AS `start_date`,
 1 AS `finish_date`,
 1 AS `start_location`,
 1 AS `finish_location`,
 1 AS `return_state`,
 1 AS `customer_id`,
 1 AS `customer_first_name`,
 1 AS `customer_last_name`,
 1 AS `customer_irs_number`,
 1 AS `vehicle_license_plate`,
 1 AS `vehicle_name`,
 1 AS `employee_irs_number`,
 1 AS `employee_first_name`,
 1 AS `employee_last_name`,
 1 AS `amount`,
 1 AS `method`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `reserves`
--

--
-- ο πινακας reserves περιεχει πληροφοριες για τις κρατησεις
--

DROP TABLE IF EXISTS `reserves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserves` (
  `license_plate` varchar(10) NOT NULL,
  `start_date` date NOT NULL,
  `start_location` varchar(45) NOT NULL,
  `finish_location` varchar(45) NOT NULL,
  `finish_date` date NOT NULL,
  `paid` bit(1) NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`license_plate`,`start_date`),
  KEY `reserves_customers_fk_idx` (`customer_id`),
  CONSTRAINT `reserves_customers_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reserves_vehicles_fk` FOREIGN KEY (`license_plate`) REFERENCES `vehicles` (`license_plate`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserves`
--

--
-- εχουμε δυο triggers BEFORE INSERT και BEFORE UPDATE που διασφαλιζουν οτι το finish date
-- δεν οριζεται πριν απο το start date και οτι το start date αφορα το μελλον
--

LOCK TABLES `reserves` WRITE;
/*!40000 ALTER TABLE `reserves` DISABLE KEYS */;
INSERT INTO `reserves` VALUES ('0DO553','2018-03-01','Gastonia NC','Southington CT','2018-11-21','\0',14),('12D468','2018-01-16','Conway SC','Southington CT','2018-11-01','\0',12),('19E686','2018-03-13','Snellville GA','Summerville SC','2018-10-15','\0',18),('566854','2018-03-11','Oak Creek WI','Gastonia NC','2018-11-27','\0',5),('6TF315','2018-01-16','Snellville GA','Gastonia NC','2018-07-26','\0',17),('8RV384','2018-07-26','Summerville SC','Oak Creek WI','2018-11-21','\0',25),('8ZS429','2018-02-24','Conway SC','Oak Creek WI','2018-06-21','\0',4),('BFC629','2018-01-02','Elizabeth City NC','Oak Creek WI','2018-07-27','\0',16),('BFY557','2018-05-12','Summerville SC','Southington CT','2018-10-22','\0',6),('CJT264','2018-04-18','Summerville SC','Oak Creek WI','2018-11-24','\0',2),('CRC435','2018-08-24','Reynoldsburg OH','Summerville SC','2018-11-15','\0',9),('DUC744','2018-02-02','Southington CT','Gastonia NC','2018-11-13','\0',22),('EBF941','2018-04-12','Hoffman Estates IL','Oak Creek WI','2018-08-26','\0',28),('F3E364','2018-01-01','Oak Creek WI','Oak Creek WI','2018-03-04','\0',27),('FQY662','2018-01-03','East Orange NJ','Reynoldsburg OH','2018-11-01','\0',3),('FYE720','2018-03-03','Summerville SC','Summerville SC','2018-10-11','\0',11),('HVW231','2018-01-24','Southington CT','Summerville SC','2018-08-21','\0',26),('JCE569','2018-02-20','Hoffman Estates IL','Southington CT','2018-07-23','\0',19),('JGD403','2018-02-27','Reynoldsburg OH','Reynoldsburg OH','2018-11-11','\0',23),('KXD520','2018-01-07','Reynoldsburg OH','Snellville GA','2018-05-19','\0',10),('LBT905','2018-05-26','Elizabeth City NC','Snellville GA','2018-11-01','\0',21),('MMY807','2018-05-14','East Orange NJ','Summerville SC','2018-11-07','\0',8),('QZC986','2018-01-05','Southington CT','Reynoldsburg OH','2018-10-23','\0',1),('RWE302','2018-02-12','Summerville SC','Southington CT','2018-11-13','\0',7),('T12278','2018-02-17','Gastonia NC','Conway SC','2018-08-27','\0',29),('VVY346','2018-03-04','Oak Creek WI','Elizabeth City NC','2018-10-21','\0',24),('VYC992','2018-01-24','Summerville SC','Gastonia NC','2018-07-18','\0',20),('W4Q586','2018-02-10','Southington CT','Elizabeth City NC','2018-11-10','\0',13),('WE0615','2018-01-25','Hoffman Estates IL','Snellville GA','2018-11-11','\0',15);
/*!40000 ALTER TABLE `reserves` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `reserves_BEFORE_INSERT` BEFORE INSERT ON `reserves` FOR EACH ROW BEGIN
	if new.finish_date < new.start_date or new.start_date < curdate() then
		SIGNAL SQLSTATE VALUE '45000'
				SET MESSAGE_TEXT = '[table:reserves] - finish date cannot be before start date and start date cannot be in the past';
		END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `reserves_BEFORE_UPDATE` BEFORE UPDATE ON `reserves` FOR EACH ROW BEGIN
	if new.finish_date < new.start_date or new.start_date < curdate() then
		SIGNAL SQLSTATE VALUE '45000'
				SET MESSAGE_TEXT = '[table:reserves] - finish date cannot be before start date and start date cannot be in the past';
		END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `reserves_with_customers_and_vehicles`
--

--
-- το view reserves_with_customers_and_vehicles ενωνει στηλες απο τους πινακες reserves, customers και vehicles
-- χρησιμοποιειται απο την εφαρμογη για να προβαλλονται πληροφοριες για τις κρατησεις που περιλαμβανουν περισσοτερες πληροφοριες για
-- τους πελατες και τα οχηματα των κρατησεων 
--

DROP TABLE IF EXISTS `reserves_with_customers_and_vehicles`;
/*!50001 DROP VIEW IF EXISTS `reserves_with_customers_and_vehicles`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `reserves_with_customers_and_vehicles` AS SELECT 
 1 AS `start_date`,
 1 AS `finish_date`,
 1 AS `start_location`,
 1 AS `finish_location`,
 1 AS `paid`,
 1 AS `customer_id`,
 1 AS `customer_first_name`,
 1 AS `customer_last_name`,
 1 AS `customer_irs_number`,
 1 AS `vehicle_license_plate`,
 1 AS `vehicle_make`,
 1 AS `vehicle_model`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `stores`
--

--
-- ο πινακας stores περιλαμβανει στοιχεια που αφορουν τα καταστηματα της εταιρειας 
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stores` (
  `store_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(45) NOT NULL,
  `street_number` varchar(10) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `city` varchar(45) NOT NULL,
  PRIMARY KEY (`store_id`),
  UNIQUE KEY `store_id_UNIQUE` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3011 DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (3000,'Shady Lane','241','38106','Beckley, WV'),(3001,'Lilac Lane','217','52804','Baldwinsville, NY'),(3002,'Park Avenue','194','23434','Hephzibah, GA'),(3003,'Willow Lane','214','21044','Wheeling, WV'),(3004,'Woodland Avenue','246','01821','Passaic, NJ'),(3005,'Garden Street','116','29621','Detroit, MI'),(3006,'Eagle Street','82','11714','Alliance, OH'),(3007,'Glenwood Drive','209','90008','East Meadow, NY'),(3008,'Harrison Avenue','198','55343','Barberton, OH'),(3009,'Harrison Avenue','176','32725','Gurnee, IL');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_type`
--

--
-- στον βοηθητικο αυτο πινακα κραταμε αποθηκευμενες τις διαφορες κατηγοριες οχηματων που διαθετει η εταιρεια
--

DROP TABLE IF EXISTS `vehicle_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle_type` (
  `vehicle_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `vehicle_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`vehicle_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_type`
--

LOCK TABLES `vehicle_type` WRITE;
/*!40000 ALTER TABLE `vehicle_type` DISABLE KEYS */;
INSERT INTO `vehicle_type` VALUES (1,'Car'),(2,'Motorcycle'),(3,'ATV'),(4,'Mini Van'),(5,'Truck');
/*!40000 ALTER TABLE `vehicle_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicles`
--

--
-- ο πινακας vehicles περιλαμβανει πολλες πληροφοριες για τα διαθεσιμα αμαξια της εταιρειας
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicles` (
  `license_plate` varchar(10) NOT NULL,
  `model` varchar(45) DEFAULT NULL,
  `type` int(10) unsigned NOT NULL,
  `make` varchar(45) DEFAULT NULL,
  `year` year(4) DEFAULT NULL,
  `kilometers` int(11) unsigned DEFAULT NULL,
  `cylinder_capacity` int(11) unsigned DEFAULT NULL,
  `horse_power` int(11) unsigned DEFAULT NULL,
  `damages` varchar(1000) DEFAULT NULL,
  `malfunctions` varchar(1000) DEFAULT NULL,
  `next_service` date DEFAULT NULL,
  `insurance_exp_date` date DEFAULT NULL,
  `last_service` date DEFAULT NULL,
  `store_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`license_plate`),
  UNIQUE KEY `license_plate_UNIQUE` (`license_plate`),
  KEY `license_plate_vehicle_fk_idx` (`type`),
  KEY `vehicles_stores_fk_idx` (`store_id`),
  CONSTRAINT `vehicles_stores_fk` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `vehicles_vehicle_type_fk` FOREIGN KEY (`type`) REFERENCES `vehicle_type` (`vehicle_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicles`
--

--
-- χρησιμοποιουμε 2 triggers: BEFORE INSERT και BEFORE UPDATE
-- με αυτα διασφαλιζουμε το ετος κατασκευης του αμαξιου να 
-- μην αφορα το μελλον, το next_service να μην ειναι πριν απο το 
-- last_service και οτι το last_service αφορα το παρελθον
--

LOCK TABLES `vehicles` WRITE;
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` VALUES ('0DO553','Yaris',2,'Toyota',2015,106310,6363,385,'None','None','2015-09-23','2015-10-26','2015-03-23',3001),('12D468','Model S',3,'Tesla',2014,121910,3653,183,'None','None','2014-06-17','2014-08-14','2014-05-03',3008),('19E686','Model S',5,'Tesla',2017,146002,2977,908,'Worn-out seats','None','2017-05-23','2017-06-13','2017-04-05',3004),('566854','Stelvio',1,'Alfa Romeo',2015,17944,3588,586,'None','None','2015-05-10','2015-08-10','2015-04-13',3003),('6TF315','Stelvio',2,'Alfa Romeo',2016,38002,4043,705,'None','Malfunctioning electrical windows','2016-10-11','2016-10-13','2016-09-09',3004),('8RV384','Pulsar',5,'Nissan',2014,125019,6619,348,'None','None','2014-05-16','2014-06-12','2014-04-09',3005),('8ZS429','Model S',5,'Tesla',2011,100558,8419,795,'None','None','2011-07-01','2011-09-06','2011-02-24',3008),('9XX243','Model S',3,'Tesla',2012,154953,2831,197,'None','Unresponsive brakes','2012-08-06','2012-11-05','2012-04-22',3002),('BFC629','A1',3,'Audi',2012,161283,8461,175,'None','Unresponsive brakes','2012-10-11','2012-10-13','2012-09-05',3009),('BFY557','M2',5,'BMW',2015,138254,6195,785,'None','None','2015-06-22','2015-08-12','2015-03-06',3005),('CJT264','A1',3,'Audi',2017,165074,4668,502,'None','None','2017-06-03','2017-07-13','2017-03-01',3005),('CRC435','M2',1,'BMW',2012,85613,6764,419,'Out of service','None','2012-10-13','2012-11-23','2012-05-09',3007),('DUC744','Yaris',2,'Toyota',2012,2883,2126,179,'None','None','2012-07-18','2012-08-19','2012-07-16',3006),('EBF941','Stonic',2,'Kia',2012,180182,5890,494,'None','None','2012-05-08','2012-10-20','2012-04-16',3002),('F3E364','Yaris',2,'Toyota',2011,176358,9826,875,'None','None','2011-10-07','2011-10-19','2011-06-11',3003),('FQY662','Pulsar',4,'Nissan',2016,197917,2166,77,'None','None','2016-05-18','2016-07-03','2016-04-14',3000),('FYE720','M2',1,'BMW',2015,152802,9003,143,'None','None','2015-08-02','2015-11-22','2015-07-19',3005),('HVW231','Stelvio',2,'Alfa Romeo',2011,179236,1443,593,'None','Malfunctioning electrical windows','2011-04-08','2011-10-10','2011-04-05',3006),('JCE569','Stonic',5,'Kia',2017,163463,8577,509,'None','None','2017-06-16','2017-09-11','2017-02-11',3002),('JGD403','Impreza',4,'Subaru',2014,81038,1092,774,'Worn-out seats','None','2014-08-26','2014-09-18','2014-07-02',3007),('KXD520','Stonic',1,'Kia',2014,80037,9039,191,'Light scratches','None','2014-08-25','2014-09-11','2014-07-17',3007),('LBT905','Pulsar',4,'Nissan',2013,147257,5791,674,'Flickering headlight','None','2013-05-12','2013-10-02','2013-03-23',3009),('MMY807','Impreza',5,'Subaru',2017,137457,5521,345,'None','None','2017-07-19','2017-09-25','2017-06-20',3000),('QZC986','GLA',1,'Mercedes-Benz',2015,91476,6735,716,'None','Multimedia center out of order','2015-07-19','2015-09-25','2015-05-10',3006),('RWE302','M2',2,'BMW',2013,142314,8082,732,'None','None','2013-05-10','2013-09-21','2013-05-09',3005),('T12278','Stonic',1,'Kia',2016,97903,9015,275,'Light scratches','None','2016-07-20','2016-08-21','2016-06-13',3001),('VVY346','A1',3,'Audi',2012,65301,7480,502,'None','None','2012-06-03','2012-11-26','2012-05-16',3003),('VYC992','A1',3,'Audi',2015,109532,1805,83,'Out of service','None','2015-06-19','2015-07-08','2015-05-07',3005),('W4Q586','M2',5,'BMW',2015,142340,3583,710,'Light scratches','None','2015-09-03','2015-10-26','2015-06-01',3006),('WE0615','A1',1,'Audi',2014,133435,7199,495,'None','Malfunctioning electrical windows','2014-08-08','2014-08-26','2014-02-04',3002);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `vehicles_BEFORE_INSERT` BEFORE INSERT ON `vehicles` FOR EACH ROW BEGIN
	if new.year > year(curdate()) or new.next_service <= new.last_service or new.next_service < curdate() or new.last_service > curdate() then
			SIGNAL SQLSTATE VALUE '45000'
					SET MESSAGE_TEXT = '[table:vehicles] - wrong year value or next service or old service date';
			END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `vehicles_BEFORE_UPDATE` BEFORE UPDATE ON `vehicles` FOR EACH ROW BEGIN
	if new.year > year(curdate()) or new.next_service <= new.last_service or new.next_service < curdate() or new.last_service > curdate() then
			SIGNAL SQLSTATE VALUE '45000'
					SET MESSAGE_TEXT = '[table:vehicles] - wrong year value or next service or old service date';
			END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `vehicles_view`
--

--
-- το view vehicles_view χρειαστηκε γιατι προβαλλει σωστα τον τυπο του 
-- οχηματος και αναφερει την πολη στην οποια βρισκεται το καταστημα στο -
-- οποιο ανηκει το οχημα
--

DROP TABLE IF EXISTS `vehicles_view`;
/*!50001 DROP VIEW IF EXISTS `vehicles_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vehicles_view` AS SELECT 
 1 AS `make`,
 1 AS `model`,
 1 AS `license_plate`,
 1 AS `type`,
 1 AS `year`,
 1 AS `cylinder_capacity`,
 1 AS `horse_power`,
 1 AS `kilometers`,
 1 AS `damages`,
 1 AS `malfunctions`,
 1 AS `last_service`,
 1 AS `next_service`,
 1 AS `insurance_exp_date`,
 1 AS `city`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `works`
--

--
-- ο πινακας works συνδεει τους υπαλληλους με τα καταστηματα 
-- στα οποια εργαζονται και περιλαμβανει προσθετες πληροφοριες 
-- που αφορουν τη θεση τους στην εταιρεια
--

DROP TABLE IF EXISTS `works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `works` (
  `irs_number` bigint(10) unsigned NOT NULL,
  `store_id` int(10) unsigned NOT NULL,
  `start_date` date NOT NULL,
  `finish_date` date DEFAULT NULL,
  `position` varchar(100) NOT NULL,
  PRIMARY KEY (`irs_number`,`store_id`,`start_date`),
  KEY `works_store_fk_idx` (`store_id`),
  CONSTRAINT `works_employees_fk` FOREIGN KEY (`irs_number`) REFERENCES `employees` (`irs_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `works_stores_fk` FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=greek;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works`
--

--
-- χρησιμοποιουμε BEFORE INSERT και BEFORE UPDATE triggers ωστε το finish date να ειναι μετα απο το start date
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
INSERT INTO `works` VALUES (1165065830,3001,'2018-01-11','2018-07-08','receptionist'),(1165758572,3002,'2018-03-06','2018-06-11','car mechanic'),(1322832573,3000,'2018-04-17','2018-10-24','unassigned'),(1575909685,3005,'2018-05-12','2018-10-15','receptionist'),(1741206407,3006,'2018-01-16','2018-09-18','accountant'),(1837916754,3005,'2018-02-11','2018-11-04','receptionist'),(1917219356,3000,'2018-02-11','2018-11-03','receptionist'),(1966141049,3005,'2018-01-27','2018-09-14','cleaner'),(2006356912,3000,'2018-04-10','2018-09-07','technical inspector'),(2107021272,3002,'2018-02-24','2018-11-23','receptionist'),(2119053821,3006,'2018-01-08','2018-10-26','unassigned'),(2215544904,3002,'2018-03-21','2018-09-01','car mechanic'),(2324933127,3008,'2018-01-09','2018-07-12','car mechanic'),(2539937004,3000,'2018-01-14','2018-09-20','store manager'),(2552953050,3005,'2018-02-04','2018-06-23','receptionist'),(2586308723,3008,'2018-05-02','2018-09-15','clerk'),(2834204605,3003,'2018-02-18','2018-10-16','accountant'),(2847939409,3002,'2018-05-02','2018-11-10','receptionist'),(2938048580,3004,'2018-01-08','2018-09-20','accountant'),(3028318468,3002,'2018-01-22','2018-05-24','cleaner'),(3038656780,3003,'2018-07-11','2018-11-27','cleaner'),(3156224124,3007,'2018-05-18','2018-10-19','receptionist'),(3406307655,3005,'2018-06-27','2018-11-27','technical personnel'),(3570954653,3004,'2018-04-10','2018-10-01','clerk'),(3628769681,3007,'2018-02-14','2018-11-13','technical inspector'),(3640615726,3001,'2018-01-02','2018-09-14','unassigned'),(3769054732,3006,'2018-02-08','2018-08-19','receptionist'),(3812701156,3006,'2018-01-27','2018-11-04','PR'),(3853465512,3002,'2018-01-14','2018-10-10','store manager'),(3873637988,3006,'2018-05-09','2018-11-09','car mechanic'),(3986540199,3006,'2018-01-21','2018-11-12','cleaner'),(4289831081,3007,'2018-02-19','2018-09-27','receptionist'),(4337208959,3009,'2018-05-12','2018-08-25','technical inspector'),(4353063036,3008,'2018-04-08','2018-11-09','accountant'),(4567626504,3001,'2018-05-12','2018-11-14','receptionist'),(4646251290,3000,'2018-02-23','2018-09-15','receptionist'),(4672589224,3007,'2018-02-04','2018-11-08','car mechanic'),(4957595344,3002,'2018-02-16','2018-07-12','cleaner'),(4989119383,3007,'2018-05-04','2018-11-16','clerk'),(5289428656,3002,'2018-02-09','2018-10-09','technical personnel'),(5328622135,3008,'2018-03-05','2018-08-21','car mechanic'),(5374858026,3001,'2018-01-18','2018-08-20','receptionist'),(5581657218,3009,'2018-02-25','2018-08-09','cleaner'),(5787781979,3001,'2018-01-20','2018-10-14','store manager'),(5856038261,3008,'2018-04-08','2018-10-07','technical personnel'),(6166652264,3007,'2018-01-14','2018-11-06','receptionist'),(6188555091,3003,'2018-08-05','2018-10-21','receptionist'),(6309652985,3006,'2018-01-18','2018-07-27','clerk'),(6341148139,3002,'2018-02-11','2018-07-15','clerk'),(6344570781,3003,'2018-01-17','2018-07-16','receptionist'),(6434736286,3001,'2018-01-23','2018-09-24','receptionist'),(6458643760,3008,'2018-04-15','2018-10-14','car mechanic'),(6471876059,3006,'2018-01-15','2018-08-09','cleaner'),(6524311213,3006,'2018-03-04','2018-09-19','car mechanic'),(6536522756,3006,'2018-01-10','2018-09-14','car mechanic'),(6629012060,3005,'2018-01-04','2018-10-03','cleaner'),(6835632574,3005,'2018-03-09','2018-10-08','store manager'),(6904284705,3001,'2018-01-05','2018-10-25','technical personnel'),(6933598590,3001,'2018-01-25','2018-11-01','receptionist'),(7025710825,3008,'2018-04-11','2018-10-07','cleaner'),(7216317287,3008,'2018-01-16','2018-09-08','technical personnel'),(7223886886,3006,'2018-02-10','2018-08-10','store manager'),(7291136612,3002,'2018-03-07','2018-10-08','receptionist'),(7356572705,3003,'2018-01-06','2018-06-04','receptionist'),(7450196837,3009,'2018-04-23','2018-11-24','technical inspector'),(7540495086,3000,'2018-02-12','2018-11-25','PR'),(7598505993,3004,'2018-02-18','2018-11-12','PR'),(7603857531,3001,'2018-02-11','2018-08-17','receptionist'),(7694714800,3009,'2018-02-09','2018-10-17','PR'),(7908323919,3006,'2018-01-23','2018-07-11','car mechanic'),(7919858173,3007,'2018-01-27','2018-11-16','technical personnel'),(8021506701,3004,'2018-02-08','2018-11-26','receptionist'),(8076750092,3002,'2018-01-16','2018-08-20','cleaner'),(8202042999,3007,'2018-01-13','2018-11-13','receptionist'),(8315854470,3006,'2018-05-06','2018-10-04','cleaner'),(8352500035,3007,'2018-04-15','2018-07-09','receptionist'),(8373664849,3001,'2018-02-05','2018-09-19','receptionist'),(8429702015,3006,'2018-06-08','2018-11-07','cleaner'),(8526176699,3004,'2018-01-15','2018-10-04','PR'),(8583227491,3004,'2018-02-02','2018-11-07','accountant'),(8635641772,3006,'2018-01-13','2018-08-11','PR'),(8639454916,3003,'2018-01-27','2018-11-21','clerk'),(8651752471,3006,'2018-01-10','2018-03-23','PR'),(8697452276,3007,'2018-02-05','2018-07-01','receptionist'),(8711825121,3003,'2018-05-13','2018-11-21','receptionist'),(8739362771,3000,'2018-04-04','2018-10-26','cleaner'),(8751065832,3007,'2018-01-08','2018-04-26','PR'),(8862311299,3000,'2018-01-05','2018-11-19','cleaner'),(8880826355,3005,'2018-05-03','2018-10-03','cleaner'),(8905039882,3003,'2018-04-23','2018-07-11','technical personnel'),(8942667394,3009,'2018-04-23','2018-10-27','store manager'),(9087972142,3009,'2018-01-23','2018-11-19','store manager'),(9129576852,3008,'2018-01-27','2018-08-04','unassigned'),(9154045162,3004,'2018-09-27','2018-11-25','technical personnel'),(9418906948,3001,'2018-01-24','2018-06-18','receptionist'),(9559460020,3005,'2018-02-19','2018-10-03','receptionist'),(9613092079,3001,'2018-01-06','2018-09-15','technical inspector'),(9634305646,3005,'2018-03-06','2018-11-25','receptionist'),(9692593435,3007,'2018-03-16','2018-09-23','clerk'),(9796005491,3003,'2018-08-11','2018-08-25','receptionist');
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `works_BEFORE_INSERT` BEFORE INSERT ON `works` FOR EACH ROW BEGIN
	if new.finish_date <= new.start_date then
    SIGNAL SQLSTATE VALUE '45000'
			SET MESSAGE_TEXT = '[table:works] - finish date cannot be before start date';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `works_BEFORE_UPDATE` BEFORE UPDATE ON `works` FOR EACH ROW BEGIN
	if new.finish_date <= new.start_date then
		SIGNAL SQLSTATE VALUE '45000'
				SET MESSAGE_TEXT = '[table:works] - finish date cannot be before start date';
		END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'rent_a_car_schema'
--

--
-- Dumping routines for database 'rent_a_car_schema'
--

--
-- Final view structure for view `customers_view`
--

/*!50001 DROP VIEW IF EXISTS `customers_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `customers_view` AS select `customers`.`customer_id` AS `customer_id`,`customers`.`irs_number` AS `irs_number`,`customers`.`social_security_number` AS `social_security_number`,`customers`.`first_name` AS `first_name`,`customers`.`last_name` AS `last_name`,`customers`.`driver_license` AS `driver_license`,`customers`.`first_registration` AS `first_registration`,`customers`.`city` AS `city`,`customers`.`postal_code` AS `postal_code`,`customers`.`street` AS `street`,`customers`.`street_number` AS `street_number` from `customers` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `employment_data`
--

/*!50001 DROP VIEW IF EXISTS `employment_data`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `employment_data` AS select `a`.`first_name` AS `first_name`,`a`.`last_name` AS `last_name`,`a`.`irs_number` AS `irs_number`,`a`.`start_date` AS `start_date`,`a`.`finish_date` AS `finish_date`,`a`.`position` AS `position`,`rent_a_car_schema`.`stores`.`city` AS `city` from ((select `rent_a_car_schema`.`works`.`irs_number` AS `irs_number`,`rent_a_car_schema`.`works`.`store_id` AS `store_id`,`rent_a_car_schema`.`works`.`start_date` AS `start_date`,`rent_a_car_schema`.`works`.`finish_date` AS `finish_date`,`rent_a_car_schema`.`works`.`position` AS `position`,`rent_a_car_schema`.`employees`.`first_name` AS `first_name`,`rent_a_car_schema`.`employees`.`last_name` AS `last_name` from (`rent_a_car_schema`.`works` join `rent_a_car_schema`.`employees` on((`rent_a_car_schema`.`works`.`irs_number` = `rent_a_car_schema`.`employees`.`irs_number`)))) `a` join `rent_a_car_schema`.`stores` on((`a`.`store_id` = `rent_a_car_schema`.`stores`.`store_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rents_with_payments_customers_vehicles_and_employees`
--

/*!50001 DROP VIEW IF EXISTS `rents_with_payments_customers_vehicles_and_employees`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `rents_with_payments_customers_vehicles_and_employees` AS select `d`.`start_date` AS `start_date`,`d`.`finish_date` AS `finish_date`,`d`.`start_location` AS `start_location`,`d`.`finish_location` AS `finish_location`,`d`.`return_state` AS `return_state`,`d`.`customer_id` AS `customer_id`,`d`.`customer_first_name` AS `customer_first_name`,`d`.`customer_last_name` AS `customer_last_name`,`d`.`customer_irs_number` AS `customer_irs_number`,`d`.`vehicle_license_plate` AS `vehicle_license_plate`,concat(`d`.`vehicle_make`,' ',`d`.`vehicle_model`) AS `vehicle_name`,`d`.`employee_irs_number` AS `employee_irs_number`,`d`.`employee_first_name` AS `employee_first_name`,`d`.`employee_last_name` AS `employee_last_name`,`d`.`amount` AS `amount`,`d`.`method` AS `method` from (select `c`.`start_date` AS `start_date`,`c`.`finish_date` AS `finish_date`,`c`.`start_location` AS `start_location`,`c`.`finish_location` AS `finish_location`,`c`.`return_state` AS `return_state`,`c`.`customer_id` AS `customer_id`,`c`.`customer_first_name` AS `customer_first_name`,`c`.`customer_last_name` AS `customer_last_name`,`c`.`customer_irs_number` AS `customer_irs_number`,`c`.`license_plate` AS `vehicle_license_plate`,`c`.`make` AS `vehicle_make`,`c`.`model` AS `vehicle_model`,`c`.`irs_number` AS `employee_irs_number`,`c`.`first_name` AS `employee_first_name`,`c`.`last_name` AS `employee_last_name`,`rent_a_car_schema`.`payment_transaction`.`payment_amount` AS `amount`,`rent_a_car_schema`.`payment_transaction`.`payment_method` AS `method` from ((select `b`.`rents_irs_number` AS `rents_irs_number`,`b`.`start_date` AS `start_date`,`b`.`finish_date` AS `finish_date`,`b`.`start_location` AS `start_location`,`b`.`finish_location` AS `finish_location`,`b`.`return_state` AS `return_state`,`b`.`customer_id` AS `customer_id`,`b`.`customer_first_name` AS `customer_first_name`,`b`.`customer_last_name` AS `customer_last_name`,`b`.`customer_irs_number` AS `customer_irs_number`,`b`.`license_plate` AS `license_plate`,`b`.`model` AS `model`,`b`.`make` AS `make`,`rent_a_car_schema`.`employees`.`irs_number` AS `irs_number`,`rent_a_car_schema`.`employees`.`first_name` AS `first_name`,`rent_a_car_schema`.`employees`.`last_name` AS `last_name` from ((select `a`.`rents_irs_number` AS `rents_irs_number`,`a`.`start_date` AS `start_date`,`a`.`finish_date` AS `finish_date`,`a`.`start_location` AS `start_location`,`a`.`finish_location` AS `finish_location`,`a`.`return_state` AS `return_state`,`a`.`customer_id` AS `customer_id`,`a`.`customer_first_name` AS `customer_first_name`,`a`.`customer_last_name` AS `customer_last_name`,`a`.`customer_irs_number` AS `customer_irs_number`,`rent_a_car_schema`.`vehicles`.`license_plate` AS `license_plate`,`rent_a_car_schema`.`vehicles`.`model` AS `model`,`rent_a_car_schema`.`vehicles`.`make` AS `make` from ((select `rent_a_car_schema`.`rents`.`license_plate` AS `lp`,`rent_a_car_schema`.`rents`.`irs_number` AS `rents_irs_number`,`rent_a_car_schema`.`rents`.`start_date` AS `start_date`,`rent_a_car_schema`.`rents`.`finish_date` AS `finish_date`,`rent_a_car_schema`.`rents`.`start_location` AS `start_location`,`rent_a_car_schema`.`rents`.`finish_location` AS `finish_location`,`rent_a_car_schema`.`rents`.`return_state` AS `return_state`,`rent_a_car_schema`.`customers`.`customer_id` AS `customer_id`,`rent_a_car_schema`.`customers`.`first_name` AS `customer_first_name`,`rent_a_car_schema`.`customers`.`last_name` AS `customer_last_name`,`rent_a_car_schema`.`customers`.`irs_number` AS `customer_irs_number` from (`rent_a_car_schema`.`rents` left join `rent_a_car_schema`.`customers` on((`rent_a_car_schema`.`rents`.`customer_id` = `rent_a_car_schema`.`customers`.`customer_id`)))) `a` left join `rent_a_car_schema`.`vehicles` on((`a`.`lp` = `rent_a_car_schema`.`vehicles`.`license_plate`)))) `b` left join `rent_a_car_schema`.`employees` on((`b`.`rents_irs_number` = `rent_a_car_schema`.`employees`.`irs_number`)))) `c` left join `rent_a_car_schema`.`payment_transaction` on(((`c`.`license_plate` = `rent_a_car_schema`.`payment_transaction`.`license_plate`) and (`c`.`start_date` = `rent_a_car_schema`.`payment_transaction`.`start_date`))))) `d` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `reserves_with_customers_and_vehicles`
--

/*!50001 DROP VIEW IF EXISTS `reserves_with_customers_and_vehicles`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `reserves_with_customers_and_vehicles` AS select `c`.`start_date` AS `start_date`,`c`.`finish_date` AS `finish_date`,`c`.`start_location` AS `start_location`,`c`.`finish_location` AS `finish_location`,`c`.`paid` AS `paid`,`c`.`customer_id` AS `customer_id`,`c`.`customer_first_name` AS `customer_first_name`,`c`.`customer_last_name` AS `customer_last_name`,`c`.`customer_irs_number` AS `customer_irs_number`,`c`.`license_plate` AS `vehicle_license_plate`,`c`.`make` AS `vehicle_make`,`c`.`model` AS `vehicle_model` from (select `b`.`lp` AS `lp`,`b`.`start_date` AS `start_date`,`b`.`start_location` AS `start_location`,`b`.`finish_date` AS `finish_date`,`b`.`finish_location` AS `finish_location`,`b`.`paid` AS `paid`,`b`.`customer_id` AS `customer_id`,`b`.`customer_first_name` AS `customer_first_name`,`b`.`customer_last_name` AS `customer_last_name`,`b`.`customer_irs_number` AS `customer_irs_number`,`b`.`license_plate` AS `license_plate`,`b`.`model` AS `model`,`b`.`type` AS `type`,`b`.`make` AS `make`,`b`.`year` AS `year`,`b`.`kilometers` AS `kilometers`,`b`.`cylinder_capacity` AS `cylinder_capacity`,`b`.`horse_power` AS `horse_power`,`b`.`damages` AS `damages`,`b`.`malfunctions` AS `malfunctions`,`b`.`next_service` AS `next_service`,`b`.`insurance_exp_date` AS `insurance_exp_date`,`b`.`last_service` AS `last_service`,`b`.`store_id` AS `store_id` from (select `a`.`lp` AS `lp`,`a`.`start_date` AS `start_date`,`a`.`start_location` AS `start_location`,`a`.`finish_date` AS `finish_date`,`a`.`finish_location` AS `finish_location`,`a`.`paid` AS `paid`,`a`.`customer_id` AS `customer_id`,`a`.`customer_first_name` AS `customer_first_name`,`a`.`customer_last_name` AS `customer_last_name`,`a`.`customer_irs_number` AS `customer_irs_number`,`rent_a_car_schema`.`vehicles`.`license_plate` AS `license_plate`,`rent_a_car_schema`.`vehicles`.`model` AS `model`,`rent_a_car_schema`.`vehicles`.`type` AS `type`,`rent_a_car_schema`.`vehicles`.`make` AS `make`,`rent_a_car_schema`.`vehicles`.`year` AS `year`,`rent_a_car_schema`.`vehicles`.`kilometers` AS `kilometers`,`rent_a_car_schema`.`vehicles`.`cylinder_capacity` AS `cylinder_capacity`,`rent_a_car_schema`.`vehicles`.`horse_power` AS `horse_power`,`rent_a_car_schema`.`vehicles`.`damages` AS `damages`,`rent_a_car_schema`.`vehicles`.`malfunctions` AS `malfunctions`,`rent_a_car_schema`.`vehicles`.`next_service` AS `next_service`,`rent_a_car_schema`.`vehicles`.`insurance_exp_date` AS `insurance_exp_date`,`rent_a_car_schema`.`vehicles`.`last_service` AS `last_service`,`rent_a_car_schema`.`vehicles`.`store_id` AS `store_id` from ((select `rent_a_car_schema`.`reserves`.`license_plate` AS `lp`,`rent_a_car_schema`.`reserves`.`start_date` AS `start_date`,`rent_a_car_schema`.`reserves`.`start_location` AS `start_location`,`rent_a_car_schema`.`reserves`.`finish_date` AS `finish_date`,`rent_a_car_schema`.`reserves`.`finish_location` AS `finish_location`,`rent_a_car_schema`.`reserves`.`paid` AS `paid`,`rent_a_car_schema`.`customers`.`customer_id` AS `customer_id`,`rent_a_car_schema`.`customers`.`first_name` AS `customer_first_name`,`rent_a_car_schema`.`customers`.`last_name` AS `customer_last_name`,`rent_a_car_schema`.`customers`.`irs_number` AS `customer_irs_number` from (`rent_a_car_schema`.`reserves` left join `rent_a_car_schema`.`customers` on((`rent_a_car_schema`.`reserves`.`customer_id` = `rent_a_car_schema`.`customers`.`customer_id`)))) `a` left join `rent_a_car_schema`.`vehicles` on((`a`.`lp` = `rent_a_car_schema`.`vehicles`.`license_plate`)))) `b`) `c` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vehicles_view`
--

/*!50001 DROP VIEW IF EXISTS `vehicles_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vehicles_view` AS select `a`.`make` AS `make`,`a`.`model` AS `model`,`a`.`license_plate` AS `license_plate`,`a`.`type` AS `type`,`a`.`year` AS `year`,`a`.`cylinder_capacity` AS `cylinder_capacity`,`a`.`horse_power` AS `horse_power`,`a`.`kilometers` AS `kilometers`,`a`.`damages` AS `damages`,`a`.`malfunctions` AS `malfunctions`,`a`.`last_service` AS `last_service`,`a`.`next_service` AS `next_service`,`a`.`insurance_exp_date` AS `insurance_exp_date`,`rent_a_car_schema`.`stores`.`city` AS `city` from ((select `rent_a_car_schema`.`vehicles`.`make` AS `make`,`rent_a_car_schema`.`vehicles`.`model` AS `model`,`rent_a_car_schema`.`vehicles`.`license_plate` AS `license_plate`,`rent_a_car_schema`.`vehicles`.`year` AS `year`,`rent_a_car_schema`.`vehicles`.`cylinder_capacity` AS `cylinder_capacity`,`rent_a_car_schema`.`vehicles`.`horse_power` AS `horse_power`,`rent_a_car_schema`.`vehicles`.`kilometers` AS `kilometers`,`rent_a_car_schema`.`vehicles`.`damages` AS `damages`,`rent_a_car_schema`.`vehicles`.`malfunctions` AS `malfunctions`,`rent_a_car_schema`.`vehicles`.`last_service` AS `last_service`,`rent_a_car_schema`.`vehicles`.`next_service` AS `next_service`,`rent_a_car_schema`.`vehicles`.`insurance_exp_date` AS `insurance_exp_date`,`rent_a_car_schema`.`vehicles`.`store_id` AS `store_id`,`rent_a_car_schema`.`vehicle_type`.`vehicle_type` AS `type` from (`rent_a_car_schema`.`vehicles` left join `rent_a_car_schema`.`vehicle_type` on((`rent_a_car_schema`.`vehicles`.`type` = `rent_a_car_schema`.`vehicle_type`.`vehicle_type_id`)))) `a` left join `rent_a_car_schema`.`stores` on((`a`.`store_id` = `rent_a_car_schema`.`stores`.`store_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-10 18:22:42
