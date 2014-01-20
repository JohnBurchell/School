-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 09, 2013 at 06:40 PM
-- Server version: 5.5.28
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project_beta_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `dishes`
--

CREATE TABLE IF NOT EXISTS `dishes` (
  `DishID` int(11) NOT NULL AUTO_INCREMENT,
  `DishName` varchar(50) NOT NULL,
  `DishPrice` int(11) NOT NULL,
  `DishType` varchar(15) NOT NULL DEFAULT 'Not Defined.',
  `DishDescription` text NOT NULL,
  `DishCuisine` varchar(20) NOT NULL DEFAULT 'Not Specified.',
  `DishContains` varchar(50) NOT NULL DEFAULT 'Not Specified.',
  `RestName` varchar(50) NOT NULL,
  PRIMARY KEY (`DishID`),
  KEY `RestName` (`RestName`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=85 ;

--
-- Dumping data for table `dishes`
--

INSERT INTO `dishes` (`DishID`, `DishName`, `DishPrice`, `DishType`, `DishDescription`, `DishCuisine`, `DishContains`, `RestName`) VALUES
(1, 'Ceasarsallad', 80, 'Main course', 'Chicken, grana padano, bacon.', 'Not Specified.', 'Chicken', 'L''s Kitchen'),
(2, 'Vegetable filled peppers', 80, 'Main course', 'Goat cheese, ruccola, Pine nuts.', 'Not Specified.', 'Vegiterian', 'L''s Kitchen'),
(3, 'Meatballs', 80, 'Main course', 'Cream sauce, Potato puree, lingon.', 'Not Specified.', 'Meat', 'L''s Kitchen'),
(4, 'Mixed Salad.', 80, 'Main course', 'smoked salmon , potatos, egg, Anchovie sauce dressing', 'Not Specified.', 'Fish', 'L''s Kitchen'),
(5, 'tortellini', 80, 'Main course', 'prosciutto, mushrooms, priscilla.', 'Italian', 'Meat', 'L''s Kitchen'),
(6, 'Herbs and fish', 80, 'Main course', 'Roast potato & fennel, orange vinaigrette.', 'Not Specified.', 'Fish', 'L''s Kitchen'),
(7, 'Caesar salad', 80, 'Main course', 'Chicken, Bacon, Roman salad, red onion, Caesar dressing.', 'Not Specified.', 'Sallad', 'Kooperativet'),
(8, 'Kalvfärsbiff', 80, 'Main course', 'Dijon Mustard & a bacon & vegetables, served with more wedegs.', 'Not Specified.', 'Meat', 'Kooperativet'),
(9, 'Chicken Szechuan', 80, 'Main course', 'Stir fried with chicken served with basmati rice.', 'Not Specified.', 'Chicken', 'Kooperativet'),
(10, 'Roast beef salad', 80, 'Main course', 'Roast beef with salad, cucumber tomato, server with dijon mustard and roast onions.', 'Not Specified.', 'Meat', 'Kooperativet'),
(11, 'Fish', 80, 'Main course', 'Seafood platter', 'Not Specified.', 'Fish', 'Kooperativet'),
(12, 'Tortiglioni con Pepe', 80, 'Main course', 'Beef pasta with pepper sauce\n', 'Not Specified.', 'Pasta', 'Kooperativet'),
(13, 'Daily Monday', 80, 'Not spec.', 'Fish fillet in sauce.', 'Not specified.', 'Fish', 'Bistrot'),
(14, 'Daily Monday', 80, 'Not spec.', 'Beef and red wine sauce', 'Not specified.', 'Meat', 'Bistrot'),
(15, 'Daily Monday', 80, 'Not spec.', 'Pasta Tortellini.', 'Not specified.', 'Pasta', 'Bistrot'),
(16, 'Daily Tuesday', 80, 'Not spec.', 'Fish fillet.', 'Not specified.', 'Fish', 'Bistrot'),
(17, 'Daily Tuesday', 80, 'Not spec.', 'Chicken stew.', 'Not specified.', 'Meat', 'Bistrot'),
(18, 'Daily Tuesday', 80, 'Not spec.', 'Pasta Tortellini with spinach.', 'Not specified.', 'Pasta', 'Bistrot'),
(19, 'Daily Wednesday', 80, 'Not spec.', 'Fish fillet with sauce.', 'Not specified.', 'Fish', 'Bistrot'),
(20, 'Daily  Wednesday', 80, 'Not spec.', 'Pastrami with beef and potato gratain.', 'Not specified.', 'Meat', 'Bistrot'),
(21, 'Daily Wednesday', 80, 'Not spec.', 'Pasta Tortellini with ricotta and cheese.', 'Not specified.', 'Pasta', 'Bistrot'),
(22, 'Daily Thursday', 80, 'Not spec.', 'Bistrots fish burger wtih extras!.', 'Not specified.', 'Fish', 'Bistrot'),
(23, 'Daily Thursday', 80, 'Not spec.', 'Chicken breast with cheese and salad.', 'Not specified.', 'Meat', 'Bistrot'),
(24, 'Daily Thursday', 80, 'Not spec.', 'Pasta Tortellini with ricotta and spinach.', 'Not specified.', 'Pasta', 'Bistrot'),
(25, 'Daily Thursday', 80, 'Not spec.', 'Salmon with Parmesan and potatoes.', 'Not specified.', 'Fish', 'Bistrot'),
(26, 'Daily Friday', 80, 'Not spec.', 'slow cooked prime beef with red wine sauce.', 'Not specified.', 'Meat', 'Bistrot'),
(27, 'Daily Friday', 80, 'Not spec.', 'Pasta Tortellini with ricotta and spinach in a 4 cheese sauce.', 'Not specified.', 'Pasta', 'Bistrot'),
(28, 'Salad of the week', 80, 'Not spec.', 'Chicken salad with curry dressing.', 'Not specified.', 'Sallad', 'Bistrot'),
(29, 'Margareta', 60, 'Pizza', 'Cheese and tomato sauce.', 'Italian', 'Meat', 'Kapten Nemos Pizzeria'),
(30, 'Cannibale', 60, 'Pizza', 'Onion and mince beef.', 'Italian', 'Meat', 'Kapten Nemos Pizzeria'),
(31, 'Reale', 75, 'Pizza', 'fillet, onion, Bearnaisesauces, egg', 'Italian', 'Meat', 'Kapten Nemos Pizzeria'),
(32, 'Hunter', 85, 'Pizza', 'fillet, bacon, mince beef, pepperoni, bearnaisesauce', 'Italian', 'Meat', 'Kapten Nemos Pizzeria'),
(33, 'Chicken Special', 70, 'Pizza', 'Chicken, peppers, onions, optional kebabsauce', 'Italian', 'Meat', 'Kapten Nemos Pizzeria'),
(34, 'Kebab pizza 1', 75, 'Pizza', 'Kebab meat, onion, pepperoni', 'Italian', 'Meat', 'Kapten Nemos Pizzeria'),
(35, 'Kebab pizza 5', 80, 'Pizza', 'Kebab meat, iceberg salad, onion, tomato, cucumber, pepperoni', 'Italian', 'Meat', 'Kapten Nemos Pizzeria'),
(36, 'Tuna Salad', 70, 'Sallad', 'Iceberg salad, cucumber, tomato, onion, sweet corn, lemon, olives, tuna', 'Sallad', 'Meat', 'Kapten Nemos Pizzeria'),
(37, 'Hamburger plate', 65, 'Hamburger', 'Iceberg Salad, cucumber, tomato, onion, dressing, chips', 'American', 'Meat', 'Kapten Nemos Pizzeria'),
(38, 'Bento platter', 95, 'Main course', 'Yakiniku + 2 yakitori + 2 nigiri + 2 maki + salad + rice', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(40, 'Dumplings', 75, 'Main course', '10 bits dumpling with rice, salad', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(41, 'Nudel soup', 75, 'Main course', 'With chicken or beef or wonton, and vegetables', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(42, 'Sashimi', 75, 'Main course', '6 Lax,2 räka, 1 crabfish,1 avocado, rice och salad', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(43, 'Sushi-10 piece', 75, 'Main course', 'Sushi-10 pieces', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(49, 'Thai Curry Chicken', 75, 'Main course', 'Red curry chicken with cocnut milk, vegeatbles and rice', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(50, 'Woked egg noodles', 75, 'Main course', 'Egg noodles with chicken fillet, vegetables.', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(51, 'Yakiniku', 75, 'Main course', 'Slice beef with vegetables, rice, yakiniku sauce and salad.', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(52, 'Yakitori', 75, 'Main course', '5 chicken skewers with rice, teriyakisauce and salad.', 'Asian', 'Sushi', 'Encounter Asian Cuisine'),
(53, 'The Day''s Pasta Buffet', 80, 'Lunch', 'Large pasta buffet, all you can eat!', 'Buffet', 'Pasta', 'Restaurang Äran '),
(54, 'The Day''s Soup', 70, 'Soup', 'The daily soup, changes every day!', 'Lunch', 'Not Specified.', 'Restaurang Äran '),
(55, 'Daily Vegetarian Dish', 80, 'Vegetarian', 'Daily vegetarian dish, perfect for those who dislike meat!', 'Not Specified.', 'Not Specified.', 'Restaurang Äran '),
(56, 'Daily Vegetarian Dish', 69, 'Not Defined.', 'Daily vegetarian dish', 'Not Specified.', 'Not Specified.', 'Spacys'),
(57, 'Chicken sallad', 76, 'Sallad', 'Chcken Salad.                                                            ', 'Not Specified.', '										Not Specified.\r\n					\r\n					\r\n					', 'Slimfood'),
(58, 'Roast beef with potato sallad', 76, 'Sallad', 'Roast beef with a tasty potato salad.', 'Not Specified.', 'Not Specified.', 'Slimfood'),
(59, 'Cheese and ham salad.', 76, 'Salad', 'Cheese and ham salad.', 'Not Specified.', 'Not Specified.', 'Slimfood'),
(60, 'Shrimp salad', 76, 'Salad', 'Shrimp salad', 'Not Specified.', 'Contains shrimp.', 'Slimfood'),
(61, 'Cray-fish salad', 76, 'Salad', 'Cray fish salad.', 'Not Specified.', 'Shell fish.', 'Slimfood'),
(62, 'Tuna Salad.', 76, 'Salad', 'Tuna salad.', 'Not Specified.', 'Tuna.', 'Slimfood'),
(63, 'Italian Salad.', 76, 'Salad', 'Italian salad.', 'Not Specified.', 'stuff', 'Slimfood'),
(64, 'Greek salad', 76, 'Salad', 'greek salad.', 'Not Specified.', 'Greeks.', 'Slimfood'),
(65, 'Cottage cheese salad.', 76, 'Salad', 'Cottage cheese salad. really cheesy.', 'Not Specified.', 'Cottage Cheese.', 'Slimfood'),
(66, 'Maguro', 86, 'Fish', 'Tuna roll.\r\n					', 'Sushi', 'Salmon, rice\r\n					', 'Slimfood'),
(67, 'Shake', 76, 'Not Defined.', 'Sushi roll with salmon', 'Not Specified.', 'Salmon, rice', 'Slimfood'),
(68, 'Yasai', 76, 'Sushi', 'Sushi roll', 'Not Specified.', 'Cucumber, radish, pickles.', 'Slimfood'),
(69, 'California Roll', 76, 'Sushi', 'Sushi roll', 'Not Specified.', 'Crab, cucumber, avocado, mayonnaise.', 'Slimfood'),
(70, 'Philadelphia Roll', 76, 'sushi', 'Sushi roll', 'Sushi', 'Crab, cucumber, avocado, dressing.', 'Slimfood');

-- --------------------------------------------------------

--
-- Table structure for table `owners`
--

CREATE TABLE IF NOT EXISTS `owners` (
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

--
-- Dumping data for table `owners`
--

INSERT INTO `owners` (`OwnerFirstName`, `OwnerLastName`, `OwnerUserName`, `OwnerPassword`, `OwnerAddress`, `OwnerPhoneNumber`, `OwnerLastActivity`, `OwnerAccountLevel`) VALUES
('admin', 'admin', 'admin', 'admin', 'admin    \r\n	', '123', '2013-01-09, 19:33:28', 3),
('Äran', 'Rest', 'ÄranRest', 'food', 'Diagonalen 6', '31772320', '0000-00-00, 00:00:00', 1),
('Owner of Bistrot!', 'The Lastname', 'Bistrot', 'Bistrot', 'Diagonalen 8, Lindholmspiren, Göteborg', '031-22 33 23', '2012-12-31, 10:29:15', 0),
('Carl', 'Berglund', 'carl', 'carliscool', 'carly', '2324242', '2012-11-20, 20:14:25', 1),
('crap', 'Man', 'crappy', 'robin', 'Bat Lair', '999222333', '0000-00-00, 00:00:00', 2),
('Me', 'too', 'hellothere', 'hithere', 'No    \r\n	', '123', '0000-00-00, 00:00:00', 1),
('Helmut', 'Tack', 'helmet', 'skydd', 'Götaverksgatan 1, 417 55 Göteborg', '078543610', '0000-00-00, 00:00:00', 0),
('Ola', 'Koivo', 'Kitchen', 'kittens', 'Lindholmspiren 5 417 56 Göteborg', '317723950', '0000-00-00, 00:00:00', 0),
('Slim', 'Food', 'SlimFood', 'foodslim', '					\r\n		ojkoko			', '313200085', '2013-01-09, 15:13:35', 1),
('Marita ', 'Spacy', 'spacefood', 'astronaut', 'Götaverksgatan 4', '709253773', '0000-00-00, 00:00:00', 1),
('Makoto', 'Mamoru', 'sushi', 'sushi', 'Vegagatan 42, 418 53 Göteborg', '031-7880665', '0000-00-00, 00:00:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE IF NOT EXISTS `restaurant` (
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
  KEY `User_Name` (`OwnerUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`RestName`, `RestAddress`, `RestOpeningHours`, `RestDescription`, `RestScore`, `RestVotes`, `RestType`, `OwnerUserName`, `RestNumber`) VALUES
('Bistrot', 'Diagonalen 8, Lindholmspiren, Göteborg', 'Lunch, Må-Fr: 11 - 14. Afterwork, Fr: 15 - 21. Övriga tider enligt förfrågan!', 'Vi serverar lunch, afterwork, anordnar\r\ncatering, event och fester i vår fina lokal. Hör av dig så berättar vi mer!', 0, 0, '', 'Bistrot', '031-22 33 23'),
('Encounter Asian Cuisine', 'Karlavagnsgatan 16, 417 53 Göteborg', 'Monday - Friday: 10:00-15:30', 'Here you can encounter real yummy asian cuisine, from Sushi,Sashimi till hot meals, like YAKINIKU, YAKITORI, fried noodles. Also we have daily special someday. We always use fresh selected ingredients for all our foods. Coffee and tea are included in lunch.\r\n\r\nPlease call in advance for take-away order so you do not have to wait.', 0, 0, 'Asian', 'sushi', '031-7880665'),
('Kapten Nemos Pizzeria', 'Karlavagnsgatan 21, 417 56 Göteborg', 'Monday-Thursday: 09:00 to 21:00\r\nFriday: 9:00 to 10:00 p.m.\r\nSaturday: 12:00 to 22:00\r\nSunday: 12:00 to 21:00', 'Pizza, pasta, kebabs, salads, burgers and falafel!\r\nShow off your student card to get a free 33 cl drink with the purchase of any dish.', 5, 1, 'Pizzeria', 'carl', '000000'),
('Kooperativet', 'Götaverksgatan 1., 417 55 Göteborg', 'Monday-Friday: 11:00-14:00\r\nSaturday-Sunday: closed', 'The cooperative in Lindholmen consists of Spoon, 14 m2at and La Briciola which serve Swedish, Asian and Italian cuisine.\r\n\r\nWe can do catering, parties, company parties and celebrations.', 0, 0, 'World', 'helmet', ''),
('L''s Kitchen', 'Lindholmspiren 5, 417 56 Göteborg', 'Monday-Friday: 11:00-1330', 'At our food court you''ll find food with inspiration from all corners of the world, but also home cooking with a modern touch as well as fresh salads and vegetarian options. You pay quickly and easily with your guest card (obtained through your employer or the Visitors Centre) and then pick a kitchen by taste. All of the guests can choose any kitchen before you sit down and eat together at the same table.\r\n\r\nWelcome!', 0, 0, 'World', 'Kitchen', ''),
('Restaurang Äran ', 'Diagonalen 6', 'Open Monday-Friday 07.00-16.00\r\nLunch 11:00 to 14:00 ', 'Affordable choices with beautiful location on the River Bank at Lindholmen. Daily specials, catering, conference lunches and refreshments. Quiet location for parties with up to 350 people.\r\n\r\nEmail: \r\ncege@rams.se\r\n\r\nWebsite: \r\nhttp://www.rams.se', 0, 0, 'Buffet', 'ÄranRest', ''),
('Slimfood', 'Lindholmsallén 45, 417 53 Göteborg ', 'Weekdays 10:00 - 18:00', '					Health food and sushi specialists..\r\n\r\nhttp://www.slimfood.se\r\n					', 0, 0, 'Healthy', 'SlimFood', ''),
('Spacys', 'Götaverksgatan 4 ', 'Weekdays 11:00 - 14:00', 'Lunch restaurant, mainly swedish food.   \r\n\r\nEmail: \r\nmarita@spacys.se\r\n\r\nWebsite: \r\nhttp://www.spacys.se                     ', 0, 0, 'Swedish', 'spacefood', '');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `ReviewComments` text NOT NULL,
  `RestName` varchar(50) NOT NULL,
  PRIMARY KEY (`ReviewID`),
  KEY `RestName` (`RestName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `statsdishes`
--

CREATE TABLE IF NOT EXISTS `statsdishes` (
  `DishScoreID` int(11) NOT NULL AUTO_INCREMENT,
  `DishSearch` varchar(50) NOT NULL,
  `NumOfSearches` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`DishScoreID`),
  UNIQUE KEY `DishSearch` (`DishSearch`),
  KEY `DishScoreID` (`DishScoreID`),
  KEY `DishName` (`DishSearch`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `statsdishes`
--

INSERT INTO `statsdishes` (`DishScoreID`, `DishSearch`, `NumOfSearches`) VALUES
(16, 'pencil', 7),
(17, 'Yakitori', 7),
(19, 'Aligator', 7),
(20, 'Chips', 7),
(21, 'Dagen', 7),
(22, 'pizza', 7),
(25, 'yaki', 7),
(27, '', 7),
(34, 'fish', 8);

-- --------------------------------------------------------

--
-- Table structure for table `statsrestaurants`
--

CREATE TABLE IF NOT EXISTS `statsrestaurants` (
  `restID` int(11) NOT NULL AUTO_INCREMENT,
  `numOfSearches` int(11) NOT NULL DEFAULT '0',
  `RestName` varchar(50) NOT NULL,
  PRIMARY KEY (`restID`),
  KEY `RestName` (`RestName`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `statsrestaurants`
--

INSERT INTO `statsrestaurants` (`restID`, `numOfSearches`, `RestName`) VALUES
(13, 0, 'Bistrot'),
(14, 0, 'Encounter Asian Cuisine'),
(15, 2, 'Kapten Nemos Pizzeria'),
(16, 0, 'Kooperativet'),
(17, 2, 'L''s Kitchen'),
(18, 0, 'Restaurang Äran '),
(19, 0, 'Spacys'),
(20, 2, 'Slimfood');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dishes`
--
ALTER TABLE `dishes`
  ADD CONSTRAINT `dishes_ibfk_1` FOREIGN KEY (`RestName`) REFERENCES `restaurant` (`RestName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD CONSTRAINT `restaurant_ibfk_1` FOREIGN KEY (`OwnerUserName`) REFERENCES `owners` (`OwnerUserName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`RestName`) REFERENCES `restaurant` (`RestName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `statsrestaurants`
--
ALTER TABLE `statsrestaurants`
  ADD CONSTRAINT `statsrestaurants_ibfk_1` FOREIGN KEY (`RestName`) REFERENCES `restaurant` (`RestName`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
