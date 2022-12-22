-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.23 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for tickethandling
DROP DATABASE IF EXISTS `tickethandling`;
CREATE DATABASE IF NOT EXISTS `tickethandling` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tickethandling`;

-- Dumping structure for table tickethandling.bookmarks
DROP TABLE IF EXISTS `bookmarks`;
CREATE TABLE IF NOT EXISTS `bookmarks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userid` bigint DEFAULT NULL,
  `ticketid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.bookmarks: ~3 rows (approximately)
DELETE FROM `bookmarks`;
/*!40000 ALTER TABLE `bookmarks` DISABLE KEYS */;
INSERT INTO `bookmarks` (`id`, `userid`, `ticketid`) VALUES
	(1, 7, 15),
	(2, 7, 14),
	(4, 19, 37);
/*!40000 ALTER TABLE `bookmarks` ENABLE KEYS */;

-- Dumping structure for table tickethandling.departments
DROP TABLE IF EXISTS `departments`;
CREATE TABLE IF NOT EXISTS `departments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `depname` varchar(50) NOT NULL DEFAULT '0',
  `depdesc` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.departments: ~10 rows (approximately)
DELETE FROM `departments`;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` (`id`, `depname`, `depdesc`) VALUES
	(1, 'Clothing', 'Clothing industry or garment industry summarizes the types of trade and industry along the production and value chain of clothing and garments, starting with the textile industry, embellishment using embroidery, via the fashion industry to apparel retailers up to trade with second-hand clothes and textile recycling'),
	(2, 'Gaming', 'The gaming industry is considered to be one of the most exciting industries in tech because of its importance to culture, entertainment and technological advancement. PC, console and mobile gaming companies are using the latest in tech to bring their games to the screens of more than two billion people all over the globe.'),
	(3, 'Groceries', 'The Supermarkets and Grocery Stores industry accounts for the largest food retail channel in the United States. Operators in this industry retail general lines of food products, including fresh and prepared meats, poultry and seafood, canned and frozen foods, fresh fruits and vegetables and various dairy products'),
	(4, 'Fossil fuels', 'Mining, drilling, and burning dirty energy are harming the environment and our health. Here’s everything you need to know about fossil fuels, and why we need to embrace a clean energy future.'),
	(5, 'Books', 'The global books market size was valued at USD 138.35 billion in 2021 and is expected to expand at a compound annual growth rate (CAGR) of 1.9% from 2022 to 2030. Increasing consumer spending on books supported by rising incomes and interest, as well as continued innovations in the format that have enhanced the overall reading experience, are among the key factors boosting the market'),
	(6, 'Electronics', 'The Indian Electronics Industry can be categorised into six sub-sectors, namely consumer electronics, industrial electronics, computers, strategic electronics, communication and broadcasting equipment and electronic components.'),
	(7, 'Furniture', 'Furniture industry, all the companies and activities involved in the design, manufacture, distribution, and sale of functional and decorative objects of household equipment'),
	(8, 'Pharmacy', 'The pharmaceutical industry discovers, develops, produces, and markets drugs or pharmaceutical drugs for use as medications to be administered to patients, with the aim to cure them, vaccinate them, or alleviate symptoms'),
	(9, 'Sports', 'Sport industry is an industry in which people, activities, business, and organizations are involved in producing, facilitating, promoting, or organizing any activity, experience, or business enterprise focused on sports'),
	(10, 'Toys', 'Sport industry is an industry in which people, activities, business, and organizations are involved in producing, facilitating, promoting, or organizing any activity, experience, or business enterprise focused on sports.');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;

