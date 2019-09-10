-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 10, 2019 at 07:38 PM
-- Server version: 5.6.35
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `petshop2`
--

-- --------------------------------------------------------

--
-- Table structure for table `agendamentos`
--

CREATE TABLE `agendamentos` (
  `cod_agend` int(11) NOT NULL,
  `fk_tipo_serv` int(11) DEFAULT NULL,
  `fk_func` int(11) DEFAULT NULL,
  `data_agend` date DEFAULT NULL,
  `horario` time DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `fk_pet` int(11) DEFAULT NULL,
  `fk_dono` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `agendamentos`
--

INSERT INTO `agendamentos` (`cod_agend`, `fk_tipo_serv`, `fk_func`, `data_agend`, `horario`, `obs`, `status`, `fk_pet`, `fk_dono`) VALUES
(1, 1, 1, '2018-10-05', '15:00:00', ' ', 10, 1, 1),
(2, 4, 2, '2018-09-28', '16:00:00', '', 1, 1, 7),
(3, 4, 2, '2018-09-28', '16:00:00', '', 1, 1, 7),
(4, 3, 2, '2018-08-09', '16:30:00', 'O Bob tem um problema na patinha, tomar cuidado\nno banho.', 1, 1, 13),
(5, 1, 3, '2018-10-05', '13:30:00', '', 1, 3, 14),
(6, 4, 2, '2018-12-15', '13:30:00', '', 1, 2, 10),
(7, 3, 5, '2018-11-12', '09:30:00', '', 1, 2, 2),
(8, 3, 5, '2018-11-12', '09:30:00', '', 1, 2, 2),
(9, 4, 3, '2018-12-25', '08:30:00', '', 1, 3, 7),
(10, 1, 5, '2018-10-07', '11:00:00', 'O Tobias tem problemas alérgicos com shampoos perfumados, utilizar shampoo neutro.', 1, 4, 10),
(11, 4, 6, '2018-12-12', '11:30:00', '', 1, 4, 10),
(12, 4, 6, '2018-10-07', '11:45:00', '', 1, 4, 10),
(13, 2, 3, '2018-10-07', '13:30:00', '', 1, 5, 13),
(14, 3, 1, '2018-10-08', '17:30:00', 'Problemas alérgicos com shampoos, utilizar neutro.', 1, 17, 16),
(15, 4, 5, '2018-10-08', '15:50:00', '', 1, 2, 8),
(16, 1, 4, '2019-06-02', '15:00:00', 'Alergia a sabão de côco.', 1, 3, 11);

-- --------------------------------------------------------

--
-- Table structure for table `cargos`
--

CREATE TABLE `cargos` (
  `cod_cargo` int(11) NOT NULL,
  `descricao` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cargos`
--

INSERT INTO `cargos` (`cod_cargo`, `descricao`) VALUES
(1, 'Gerente'),
(2, 'Veterinário'),
(3, 'Tosador'),
(4, 'Estoquista');

-- --------------------------------------------------------

--
-- Table structure for table `categorias`
--

CREATE TABLE `categorias` (
  `cod_catego` int(11) NOT NULL,
  `descricao` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorias`
--

INSERT INTO `categorias` (`cod_catego`, `descricao`) VALUES
(1, 'Ração'),
(2, 'Comedouro'),
(3, 'Brinquedos'),
(4, 'Casinhas'),
(5, 'Camas'),
(6, 'Shampoo'),
(7, 'Roupa para gatos'),
(8, 'Petiscos'),
(9, 'Comida para Aves');

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--

CREATE TABLE `clientes` (
  `cod_cli` int(11) NOT NULL,
  `nome` varchar(52) DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `rg` varchar(9) DEFAULT NULL,
  `data_nasc` date DEFAULT NULL,
  `tel` varchar(10) DEFAULT NULL,
  `cel` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `compl` varchar(255) DEFAULT NULL,
  `cep` varchar(8) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`cod_cli`, `nome`, `cpf`, `rg`, `data_nasc`, `tel`, `cel`, `email`, `endereco`, `numero`, `compl`, `cep`, `cidade`, `bairro`, `uf`) VALUES
(1, 'Carmen da Silva Jesus', '77529145096', '379779262', '1980-06-07', '1124459988', '11988881111', 'carmen@bol.com.br', 'Av. Monteiro Lobato', '667', '', '07224080', 'Guarulhos', 'Jardim Macedo', 'SP'),
(2, 'Ken Ashkorp', '08863644004', '374375628', '1995-10-12', '1124559998', '11988881221', 'kenash@yt.com', 'Rua Alexandria II', '556', 'Sobrado', '07554020', 'São Paulo', 'Vila Panda', 'SP'),
(3, 'Jorge Faria Silva', '51472140036', '505204769', '1993-12-31', '1124566798', '11988881221', 'jorgeFaria22@gmail.com', 'Viela Padre Cícero', '488', '', '07234020', 'Guarulhos', 'Vila Galvão', 'SP'),
(4, 'Melchior Da Silva', '56832564798', '126498782', '1997-01-17', '1124143553', '11984457452', 'melchiorbrasil@gmail.com', 'Av Jobson Lobato', '87', NULL, '07058060', 'Guarulhos', 'Vila Madalena', 'SP'),
(5, 'Maria das Dores Silva', '38352605291', '412902448', '1983-04-05', '1538150815', '15981608032', 'mariadores2004@globo.com.br', 'Rua Rene Regaconi', '776', 'Apt. 45', '18087663', 'São Paulo', 'Sorocaba', 'SP'),
(6, 'Guilherme Manuel Ramos', '30075913844', '222677934', '1986-06-22', '1127975419', '11982313734', 'guilhermemanuel@gmail.com', 'Viela São José dos Cordeiros', '325', 'ap 02', '07195331', 'São Paulo', 'Jardim Alvorada', NULL),
(7, 'Carlos Eduardo Rafael Ferreira', '13634426861', '366893142', '1996-09-11', '1136049770', '11981542095', 'carlosedrafa@outlook.com', 'Rua Augusto Calheiros', '582', '', '07120250', 'Guarulhos', 'Jardim Paraventi', 'SP'),
(8, 'Noah Renan Moura', '79798976843', '234962586', '1993-02-02', '1138640430', '11986522474', 'mouranoah@gmail.com', 'Rua Dez', '361', 'Apt.24', '07075400', 'Guarulhos', 'Jardim dos Cardoso', 'SP'),
(9, 'Isaac Gael da Silva', '93763893873', '107881755', '1996-03-11', '1137084429', '11996989421', 'isaacsilva147@gmail.com', 'Rua Jeni Ikeda', '614', '', '07093081', 'Guarulhos', 'Vila Progresso', 'SP'),
(10, 'Ryan Sebastião Oliveira', '72898733865', '287919924', '1991-03-05', '1128690914', '11985425139', 'ryanoliveira87@globo.com', 'Viela Salgueiro', '157', 'Apt.45', '07241241', 'Guarulhos', 'Vila Progresso', 'SP'),
(11, 'Paulo Giovanni Arthur Moreira', '01088975836', '366424658', '1996-04-14', '1139410337', '11982996741', 'paulogiovani157@hotmail.com', 'Rua Ladainha', '157', '', '07031060', 'Guarulhos', 'Vila Venditti', 'SP'),
(12, 'Caio Diego Filipe Araújo', '89447118879', '430655113', '1996-08-23', '1125350996', '11986904532', 'caiofil10a@outlook.com', 'Rua Franca', '174', 'Apt 02', '07161310', 'Guarulhos', 'Cidade Soberana', 'SP'),
(13, 'Jorge Theo da Rocha', '13282579837', '500705021', '1995-07-23', '1137194240', '11985171142', 'jorgerocha14@hotmail.com', 'Rua Soldado Alcebíades Bobadilha da Cunha', '157', '', '07032130', 'Guarulhos', 'Vila Zamataro', 'SP'),
(14, 'Luan Lucca Oliveira', '32180448805', '212074568', '1996-06-08', '1138531641', '11995801961', 'luccaolivs187@uol.com', 'Viela do Véu', '434', '', '07160380', 'Guarulhos', 'Jardim Matarazzo', 'SP'),
(15, 'Nicolas Anthony Caldeira', '34011555800', '215235836', '1990-05-23', '1139226398', '11989773635', 'nicolascaldeira15@outlook.com', 'Praça Elpídio Roque de Oliveira', '1574', 'Apt 52', '07121083', 'Guarulhos', 'Jardim São Roberto', 'SP'),
(16, 'Tiago Yago Joaquim Pires', '71954789807', '339924329', '1996-01-15', '1124216578', '11997845124', 'tiagoyagojoaquim@hotmail.com', 'Viela Ruanda', '732', '', '07150041', 'Guarulhos', 'Cidade Seródio', 'SP'),
(17, 'César Rafael Fábio das Neves', '93735851851', '381086896', '1996-08-11', '1126840667', '11989965890', 'cesarrafaelf@gmail.com', 'Rua Teofilândia', '963', 'Apt 45', '07171132', 'Guarulhos', 'Jardim Presidente Dutra', 'SP'),
(18, 'Raul Noah Cauã das Neves', '51672854881', '164542498', '1996-09-09', '1138801430', '11988147166', 'raulnoahc14@europamotors.com', 'Rua das Palmas', '700', 'Apt 023', '07178460', 'Guarulhos', 'Vila Carmela I', 'SP'),
(19, 'Renato Renan Nicolas Almeida', '18732795823', '234852392', '1996-05-22', '1137912012', '11981461322', 'renatorenannicolas@hotmail.com', 'Rua Antônio Pohlman', '768', '', '07082020', 'Guarulhos', 'Jardim Terezópolis', 'SP'),
(20, 'Felipe Enrico Ramos', '51076232809', '373672159', '1996-04-18', '1135380130', '11986019652', 'felipeenricoramos_@unitau.br', 'Rua Caudilho', '586', 'Apt.987', '07263345', 'Guarulhos', 'Cidade Tupinambá', 'SP'),
(21, 'Noah Thomas Giovanni Pires', '94401042875', '403506864', '1996-12-01', '1139796143', '11984023622', 'nnoahthomasgiovann147@uol.com', 'Rua Alfredo Guedes da Silva', '859', '', '07151100', 'Guarulhos', 'Jardim São João', 'SP'),
(22, 'Elias Vicente da Conceição', '81335224807', '482366928', '1996-12-10', '1127861180', '11992670164', 'eeliasvicentedaconceicao.20@uol.com', 'Rua Quatro', '336', '', '07075340', 'Guarulhos', 'Jardim dos Cardoso', 'SP'),
(23, 'Gabriel Thiago Nicolas Almeida', '18855435817', '488499525', '1996-06-14', '1127047118', '11984232299', 'gabrielthiago-77@gmail.com', 'Rua Martina Leon de Huamani', '276', '', '07160570', 'Guarulhos', 'Jardim Jade', 'SP'),
(24, 'Ryan Calebe Francisco Brito', '79958097869', '214013947', '1996-11-04', '1136295341', '11991665771', 'ryancalebe@globo.com', 'v', '382', 'Apt.673', '07243162', 'Guarulhos', 'Jardim Silvestre', 'SP'),
(25, 'Zeca Serafino silva', '20304030830', '136986894', '1996-11-14', '1136939791', '11992453259', 'henrycarlos32@hotmail.com', 'Viela Quixerê', '764', 'Apt.415', '07230470', 'Guarulhos', 'Jardim Santa Helena', 'SP'),
(26, 'Konrad Benenos', '86873827827', '385075637', '1996-06-15', '1129289096', '11996924476', 'kevinyagoramos80@hotmail.com', 'Rua Ivinhema', '806', '', '07215130', 'Guarulhos', 'Jardim Santo Afonso', 'SP'),
(27, 'Raul Edson Vitor Rodrigues', '59499006850', '459772107', '1996-05-12', '1126296920', '11995851112', 'rauledsonvitor57@outlook.com', 'Rua Mauá', '255', '', '07114110', 'Guarulhos', 'Jardim Santa Clara', 'SP'),
(28, 'Natália Andreia Figueiredo', '98667072885', '509835235', '1996-08-07', '1126619543', '11997256071', 'nnataliaandreia@hotmail.com', 'Rua Roberto Venturole', '895', '', '07250015', 'Guarulhos', 'Cidade Aracilia', 'SP'),
(29, 'Allana Lívia Liz Gonçalves', '09309729880', '236131667', '1996-05-03', '1129264018', '11985097277', 'allanalivializ@globo.com', 'Rua São João Pau D\'Alho', '143', 'Apt.71', '07182330', 'Guarulhos', 'Jardim Castanha', 'SP'),
(30, 'Marli Sophia Emily de Paula', '04562415827', '507698472', '1996-05-08', '1135909347', '11991865030', 'marlisophiaemily_87@uol.com', 'Estrada Sebastião Walter Fusco', 'Estrada Sebastião Walter Fusco', '', '07183000', 'Guarulhos', 'Cidade SOIMCO', 'SP'),
(31, 'Pietra Antônia Galvão', '69791927871', '489709783', '1996-01-18', '1127030563', '11989192775', 'ppietraantoniagalvao@outlook.com', 'Rua São Bernardo', '663', 'Apt.50', '07132310', 'Guarulhos', 'Jardim Bela Vista', 'SP');

-- --------------------------------------------------------

--
-- Table structure for table `fornecedores`
--

CREATE TABLE `fornecedores` (
  `cod_fornec` int(11) NOT NULL,
  `nomeFornecedor` varchar(255) DEFAULT NULL,
  `detalhes` varchar(255) DEFAULT NULL,
  `cnpj` varchar(14) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(10) DEFAULT NULL,
  `cel` varchar(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fornecedores`
--

INSERT INTO `fornecedores` (`cod_fornec`, `nomeFornecedor`, `detalhes`, `cnpj`, `email`, `tel`, `cel`) VALUES
(1, 'Distribuidora Pet Best Ally', 'Distribuidora de ração Magnus e acessórios pet', '83525854000119', 'allyracoesbr@oul.com.br', '1127982112', '11989592177'),
(2, 'Pet Love rações e CIA', 'Distribuidora de rações em geral e acessórios', '70975248000153', 'petlovecontato@petlovebr.com.br', '1938126291', '19987104739'),
(3, 'Casa de Ração Luiz e Sandro Ltda', 'Distribuidora de produtos Pedigree', '65421759000193', 'luizesandroempresapet@terra.com.br', '1125201720', '11998620896'),
(4, 'Casa de aves uirapuru', 'Produtos para aves em geral', '89349067000140', '', '1128139859', '11984848854');

-- --------------------------------------------------------

--
-- Table structure for table `funcionarios`
--

CREATE TABLE `funcionarios` (
  `cod_func` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `rg` varchar(9) NOT NULL,
  `data_nasc` date NOT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `cel` varchar(12) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `compl` varchar(255) DEFAULT NULL,
  `cep` varchar(8) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `bairro` varchar(255) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  `conta_corrente` varchar(25) DEFAULT NULL,
  `agencia` varchar(25) DEFAULT NULL,
  `banco` varchar(25) DEFAULT NULL,
  `cidade_conta` varchar(25) DEFAULT NULL,
  `estado_conta` varchar(25) DEFAULT NULL,
  `PIS` varchar(12) DEFAULT NULL,
  `salario` float DEFAULT NULL,
  `cargo` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcionarios`
--

INSERT INTO `funcionarios` (`cod_func`, `nome`, `cpf`, `rg`, `data_nasc`, `tel`, `cel`, `email`, `endereco`, `numero`, `compl`, `cep`, `cidade`, `bairro`, `uf`, `conta_corrente`, `agencia`, `banco`, `cidade_conta`, `estado_conta`, `PIS`, `salario`, `cargo`) VALUES
(1, 'Lucia Gabriela dos Santos Silva', '96749598072', '299319817', '1999-08-27', '1128107647', '11987197740', 'luciagab2010@outlook.com', 'Rua Luciara', '454', '', '07183100', 'Guarulhos', 'Jardim São Manoel', 'SP', '1162571-1', '0111', 'Bradesco', 'Guarulhos', 'SP', '56883528768', 1245.53, ' 3'),
(2, 'Cristiane Débora Vieira', '41521905827', '303242176', '1997-01-03', '1135471600', '11985884299', 'chris_vieira2223@hotmail.com', 'Viela Santa Maria de Fátima', '150', NULL, '07159700', 'Guarulhos', 'Parque Residencial Bambi', 'SP', '51609-1', '0211', 'Itaú', 'São Paulo', 'SP', '96191676726', 1266.87, '3'),
(3, 'Victor Danilo da Mata', '15287641885', '434269712', '1991-11-17', '1125838679', '11984844817', 'vitorfigaro@yahoo.com.br', 'Rua Itororó', '136', 'Sobrado', '07144470', 'Guarulhos', 'SP', NULL, '0068399P', '1040', 'Bradesco', 'Sao Paulo', 'SP', '40107301988', 3199.85, '2'),
(4, 'Cauã Henry Castro', '26486360801', '450103602', '1995-10-12', '1128804543', '11981236933', 'caulehenry2009@gmail.com', 'Passagem Carrancas', '421', '', '07176410', 'Guarulhos', 'SP', NULL, '05384546-1', '0820', 'Santander', 'Barretos', 'SP', '59348282869', 1288.65, '3'),
(5, 'Marina Marlene Galvão', '11257046837', '157156084', '1999-03-05', '1137961320', '11995600774', 'marigalvao2012@hotmail.com', 'Rua Manoel da Cruz Filho', '441', 'Condomínio B 67', '07192018', 'Guarulhos', 'Vila Barros', 'SP', '1838938-1', '0057', 'Citibank', 'Sao Paulo', 'SP', '55416480346', 1166.87, '3'),
(6, 'Isabelly Camila Luciana Vieira', '48317657833', '104883728', '1990-05-19', '1136014474', '119 83696714', 'isaveterinaria2014sp@outlook.com', 'Praça Estrela do Norte', '129', 'Apartamento 13', '07224290', 'Guarulhos', 'Cidade Industrial Satélite de São Paulo', 'SP', '92990602-7', '3809', 'Santander', 'Sao Paulo', 'SP', '04586350031', 2499.65, '2');

-- --------------------------------------------------------

--
-- Table structure for table `pets`
--

CREATE TABLE `pets` (
  `cod_pet` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `raca` varchar(25) DEFAULT NULL,
  `cor` varchar(25) DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `sexo` enum('Macho','Fêmea') DEFAULT NULL,
  `tipo_animal` enum('Cães','Gatos','Pássaros','Roedores','Peixes','Outros') DEFAULT NULL,
  `idade` enum('Filhotes','Adultos','Idosos') DEFAULT NULL,
  `porte` enum('Porte pequeno','Porte médio','Porte grande') DEFAULT NULL,
  `obs` varchar(255) DEFAULT NULL,
  `fk_dono` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pets`
--

INSERT INTO `pets` (`cod_pet`, `nome`, `raca`, `cor`, `peso`, `sexo`, `tipo_animal`, `idade`, `porte`, `obs`, `fk_dono`) VALUES
(1, 'Bob', 'Não definido', 'Preto', 9.5, 'Macho', 'Cães', 'Adultos', 'Porte médio', NULL, 3),
(2, 'Serenna', 'Siamesa', 'Cinza', 7, 'Fêmea', 'Gatos', 'Adultos', 'Porte médio', '', 8),
(3, 'Tadeu', 'Não definido', 'Preto e branco', 10, 'Macho', 'Gatos', 'Adultos', 'Porte médio', '', 11),
(4, 'Tobias', 'Bulldog', 'Cinza', 9, 'Macho', 'Cães', 'Idosos', 'Porte pequeno', '', 10),
(5, 'Madona', 'Não definido', 'Branca', 15, 'Fêmea', 'Cães', 'Adultos', 'Porte médio', '', 13),
(6, 'Napoleão', 'Borzoi ', 'Preto', 9, 'Macho', 'Cães', 'Filhotes', 'Porte pequeno', 'Pata quebrada', 3),
(7, 'Honey ', 'yorkshire', 'Castanho', 11, 'Macho', 'Cães', 'Adultos', 'Porte pequeno', '', 6),
(8, 'Thor', 'Chihuahua', 'Cinzento', 10, 'Macho', 'Cães', 'Filhotes', 'Porte pequeno', '', 9),
(9, 'Anubis', 'Beagle', 'Dourado', 8, 'Macho', 'Cães', 'Adultos', 'Porte pequeno', 'Patinha machucada.', 17),
(10, 'Sofia', 'Basset Hound', 'Dourado', 13, 'Fêmea', 'Cães', 'Adultos', 'Porte pequeno', '', 29),
(11, 'Filó', 'Collie', 'Branco', 15, 'Fêmea', 'Cães', 'Adultos', 'Porte grande', '', 31),
(12, 'Digo', 'Persa', 'Dourado', 8, 'Macho', 'Gatos', 'Adultos', 'Porte pequeno', '', 27),
(13, 'Kimberly ', 'Siamês', 'Cinzento', 7, 'Fêmea', 'Gatos', 'Idosos', 'Porte grande', '', 22),
(14, 'Ed', 'Ragdoll', 'Branco', 10, 'Macho', 'Gatos', 'Adultos', 'Porte pequeno', '', 4),
(15, 'Penélope', 'Himalaio', 'Cinzento', 9, 'Fêmea', 'Gatos', 'Adultos', 'Porte pequeno', '', 5),
(16, 'Mirna', 'Calopsita', 'Amarela', 0.9, 'Fêmea', 'Pássaros', 'Adultos', 'Porte pequeno', '', 12),
(17, 'Rusty', 'russo', 'Branco', 0.8, 'Macho', 'Roedores', 'Filhotes', 'Porte pequeno', '', 16),
(18, 'Torresmo', 'papagaio ', 'Verde', 0.24, 'Macho', 'Pássaros', 'Filhotes', 'Porte pequeno', '', 18),
(19, 'Zimba', 'Cacatua', 'Branco', 0.5, 'Macho', 'Pássaros', 'Idosos', 'Porte grande', '', 8),
(20, 'Bob', 'poodle', 'Marrom', 19, 'Macho', 'Cães', 'Filhotes', 'Porte grande', '', 14);

-- --------------------------------------------------------

--
-- Table structure for table `produtos`
--

CREATE TABLE `produtos` (
  `cod_prod` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `qtd` int(11) DEFAULT NULL,
  `preco` float DEFAULT NULL,
  `fk_categoria` int(11) DEFAULT NULL,
  `fk_fornec` int(11) DEFAULT NULL,
  `tipo_pet` set('Cães','Gatos','Pássaros','Roedores','Peixes','Outros') DEFAULT NULL,
  `idade_pet` set('Filhotes','Adultos','Idosos') DEFAULT NULL,
  `tamanho_pet` set('Porte pequeno','Porte médio','Porte grande') DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produtos`
--

INSERT INTO `produtos` (`cod_prod`, `nome`, `descricao`, `qtd`, `preco`, `fk_categoria`, `fk_fornec`, `tipo_pet`, `idade_pet`, `tamanho_pet`) VALUES
(1, 'Ração Magnus Todo Dia para Cães 25Kg', 'Transgenico, omega 3 e 6, Vitamina E', 242, 109.9, 1, 1, 'Cães', 'Adultos,Idosos', 'Porte pequeno,Porte médio,Porte grande'),
(2, 'Ração Magnus Premium Especial para Cães Adultos 15Kg', 'Transgenico, omega 3 e 6, Vitamina E, extrado de yucca, sem corante', 265, 99.9, 1, 1, 'Cães', 'Adultos', 'Porte médio'),
(3, 'Ração Adimax Pet Magnus Premium Vegetais para Cães Filhotes 25Kg', 'Transgenico, Bio complex, Vitamina E orgânica', 258, 169.9, 1, 1, 'Cães', 'Filhotes', 'Porte pequeno,Porte médio,Porte grande'),
(4, 'Ração Golden Gatos Adultos Carne 10Kg', 'Controle PH urinário, Trangenico, Ingredientes naturais sem corantes', 488, 89.9, 1, 2, 'Gatos', 'Adultos', 'Porte pequeno,Porte médio,Porte grande'),
(5, 'Ração Golden Gatos Filhotes Frango 8Kg', 'Rico em taurina, Ingredientes naturais sem corantes, Trato urinário', 192, 107.9, 1, 2, 'Gatos', 'Filhotes', 'Porte pequeno,Porte médio,Porte grande'),
(6, 'Bola de Tênis KONG', 'Exercita mandíbula, macia', 0, 15.99, 3, 2, 'Cães,Gatos', 'Filhotes,Adultos,Idosos', 'Porte pequeno,Porte médio,Porte grande'),
(7, 'Sache Magnus Cat Carne ao Molho para Gatos Adultos', 'Auxilia no Crescimento, rico em vitaminas', 0, 1.99, 8, 2, 'Gatos', 'Adultos', 'Porte pequeno,Porte médio,Porte grande'),
(8, 'Sache Magnus Carne para Cães Adulto de Pequeno Porte', 'Rico em proteínas, sem transgênicos', 500, 1.99, 8, 2, 'Cães', 'Filhotes', 'Porte pequeno'),
(9, 'Sache Magnus Frango para Cães Filhote', 'Cozinhado à vapor, rico em proteínas', 0, 1.99, 8, 2, 'Cães', 'Filhotes', 'Porte pequeno,Porte médio,Porte grande'),
(10, 'Ração Nutrópica com Frutas para Calopsita  Ração Nutrópica com Frutas para Calopsita', 'Rico em proteínas', 900, 30, 9, 2, 'Pássaros', 'Idosos', 'Porte pequeno,Porte médio,Porte grande'),
(11, 'Ração Premier Golden Formula Cães Filhotes Frango e Arroz 3Kg', 'Alimento Premium, foco em pele saudável', 1800, 25.99, 1, 2, 'Cães', 'Filhotes', 'Porte pequeno,Porte médio,Porte grande'),
(12, 'Ração Premier Pet Ambientes Internos Cães Adultos Frango e Salmão 1Kg', 'Rico em vitaminas e sais minerais', 700, 15.75, 1, 2, 'Cães', 'Filhotes', 'Porte pequeno,Porte médio');

-- --------------------------------------------------------

--
-- Table structure for table `servicos`
--

CREATE TABLE `servicos` (
  `cod_serv` int(11) NOT NULL,
  `descricao` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `servicos`
--

INSERT INTO `servicos` (`cod_serv`, `descricao`) VALUES
(1, 'Banho'),
(2, 'Tosa'),
(3, 'Banho&Tosa'),
(4, 'Consulta');

-- --------------------------------------------------------

--
-- Table structure for table `vendas`
--

CREATE TABLE `vendas` (
  `cod_venda` int(11) NOT NULL,
  `data_venda` timestamp NULL DEFAULT NULL,
  `total` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vendas`
--

INSERT INTO `vendas` (`cod_venda`, `data_venda`, `total`) VALUES
(1, '2018-10-05 03:00:00', NULL),
(2, '2018-10-05 03:00:00', NULL),
(3, '2018-10-05 03:00:00', NULL),
(4, '2018-10-05 03:00:00', NULL),
(5, '2018-10-05 03:00:00', NULL),
(6, '2018-10-05 03:00:00', NULL),
(7, '2018-10-05 03:00:00', 303),
(8, '2018-10-05 03:00:00', NULL),
(9, '2018-10-05 03:00:00', NULL),
(10, '2018-10-05 03:00:00', 607.5699920654297),
(11, '2018-10-05 03:00:00', 705.5399856567383),
(12, '2018-10-05 03:00:00', 659.4799919128418),
(13, '2018-10-05 03:00:00', 1285.1000061035156),
(14, '2018-10-05 03:00:00', 639.5799942016602),
(15, '2018-10-05 03:00:00', 1388.7999877929688),
(16, '2018-10-05 03:00:00', 1269.2499237060547),
(17, '2018-10-05 03:00:00', 339.79998779296875),
(18, '2018-10-05 03:00:00', 139.88000106811523),
(19, '2018-10-05 03:00:00', 339.79998779296875),
(20, '2018-10-05 03:00:00', 1529.0999755859375),
(21, '2018-10-05 03:00:00', 10250.999977111816),
(22, '2018-10-05 03:00:00', 251.81000518798828),
(23, '2018-10-05 03:00:00', 503.50001525878906),
(24, '2018-10-05 23:40:00', 17940.209838867188),
(25, '2018-10-06 00:40:19', 421.70999908447266),
(26, '2018-10-06 00:49:11', 1698.300048828125),
(27, '2018-10-06 00:49:55', 299.70001220703125),
(28, '2018-10-06 00:56:50', 299.70001220703125),
(29, '2018-10-06 22:13:36', 4823.890085220337),
(30, '2018-10-07 00:18:45', 1752.6899814605713),
(31, '2018-10-07 15:36:09', 1334.7900485992432),
(32, '2019-04-20 04:33:04', 1992384.2550048828),
(33, '2019-06-02 15:41:35', 3399.179931640625);

-- --------------------------------------------------------

--
-- Table structure for table `venda_produtos`
--

CREATE TABLE `venda_produtos` (
  `fk_cod_venda` int(11) DEFAULT NULL,
  `fk_cod_produto` int(11) DEFAULT NULL,
  `qtd_produto` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venda_produtos`
--

INSERT INTO `venda_produtos` (`fk_cod_venda`, `fk_cod_produto`, `qtd_produto`) VALUES
(6, 3, 1),
(6, 2, 1),
(6, 6, 2),
(7, 3, 3),
(7, 5, 4),
(10, 3, 2),
(10, 1, 2),
(10, 6, 3),
(11, 3, 3),
(11, 2, 1),
(11, 6, 6),
(12, 3, 2),
(12, 4, 2),
(12, 5, 1),
(12, 6, 2),
(13, 3, 5),
(13, 5, 2),
(13, 1, 2),
(14, 3, 2),
(14, 5, 1),
(14, 6, 12),
(15, 3, 2),
(15, 1, 5),
(15, 2, 5),
(16, 3, 7),
(16, 6, 5),
(17, 3, 2),
(18, 5, 1),
(18, 6, 2),
(19, 3, 2),
(20, 3, 9),
(21, 3, 9),
(21, 5, 80),
(21, 4, 1),
(22, 6, 9),
(22, 5, 1),
(23, 5, 3),
(23, 4, 2),
(24, 5, 6),
(24, 1, 12),
(24, 6, 999),
(25, 3, 1),
(25, 5, 1),
(25, 6, 9),
(26, 2, 17),
(27, 2, 3),
(28, 2, 3),
(29, 1, 9),
(29, 2, 6),
(29, 3, 9),
(29, 4, 8),
(29, 5, 9),
(29, 6, 1),
(30, 1, 2),
(30, 2, 3),
(30, 3, 6),
(30, 4, 1),
(30, 5, 1),
(30, 6, 1),
(31, 1, 12),
(31, 6, 1),
(32, 2, 3),
(32, 5, 6),
(32, 6, 6),
(32, 9, 1000008),
(32, 7, 666),
(33, 6, 12),
(33, 1, 23),
(33, 3, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agendamentos`
--
ALTER TABLE `agendamentos`
  ADD PRIMARY KEY (`cod_agend`),
  ADD KEY `fk_tipo_serv` (`fk_tipo_serv`),
  ADD KEY `fk_func` (`fk_func`),
  ADD KEY `fk_pet` (`fk_pet`),
  ADD KEY `fk_dono` (`fk_dono`);

--
-- Indexes for table `cargos`
--
ALTER TABLE `cargos`
  ADD PRIMARY KEY (`cod_cargo`);

--
-- Indexes for table `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`cod_catego`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cod_cli`);

--
-- Indexes for table `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`cod_fornec`);

--
-- Indexes for table `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`cod_func`);

--
-- Indexes for table `pets`
--
ALTER TABLE `pets`
  ADD PRIMARY KEY (`cod_pet`),
  ADD KEY `fk_dono` (`fk_dono`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`cod_prod`),
  ADD KEY `fk_categoria` (`fk_categoria`),
  ADD KEY `fk_fornec` (`fk_fornec`);

--
-- Indexes for table `servicos`
--
ALTER TABLE `servicos`
  ADD PRIMARY KEY (`cod_serv`);

--
-- Indexes for table `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`cod_venda`);

--
-- Indexes for table `venda_produtos`
--
ALTER TABLE `venda_produtos`
  ADD KEY `fk_cod_venda` (`fk_cod_venda`),
  ADD KEY `fk_cod_produto` (`fk_cod_produto`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agendamentos`
--
ALTER TABLE `agendamentos`
  MODIFY `cod_agend` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `cargos`
--
ALTER TABLE `cargos`
  MODIFY `cod_cargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `categorias`
--
ALTER TABLE `categorias`
  MODIFY `cod_catego` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cod_cli` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `fornecedores`
--
ALTER TABLE `fornecedores`
  MODIFY `cod_fornec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `cod_func` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `pets`
--
ALTER TABLE `pets`
  MODIFY `cod_pet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `cod_prod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `servicos`
--
ALTER TABLE `servicos`
  MODIFY `cod_serv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `vendas`
--
ALTER TABLE `vendas`
  MODIFY `cod_venda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
