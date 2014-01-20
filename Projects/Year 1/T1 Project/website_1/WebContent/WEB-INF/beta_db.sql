-- MySQL dump 10.13  Distrib 5.5.28, for osx10.6 (i386)
--
-- Host: localhost    Database: project_beta_database
-- ------------------------------------------------------
-- Server version	5.5.28-log

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
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dishes` (
  `DishID` int(11) NOT NULL AUTO_INCREMENT,
  `DishName` varchar(50) NOT NULL,
  `DishPrice` int(11) NOT NULL,
  `DishType` varchar(15) NOT NULL DEFAULT 'Not Defined.',
  `DishDescription` text NOT NULL,
  `DishCuisine` varchar(20) NOT NULL DEFAULT 'Not Specified.',
  `DishContains` varchar(50) NOT NULL DEFAULT 'Not Specified.',
  `RestName` varchar(50) NOT NULL,
  PRIMARY KEY (`DishID`),
  KEY `RestName` (`RestName`),
  CONSTRAINT `dishes_ibfk_1` FOREIGN KEY (`RestName`) REFERENCES `restaurant` (`RestName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES (1,'Ceasarsallad',80,'Main course','Kyckling, grana padano, bacon.','Not Specified.','Chicken','L\'s Kitchen'),(2,'Grönsaksfylld Paprika',80,'Main course','Getost, ruccola, pinjenötter.','Not Specified.','Vegiterian','L\'s Kitchen'),(3,'Köttbullar',80,'Main course','Gräddsås, potatispuré, lingon.','Not Specified.','Meat','L\'s Kitchen'),(4,'Mixsallad',80,'Main course','rökt lax, potatis, ägg, ansjovisdressing','Not Specified.','Fish','L\'s Kitchen'),(5,'Tortiglione',80,'Main course','Lufttorkad skinka, champinjoner, persilja.','Italian','Meat','L\'s Kitchen'),(6,'Örtbakad sej',80,'Main course','Rostad potatis & fänkål, apelsinvinägrett.\r\n','Not Specified.','Fish','L\'s Kitchen'),(7,'Caesarsallad',80,'Main course','Sallad\'n shit...','Not Specified.','Sallad','Kooperativet'),(8,'Kalvfärsbiff',80,'Main course','Med dijoncreme & en bacon- & savoykålsragu, serveras med rostad klyftpotatis','Not Specified.','Meat','Kooperativet'),(9,'Kyckling Szechuan',80,'Main course','wokade grönsaker med kyckling, serveras med basmatiris. Finns även med biff och som vegetarisk','Not Specified.','Chicken','Kooperativet'),(10,'Rostbiffssallad',80,'Main course','Rostbiff med plocksallad, gurka, tomat, serveras med en dijondressing, toppas med rostad lök.','Not Specified.','Meat','Kooperativet'),(11,'Smörstekt sej ',80,'Main course','Med skagenröra, serveras med potatispuré, toppas med en dill- & ärtskottssallad samt citronklyfta.','Not Specified.','Fish','Kooperativet'),(12,'Tortiglioni con Pepe',80,'Main course','Pasta med strimlad biff i krämig pepparsås, toppas med riven parmesan','Not Specified.','Pasta','Kooperativet'),(13,'Dagens måndag',80,'Not spec.','Kräftrimmad sejfilé servers med aquavitsås, dillkokt potatis samt söt-syrlig rättika- och fänkålssallad.','Not specified.','Fish','Bistrot'),(14,'Dagens måndag',80,'Not spec.','Pannbiff på klassisk manér med rödvinsky, farinbrässerad lök samt råstekta potatis- och morotsslantar.','Not specified.','Meat','Bistrot'),(15,'Dagens måndag',80,'Not spec.','Pasta Tortellini med ricotta och spenat i quattroformagiosås samt zucchini och broccoli.','Not specified.','Pasta','Bistrot'),(16,'Dagens tisdag',80,'Not spec.','Koljafilé gratinerad med vitvinssås, spenat och purjolök serveras med potatispuré smaksatt med mozzarella och cheddarost.','Not specified.','Fish','Bistrot'),(17,'Dagens tisdag',80,'Not spec.','Coq au vin, kycklinggryta med rödvin, sidfläsk och syltlök serveras med jasminris samt gröna brytbönor.','Not specified.','Meat','Bistrot'),(18,'Dagens tisdag',80,'Not spec.','Pasta Tortellini med ricotta och spenat i quattrofirmagiosås samt zucchini och broccoli.','Not specified.','Pasta','Bistrot'),(19,'Dagens onsdag',80,'Not spec.','Pankopanerad flundrafilé med räk- och gräslökshollandaise serveras med dillpotatis och citronklyfta.','Not specified.','Fish','Bistrot'),(20,'Dagens onsdag',80,'Not spec.','Pastramikryddad fläskytterfilé med potatisgratäng och balsamicosky samt semitorkade tomater.','Not specified.','Meat','Bistrot'),(21,'Dagens onsdag',80,'Not spec.','Pasta Tortellini med ricotta och spenat i quattroformagiosås samt zucchini och broccoli.','Not specified.','Pasta','Bistrot'),(22,'Dagens torsdag',80,'Not spec.','Bistrots fiskburgare med klyftpotatis, sallad, tomat samt en chili- och gurkdressing.','Not specified.','Fish','Bistrot'),(23,'Dagens torsdag',80,'Not spec.','Tomat och mozzarella gratinerat kycklingbröst med risotto samt chili- och oliv marinerade grönsaker. Dessert: Bistrots chocklad mousse.','Not specified.','Meat','Bistrot'),(24,'Dagens torsdag',80,'Not spec.','Pasta Tortellini med ricotta och spenat i quattroformagiosås samt zucchini och broccoli.','Not specified.','Pasta','Bistrot'),(25,'Dagens fredag',80,'Not spec.','Örthalstrad lax med parmesansås och basilikasvängd potatis samt ärtskott.','Not specified.','Fish','Bistrot'),(26,'Dagens fredag',80,'Not spec.','Långkokt högrev med tomatiserad rödvinssky, gremolata och rosmarinsbakade rotfrukter.','Not specified.','Meat','Bistrot'),(27,'Dagens fredag',80,'Not spec.','Pasta Tortellini med ricotta och spenat i quattroformagiosås samt zucchini och broccoli.','Not specified.','Pasta','Bistrot'),(28,'Veckans sallad',80,'Not spec.','Kycklingsallad med currydressing och chilimarinerad haloumi.','Not specified.','Sallad','Bistrot'),(29,'Margareta',60,'Pizza','Mozzarella','Italian','Meat','Kapten Nemo\'s Pizzeria'),(30,'Cannibale',60,'Pizza','Lök, Köttfärs','Italian','Meat','Kapten Nemo\'s Pizzeria'),(31,'Reale',75,'Pizza','Oxfilé, lök, Bearnaisesås, ägg','Italian','Meat','Kapten Nemo\'s Pizzeria'),(32,'Jägare',85,'Pizza','Oxfilé, bacon, köttfärs, pepperoni, bearnaisesås','Italian','Meat','Kapten Nemo\'s Pizzeria'),(33,'Kyckling Special',70,'Pizza','Kyckling, paprika, lök, valfri kebabsås','Italian','Meat','Kapten Nemo\'s Pizzeria'),(34,'Kebabpizza 1',75,'Pizza','Kebabkött, lök, pepperoni','Italian','Meat','Kapten Nemo\'s Pizzeria'),(35,'Kebabpizza 5',80,'Pizza','Kebabkött, isbergssallad, lök, tomat, gurka, pepperoni','Italian','Meat','Kapten Nemo\'s Pizzeria'),(36,'Tonfisksallad',70,'Sallad','Isbergssallad, gurka, tomat, lök, majs, citron, oliver, tonfisk','Sallad','Meat','Kapten Nemo\'s Pizzeria'),(37,'Hamburgartallrik',65,'Hamburger','Isbergssallad, gurka, tomat, lök, dressing, pommes frites','American','Meat','Kapten Nemo\'s Pizzeria'),(38,'Bentolåda',95,'Main course','Yakiniku+2 yakitori+2 nigiri+2 maki+sallad+ris','Asian','Sushi','Encounter Asian Cuisine'),(40,'Dumpling',75,'Main course','10 bitar dumpling med ris, sallad','Asian','Sushi','Encounter Asian Cuisine'),(41,'Nudelsoppa',75,'Main course','med kyckling eller biff eller wonton, och grönsaker','Asian','Sushi','Encounter Asian Cuisine'),(42,'Sashimi',75,'Main course','6 Lax,2 räka, 1crabfish,1 avocado, ris och sallad','Asian','Sushi','Encounter Asian Cuisine'),(43,'Sushi-10 bitar',75,'Main course','Sushi-10 bitar','Asian','Sushi','Encounter Asian Cuisine'),(49,'Thai Curry kyckling',75,'Main course','Röd curry kyckling med kokosmjölk, grönsaker och ris','Asian','Sushi','Encounter Asian Cuisine'),(50,'Wokade äggnudlar',75,'Main course','Äggnudlar med kycklingfile, grönsaker','Asian','Sushi','Encounter Asian Cuisine'),(51,'Yakiniku',75,'Main course','Skivad entrecote med grönsaker, ris, yakinikusås och sallad','Asian','Sushi','Encounter Asian Cuisine'),(52,'Yakitori',75,'Main course','5 kycklingspett med ris, teriyakisås och sallad','Asian','Sushi','Encounter Asian Cuisine'),(53,'The Day\'s Pasta Buffet',80,'Lunch','Large pasta buffet, all you can eat!','Buffet','Pasta','Restaurang Äran '),(54,'The Day\'s Soup',70,'Soup','The daily soup, changes every day!','Lunch','Not Specified.','Restaurang Äran '),(55,'Daily Vegetarian Dish',80,'Vegetarian','Daily vegetarian dish, perfect for those who dislike meat!','Not Specified.','Not Specified.','Restaurang Äran '),(56,'Daily Vegetarian Dish',69,'Not Defined.','Daily vegetarian dish','Not Specified.','Not Specified.','Spacys'),(57,'Chicken sallad',76,'Sallad','Chicken Sallad.','Not Specified.','Not Specified.','Slimfood'),(58,'Roast beef with potato sallad',76,'Sallad','Roast beef with a tasty potato salad.','Not Specified.','Not Specified.','Slimfood'),(59,'Cheese and ham salad.',76,'Salad','Cheese and ham salad.','Not Specified.','Not Specified.','Slimfood'),(60,'Shrimp salad',76,'Salad','Shrimp salad','Not Specified.','Contains shrimp.','Slimfood'),(61,'Cray-fish salad',76,'Salad','Cray fish salad.','Not Specified.','Shell fish.','Slimfood'),(62,'Tuna Salad.',76,'Salad','Tuna salad.','Not Specified.','Tuna.','Slimfood'),(63,'Italian Salad.',76,'Salad','Italian salad.','Not Specified.','stuff','Slimfood'),(64,'Greek salad',76,'Salad','greek salad.','Not Specified.','Greeks.','Slimfood'),(65,'Cottage cheese salad.',76,'Salad','Cottage cheese salad. really cheesy.','Not Specified.','Cottage Cheese.','Slimfood'),(66,'Maguro',86,'Fish','Tuna roll.','Sushi','Salmon, rice','Slimfood'),(67,'Shake',76,'Not Defined.','Sushi roll with salmon','Not Specified.','Salmon, rice','Slimfood'),(68,'Yasai',76,'Sushi','Sushi roll','Not Specified.','Cucumber, radish, pickles.','Slimfood'),(69,'California Roll',76,'Sushi','Sushi roll','Not Specified.','Crab, cucumber, avocado, mayonnaise.','Slimfood'),(70,'Philadelphia Roll',76,'sushi','Sushi roll','Sushi','Crab, cucumber, avocado, dressing.','Slimfood');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owners`
--

DROP TABLE IF EXISTS `owners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owners` (
  `OwnerFirstName` varchar(50) NOT NULL,
  `OwnerLastName` varchar(50) NOT NULL,
  `OwnerUserName` varchar(18) NOT NULL,
  `OwnerPassword` varchar(15) NOT NULL,
  `OwnerAddress` varchar(100) NOT NULL,
  `OwnerPhoneNumber` text NOT NULL,
  `OwnerLastActivity` varchar(20) NOT NULL DEFAULT '0000-00-00, 00:00:00',
  `OwnerAccountLevel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`OwnerUserName`),
  KEY `UserName` (`OwnerUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owners`
--

LOCK TABLES `owners` WRITE;
/*!40000 ALTER TABLE `owners` DISABLE KEYS */;
INSERT INTO `owners` VALUES ('Äran','Rest','ÄranRest','food','Diagonalen 6','31772320','0000-00-00, 00:00:00',1),('Owner of Bistrot!','The Lastname','Bistrot','Bistrot','Diagonalen 8, Lindholmspiren, Göteborg','031-22 33 23','2012-11-14, 17:24:31',0),('Carl','Berglund','carl','carliscool','carly','2324242','0000-00-00, 00:00:00',1),('crap','Man','crappy','robin','Bat Lair','999222333','0000-00-00, 00:00:00',2),('Helmut','Tack','helmet','skydd','Götaverksgatan 1, 417 55 Göteborg','078543610','0000-00-00, 00:00:00',0),('Ola','Koivo','Kitchen','kittens','Lindholmspiren 5 417 56 Göteborg','317723950','0000-00-00, 00:00:00',0),('Slim','Food','SlimFood','foodslim','Lindholmsallén 45 (vid busshållplatsen) 417 53 Göteborg','313200085','0000-00-00, 00:00:00',1),('Marita ','Spacy','spacefood','astronaut','Götaverksgatan 4','709253773','0000-00-00, 00:00:00',1),('Makoto','Mamoru','sushi','sushi','Vegagatan 42, 418 53 Göteborg','031-7880665','0000-00-00, 00:00:00',0);
/*!40000 ALTER TABLE `owners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurant` (
  `RestName` varchar(50) NOT NULL,
  `RestAddress` varchar(100) NOT NULL,
  `RestOpeningHours` text NOT NULL,
  `RestDescription` text NOT NULL,
  `RestScore` int(11) NOT NULL,
  `RestVotes` int(11) NOT NULL,
  `RestType` varchar(10) NOT NULL,
  `OwnerUserName` varchar(15) NOT NULL,
  `RestNumber` text NOT NULL,
  PRIMARY KEY (`RestName`),
  KEY `User_Name` (`OwnerUserName`),
  CONSTRAINT `restaurant_ibfk_1` FOREIGN KEY (`OwnerUserName`) REFERENCES `owners` (`OwnerUserName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES ('Bistrot','Diagonalen 8, Lindholmspiren, Göteborg','Lunch, Må-Fr: 11 - 14. Afterwork, Fr: 15 - 21. Övriga tider enligt förfrågan!','Vi serverar lunch, afterwork, anordnar\r\ncatering, event och fester i vår fina lokal. Hör av dig så berättar vi mer!',0,0,'','Bistrot','031-22 33 23'),('Encounter Asian Cuisine','Karlavagnsgatan 16, 417 53 Göteborg','Monday - Friday: 10:00-15:30','Here you can encounter real yummy asian cuisine, from Sushi,Sashimi till hot meals, like YAKINIKU, YAKITORI, fried noodles. Also we have daily special someday. We always use fresh selected ingredients for all our foods. Coffee and tea are included in lunch.\r\n\r\nPlease call in advance for take-away order so you do not have to wait.',0,0,'Asian','sushi','031-7880665'),('Kapten Nemo\'s Pizzeria','Karlavagnsgatan 21, 417 56 Göteborg','Monday-Thursday: 09:00 to 21:00\r\nFriday: 9:00 to 10:00 p.m.\r\nSaturday: 12:00 to 22:00\r\nSunday: 12:00 to 21:00','Pizza, pasta, kebabs, salads, burgers and falafel!\r\nShow off your student card to get a free 33 cl drink with the purchase of any dish.',5,1,'Pizzeria','carl','000000'),('Kooperativet','Götaverksgatan 1., 417 55 Göteborg','Monday-Friday: 11:00-14:00\r\nSaturday-Sunday: closed','The cooperative in Lindholmen consists of Spoon, 14 m2at and La Briciola which serve Swedish, Asian and Italian cuisine.\r\n\r\nWe can do catering, parties, company parties and celebrations.',0,0,'World','helmet',''),('L\'s Kitchen','Lindholmspiren 5, 417 56 Göteborg','Monday-Friday: 11:00-1330','At our food court you\'ll find food with inspiration from all corners of the world, but also home cooking with a modern touch as well as fresh salads and vegetarian options. You pay quickly and easily with your guest card (obtained through your employer or the Visitors Centre) and then pick a kitchen by taste. All of the guests can choose any kitchen before you sit down and eat together at the same table.\r\n\r\nWelcome!',0,0,'World','Kitchen',''),('Restaurang Äran ','Diagonalen 6','Open Monday-Friday 07.00-16.00\r\nLunch 11:00 to 14:00 ','Affordable choices with beautiful location on the River Bank at Lindholmen. Daily specials, catering, conference lunches and refreshments. Quiet location for parties with up to 350 people.\r\n\r\nEmail: \r\ncege@rams.se\r\n\r\nWebsite: \r\nhttp://www.rams.se',0,0,'Buffet','ÄranRest',''),('Slimfood','Lindholmsallén 45, 417 53 Göteborg ','Weekdays 10:00 - 18:00','Health food and sushi specialists.\r\n\r\nhttp://www.slimfood.se',0,0,'Healthy','SlimFood',''),('Spacys','Götaverksgatan 4 ','Weekdays 11:00 - 14:00','Lunch restaurant, mainly swedish food.   \r\n\r\nEmail: \r\nmarita@spacys.se\r\n\r\nWebsite: \r\nhttp://www.spacys.se                     ',0,0,'Swedish','spacefood','');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `ReviewComments` text NOT NULL,
  `RestName` varchar(50) NOT NULL,
  PRIMARY KEY (`ReviewID`),
  KEY `RestName` (`RestName`),
  CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`RestName`) REFERENCES `restaurant` (`RestName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-15 18:30:55