-- Dumping structure for table tickethandling.emailactivator
DROP TABLE IF EXISTS `emailactivator`;
CREATE TABLE IF NOT EXISTS `emailactivator` (
  `emailid` varchar(50) DEFAULT NULL,
  `emailactivatingtext` longtext,
  `id` int NOT NULL AUTO_INCREMENT,
  `createdat` datetime DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.emailactivator: ~4 rows (approximately)
DELETE FROM `emailactivator`;
/*!40000 ALTER TABLE `emailactivator` DISABLE KEYS */;
INSERT INTO `emailactivator` (`emailid`, `emailactivatingtext`, `id`, `createdat`, `userid`) VALUES
	('bhargav.gandham44@gmail.com', 'efiA7Ss9etJfJiQNuDBDe8dOsTZ8BkgeAuCiiieSu7iYUAWw5MecfL2SfghXEtUeWgTgIubuDf3eeLfYRAiFGifAs82RWKFMStgGFw9VfJScUO1ueg3eSYXui7B6SVVhQgciuc3GgQANgPdkuTNBbiKagfN598u2effVccXQOsK57Juz8zCMw2FPuOffagsasgb33OUe', 11, '2022-08-21 11:46:17', 19),
	('test123@gmai.com', 'MIgIIhiAKte7atDdQtzizEitWcMbzCdt47OggiiGCiu67heMCwcbfWc6iW6c5M5NCgGWYdUtEuHFsTRw1HeRQ4fFJQbeiWLEuZ3fP18GuehC6XKdMwVBiPetfXLiJui3bfIbwee61wIZbgt1u7Mt8ViWe2V8gwciXu3Tt4eGwKgGeuRV7utX3f3t9euQzGuJwMBVuwtH', 12, '2022-08-21 11:48:20', 20),
	('testuser@g.com', 'twzZXtigi8u4fI3FuISNihgiiFNaeiAKguZHzufgiNguSPJ89F13PeutDsgetWwei4Ptu36eaCHgfeuHuHws7QweiPcK9WNA2LuNez5zGiufieufWfuecfCRtwuegMYJOeRgJU4ghwwEPzcfkesJsIfdfEuikegU2uugggGte2IiD2INuHBfufdZHubgKZukttuIK9AI', 13, '2022-08-22 09:06:14', 23),
	('gy@hoi.com', 'ifiPe88TOggB1wwZ9OXCaJ4IeMfkutebwiiitBS5wz3keDeV7RwwUg3siTHkFiRLigfcHEA1ufH21fgdUeizeuJbLBewfg7NOtQeKS1ukRw5zYIg1iePuYutkQBIcF1VRSdhcuX8EgKuiiiWwiEe5KFYe683LCeiHiHOzuUkuwHRMBeWLAGikXfkhVMPPXeNiisDhise', 14, '2022-08-23 00:36:14', 24);
/*!40000 ALTER TABLE `emailactivator` ENABLE KEYS */;

-- Dumping structure for procedure tickethandling.getPieChartCounts
DROP PROCEDURE IF EXISTS `getPieChartCounts`;
DELIMITER //
CREATE PROCEDURE `getPieChartCounts`(
	IN `Orgid` INT,
	IN `Depid` INT
)
BEGIN

SELECT 
COUNT(CASE WHEN tm.`status`= 0 THEN 1 END)  AS openticketcount,
COUNT(CASE WHEN tm.`status`= 1 THEN 1 END)  AS progressticketcount,
COUNT(CASE WHEN tm.`status`= 2 THEN 1 END)  AS closedtcketcount
FROM ticketmetadata tm
WHERE tm.orgid = @Orgid AND tm.depid = @Depid
GROUP BY tm.orgid,tm.depid;

SELECT @Orgid;

END//
DELIMITER ;

-- Dumping structure for procedure tickethandling.getRolenameOnRoleId
DROP PROCEDURE IF EXISTS `getRolenameOnRoleId`;
DELIMITER //
CREATE PROCEDURE `getRolenameOnRoleId`(
	IN `roleid` INT
)
BEGIN
	
	SELECT r.rolename FROM roletbl r WHERE r.id = @roleid ;
	
END//
DELIMITER ;

-- Dumping structure for table tickethandling.mngraccess
DROP TABLE IF EXISTS `mngraccess`;
CREATE TABLE IF NOT EXISTS `mngraccess` (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `key` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.mngraccess: ~1 rows (approximately)
DELETE FROM `mngraccess`;
/*!40000 ALTER TABLE `mngraccess` DISABLE KEYS */;
INSERT INTO `mngraccess` (`username`, `key`) VALUES
	('admin', 'admin');
/*!40000 ALTER TABLE `mngraccess` ENABLE KEYS */;

-- Dumping structure for table tickethandling.organizations
DROP TABLE IF EXISTS `organizations`;
CREATE TABLE IF NOT EXISTS `organizations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `depid` int DEFAULT NULL,
  `orgname` varchar(50) DEFAULT NULL,
  `orgdesc` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.organizations: ~40 rows (approximately)
DELETE FROM `organizations`;
/*!40000 ALTER TABLE `organizations` DISABLE KEYS */;
INSERT INTO `organizations` (`id`, `depid`, `orgname`, `orgdesc`) VALUES
	(1, 4, 'Hp petroleum', 'Hindustan Petroleum Corporation Limited (HPCL) is an Indian oil and gas refining company headquartered in Mumbai.[4][5][6] Since 2018, ONGC has owned a majority stake in the company.[3] The company is ranked 367th on the Fortune Global 500 list of the world\'s biggest corporations as of 2016.[7] On 24 October 2019, the company became a Maharatna PSU.[8]'),
	(2, 4, 'Bharat-Petroleum', 'Bharat Petroleum Corporation Limited is an Indian government-owned oil and gas explorer and producer, headquartered in Mumbai. It operates three refineries in Bina, Kochi and Mumbai'),
	(3, 4, 'UNIPEC', 'UNIPEC America, Inc. is a wholly owned subsidiary of China International United Petroleum and Chemical Co. Ltd., better known as UNIPEC. UNIPEC is the trading arm of SINOPEC, currently No.2 largest company on the Global Fortune 500 list in 2019.'),
	(4, 1, 'Myntra', 'Myntra is a major Indian fashion e-commerce company headquartered in Bengaluru, Karnataka, India.[1] The company was founded in 2007 to sell personalized gift items.[2][3][4][5] In May 2014, Myntra.com was acquired by Flipkart.[6][7][8]'),
	(5, 1, 'Zara', 'ZARA, is a Spanish apparel retailer based in Arteixo, A Coruña, Galicia, Spain. The company specialises in fast fashion, and their products include clothing, accessories, shoes, swimwear, beauty, and perfumes'),
	(6, 1, 'Wallmart', 'Walmart Inc. ( /ˈwɔːlmɑːrt/; formerly Wal-Mart Stores, Inc.) is an American multinational retail corporation that operates a chain of hypermarkets (also called supercenters), discount department stores, and grocery stores from the United States, headquartered in Bentonville, Arkansas.[9] The company was founded by Sam Walton in nearby Rogers, Arkansas in 1962 and incorporated under Delaware General Corporation Law on October 31, 1969. It also owns and operates Sam\'s Club retail warehouses'),
	(7, 1, 'GAP', 'The Gap, Inc.,[5] commonly known as Gap Inc. or Gap (stylized as GAP), is an American worldwide clothing and accessories retailer. Gap was founded in 1969 by Donald Fisher and Doris F. Fisher and is headquartered in San Francisco, California. The company operates four primary divisions: Gap (the namesake banner), Banana Republic, Old Navy, and, Athleta'),
	(8, 3, 'Amigos', 'Amigos de las Américas, or AMIGOS, is a nonprofit organization based in Houston, Texas with 25 chapters across the USA. The Vision of AMIGOS is "A world where each young person becomes a life-long catalyst for social change.'),
	(10, 2, 'XBOX', 'Xbox is a video gaming brand created and owned by Microsoft. The brand consists of five video game consoles, as well as applications (games), streaming services, an online service by the name of Xbox network, and the development arm by the name of Xbox Game Studios. The brand was first introduced in the United States in November 2001, with the launch of the original Xbox console'),
	(11, 2, 'Microsoft Gaming', 'Responsible for developing and publishing some of the biggest franchises in history: Age of Empires, Forza, Gears of War, Halo, Minecraft, Fallout, Microsoft Solitaire, Microsoft Flight Simulator, DOOM, The Elder Scrolls, and many more'),
	(12, 1, 'Aeropostale', 'Aéropostale, also called Aero, is an American shopping mall–based retailer of casual apparel and accessories, principally targeting young adults through its Aéropostale stores. Aéropostale maintains control over its proprietary brands by designing, sourcing, marketing, and selling all of its own merchandise'),
	(13, 2, 'Nintendo', 'Nintendo Co., Ltd. is a Japanese multinational video game company headquartered in Kyoto, Japan. It develops video games and video game consoles. Nintendo was founded in 1889 as Nintendo Karuta by craftsman Fusajiro Yamauchi and originally produced handmade hanafuda playing cards'),
	(14, 2, 'Sony', 'Sony Group Corporation (ソニーグループ株式会社, Sonī Gurūpu kabushiki gaisha, /ˈsoʊni/ SOH-nee), commonly known as Sony and stylized as SONY, is a Japanese multinational conglomerate corporation headquartered in Kōnan, Minato, Tokyo, Japan.[7] As a major technology company, it operates as one of the world\'s largest manufacturers of consumer and professional electronic products, the largest video game console company and the largest video game publisher'),
	(15, 3, 'Flipkart', 'Flipkart is an Indian e-commerce company, headquartered in Bangalore, and incorporated in Singapore as a private limited company. The company initially focused on online book sales before expanding into other product categories such as consumer electronics, fashion, home essentials, groceries, and lifestyle products'),
	(16, 3, 'InstaCart', 'Instacart is an American company that operates a grocery delivery and pick-up service in the United States and Canada. The company offers its services via a website and mobile app. The service allows customers to order groceries from participating retailers with the shopping being done by a personal shopper'),
	(17, 3, 'Reliancefresh', 'Reliance Retail is an Indian retail company and a subsidiary of Reliance Industries Limited. Founded in 2006, it is the largest retailer in India in terms of revenue. Its retail outlets offer foods, groceries, apparel, footwear, toys, home improvement products, electronic goods, and farm implements and inputs.'),
	(18, 3, 'Towncountry', 'Town & Country, formerly the Home Journal and The National Press, is a monthly American lifestyle magazine. It is the oldest continually published general interest magazine in the United States.'),
	(19, 4, 'Esso', 'Esso is a trading name for ExxonMobil. Originally, the name was primarily used by its predecessor Standard Oil of New Jersey after the breakup of the original Standard Oil company in 1911.'),
	(20, 4, 'Shell', 'A shell corporation is a company or corporation that exists only on paper and has no office and no employees, but may have a bank account or may hold passive investments or be the registered owner of assets, such as intellectual property, or ships'),
	(21, 5, 'AmazonKindle', 'Amazon Kindle is a series of e-readers designed and marketed by Amazon. Amazon Kindle devices enable users to browse, buy, download, and read e-books, newspapers, magazines and other digital media via wireless networking to the Kindle Store.[5] The hardware platform, which Amazon subsidiary Lab126 developed, began as a single device in 2007. Currently, it comprises a range of devices, including e-readers with E Ink electronic paper displays and Kindle applications on all major computing platforms'),
	(22, 5, 'AmericanBook', 'American Book Company is a textbook and software publishing company. Its main focus is on standardized test preparation materials. It offers books covering language arts, mathematics, science, and social studies tests. The company also produces transparencies, basic review books, and ACT and SAT preparation books'),
	(23, 6, 'Amazon', 'Amazon.com, Inc. is an American multinational technology company which focuses on e-commerce, cloud computing, digital streaming, and artificial intelligence. It has been referred to as "one of the most influential economic and cultural forces in the world," and is one of the world\'s most valuable brands.'),
	(24, 6, 'Apple', 'Apple Inc. is an American multinational technology company that specializes in consumer electronics, software and online services headquartered in Cupertino, California, United States'),
	(25, 6, 'Lenovo', 'Lenovo Group Limited, often shortened to Lenovo, is a Chinese multinational technology company specializing in designing, manufacturing, and marketing consumer electronics, personal computers, software, business solutions, and related services.'),
	(26, 6, 'LG', 'LG Corporation, formerly Lucky-Goldstar from 1983 to 1995, is a South Korean multinational conglomerate founded by Koo In-hwoi and managed by successive generations of his family. It is the fourth-largest chaebol in South Korea'),
	(27, 6, 'Samsung', 'The Samsung Group is a South Korean multinational manufacturing conglomerate headquartered in Samsung Town, Seoul, South Korea. It comprises numerous affiliated businesses, most of them united under the Samsung brand, and is the largest South Korean chaebol. As of 2020, Samsung has the 8th highest global brand value'),
	(28, 7, 'pepperfry', 'Pepperfry had initially started out as a horizontal online marketplace that focused on facilitating products across the range of categories like fashion and lifestyle but a year later in 2013, the platform shifted towards establishing its place in the category of furnishings, home decor, and related items online'),
	(29, 7, 'Shopclues', 'ShopClues is an Indian online marketplace, owned by Clues Network Pvt. Ltd. The company was established in July 2011 by Sanjay Sethi, Sandeep Aggarwal and Radhika Aggarwal. In 2015, ShopClues was valued at US$1.1 billion, with Tiger Global, Helion Ventures, and Nexus Venture Partners as major investors'),
	(30, 7, 'Snapdeal', 'Snapdeal is an Indian e-commerce company, based in New Delhi, India. It was founded in February 2010 by Kunal Bahl and Rohit Bansal. Snapdeal is one of the largest online marketplaces in India. Snapdeal targets the value e-commerce segment, which Bahl estimated to be three times larger than the branded goods market'),
	(31, 7, 'trade', 'Trade involves the transfer of goods and services from one person or entity to another, often in exchange for money. Economists refer to a system or network that allows trade as a market'),
	(32, 8, 'cipla', 'Cipla Limited is an Indian multinational pharmaceutical company, headquartered in Mumbai, India. Cipla primarily develops medicines to treat respiratory disease, cardiovascular disease, arthritis, diabetes, depression and many other medical conditions.'),
	(33, 8, 'glaxo', 'GSK plc, formerly GlaxoSmithKline plc, is a British multinational pharmaceutical and biotechnology company with global headquarters in London, England. Established in 2000 by a merger of Glaxo Wellcome and SmithKline Beecham'),
	(34, 8, 'pfizer', 'Pfizer Inc. is an American multinational pharmaceutical and biotechnology corporation headquartered on 42nd Street in Manhattan, New York City. The company was established in 1849 in New York by two German immigrants, Charles Pfizer and his cousin Charles F. Erhart'),
	(36, 9, 'adidas', 'Adidas AG is a German multinational corporation, founded and headquartered in Herzogenaurach, Bavaria, that designs and manufactures shoes, clothing and accessories. It is the largest sportswear manufacturer in Europe, and the second largest in the world, after Nike'),
	(37, 9, 'nike', 'Nike, Inc. is an American multinational corporation that is engaged in the design, development, manufacturing, and worldwide marketing and sales of footwear, apparel, equipment, accessories, and services. The company is headquartered near Beaverton, Oregon, in the Portland metropolitan area'),
	(38, 9, 'puma', 'Puma SE, branded as Puma, is a German multinational corporation that designs and manufactures athletic and casual footwear, apparel and accessories, which is headquartered in Herzogenaurach, Bavaria, Germany. Puma is the third largest sportswear manufacturer in the world.'),
	(39, 9, 'Underarmour', 'Under Armour, Inc. is an American sports equipment company that manufactures footwear, sports and casual apparel'),
	(40, 10, 'barbie', 'Barbie is a fashion doll manufactured by the American toy company Mattel, Inc. and launched in March 1959. American businesswoman Ruth Handler is credited with the creation of the doll using a German doll called Bild Lilli as her inspiration.'),
	(41, 10, 'hotwheels', 'Hot Wheels is an American brand of scale model cars introduced by American toymaker Mattel in 1968. It was the primary competitor of Matchbox until 1997, when Mattel bought Tyco Toys, then owner of Matchbox'),
	(42, 10, 'lego', 'Lego A/S is a Danish toy production company based in Billund, Denmark. It manufactures Lego-brand toys, consisting mostly of interlocking plastic bricks. The Lego Group has also built several amusement parks around the world, each known as Legoland, and operates numerous retail stores');
/*!40000 ALTER TABLE `organizations` ENABLE KEYS */;

-- Dumping structure for table tickethandling.passwordreset
DROP TABLE IF EXISTS `passwordreset`;
CREATE TABLE IF NOT EXISTS `passwordreset` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` int NOT NULL,
  `createdat` datetime(6) DEFAULT NULL,
  `emailid` varchar(255) DEFAULT NULL,
  `passwordresetlink` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.passwordreset: ~0 rows (approximately)
DELETE FROM `passwordreset`;
/*!40000 ALTER TABLE `passwordreset` DISABLE KEYS */;
/*!40000 ALTER TABLE `passwordreset` ENABLE KEYS */;

-- Dumping structure for table tickethandling.roletbl
DROP TABLE IF EXISTS `roletbl`;
CREATE TABLE IF NOT EXISTS `roletbl` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.roletbl: ~2 rows (approximately)
DELETE FROM `roletbl`;
/*!40000 ALTER TABLE `roletbl` DISABLE KEYS */;
INSERT INTO `roletbl` (`id`, `rolename`) VALUES
	(1, 'Manager'),
	(2, 'OrgMember'),
	(3, 'Customer');
/*!40000 ALTER TABLE `roletbl` ENABLE KEYS */;

-- Dumping structure for table tickethandling.sessiondetails
DROP TABLE IF EXISTS `sessiondetails`;
CREATE TABLE IF NOT EXISTS `sessiondetails` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `sessionkey` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `loggedinat` datetime DEFAULT NULL,
  `loggedoutat` datetime DEFAULT NULL,
  `active` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.sessiondetails: ~55 rows (approximately)
DELETE FROM `sessiondetails`;
/*!40000 ALTER TABLE `sessiondetails` DISABLE KEYS */;
INSERT INTO `sessiondetails` (`id`, `userid`, `sessionkey`, `loggedinat`, `loggedoutat`, `active`) VALUES
	(27, 8, 'B92gLYIZ1W5xFC5/8wOnLNVnDLZ7EVpVR36a8wouNgqe0bIKYCgPggO0+EuLU9Y2', '2022-07-12 07:21:11', NULL, 0),
	(28, 8, 'pbkm/ZBhOWWkAXNAC+Uy84p4Lqnud7aEiiiMLp9ZoqXMw2g2Okey3LDETiHnazD8', '2022-07-12 07:24:10', NULL, 0),
	(29, 8, '+feymB0bcgkneySCiLzDzGMSRrzk8EBx3v4RM43J4LucNeloASVWE1q7OfEY36pA', '2022-07-12 07:41:19', NULL, 0),
	(30, 8, 'z1Rs8tLUVdgY0KV6lPynVsZP4VhFsUyKRtfKIUp/9S6DeqilRHwSrkokidDKLMUL', '2022-07-12 07:43:38', NULL, 0),
	(31, 8, '7MCey8L1W+MYI4dQzX31g1QUI42q4oPyyvm/BJU1Qwz49mYrXdQDNiUDtE/Q/GWd', '2022-07-12 09:15:30', NULL, 0),
	(32, 8, 'fuKmnlD5A0P0ThlImX9XJbvbeY/d/wxagt3TuT4pQfe/CNtpoYYptYABMgywjIbn', '2022-07-12 09:18:22', NULL, 0),
	(33, 8, 'CLY/nCB0gWl9OXg9UUPd7lXd6+rZ/DwnWgazDFbawIoAGz4fJgtFIr3t+YQBMhwv', '2022-07-12 09:20:38', NULL, 0),
	(34, 8, 'z0TVOBuVJVC+GxBpC1KGzl1Rs9XeKHlqHxXhbjWGmw9sbxEYWhsKaEF98RGZhJjs', '2022-07-12 09:21:42', NULL, 0),
	(35, 8, 'tPToSch7gGfhGFE7MYuY2Pkq4d4Go76DQR8tW56MbfemQ7AnnFmNnoo5Eyum/6LQ', '2022-07-12 09:26:47', NULL, 0),
	(36, 8, 'PKGfFyAbO7Ekye4cRqtcIcwAgCuukYCuA6eQkcnXxG2d2MrnfmEEmO+bK8NTdeWi', '2022-07-12 09:27:11', NULL, 0),
	(37, 8, 'IrBmfMYIUweDmIMDtBvqVmVH2yJS34wEG8CbrBGz7YfmLY3VA/UCniWWboBEXn1l', '2022-07-12 09:36:06', NULL, 0),
	(38, 8, 'TqfLekZRha97MO5nyD+tLl1SM6W8PKKt8Co7td5mfpdkARKvIVky+SsUsesCoFur', '2022-07-13 01:39:24', NULL, 0),
	(39, 7, 'Y4QCNPpxUzbtzsGo5zrlBUtV+yjVj1EfNwRDiJw0sZQTFdA3nL4ybT7noQpIUM8W', '2022-07-15 03:52:02', NULL, 0),
	(40, 6, 'oMTHl0x1mTdAQI8uCE+E69l7qETMRi/reouqHZnFGoCHO+se1EcD27gACCdDEJIx', '2022-07-15 04:23:58', NULL, 0),
	(41, 6, 'Lhl9oQzUFmz5Jkn6+FOAXXin8QWsKl9pFGnfsOID0q8Js8mhItSGlHmiMQS8WPvb', '2022-07-15 04:24:43', NULL, 0),
	(42, 6, 'OCNy0I7i/nLHd6buCkl5YyrQcJBeJha15F33WqYWNMMsKaoyNczjJpKs854j+R/r', '2022-07-15 04:26:34', NULL, 0),
	(43, 6, '50e0DU1bLc0ws/fURCCXuRysjK25Gy3Q1YjiF04bu/bZWg0u4NiGEQ9pzz2tHdqC', '2022-07-15 05:00:50', NULL, 0),
	(44, 6, 'N9e7KjML1FopM9a4wjPHtpPQbOeh9bIW/GoRXyjTIvnEC8nkxEb9vXjynpvzuqdN', '2022-07-16 03:29:35', NULL, 0),
	(45, 6, '9VspiyRkFWVfOAN2/8vYaSrRh6eS4petGLMsoQD74MVD0yudR8U7XPPofDABB/ym', '2022-07-16 03:30:56', NULL, 0),
	(46, 6, 'T6YWbaHPbHc5c/r4T9od60y4CP1BXZSvqvF1MDwY7c0cYap1muJ54wfhoeAUqJj7', '2022-07-16 03:32:28', NULL, 0),
	(47, 6, 'sec9maMN8tI7PeKzO+IskBsG/zz5pWaEweDm1wGwzOQx8wAdoNJGvt9eBJLu1cMv', '2022-07-16 03:34:22', NULL, 0),
	(48, 6, 'bLMFsAf9TZWwtQymzs2zaLQ6CUf1y5iEoNNOjLQj9oU+1S6Ako0x1DaLLRAiYP4u', '2022-07-18 09:09:02', NULL, 0),
	(49, 7, 'oFMsyjiBZz+LO+dGaVE8Xov0JbNfD/v0JJp7XHsoa1t2KQDjIj/jNKeEGDWP5G/m', '2022-07-30 10:42:50', NULL, 0),
	(50, 7, 'BnlYTv5e8ZvvAmGFuqS+z+9efbpny0KYQDfZbsjpuKFSR7AWdNokkiOxdu9iteFq', '2022-07-30 10:48:15', NULL, 0),
	(51, 17, 'yRt22Mo9ebeNTCeNLvMbOGJkYtdVvnLcP4JsgEPdDVRiFf7kehjhxvB9EPS2Ib74', '2022-08-06 02:56:03', NULL, 0),
	(52, 17, 'VGTtP38ZyJ5Ysx2I1sT0xlKUr5S3bKv/piMmiGaeUFI/CeEV/uVYD/n28UvMlLS3', '2022-08-06 03:07:49', NULL, 0),
	(53, 17, '9+bDJ0lgXYYWHoudVrjaAaWnXzUbHF8mu269KLV5OGD3XyzimqrR88J24/R1Nqd4', '2022-08-09 06:42:21', NULL, 1),
	(54, 17, 'lRlTuZ/7MOnD+6PagO9TK9jweNnSbyO2AYqJguJRAyFSoH5aPyvkm+jAk/3cOXq6', '2022-08-09 06:56:43', NULL, 1),
	(55, 7, 'KouVlqhjsSfuKh+ypnRel59I7t7YNWuBuYzYPk9WkQ3bTml7lFio+xMI/yO0Vi0s', '2022-08-09 06:57:57', NULL, 1),
	(56, 17, 'qthAswOgvvtfSjxdLyF0KONEbY33q0jWXtPMzaDeDX8yCP2qgbZXXxQKqbfzNASd', '2022-08-09 09:38:07', NULL, 1),
	(57, 17, 'fuefOg4KaS50asifCgtv/rtm93/zYgFX0C4s+BEk2FKMr94mtNEoFbX8wHlVvF+X', '2022-08-16 02:16:31', '2022-08-16 02:17:16', 0),
	(58, 17, 'vYThi4Mi5qPWInKA9nLp2mijXLf0Hp/bkiesA2VD0PzzCrrTvixEfF7eWjhuYErc', '2022-08-19 00:35:02', NULL, 1),
	(59, 7, 'zCaEIadEe5KTd27XBczQ+9J/4LQLb0d+zwfbghihestc4JSNLN3zNQkWpuzUGiUf', '2022-08-19 00:35:52', NULL, 1),
	(60, 7, 'fjsCYxLoWPC0eJND+jeRdKq8/RUhd/e00you7kYEt/dpKBdxRiBUtVqwmDYruPNo', '2022-08-19 00:36:22', NULL, 1),
	(61, 7, 'gtMp4XGKACSsT4b5/1egNVCF8BsWzqQs82aQ4n+kOOTXkt/5f4YOw8PDMGSM+R76', '2022-08-19 00:37:12', NULL, 1),
	(62, 7, '6NxzskjHGKg5e1zokmoVT4xGrqiutoNm1z1UsbWzIPih+B9K6RzkcTbmZH+N//30', '2022-08-19 01:07:07', NULL, 1),
	(63, 19, 'jhRvnu1s/+f/GDCi08go+4fY/YVwIvoqEX1aq/ixGm5dDXstk/1znT3vc2ppwFuj', '2022-08-21 11:46:28', '2022-08-21 11:47:38', 0),
	(64, 20, '/DQ1vDmAcQCAYvcaXddiEiiiZ6voTNYLEyQYInh6HLvRejaSQH7vyTYEz3LAKe5K', '2022-08-21 11:48:32', '2022-08-21 11:49:58', 0),
	(65, 19, 'WbcrJIfqq5cFjutimlFHEZ0NUL7+iSNZ6z19A0qV7gQAVlLH2W5PmnVBnetsDzva', '2022-08-21 11:50:03', '2022-08-21 11:50:49', 0),
	(66, 21, '45YKm9bTRLXtCrDSxWTmJr93IbheYd87nsUaAX29/q711dzHrpUsRt2lFWs+SHr/', '2022-08-21 11:50:58', '2022-08-21 11:52:04', 0),
	(67, 21, '3J4lwAPYNyuBJM1lqGoHZ5dYHhST1s9zm/tFJensU7vmcBybqIm5rvCyIPjD5EpZ', '2022-08-21 11:51:46', NULL, 1),
	(68, 19, 'CCl3zFxBeNc7NP8ROFYOoaeKZlKe0wOl+tZZp2lneyyyNSm1May9Vz/KY5zhN/rq', '2022-08-21 11:52:08', NULL, 1),
	(69, 21, 'y5QgNLagH5vTCK5kmPbV057i+xpeuxe08Jz8Cm+a5cx5MmaGrBWbMLV4+gtOTBs2', '2022-08-22 09:05:01', '2022-08-22 09:05:16', 0),
	(70, 23, 'js9n9voNnaTz19ruj4KNJ7234nHJ6SGSHtQ3vn7PyVot1H47KwvRV6kAINRrdJ3x', '2022-08-22 09:06:32', '2022-08-22 09:07:39', 0),
	(71, 21, 'PmdICV2g+MbtXyHuj76fyqqPYdEwGhJt46LSrWQGSZEQsm5AGi1imBThEJvHMHSM', '2022-08-22 09:08:17', NULL, 1),
	(72, 21, 'EUVyw3t5SCgbpNmG1sSeQaq654H0LdnJDUafvru1fXRIdJgy5dBNrvjwmXfZGxZ2', '2022-08-23 00:34:01', NULL, 1),
	(73, 21, 'i4fFr1WwybrijdA28fCYFgZpX9N3YDP/+BqkMpYGap4mqUZichr1C3zd9CRptrAK', '2022-08-23 00:34:02', NULL, 1),
	(74, 23, '3ZtDW56KcMBARkJGgXg75RZSshQo5Y1/N6KRUDsbwBlbWPIUoqe7hPZ9YSjO44/t', '2022-08-23 00:34:07', NULL, 1),
	(75, 24, 'nKXoBuBPpRwAU2bYpblFUVyBMM6X+BlaCgzjI/EsR+1UfmyeefFEO0iY8Odv/icN', '2022-08-23 00:36:22', NULL, 1),
	(76, 24, 'afcvPeBuSY7bPIGGPvUxCmHPF7hGCcffJD/c/XJ1bUnrSDu6vNJIz+d8OelKnxv3', '2022-08-23 00:36:29', '2022-08-23 01:12:41', 0),
	(77, 19, 'FTqL4rt7j8qqwQEGrCkEreedoedmUpXMEB2imHEA8xn+m+rhyHFFyW8aD0W8Tvim', '2022-08-23 01:12:48', NULL, 1),
	(78, 19, 'QNPKZ2wJTRXQNVHOEM9mPoJ9oGuUCYQVOScwYx3SVcqvW9W2WHNYQYKkKVsPAhFJ', '2022-08-23 01:45:07', NULL, 1),
	(79, 19, '+T8TkFvy/S6CJ6XoOshz33b3MOEh7TeLUsCb7lTrLpPDnYj6dp7DquHSZ6k0nRDG', '2022-08-23 01:45:31', NULL, 1),
	(80, 19, 'MA6BrCuMXpPazztUf8g2ArATzBzusa5g/1L+20F53abYrFXxlLEeT2+UA1LmwC5Y', '2022-08-23 01:46:16', NULL, 1),
	(81, 19, '63juT/nT4BHlyKQZz/ka4kataTard7kblBPuO/M2uQc63GxXRGkLV967iJ4SKCxc', '2022-08-23 01:46:41', '2022-08-23 01:47:03', 0);
/*!40000 ALTER TABLE `sessiondetails` ENABLE KEYS */;

-- Dumping structure for table tickethandling.tags
DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.tags: ~7 rows (approximately)
DELETE FROM `tags`;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` (`id`, `name`) VALUES
	(1, 'Wrong Product'),
	(2, 'Purchase block'),
	(3, 'Product misconfiguration'),
	(4, 'Broken Product'),
	(5, 'Port malfunctioning'),
	(6, 'Return issues'),
	(7, 'Return blocked'),
	(8, 'Mismatching color'),
	(9, 'Wrong IMEI number'),
	(10, 'Screen broken'),
	(11, 'Customer care support'),
	(12, 'Wrong Gaming Equipment');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;

-- Dumping structure for table tickethandling.ticket
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ticketid` int NOT NULL,
  `currentassigneeuserid` int DEFAULT '-1',
  `currentassigneeroleid` int DEFAULT '-1',
  `assignedbyuserid` int DEFAULT '-1',
  `assignedon` datetime DEFAULT NULL,
  `assignedbyroleid` int DEFAULT '-1',
  `title` longtext NOT NULL,
  `content` longtext NOT NULL,
  `workdone` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `notes` longtext,
  `imagepath` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `updatedon` datetime DEFAULT NULL,
  `closedbyuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '-1',
  `closedbyroleid` int DEFAULT '-1',
  `closedon` datetime DEFAULT NULL,
  `activestage` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.ticket: ~19 rows (approximately)
DELETE FROM `ticket`;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` (`id`, `ticketid`, `currentassigneeuserid`, `currentassigneeroleid`, `assignedbyuserid`, `assignedon`, `assignedbyroleid`, `title`, `content`, `workdone`, `notes`, `imagepath`, `updatedon`, `closedbyuserid`, `closedbyroleid`, `closedon`, `activestage`) VALUES
	(48, 29, NULL, NULL, 13, '2022-08-16 03:57:52', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 15:58:06', NULL, NULL, NULL, 0),
	(49, 29, 15, 2, 15, '2022-08-16 03:57:52', 2, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(50, 30, NULL, NULL, 13, '2022-08-16 04:02:08', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 16:02:24', NULL, NULL, NULL, 0),
	(51, 30, 15, 2, 15, '2022-08-16 04:02:08', 2, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(52, 31, NULL, NULL, 13, '2022-08-16 04:13:16', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 16:13:35', NULL, NULL, NULL, 0),
	(53, 31, 15, 2, 15, '2022-08-16 04:13:16', 2, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(54, 32, NULL, NULL, 13, '2022-08-16 04:16:01', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 16:16:24', NULL, NULL, NULL, 0),
	(55, 32, 15, 2, 15, '2022-08-16 04:16:01', 2, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(56, 33, NULL, NULL, 13, '2022-08-16 04:19:29', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 16:19:43', NULL, NULL, NULL, 0),
	(57, 33, 15, 2, 15, '2022-08-16 04:19:29', 2, 'fp1', 'flipkart refund issues', 'Completed refund part', 'Is refund done', NULL, '2022-08-16 16:21:32', NULL, NULL, NULL, 0),
	(58, 33, 16, 2, 15, '2022-08-16 04:19:29', 2, 'fp1', 'flipkart refund issues', 'Completed refund part', 'Is refund done', NULL, '2022-08-16 16:25:08', '16', 2, '2022-08-16 16:25:08', 1),
	(59, 34, NULL, NULL, 13, '2022-08-16 04:31:39', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 16:31:46', NULL, NULL, NULL, 0),
	(60, 34, 15, 2, 15, '2022-08-16 04:31:39', 2, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 17:05:01', NULL, NULL, NULL, 0),
	(61, 35, NULL, NULL, 13, '2022-08-16 05:04:54', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, '2022-08-16 17:05:23', NULL, NULL, NULL, 0),
	(62, 34, 15, 2, 15, '2022-08-16 04:31:39', 2, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(63, 35, 15, 2, 15, '2022-08-16 05:04:54', 2, 'fp1', 'flipkart refund issues', 'Completed refund part', 'Is refund done', NULL, '2022-08-16 17:14:49', '16', 2, '2022-08-16 17:14:49', 1),
	(64, 36, NULL, NULL, 13, '2022-08-16 05:19:54', NULL, 'fp1', 'flipkart refund issues', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
	(65, 37, NULL, NULL, 13, '2022-08-16 05:20:17', NULL, 'Refund problem', 'Myntra refund issues, geting regularly', NULL, NULL, NULL, '2022-08-16 17:20:40', NULL, NULL, NULL, 0),
	(66, 37, 15, 2, 15, '2022-08-16 05:20:17', 2, 'Refund problem', 'Myntra refund issues, geting regularly', 'Completed refund part', 'Is refund done', NULL, '2022-08-16 17:22:41', '16', 2, '2022-08-16 17:22:41', 1),
	(67, 38, NULL, NULL, 13, '2022-08-23 01:12:35', NULL, 'Refund issues', '<p>Not able to return the product</p>', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;

-- Dumping structure for table tickethandling.ticketmetadata
DROP TABLE IF EXISTS `ticketmetadata`;
CREATE TABLE IF NOT EXISTS `ticketmetadata` (
  `ticketid` int NOT NULL AUTO_INCREMENT,
  `creatoruserid` int DEFAULT NULL,
  `createdat` datetime DEFAULT NULL,
  `creatorroleid` bigint DEFAULT NULL,
  `orgid` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `tags` longtext,
  `depid` int DEFAULT NULL,
  PRIMARY KEY (`ticketid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.ticketmetadata: ~9 rows (approximately)
DELETE FROM `ticketmetadata`;
/*!40000 ALTER TABLE `ticketmetadata` DISABLE KEYS */;
INSERT INTO `ticketmetadata` (`ticketid`, `creatoruserid`, `createdat`, `creatorroleid`, `orgid`, `status`, `tags`, `depid`) VALUES
	(29, 6, '2022-08-16 03:57:52', 3, 2, 1, ';1;2;3;', 4),
	(30, 6, '2022-08-16 04:02:08', 3, 2, 1, ';1;2;3;', 4),
	(31, 6, '2022-08-16 04:13:16', 3, 2, 1, ';1;2;3;', 4),
	(32, 6, '2022-08-16 04:16:01', 3, 2, 1, ';1;2;3;', 4),
	(33, 6, '2022-08-16 04:19:29', 3, 2, 2, ';1;2;3;', 4),
	(34, 6, '2022-08-16 04:31:39', 3, 10, 1, ';1;2;3;', 2),
	(35, 6, '2022-08-16 05:04:54', 3, 2, 2, ';1;2;3;', 4),
	(36, 6, '2022-08-16 05:19:54', 3, 2, 0, ';1;2;3;', 4),
	(37, 6, '2022-08-16 05:20:17', 3, 10, 2, ';1;2;3;', 2),
	(38, 24, '2022-08-23 01:12:35', 3, 10, 0, ';1;2;3;', 2);
/*!40000 ALTER TABLE `ticketmetadata` ENABLE KEYS */;

-- Dumping structure for table tickethandling.userdetails
DROP TABLE IF EXISTS `userdetails`;
CREATE TABLE IF NOT EXISTS `userdetails` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `emailid` varchar(50) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `roleid` int DEFAULT NULL,
  `createdby` int DEFAULT NULL,
  `avatarid` int DEFAULT '1',
  `emailidactivated` int DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.userdetails: ~19 rows (approximately)
DELETE FROM `userdetails`;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` (`userid`, `username`, `emailid`, `password`, `dob`, `roleid`, `createdby`, `avatarid`, `emailidactivated`) VALUES
	(5, 'vamsiii', 'vamsiii@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22 19:00:00', 2, -1, 1, 1),
	(6, 'vamsi', 'krishna14a0535@gvpce.ac.in', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22 19:00:00', 3, -1, 1, 1),
	(7, 'mgr', 'vamsiii@gmail.com', 'BxwDnrlurKNM1QXoaKpPhw==', '1997-07-22 19:00:00', 1, -1, 1, 1),
	(8, 'bhargav', 'b@gmail.com', 'JTvohLiLKLY0cKyzpHtBWw==', '2021-08-06 19:00:00', 3, -1, 1, 1),
	(9, 'bhargavtt', 'bh@kusdghv.com', 'JTvohLiLKLY0cKyzpHtBWw==', '2022-07-28 19:00:00', 1, -1, 1, 1),
	(10, 'mger', 'mger@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22 19:00:00', 1, -1, 1, 1),
	(11, 'mgedr', 'mgeer@gmail.com', 'zEhq/Nf9IuuDN3XWL+o54A==', '1997-07-22 19:00:00', 3, -1, 1, 1),
	(12, 'mgkhedr', 'mgeerkh@gmail.com', 'WLAPu7j1gjauTx5pB0Dc+A==', '1997-07-22 19:00:00', 3, -1, 1, 1),
	(13, 'SYSTEM', 'system@self.com', 'WfsguiHUYHjkfgsjk9==r8347+', NULL, -1, -1, 1, 1),
	(14, 'teja', 'teja@gmail.com', 'ejDSfSan6CquBKUe0q2tRw==', '1997-07-22 19:00:00', 2, 7, 1, 1),
	(15, 'ashok', 'testengland069@gmail.com', 'oQ2OILhMS2lh0e9GDHiE4A==', '1997-07-22 19:00:00', 2, 7, 1, 1),
	(16, 'pavan', 'shid@uh.com', 'fVQA4ETqERy1w2/JECINKg==', '1997-06-12 19:00:00', 2, 7, 1, 1),
	(17, 'Prerana', 'shid@uh.com', 'BxwDnrlurKNM1QXoaKpPhw==', '1997-06-12 19:00:00', 2, 7, 1, 1),
	(19, 'gopi', 'bhargav.gandham44@gmail.com', 'WiJUfFfzq0DiVefksQkk5A==', '2022-08-14 19:00:00', 1, -1, 1, 0),
	(20, 'test', 'test123@gmai.com', 'a7E/kdNFVJba89DGazWgRA==', '2022-08-07 19:00:00', 3, -1, 1, 0),
	(21, 'mani', 'mh@kD8yK8cxKQtt.com', 'apbpeKRO5LTu8931ueUmXw==', '2022-08-15 19:00:00', 2, NULL, 1, 1),
	(22, 'vamsimsr', 'vamsimsr@g.com', 'BtmpJuWbUJRqig9kFBCT4A==', '2022-08-15 19:00:00', 2, NULL, 1, 1),
	(23, 'testuser', 'testuser@g.com', 'a7E/kdNFVJba89DGazWgRA==', '2022-08-14 19:00:00', 3, -1, 1, 0),
	(24, 'tuser', 'gy@hoi.com', 'a7E/kdNFVJba89DGazWgRA==', '2022-08-03 19:00:00', 3, -1, 1, 0);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;

-- Dumping structure for table tickethandling.userorgmap
DROP TABLE IF EXISTS `userorgmap`;
CREATE TABLE IF NOT EXISTS `userorgmap` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `depid` int DEFAULT NULL,
  `orgid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table tickethandling.userorgmap: ~12 rows (approximately)
DELETE FROM `userorgmap`;
/*!40000 ALTER TABLE `userorgmap` DISABLE KEYS */;
INSERT INTO `userorgmap` (`id`, `userid`, `depid`, `orgid`) VALUES
	(5, 16, 4, 2),
	(6, 17, 4, 2),
	(7, 5, 4, 2),
	(11, 14, 4, 2),
	(12, 15, 4, 2),
	(13, 7, 4, 2),
	(14, 7, 2, 11),
	(15, 7, 1, 12),
	(16, 19, 2, 14),
	(17, 19, 2, 10),
	(18, 21, 10, 2),
	(19, 22, 10, 2);
/*!40000 ALTER TABLE `userorgmap` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
