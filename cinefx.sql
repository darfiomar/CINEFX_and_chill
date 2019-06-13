-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 13 juin 2019 à 22:31
-- Version du serveur :  5.7.21
-- Version de PHP :  7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `cinefx`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateurs`
--

DROP TABLE IF EXISTS `administrateurs`;
CREATE TABLE IF NOT EXISTS `administrateurs` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NomAdmin` varchar(255) DEFAULT NULL,
  `PrenomAdmin` varchar(255) DEFAULT NULL,
  `EmailAdmin` varchar(255) DEFAULT NULL,
  `PasswordAdmin` varchar(255) DEFAULT NULL,
  `PhoneAdmin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `administrateurs`
--

INSERT INTO `administrateurs` (`ID`, `NomAdmin`, `PrenomAdmin`, `EmailAdmin`, `PasswordAdmin`, `PhoneAdmin`) VALUES
(1, 'Whoever', 'Yourname', 'admin1@gmail.com', '1234', '0600000000'),
(2, 'Whoever', 'Yourname', 'admin2@gmail.com', '1234', '0606060606');

-- --------------------------------------------------------

--
-- Structure de la table `cartesbancaires`
--

DROP TABLE IF EXISTS `cartesbancaires`;
CREATE TABLE IF NOT EXISTS `cartesbancaires` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NumeroCarte` varchar(255) DEFAULT NULL,
  `DECarte` varchar(255) DEFAULT NULL,
  `CryptoCarte` int(10) NOT NULL,
  `SoldeCarte` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `cartesbancaires`
--

INSERT INTO `cartesbancaires` (`ID`, `NumeroCarte`, `DECarte`, `CryptoCarte`, `SoldeCarte`) VALUES
(1, '1111 2222 3333 4444', '01/21', 123, 7402707),
(2, '1234 1234 1234 1234', '01/21', 123, 20),
(3, '5555 6666 7777 8888', '01/21', 123, 1156),
(4, '5678 5678 5678 5678', '01/21', 123, 267.54);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ForfaitAbonne` int(10) DEFAULT NULL,
  `fraisabonnements` double NOT NULL DEFAULT '0',
  `EmailClient` varchar(255) DEFAULT NULL,
  `FinAbonnement` date DEFAULT NULL,
  `NomClient` varchar(255) DEFAULT NULL,
  `PrenomClient` varchar(255) DEFAULT NULL,
  `AdresseAbonne` varchar(255) DEFAULT NULL,
  `PhoneAbonne` varchar(255) DEFAULT NULL,
  `NAchats` int(10) NOT NULL DEFAULT '0',
  `FlagAbonne` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `dates`
--

DROP TABLE IF EXISTS `dates`;
CREATE TABLE IF NOT EXISTS `dates` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Dates` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `dates`
--

INSERT INTO `dates` (`ID`, `Dates`) VALUES
(1, '10/06/2020'),
(2, '11/06/2020'),
(3, '12/06/2018'),
(4, '13/06/2019'),
(5, '14/06/2020'),
(6, '15/06/2020'),
(7, '16/06/2020'),
(8, '17/06/2020'),
(9, '18/06/2020'),
(10, '19/06/2020');

-- --------------------------------------------------------

--
-- Structure de la table `films`
--

