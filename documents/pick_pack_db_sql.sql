-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 10.129.76.12
-- Tempo de geração: 06/05/2021 às 20:29
-- Versão do servidor: 5.6.26-log
-- Versão do PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pick_pack`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `ITENS_PEDIDOS`
--

CREATE TABLE `ITENS_PEDIDOS` (
  `id` varchar(200) DEFAULT NULL,
  `data_digitacao_pedido` datetime DEFAULT NULL,
  `estampa` varchar(100) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `desconto` double DEFAULT NULL,
  `barra` varchar(100) DEFAULT NULL,
  `tamanho` varchar(100) DEFAULT NULL,
  `cod_produto` varchar(100) DEFAULT NULL,
  `sku` varchar(100) DEFAULT NULL,
  `quantidade` double DEFAULT NULL,
  `cod_cor` int(11) DEFAULT NULL,
  `desc_cor` varchar(100) DEFAULT NULL,
  `desc_produto` varchar(100) DEFAULT NULL,
  `cod_pedidov` varchar(100) DEFAULT NULL,
  `cod_cliente` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `cep` varchar(100) DEFAULT NULL,
  `email_cliente` varchar(500) DEFAULT NULL,
  `telefone_cliente` varchar(500) DEFAULT NULL,
  `data_inclusao` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `ITENS_PEDIDOS`
--

INSERT INTO `ITENS_PEDIDOS` (`id`, `data_digitacao_pedido`, `estampa`, `preco`, `desconto`, `barra`, `tamanho`, `cod_produto`, `sku`, `quantidade`, `cod_cor`, `desc_cor`, `desc_produto`, `cod_pedidov`, `cod_cliente`, `estado`, `cidade`, `cep`, `email_cliente`, `telefone_cliente`, `data_inclusao`) VALUES
('123456', '2020-11-27 00:10:41', '0', 17.00, 0, '69225415455', 'U', '000140', '142457', 1, 0, 'UNICA', 'PRODUTO X', '125487', '0000001587', 'PE', 'ITAIBA', '6000000', 'TESTE@GMAIL.COM', '123456789', NULL);
-- --------------------------------------------------------

--
-- Estrutura para tabela `PEDIDOS`
--

CREATE TABLE `PEDIDOS` (
  `ID` int(11) NOT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `pedido` varchar(100) DEFAULT NULL,
  `operacao` varchar(100) DEFAULT NULL,
  `empresa` varchar(10) DEFAULT NULL,
  `datahora` datetime NOT NULL,
  `datainclusao` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `PEDIDOS`
--

INSERT INTO `PEDIDOS` (`ID`, `usuario`, `pedido`, `operacao`, `empresa`, `datahora`, `datainclusao`) VALUES
(6874, 'AUXILIAR1', '689747', 'embalagem', '2', '2020-04-17 10:12:42', '2020-04-17 13:12:42'),
(6875, 'AUXILIAR1', '694508', 'embalagem', '2', '2020-04-17 10:28:43', '2020-04-17 13:28:43'),
(120621, 'FUNCONARIO2', '943999', 'montagem', '2', '2021-05-06 16:51:53', '2021-05-06 19:51:53');

-- --------------------------------------------------------

--
-- Estrutura para tabela `PEDIDOS_V`
--

CREATE TABLE `PEDIDOS_V` (
  `cod_pedidov` varchar(100) NOT NULL,
  `status_workflow_desc` varchar(100) NOT NULL,
  `data_digitacao` datetime NOT NULL,
  `data_inclusao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `horas_com_pedido` double DEFAULT NULL,
  `data_atualizacao` datetime DEFAULT NULL,
  `nome_transportadora` varchar(100) DEFAULT NULL,
  `data_entrega` datetime DEFAULT NULL,
  `data_pagamento` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `v_frete` double DEFAULT NULL,
  `empresa` varchar(100) NOT NULL,
  `produtos_ok` int(11) DEFAULT NULL,
  `estado` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `cep` varchar(100) NOT NULL,
  `desc_tipo_pagto` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `PEDIDOS_V`