DROP TABLE IF EXISTS `films`;
CREATE TABLE IF NOT EXISTS `films` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `AdministrateursID` int(10) NOT NULL,
  `LanguesID` int(10) NOT NULL,
  `PaysID` int(10) NOT NULL,
  `NomFilm` varchar(255) DEFAULT NULL,
  `SynopsisFilm` text,
  `TrailerFilm` varchar(255) DEFAULT NULL,
  `ImageFilm` varchar(255) DEFAULT NULL,
  `ActeursFilm` varchar(255) DEFAULT NULL,
  `RealisateurFilm` varchar(255) DEFAULT NULL,
  `DureeFilm` int(10) NOT NULL,
  `GenresFilm` varchar(255) DEFAULT NULL,
  `ImdbFilm` double NOT NULL,
  `ReleaseFilm` int(10) NOT NULL,
  `Projectable` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `FKFilms973461` (`PaysID`),
  KEY `FKFilms945466` (`LanguesID`),
  KEY `FKFilms489655` (`AdministrateursID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `films`
--

INSERT INTO `films` (`ID`, `AdministrateursID`, `LanguesID`, `PaysID`, `NomFilm`, `SynopsisFilm`, `TrailerFilm`, `ImageFilm`, `ActeursFilm`, `RealisateurFilm`, `DureeFilm`, `GenresFilm`, `ImdbFilm`, `ReleaseFilm`, `Projectable`) VALUES
(1, 2, 1, 231, 'Captain Marvel', 'After crashing an experimental aircraft, Air Force pilot Carol Danvers is discovered by the Kree and trained as a member of the elite Starforce Military under the command of her mentor Yon-Rogg. Six years later, after escaping to Earth while under attack by the Skrulls, Danvers begins to discover there\'s more to her past. With help from S.H.I.E.L.D. agent Nick Fury, they set out to unravel the truth.\r\n', 'https://www.youtube.com/embed/Z1BCujX3pw8', 'https://img.yts.am/assets/images/movies/captain_marvel_2019/medium-cover.jpg', 'Brie Larson, Samuel L. Jackson, Ben Mendelsohn ', 'Anna Boden, Ryan Fleck', 120, 'Adventure', 7, 2019, 1),
(2, 1, 1, 231, 'Logan', 'In 2029 the mutant population has shrunken significantly due to genetically modified plants designed to reduce mutant powers and the X-Men have disbanded. Logan, whose power to self-heal is dwindling, has surrendered himself to alcohol and now earns a living as a chauffeur. He takes care of the ailing old Professor X whom he keeps hidden away. One day, a female stranger asks Logan to drive a girl named Laura to the Canadian border. At first he refuses, but the Professor has been waiting for a long time for her to appear. Laura possesses an extraordinary fighting prowess and is in many ways like Wolverine. She is pursued by sinister figures working for a powerful corporation; this is because they made her, with Logan\'s DNA. A decrepit Logan is forced to ask himself if he can or even wants to put his remaining powers to good use. It would appear that in the near-future, the times in which they were able put the world to rights with razor sharp claws and telepathic powers are now over.', 'https://www.youtube.com/embed/Div0iP65aZo', 'https://img.yts.am/assets/images/movies/logan_2017/medium-cover.jpg', 'Hugh Jackman, Stephen Merchant, Boyd Holbrook, Patrick Stewart', 'James Mangold', 132, 'Action/Drama', 8.1, 2017, 1),
(3, 1, 1, 231, 'The Imitation Game', 'Based on the real life story of legendary cryptanalyst Alan Turing, the film portrays the nail-biting race against time by Turing and his brilliant team of code-breakers at Britain\'s top-secret Government Code and Cypher School at Bletchley Park, during the darkest days of World War II.', 'https://www.youtube.com/embed/nuPZUUED5uk', 'https://img.yts.am/assets/images/movies/the_imitation_game_2014/medium-cover.jpg', 'Benedict Cumberbatch, Keira Knightley, Matthew Goode, Tuppence Middleton', 'Morten Tyldum', 112, 'Biography', 8, 2014, 1),
(4, 2, 2, 72, 'The Intouchables', 'In Paris, the aristocratic and intellectual Philippe is a quadriplegic millionaire who is interviewing candidates for the position of his carer, with his red-haired secretary Magalie. Out of the blue, Driss cuts the line of candidates and brings a document from the Social Security and asks Phillipe to sign it to prove that he is seeking a job position so he can receive his unemployment benefit. Philippe challenges Driss, offering him a trial period of one month to gain experience helping him. Then Driss can decide whether he would like to stay with him or not. Driss accepts the challenge and moves to the mansion, changing the boring life of Phillipe and his employees.\r\n', 'https://www.youtube.com/embed/34WIbmXkewU', 'https://img.yts.am/assets/images/movies/the_intouchables_2011/medium-cover.jpg', 'Omar Sy, François Cluzet, Audrey Fleurot', 'Olivier Nakache', 109, 'Comedy', 8.5, 2011, 1),
(5, 1, 1, 231, 'Tomb Raider', 'Lara Croft is the fiercely independent daughter of an eccentric adventurer who vanished when she was scarcely a teen. Now a young woman of twenty-one without any real focus or purpose, Lara navigates the chaotic streets of trendy East London as a bike courier, barely making the rent, and takes college courses, rarely making it to class. Determined to forge her own path, she refuses to take the reins of her father\'s global empire just as staunchly as she rejects the idea that he\'s truly gone. Advised to face the facts and move forward after seven years without him, even Lara can\'t understand what drives her to finally solve the puzzle of his mysterious death. Going explicitly against his final wishes, she leaves everything she knows behind in search of her dad\'s last-known destination: a fabled tomb on a mythical island that might be somewhere off the coast of Japan. But her mission will not be an easy one; just reaching the island will be extremely treacherous. Suddenly, the stakes ...', 'https://www.youtube.com/embed/rOEHpkZCc1Y', 'https://img.yts.am/assets/images/movies/tomb_raider_2018/medium-cover.jpg', 'Alicia Vikander, Daniel Wu, Dominic West, Walton Goggins', 'Roar Uthaug', 119, 'Fantasy', 7, 2018, 1),
(6, 2, 1, 231, 'Arrival', 'Linguistics professor Louise Banks leads an elite team of investigators when gigantic spaceships touchdown in 12 locations around the world. As nations teeter on the verge of global war, Banks and her crew must race against time to find a way to communicate with the extraterrestrial visitors. Hoping to unravel the mystery, she takes a chance that could threaten her life and quite possibly all of mankind.', 'https://www.youtube.com/embed/AMgyWT075KY', 'https://img.yts.am/assets/images/movies/arrival_2016/medium-cover.jpg', 'Amy Adams, Jeremy Renner, Forest Whitaker', 'Denis Villeneuve', 116, 'Drama', 8, 2016, 1),
(7, 2, 1, 231, 'Game Night', 'Six close friends meet each week for a game night involving board games, charades and pop culture trivia quizzes. Being the most competitive of the bunch, Max and his wife Annie, who seem to be a perfect match in every way, usually win every time. However, their marriage is on rocky ground as Annie fears that Max doesn\'t want to have children. When Max\'s shady brother Brooks reappears after a long mysterious absence and suggests that they have their next gathering at his place, no one expects that their weekly game night is about to go to the next level as Brooks organizes a full blown murder mystery party complete with actors as criminals and cops for them. However, when Brooks is violently kidnapped in front of everyone, it turns out that the game is all too real. Now, Max, Annie, their womanizing dimwitted friend Ryan, his domineering Irish date Sarah, and their childhood friends Michelle and her husband Kevin, who\'s obsessed with finding out with which mysterious celebrity ...\r\n', 'https://www.youtube.com/embed/fNtLIcyjsnI', 'https://img.yts.am/assets/images/movies/game_night_2018/medium-cover.jpg', 'Rachel McAdams, Jesse Plemons, Jason Bateman', 'John Francis Daley', 100, 'Crime/Mystery', 7.5, 2018, 1),
(8, 1, 1, 231, 'The Queen\'s Corgi', 'This is about the adventure of Rex (Jack Whitehall), the British monarch\'s (Dame Julie Walters\') most beloved dog, who loses track of his mistress and stumbles across a clan with dogs of all kinds confronting and fighting each other. During his epic journey to return to the Queen, Rex falls in love and discovers his true self.', 'https://www.youtube.com/embed/sbrEmlTJd7E', 'https://img.yts.am/assets/images/movies/the_queens_corgi_2019/medium-cover.jpg', 'Julie Walters, Ray Winstone, Sheridan Smith, Matt Lucas', 'Ben Stassen', 85, 'Animation', 6, 2019, 1),
(9, 1, 2, 72, 'Ratatouille', 'A rat named Remy dreams of becoming a great French chef despite his family\'s wishes and the obvious problem of being a rat in a decidedly rodent-phobic profession. When fate places Remy in the sewers of Paris, he finds himself ideally situated beneath a restaurant made famous by his culinary hero, Auguste Gusteau. Despite the apparent dangers of being an unlikely, and certainly unwanted, visitor in the kitchen of a fine French restaurant, Remy\'s passion for cooking soon sets into motion a hilarious and exciting rat race that turns the culinary world of Paris upside down.', 'https://www.youtube.com/embed/nTTxc1Vf2pQ', 'https://img.yts.am/assets/images/movies/Ratatouille_2007/medium-cover.jpg', 'John Ratzenberger, Janeane Garofalo, Peter Sohn, Will Arnet', 'Brad Bird', 111, 'Family/Fantasy', 8.5, 2007, 1),
(10, 2, 2, 72, 'Amelie', 'Amélie is a story about a girl named Amélie whose childhood was suppressed by her Father\'s mistaken concerns of a heart defect. With these concerns Amélie gets hardly any real life contact with other people. This leads Amélie to resort to her own fantastical world and dreams of love and beauty. She later on becomes a young woman and moves to the central part of Paris as a waitress. After finding a lost treasure belonging to the former occupant of her apartment, she decides to return it to him. After seeing his reaction and his new found perspective - she decides to devote her life to the people around her. Such as, her father who is obsessed with his garden-gnome, a failed writer, a hypochondriac, a man who stalks his ex girlfriends, the \"ghost\", a suppressed young soul, the love of her life and a man whose bones are as brittle as glass. But after consuming herself with these escapades - she finds out that she is disregarding her own life and damaging her quest for love. Amélie then ...', 'https://www.youtube.com/embed/-ONWqL_BvtM', 'https://img.yts.am/assets/images/movies/amelie_2001/medium-cover.jpg', 'Audrey Tautou, Spencer Tracy, Mathieu Kassovitz', 'Jean-Pierre Jeunet', 122, 'Romance', 9, 2001, 1);

-- --------------------------------------------------------

--
-- Structure de la table `horaires`
--

DROP TABLE IF EXISTS `horaires`;
CREATE TABLE IF NOT EXISTS `horaires` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Horaire` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `horaires`
--

INSERT INTO `horaires` (`ID`, `Horaire`) VALUES
(1, '14h15'),
(2, '17h15'),
(3, '20h15'),
(4, '23h15');

-- --------------------------------------------------------

--
-- Structure de la table `langues`
--

DROP TABLE IF EXISTS `langues`;
CREATE TABLE IF NOT EXISTS `langues` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Langue` varchar(255) DEFAULT NULL,
  `Iso6391` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `langues`
--

INSERT INTO `langues` (`ID`, `Langue`, `Iso6391`) VALUES
(1, 'English', 'en'),
(2, 'French', 'fr'),
(3, 'Arabic', 'ar'),
(4, 'Spanish', 'es'),
(5, 'German', 'de'),
(6, 'Turkish', 'tr'),
(7, 'Hindi', 'hi'),
(8, 'Afar', 'aa'),
(9, 'Abkhazian', 'ab'),
(10, 'Afrikaans', 'af'),
(11, 'Amharic', 'am'),
(12, 'Assamese', 'as'),
(13, 'Aymara', 'ay'),
(14, 'Azerbaijani', 'az'),
(15, 'Bashkir', 'ba'),
(16, 'Belarusian', 'be'),
(17, 'Bulgarian', 'bg'),
(18, 'Bihari', 'bh'),
(19, 'Bislama', 'bi'),
(20, 'Bengali/Bangla', 'bn'),
(21, 'Tibetan', 'bo'),
(22, 'Breton', 'br'),
(23, 'Catalan', 'ca'),
(24, 'Corsican', 'co'),
(25, 'Czech', 'cs'),
(26, 'Welsh', 'cy'),
(27, 'Danish', 'da'),
(28, 'Bhutani', 'dz'),
(29, 'Greek', 'el'),
(30, 'Esperanto', 'eo'),
(31, 'Estonian', 'et'),
(32, 'Basque', 'eu'),
(33, 'Persian', 'fa'),
(34, 'Finnish', 'fi'),
(35, 'Fiji', 'fj'),
(36, 'Faeroese', 'fo'),
(37, 'Frisian', 'fy'),
(38, 'Irish', 'ga'),
(39, 'Scots/Gaelic', 'gd'),
(40, 'Galician', 'gl'),
(41, 'Guarani', 'gn'),
(42, 'Gujarati', 'gu'),
(43, 'Hausa', 'ha'),
(44, 'Croatian', 'hr'),
(45, 'Hungarian', 'hu'),
(46, 'Armenian', 'hy'),
(47, 'Interlingua', 'ia'),
(48, 'Interlingue', 'ie'),
(49, 'Inupiak', 'ik'),
(50, 'Indonesian', 'in'),
(51, 'Icelandic', 'is'),
(52, 'Italian', 'it'),
(53, 'Hebrew', 'iw'),
(54, 'Japanese', 'ja'),
(55, 'Yiddish', 'ji'),
(56, 'Javanese', 'jw'),
(57, 'Georgian', 'ka'),
(58, 'Kazakh', 'kk'),
(59, 'Greenlandic', 'kl'),
(60, 'Cambodian', 'km'),
(61, 'Kannada', 'kn'),
(62, 'Korean', 'ko'),
(63, 'Kashmiri', 'ks'),
(64, 'Kurdish', 'ku'),
(65, 'Kirghiz', 'ky'),
(66, 'Latin', 'la'),
(67, 'Lingala', 'ln'),
(68, 'Laothian', 'lo'),
(69, 'Lithuanian', 'lt'),
(70, 'Latvian/Lettish', 'lv'),
(71, 'Malagasy', 'mg'),
(72, 'Maori', 'mi'),
(73, 'Macedonian', 'mk'),
(74, 'Malayalam', 'ml'),
(75, 'Mongolian', 'mn'),
(76, 'Moldavian', 'mo'),
(77, 'Marathi', 'mr'),
(78, 'Malay', 'ms'),
(79, 'Maltese', 'mt'),
(80, 'Burmese', 'my'),
(81, 'Nauru', 'na'),
(82, 'Nepali', 'ne'),
(83, 'Dutch', 'nl'),
(84, 'Norwegian', 'no'),
(85, 'Occitan', 'oc'),
(86, '(Afan)/Oromoor/Oriya', 'om'),
(87, 'Punjabi', 'pa'),
(88, 'Polish', 'pl'),
(89, 'Pashto/Pushto', 'ps'),
(90, 'Portuguese', 'pt'),
(91, 'Quechua', 'qu'),
(92, 'Rhaeto-Romance', 'rm'),
(93, 'Kirundi', 'rn'),
(94, 'Romanian', 'ro'),
(95, 'Russian', 'ru'),
(96, 'Kinyarwanda', 'rw'),
(97, 'Sanskrit', 'sa'),
(98, 'Sindhi', 'sd'),
(99, 'Sangro', 'sg'),
(100, 'Serbo-Croatian', 'sh'),
(101, 'Singhalese', 'si'),
(102, 'Slovak', 'sk'),
(103, 'Slovenian', 'sl'),
(104, 'Samoan', 'sm'),
(105, 'Shona', 'sn'),
(106, 'Somali', 'so'),
(107, 'Albanian', 'sq'),
(108, 'Serbian', 'sr'),
(109, 'Siswati', 'ss'),
(110, 'Sesotho', 'st'),
(111, 'Sundanese', 'su'),
(112, 'Swedish', 'sv'),
(113, 'Swahili', 'sw'),
(114, 'Tamil', 'ta'),
(115, 'Telugu', 'te'),
(116, 'Tajik', 'tg'),
(117, 'Thai', 'th'),
(118, 'Tigrinya', 'ti'),
(119, 'Turkmen', 'tk'),
(120, 'Tagalog', 'tl'),
(121, 'Setswana', 'tn'),
(122, 'Tonga', 'to'),
(123, 'Tsonga', 'ts'),
(124, 'Tatar', 'tt'),
(125, 'Twi', 'tw'),
(126, 'Ukrainian', 'uk'),
(127, 'Urdu', 'ur'),
(128, 'Uzbek', 'uz'),
(129, 'Vietnamese', 'vi'),
(130, 'Volapuk', 'vo'),
(131, 'Wolof', 'wo'),
(132, 'Xhosa', 'xh'),
(133, 'Yoruba', 'yo'),
(134, 'Chinese', 'zh'),
(135, 'Zulu', 'zu');

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

DROP TABLE IF EXISTS `pays`;
CREATE TABLE IF NOT EXISTS `pays` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `CodePays` varchar(2) NOT NULL,
  `Pays` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=247 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`ID`, `CodePays`, `Pays`) VALUES
(1, 'AF', 'Afghanistan'),
(2, 'AL', 'Albania'),
(3, 'DZ', 'Algeria'),
(4, 'DS', 'American Samoa'),
(5, 'AD', 'Andorra'),
(6, 'AO', 'Angola'),
(7, 'AI', 'Anguilla'),
(8, 'AQ', 'Antarctica'),
(9, 'AG', 'Antigua and Barbuda'),
(10, 'AR', 'Argentina'),
(11, 'AM', 'Armenia'),
(12, 'AW', 'Aruba'),
(13, 'AU', 'Australia'),
(14, 'AT', 'Austria'),
(15, 'AZ', 'Azerbaijan'),
(16, 'BS', 'Bahamas'),
(17, 'BH', 'Bahrain'),
(18, 'BD', 'Bangladesh'),
(19, 'BB', 'Barbados'),
(20, 'BY', 'Belarus'),
(21, 'BE', 'Belgium'),
(22, 'BZ', 'Belize'),
(23, 'BJ', 'Benin'),
(24, 'BM', 'Bermuda'),
(25, 'BT', 'Bhutan'),
(26, 'BO', 'Bolivia'),
(27, 'BA', 'Bosnia and Herzegovina'),
(28, 'BW', 'Botswana'),
(29, 'BV', 'Bouvet Island'),
(30, 'BR', 'Brazil'),
(31, 'IO', 'British Indian Ocean Territory'),
(32, 'BN', 'Brunei Darussalam'),
(33, 'BG', 'Bulgaria'),
(34, 'BF', 'Burkina Faso'),
(35, 'BI', 'Burundi'),
(36, 'KH', 'Cambodia'),
(37, 'CM', 'Cameroon'),
(38, 'CA', 'Canada'),
(39, 'CV', 'Cape Verde'),
(40, 'KY', 'Cayman Islands'),
(41, 'CF', 'Central African Republic'),
(42, 'TD', 'Chad'),
(43, 'CL', 'Chile'),
(44, 'CN', 'China'),
(45, 'CX', 'Christmas Island'),
(46, 'CC', 'Cocos (Keeling) Islands'),
(47, 'CO', 'Colombia'),
(48, 'KM', 'Comoros'),
(49, 'CG', 'Congo'),
(50, 'CK', 'Cook Islands'),
(51, 'CR', 'Costa Rica'),
(52, 'HR', 'Croatia (Hrvatska)'),
(53, 'CU', 'Cuba'),
(54, 'CY', 'Cyprus'),
(55, 'CZ', 'Czech Republic'),
(56, 'DK', 'Denmark'),
(57, 'DJ', 'Djibouti'),
(58, 'DM', 'Dominica'),
(59, 'DO', 'Dominican Republic'),
(60, 'TP', 'East Timor'),
(61, 'EC', 'Ecuador'),
(62, 'EG', 'Egypt'),
(63, 'SV', 'El Salvador'),
(64, 'GQ', 'Equatorial Guinea'),
(65, 'ER', 'Eritrea'),
(66, 'EE', 'Estonia'),
(67, 'ET', 'Ethiopia'),
(68, 'FK', 'Falkland Islands (Malvinas)'),
(69, 'FO', 'Faroe Islands'),
(70, 'FJ', 'Fiji'),
(71, 'FI', 'Finland'),
(72, 'FR', 'France'),
(73, 'FX', 'France, Metropolitan'),
(74, 'GF', 'French Guiana'),
(75, 'PF', 'French Polynesia'),
(76, 'TF', 'French Southern Territories'),
(77, 'GA', 'Gabon'),
(78, 'GM', 'Gambia'),
(79, 'GE', 'Georgia'),
(80, 'DE', 'Germany'),
(81, 'GH', 'Ghana'),
(82, 'GI', 'Gibraltar'),
(83, 'GK', 'Guernsey'),
(84, 'GR', 'Greece'),
(85, 'GL', 'Greenland'),
(86, 'GD', 'Grenada'),
(87, 'GP', 'Guadeloupe'),
(88, 'GU', 'Guam'),
(89, 'GT', 'Guatemala'),
(90, 'GN', 'Guinea'),
(91, 'GW', 'Guinea-Bissau'),
(92, 'GY', 'Guyana'),
(93, 'HT', 'Haiti'),
(94, 'HM', 'Heard and Mc Donald Islands'),
(95, 'HN', 'Honduras'),
(96, 'HK', 'Hong Kong'),
(97, 'HU', 'Hungary'),
(98, 'IS', 'Iceland'),
(99, 'IN', 'India'),
(100, 'IM', 'Isle of Man'),
(101, 'ID', 'Indonesia'),
(102, 'IR', 'Iran (Islamic Republic of)'),
(103, 'IQ', 'Iraq'),
(104, 'IE', 'Ireland'),
(105, 'IL', 'Israel'),
(106, 'IT', 'Italy'),
(107, 'CI', 'Ivory Coast'),
(108, 'JE', 'Jersey'),
(109, 'JM', 'Jamaica'),
(110, 'JP', 'Japan'),
(111, 'JO', 'Jordan'),
(112, 'KZ', 'Kazakhstan'),
(113, 'KE', 'Kenya'),
(114, 'KI', 'Kiribati'),
(115, 'KP', 'Korea, Democratic People\'s Republic of'),
(116, 'KR', 'Korea, Republic of'),
(117, 'XK', 'Kosovo'),
(118, 'KW', 'Kuwait'),
(119, 'KG', 'Kyrgyzstan'),
(120, 'LA', 'Lao People\'s Democratic Republic'),
(121, 'LV', 'Latvia'),
(122, 'LB', 'Lebanon'),
(123, 'LS', 'Lesotho'),
(124, 'LR', 'Liberia'),
(125, 'LY', 'Libyan Arab Jamahiriya'),
(126, 'LI', 'Liechtenstein'),
(127, 'LT', 'Lithuania'),
(128, 'LU', 'Luxembourg'),
(129, 'MO', 'Macau'),
(130, 'MK', 'Macedonia'),
(131, 'MG', 'Madagascar'),
(132, 'MW', 'Malawi'),
(133, 'MY', 'Malaysia'),
(134, 'MV', 'Maldives'),
(135, 'ML', 'Mali'),
(136, 'MT', 'Malta'),
(137, 'MH', 'Marshall Islands'),
(138, 'MQ', 'Martinique'),
(139, 'MR', 'Mauritania'),
(140, 'MU', 'Mauritius'),
(141, 'TY', 'Mayotte'),
(142, 'MX', 'Mexico'),
(143, 'FM', 'Micronesia, Federated States of'),
(144, 'MD', 'Moldova, Republic of'),
(145, 'MC', 'Monaco'),
(146, 'MN', 'Mongolia'),
(147, 'ME', 'Montenegro'),
(148, 'MS', 'Montserrat'),
(149, 'MA', 'Morocco'),
(150, 'MZ', 'Mozambique'),
(151, 'MM', 'Myanmar'),
(152, 'NA', 'Namibia'),
(153, 'NR', 'Nauru'),
(154, 'NP', 'Nepal'),
(155, 'NL', 'Netherlands'),
(156, 'AN', 'Netherlands Antilles'),
(157, 'NC', 'New Caledonia'),
(158, 'NZ', 'New Zealand'),
(159, 'NI', 'Nicaragua'),
(160, 'NE', 'Niger'),
(161, 'NG', 'Nigeria'),
(162, 'NU', 'Niue'),
(163, 'NF', 'Norfolk Island'),
(164, 'MP', 'Northern Mariana Islands'),
(165, 'NO', 'Norway'),
(166, 'OM', 'Oman'),
(167, 'PK', 'Pakistan'),
(168, 'PW', 'Palau'),
(169, 'PS', 'Palestine'),
(170, 'PA', 'Panama'),
(171, 'PG', 'Papua New Guinea'),
(172, 'PY', 'Paraguay'),
(173, 'PE', 'Peru'),
(174, 'PH', 'Philippines'),
(175, 'PN', 'Pitcairn'),
(176, 'PL', 'Poland'),
(177, 'PT', 'Portugal'),
(178, 'PR', 'Puerto Rico'),
(179, 'QA', 'Qatar'),
(180, 'RE', 'Reunion'),
(181, 'RO', 'Romania'),
(182, 'RU', 'Russian Federation'),
(183, 'RW', 'Rwanda'),
(184, 'KN', 'Saint Kitts and Nevis'),
(185, 'LC', 'Saint Lucia'),
(186, 'VC', 'Saint Vincent and the Grenadines'),
(187, 'WS', 'Samoa'),
(188, 'SM', 'San Marino'),
(189, 'ST', 'Sao Tome and Principe'),
(190, 'SA', 'Saudi Arabia'),
(191, 'SN', 'Senegal'),
(192, 'RS', 'Serbia'),
(193, 'SC', 'Seychelles'),
(194, 'SL', 'Sierra Leone'),
(195, 'SG', 'Singapore'),
(196, 'SK', 'Slovakia'),
(197, 'SI', 'Slovenia'),
(198, 'SB', 'Solomon Islands'),
(199, 'SO', 'Somalia'),
(200, 'ZA', 'South Africa'),
(201, 'GS', 'South Georgia South Sandwich Islands'),
(202, 'SS', 'South Sudan'),
(203, 'ES', 'Spain'),
(204, 'LK', 'Sri Lanka'),
(205, 'SH', 'St. Helena'),
(206, 'PM', 'St. Pierre and Miquelon'),
(207, 'SD', 'Sudan'),
(208, 'SR', 'Suriname'),
(209, 'SJ', 'Svalbard and Jan Mayen Islands'),
(210, 'SZ', 'Swaziland'),
(211, 'SE', 'Sweden'),
(212, 'CH', 'Switzerland'),
(213, 'SY', 'Syrian Arab Republic'),
(214, 'TW', 'Taiwan'),
(215, 'TJ', 'Tajikistan'),
(216, 'TZ', 'Tanzania, United Republic of'),
(217, 'TH', 'Thailand'),
(218, 'TG', 'Togo'),
(219, 'TK', 'Tokelau'),
(220, 'TO', 'Tonga'),
(221, 'TT', 'Trinidad and Tobago'),
(222, 'TN', 'Tunisia'),
(223, 'TR', 'Turkey'),
(224, 'TM', 'Turkmenistan'),
(225, 'TC', 'Turks and Caicos Islands'),
(226, 'TV', 'Tuvalu'),
(227, 'UG', 'Uganda'),
(228, 'UA', 'Ukraine'),
(229, 'AE', 'United Arab Emirates'),
(230, 'GB', 'United Kingdom'),
(231, 'US', 'United States'),
(232, 'UM', 'United States minor outlying islands'),
(233, 'UY', 'Uruguay'),
(234, 'UZ', 'Uzbekistan'),
(235, 'VU', 'Vanuatu'),
(236, 'VA', 'Vatican City State'),
(237, 'VE', 'Venezuela'),
(238, 'VN', 'Vietnam'),
(239, 'VG', 'Virgin Islands (British)'),
(240, 'VI', 'Virgin Islands (U.S.)'),
(241, 'WF', 'Wallis and Futuna Islands'),
(242, 'EH', 'Western Sahara'),
(243, 'YE', 'Yemen'),
(244, 'ZR', 'Zaire'),
(245, 'ZM', 'Zambia'),
(246, 'ZW', 'Zimbabwe');

-- --------------------------------------------------------

--
-- Structure de la table `salles`
--

DROP TABLE IF EXISTS `salles`;
CREATE TABLE IF NOT EXISTS `salles` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Salle` varchar(255) DEFAULT NULL,
  `Nplaces` int(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `salles`
--

INSERT INTO `salles` (`ID`, `Salle`, `Nplaces`) VALUES
(1, 'K01', 80),
(2, 'K02', 100),
(3, 'K03', 85),
(4, 'K04', 90),
(5, 'K05', 60),
(6, 'K06', 90),
(7, 'K07', 65),
(8, 'K08', 40),
(9, 'K09', 50),
(10, 'K10', 100);

-- --------------------------------------------------------

--
-- Structure de la table `seances`
--

DROP TABLE IF EXISTS `seances`;
CREATE TABLE IF NOT EXISTS `seances` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `SallesID` int(10) NOT NULL,
  `HorairesID` int(10) NOT NULL,
  `DatesID` int(10) NOT NULL,
  `AdministrateursID` int(10) NOT NULL,
  `FilmsID` int(10) NOT NULL,
  `PrixSeance` double NOT NULL,
  `NReservation` int(10) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKSeances689149` (`FilmsID`),
  KEY `FKSeances494776` (`AdministrateursID`),
  KEY `FKSeances281509` (`DatesID`),
  KEY `FKSeances734065` (`HorairesID`),
  KEY `FKSeances253344` (`SallesID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `seances`
--

INSERT INTO `seances` (`ID`, `SallesID`, `HorairesID`, `DatesID`, `AdministrateursID`, `FilmsID`, `PrixSeance`, `NReservation`) VALUES
(1, 1, 1, 1, 1, 1, 55, 2),
(2, 2, 1, 1, 1, 2, 60, 0),
(3, 3, 1, 1, 2, 3, 55, 0),
(4, 4, 1, 1, 2, 4, 50, 0),
(5, 5, 1, 1, 1, 5, 70, 1),
(6, 6, 1, 1, 1, 6, 45, 0),
(7, 1, 2, 2, 2, 7, 65, 0),
(8, 2, 3, 2, 2, 8, 55, 1),
(9, 3, 4, 2, 1, 9, 55, 1),
(10, 4, 3, 3, 2, 5, 60, 0);

-- --------------------------------------------------------

--
-- Structure de la table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `ClientsID` int(10) NOT NULL,
  `CartesbancairesID` int(10) NOT NULL,
  `SeancesID` int(10) NOT NULL,
  `tempsTicket` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PrixTicket` double NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKTickets807122` (`SeancesID`),
  KEY `FKTickets612375` (`CartesbancairesID`),
  KEY `FKTickets153187` (`ClientsID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