--

INSERT INTO `PEDIDOS_V` (`cod_pedidov`, `status_workflow_desc`, `data_digitacao`, `data_inclusao`, `horas_com_pedido`, `data_atualizacao`, `nome_transportadora`, `data_entrega`, `data_pagamento`, `total`, `v_frete`, `empresa`, `produtos_ok`, `estado`, `cidade`, `cep`, `desc_tipo_pagto`) VALUES
(' CR-628329NOVO', 'ENVIADO', '2019-12-07 23:00:00', '2019-12-09 12:09:48', NULL, NULL, 'MOTOBOY', '2019-12-11 23:00:00', '2019-12-08 23:00:00', 125.6, 0, '01', NULL, 'CE', 'FORTALEZA', '0000000', 'DINHEIRO'),
('000000003474', 'CANCELADO', '2019-12-17 00:00:00', '2020-01-03 13:51:12', NULL, NULL, 'CORREIOS', NULL, NULL, 377.36, 0, '02', NULL, 'SP', 'SAO PAULO', '00000000', 'DINHEIRO'),
('000000004648', 'CANCELADO', '2020-02-19 00:00:00', '2020-02-28 19:34:03', NULL, NULL, 'CORREIOS', NULL, NULL, 185.3, 0, '02', NULL, 'SP', 'SAO PAULO', '00000000', 'DINHEIRO'),
('000000005099', 'ENTREGUE', '2020-03-10 00:00:00', '2020-03-13 11:52:53', NULL, NULL, 'CORREIOS', NULL, NULL, 369.6, 0, '02', NULL, 'SP', 'SAO PAULO', '00000000', 'DINHEIRO'),
('004180', 'CANCELADO', '2020-03-05 00:00:00', '2020-03-06 09:17:37', NULL, NULL, 'MANDAE', '2020-03-08 00:00:00', '2020-03-06 00:00:00', 15.9, 0, '02', NULL, 'SP', 'SAO PAULO', '00000000', 'DINHEIRO');

-- --------------------------------------------------------

--
-- Estrutura para tabela `PRODUTOS`
--

CREATE TABLE `PRODUTOS` (
  `cod_produto` varchar(100) NOT NULL,
  `desc_marca` varchar(100) NOT NULL,
  `categoria` varchar(100) NOT NULL,
  `departamento` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `PRODUTOS`
--

INSERT INTO `PRODUTOS` (`cod_produto`, `desc_marca`, `categoria`, `departamento`) VALUES
('000017', 'MARCA X', 'KIT DE MAQUIAGEM', 'FACE'),
('000018', 'MARCA Z', 'DELINEADOR', 'OLHOS');

-- --------------------------------------------------------

--
-- Estrutura para tabela `USERS`
--

CREATE TABLE `USERS` (
  `usuario` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `refUser` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Despejando dados para a tabela `USERS`
--

INSERT INTO `USERS` (`usuario`, `senha`, `refUser`) VALUES
('USER1', 'X1', 'X1'),
('USER2', 'X2', 'X2');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `ITENS_PEDIDOS`
--
ALTER TABLE `ITENS_PEDIDOS`
  ADD UNIQUE KEY `id` (`id`);

--
-- Índices de tabela `PEDIDOS`
--
ALTER TABLE `PEDIDOS`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `ID_2` (`ID`);

--
-- Índices de tabela `PEDIDOS_V`
--
ALTER TABLE `PEDIDOS_V`
  ADD PRIMARY KEY (`cod_pedidov`),
  ADD UNIQUE KEY `CodigoPedido` (`cod_pedidov`);

--
-- Índices de tabela `PRODUTOS`
--
ALTER TABLE `PRODUTOS`
  ADD UNIQUE KEY `cod_produto` (`cod_produto`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `PEDIDOS`
--
ALTER TABLE `PEDIDOS`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120623;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
