-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 29/08/2023 às 19:01
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `cobranca`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `acao_cobranca`
--

CREATE TABLE `acao_cobranca` (
  `idAcao` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idDevedor` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `tipoAcao` varchar(20) NOT NULL,
  `descricaoAcao` text NOT NULL,
  `dataAcao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `acao_cobranca`
--

INSERT INTO `acao_cobranca` (`idAcao`, `idCliente`, `idDevedor`, `idUsuario`, `tipoAcao`, `descricaoAcao`, `dataAcao`) VALUES
(12, 5, 51, 0, 'Telefonema', 'Ligação efetuada, devedor virá negociar.', '2023-07-10'),
(13, 5, 51, 0, 'Telefonema', 'Ligação efetuada, devedor virá negociar.', '2023-07-11'),
(14, 5, 51, 0, 'WhatsApp', 'Encaminhada mensagem, devedor não respondeu.', '2023-07-09'),
(15, 7, 51, 0, 'E-mail', 'Enviado e-mail para o endereço registrado!', '2023-07-18');

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `cpfcnpj` varchar(18) NOT NULL,
  `valorEmCobranca` double NOT NULL,
  `comissao` double NOT NULL,
  `diaReembolso` int(11) NOT NULL,
  `custoDevedorNaoPago` double NOT NULL,
  `razao` varchar(150) NOT NULL,
  `responsavel` varchar(50) NOT NULL,
  `informacoesAdicionais` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nome`, `cpfcnpj`, `valorEmCobranca`, `comissao`, `diaReembolso`, `custoDevedorNaoPago`, `razao`, `responsavel`, `informacoesAdicionais`) VALUES
(5, 'Vencal', '01620222000100', 300, 10, 7, 35, 'Vencal Calçados Ltda', 'Eduardo', 'Loja de calçados.'),
(7, 'Super Útil', '10748256000190', 115, 10, 5, 30, 'Comercial Schwertz Ltda', 'Sadi', '');

-- --------------------------------------------------------

--
-- Estrutura para tabela `comissoes`
--

CREATE TABLE `comissoes` (
  `idComissao` int(11) NOT NULL,
  `idPagamento` int(11) NOT NULL,
  `valorComissao` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `comissoes`
--

INSERT INTO `comissoes` (`idComissao`, `idPagamento`, `valorComissao`) VALUES
(1, 32, 6),
(2, 35, 6.3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `comprovantes`
--

CREATE TABLE `comprovantes` (
  `idDevedor` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idDebito` int(11) NOT NULL,
  `arquivo` text NOT NULL,
  `id` int(11) NOT NULL,
  `dataInclusao` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `comprovantes`
--

INSERT INTO `comprovantes` (`idDevedor`, `idCliente`, `idDebito`, `arquivo`, `id`, `dataInclusao`) VALUES
(51, 5, 190, 'IMG_20220505_111617.jpg', 180, '2023-07-10'),
(51, 5, 191, 'IMG_20220509_123112.jpg', 181, '2023-07-10'),
(51, 5, 192, 'IMG_20230310_160315.jpg', 182, '2023-07-10'),
(51, 7, 193, 'IMG_20210331_120449.jpg', 183, '2023-07-10'),
(51, 5, 194, 'IMG_20210502_165926.jpg', 184, '2023-07-10');

-- --------------------------------------------------------

--
-- Estrutura para tabela `debitos`
--

CREATE TABLE `debitos` (
  `idDebito` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idDevedor` int(11) NOT NULL,
  `valor` double NOT NULL,
  `dataCompra` date NOT NULL,
  `descricao` text NOT NULL,
  `dataInclusao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `debitos`
--

INSERT INTO `debitos` (`idDebito`, `idCliente`, `idDevedor`, `valor`, `dataCompra`, `descricao`, `dataInclusao`) VALUES
(190, 5, 51, 50, '2023-05-10', 'Chinelo', '2023-07-10'),
(191, 5, 51, 115, '2023-05-10', 'Tenis	', '2023-07-10'),
(192, 5, 51, 100, '2023-07-12', 'Chapéu	', '2023-07-10'),
(193, 7, 51, 115, '2023-06-15', 'Rancho', '2023-07-10'),
(194, 5, 51, 35, '2023-03-15', 'Camiseta	', '2023-07-10');

-- --------------------------------------------------------

--
-- Estrutura para tabela `devedor-cliente`
--

CREATE TABLE `devedor-cliente` (
  `idDevedor` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `dataFinalizacaoCobranca` date NOT NULL,
  `taxaJuros` double NOT NULL,
  `saldoDevedor` double NOT NULL,
  `dataCadastro` date NOT NULL,
  `ativado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `devedor-cliente`
--

INSERT INTO `devedor-cliente` (`idDevedor`, `idCliente`, `dataFinalizacaoCobranca`, `taxaJuros`, `saldoDevedor`, `dataCadastro`, `ativado`) VALUES
(51, 5, '2023-12-10', 5, 20, '2023-07-10', 0),
(51, 7, '2024-01-01', 7, 46, '2023-07-10', 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `devedores`
--

CREATE TABLE `devedores` (
  `idDevedor` int(11) NOT NULL,
  `cpfcnpj` varchar(18) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `razao` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `devedores`
--

INSERT INTO `devedores` (`idDevedor`, `cpfcnpj`, `nome`, `razao`) VALUES
(51, '814.956.001-32', 'Tib', 'Tiburcio');

-- --------------------------------------------------------

--
-- Estrutura para tabela `devolucoes`
--

CREATE TABLE `devolucoes` (
  `idDevolucao` int(11) NOT NULL,
  `idPagamento` int(11) NOT NULL,
  `valorDevolvido` double NOT NULL,
  `devolvido` tinyint(1) NOT NULL,
  `dataDevolucao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `devolucoes`
--

INSERT INTO `devolucoes` (`idDevolucao`, `idPagamento`, `valorDevolvido`, `devolvido`, `dataDevolucao`) VALUES
(1, 32, 54, 0, '2023-10-07'),
(2, 35, 20.7, 0, '2023-12-05');

-- --------------------------------------------------------

--
-- Estrutura para tabela `enderecos`
--

CREATE TABLE `enderecos` (
  `id` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idDevedor` int(11) DEFAULT NULL,
  `cep` varchar(10) NOT NULL,
  `rua` varchar(100) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `complemento` varchar(50) NOT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `referencia` text NOT NULL,
  `dataInclusao` varchar(10) NOT NULL,
  `dataUltimoContato` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `enderecos`
--

INSERT INTO `enderecos` (`id`, `idCliente`, `idDevedor`, `cep`, `rua`, `numero`, `complemento`, `bairro`, `cidade`, `estado`, `referencia`, `dataInclusao`, `dataUltimoContato`) VALUES
(26, 5, 51, '1111111', '501', '155', 'ap 301', 'centro', 'cruz alta', 'Santa Catarina', 'teste', '2023-07-10', '2023-07-10'),
(27, 7, NULL, '98005100', 'Domingos Verissimo', '100', '0', 'Fatima', 'Cruz Alta', 'Rio Grande do Sul', 'Supermercado', '2023-05-02', '1900-01-01'),
(28, 5, NULL, '98005100', 'Pinheiro Machado', '250', '0', 'Centro', 'Cruz Alta', 'Rio Grande do Sul', 'Calçadão', '2023-05-02', '1900-01-01');

-- --------------------------------------------------------

--
-- Estrutura para tabela `negociacao`
--

CREATE TABLE `negociacao` (
  `idNegociacao` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idDevedor` int(11) NOT NULL,
  `dataNegociacao` date NOT NULL,
  `dataPrimeiraParcela` date NOT NULL,
  `formaPagamento` text NOT NULL,
  `valorTotal` double NOT NULL,
  `valorPago` double DEFAULT NULL,
  `valorOriginal` double NOT NULL,
  `Juros` double NOT NULL,
  `saldoDevedor` double NOT NULL,
  `nParcelas` int(11) NOT NULL,
  `pago` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `negociacao`
--

INSERT INTO `negociacao` (`idNegociacao`, `idCliente`, `idDevedor`, `dataNegociacao`, `dataPrimeiraParcela`, `formaPagamento`, `valorTotal`, `valorPago`, `valorOriginal`, `Juros`, `saldoDevedor`, `nParcelas`, `pago`) VALUES
(17, 5, 51, '2023-07-10', '2023-07-10', 'Dinheiro', 180, 180, 0, 0, 0, 3, 1),
(18, 5, 51, '2023-07-12', '2023-07-12', 'Dinheiro', 100, 100, 0, 0, 0, 1, 1),
(19, 7, 51, '2023-07-10', '2023-08-10', 'Dinheiro', 115, 50, 0, 0, 69, 5, NULL),
(20, 5, 51, '2023-06-20', '2023-06-20', 'Dinheiro', 42, 0, 0, 0, 42, 2, NULL);

-- --------------------------------------------------------

--
-- Estrutura para tabela `negociacao_debitos`
--

CREATE TABLE `negociacao_debitos` (
  `id` int(11) NOT NULL,
  `idNegociacao` int(11) NOT NULL,
  `idDebito` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `negociacao_debitos`
--

INSERT INTO `negociacao_debitos` (`id`, `idNegociacao`, `idDebito`) VALUES
(29, 17, 190),
(30, 17, 191),
(31, 18, 192),
(32, 19, 193),
(33, 20, 194);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pagamentos`
--

CREATE TABLE `pagamentos` (
  `idPagamento` int(11) NOT NULL,
  `dataPagamento` date NOT NULL,
  `formaPagamento` text NOT NULL,
  `valorPago` double NOT NULL,
  `valorParcela` double NOT NULL,
  `idNegociacao` int(11) NOT NULL,
  `dataVencimento` date NOT NULL,
  `pago` tinyint(1) NOT NULL,
  `encargos` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `pagamentos`
--

INSERT INTO `pagamentos` (`idPagamento`, `dataPagamento`, `formaPagamento`, `valorPago`, `valorParcela`, `idNegociacao`, `dataVencimento`, `pago`, `encargos`) VALUES
(30, '2023-07-10', 'Dinheiro', 60, 60, 17, '2023-07-10', 1, 0),
(31, '2023-07-12', 'Dinheiro', 60, 60, 17, '2023-08-10', 1, 0),
(32, '2023-09-10', 'Dinheiro', 60, 60, 17, '2023-09-10', 1, 0),
(33, '2023-07-12', 'Dinheiro', 100, 100, 18, '2023-07-12', 1, 5),
(34, '2023-07-10', 'Dinheiro', 23, 23, 19, '2023-08-10', 1, 0),
(35, '2023-11-10', 'Dinheiro', 27, 23, 19, '2023-09-10', 1, 4),
(36, '1900-01-01', 'Dinheiro', 0, 23, 19, '2023-10-10', 0, 0),
(37, '1900-01-01', 'Dinheiro', 0, 23, 19, '2023-11-10', 0, 0),
(38, '1900-01-01', 'Dinheiro', 0, 23, 19, '2023-12-10', 0, 0),
(39, '1900-01-01', 'Dinheiro', 0, 21, 20, '2023-06-20', 0, 0),
(40, '1900-01-01', 'Dinheiro', 0, 21, 20, '2023-07-20', 0, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `perfil_usuarios`
--

CREATE TABLE `perfil_usuarios` (
  `idPerfil` int(11) NOT NULL,
  `nomePerfil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `permissoes_perfil`
--

CREATE TABLE `permissoes_perfil` (
  `id` int(11) NOT NULL,
  `idPerfil` int(11) NOT NULL,
  `clientes` int(11) NOT NULL,
  `devedores` int(11) NOT NULL,
  `debitos` int(11) NOT NULL,
  `negociacoes` int(11) NOT NULL,
  `acoes` int(11) NOT NULL,
  `pagamentos` int(11) NOT NULL,
  `relatorios` int(11) NOT NULL,
  `usuarios` int(11) NOT NULL,
  `permissoes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `telefones`
--

CREATE TABLE `telefones` (
  `idTelefone` int(11) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `idDevedor` int(11) DEFAULT NULL,
  `idCliente` int(11) NOT NULL,
  `dataInclusao` date NOT NULL,
  `dataUltimoContato` date NOT NULL,
  `obs` text NOT NULL,
  `email` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `telefones`
--

INSERT INTO `telefones` (`idTelefone`, `telefone`, `idDevedor`, `idCliente`, `dataInclusao`, `dataUltimoContato`, `obs`, `email`) VALUES
(44, '55992320552', 51, 5, '2023-07-10', '2023-07-10', 'Teste', 'teste@tiburcio.com.br'),
(45, '5533225500', NULL, 7, '2023-05-02', '1900-01-01', 'Sem obs', 'superutil@superutil.com.br'),
(46, '5533220000', NULL, 5, '2023-05-02', '1900-01-01', 'Sem obs', 'vencal@vencal.com.br');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `idUsuario` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `senha` text NOT NULL,
  `idPerfil` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `acao_cobranca`
--
ALTER TABLE `acao_cobranca`
  ADD PRIMARY KEY (`idAcao`),
  ADD KEY `FK_Acao_Cliente_Devedor` (`idCliente`,`idDevedor`);

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Índices de tabela `comissoes`
--
ALTER TABLE `comissoes`
  ADD PRIMARY KEY (`idComissao`),
  ADD KEY `FK_Pagamento_Comissao` (`idPagamento`);

--
-- Índices de tabela `comprovantes`
--
ALTER TABLE `comprovantes`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `debitos`
--
ALTER TABLE `debitos`
  ADD PRIMARY KEY (`idDebito`);

--
-- Índices de tabela `devedor-cliente`
--
ALTER TABLE `devedor-cliente`
  ADD PRIMARY KEY (`idDevedor`,`idCliente`),
  ADD KEY `FK_Cliente_DevedorCliente` (`idCliente`),
  ADD KEY `FK_Devedor_DevedorCliente` (`idDevedor`);

--
-- Índices de tabela `devedores`
--
ALTER TABLE `devedores`
  ADD PRIMARY KEY (`idDevedor`);

--
-- Índices de tabela `devolucoes`
--
ALTER TABLE `devolucoes`
  ADD PRIMARY KEY (`idDevolucao`),
  ADD KEY `FK_Pagamento_Devolucao` (`idPagamento`);

--
-- Índices de tabela `enderecos`
--
ALTER TABLE `enderecos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_End_Cliente` (`idCliente`),
  ADD KEY `FK_End_Devedor` (`idDevedor`);

--
-- Índices de tabela `negociacao`
--
ALTER TABLE `negociacao`
  ADD PRIMARY KEY (`idNegociacao`),
  ADD KEY `FKClienteNegociacao` (`idCliente`),
  ADD KEY `FKDevedorNegociacao` (`idDevedor`);

--
-- Índices de tabela `negociacao_debitos`
--
ALTER TABLE `negociacao_debitos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idNegociacao` (`idNegociacao`,`idDebito`),
  ADD KEY `FKDebitos` (`idDebito`);

--
-- Índices de tabela `pagamentos`
--
ALTER TABLE `pagamentos`
  ADD PRIMARY KEY (`idPagamento`),
  ADD KEY `FKNegocia` (`idNegociacao`);

--
-- Índices de tabela `perfil_usuarios`
--
ALTER TABLE `perfil_usuarios`
  ADD PRIMARY KEY (`idPerfil`);

--
-- Índices de tabela `permissoes_perfil`
--
ALTER TABLE `permissoes_perfil`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `telefones`
--
ALTER TABLE `telefones`
  ADD PRIMARY KEY (`idTelefone`),
  ADD KEY `FKCliente` (`idCliente`),
  ADD KEY `FKDevedor` (`idDevedor`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `acao_cobranca`
--
ALTER TABLE `acao_cobranca`
  MODIFY `idAcao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `comissoes`
--
ALTER TABLE `comissoes`
  MODIFY `idComissao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `comprovantes`
--
ALTER TABLE `comprovantes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=185;

--
-- AUTO_INCREMENT de tabela `debitos`
--
ALTER TABLE `debitos`
  MODIFY `idDebito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=195;

--
-- AUTO_INCREMENT de tabela `devedores`
--
ALTER TABLE `devedores`
  MODIFY `idDevedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de tabela `devolucoes`
--
ALTER TABLE `devolucoes`
  MODIFY `idDevolucao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `enderecos`
--
ALTER TABLE `enderecos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de tabela `negociacao`
--
ALTER TABLE `negociacao`
  MODIFY `idNegociacao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de tabela `negociacao_debitos`
--
ALTER TABLE `negociacao_debitos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de tabela `pagamentos`
--
ALTER TABLE `pagamentos`
  MODIFY `idPagamento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de tabela `perfil_usuarios`
--
ALTER TABLE `perfil_usuarios`
  MODIFY `idPerfil` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `permissoes_perfil`
--
ALTER TABLE `permissoes_perfil`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `telefones`
--
ALTER TABLE `telefones`
  MODIFY `idTelefone` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `acao_cobranca`
--
ALTER TABLE `acao_cobranca`
  ADD CONSTRAINT `FK_Acao_Cliente_Devedor` FOREIGN KEY (`idCliente`,`idDevedor`) REFERENCES `devedor-cliente` (`idCliente`, `idDevedor`);

--
-- Restrições para tabelas `comissoes`
--
ALTER TABLE `comissoes`
  ADD CONSTRAINT `FK_Pagamento_Comissao` FOREIGN KEY (`idPagamento`) REFERENCES `pagamentos` (`idPagamento`);

--
-- Restrições para tabelas `devedor-cliente`
--
ALTER TABLE `devedor-cliente`
  ADD CONSTRAINT `FK_Cliente_DevedorCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `FK_Devedor_DevedorCliente` FOREIGN KEY (`idDevedor`) REFERENCES `devedores` (`idDevedor`);

--
-- Restrições para tabelas `devolucoes`
--
ALTER TABLE `devolucoes`
  ADD CONSTRAINT `FK_Pagamento_Devolucao` FOREIGN KEY (`idPagamento`) REFERENCES `pagamentos` (`idPagamento`);

--
-- Restrições para tabelas `enderecos`
--
ALTER TABLE `enderecos`
  ADD CONSTRAINT `FK_End_Cliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `FK_End_Devedor` FOREIGN KEY (`idDevedor`) REFERENCES `devedores` (`idDevedor`);

--
-- Restrições para tabelas `negociacao`
--
ALTER TABLE `negociacao`
  ADD CONSTRAINT `FKClienteNegociacao` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `FKDevedorNegociacao` FOREIGN KEY (`idDevedor`) REFERENCES `devedores` (`idDevedor`);

--
-- Restrições para tabelas `negociacao_debitos`
--
ALTER TABLE `negociacao_debitos`
  ADD CONSTRAINT `FKDebitos` FOREIGN KEY (`idDebito`) REFERENCES `debitos` (`idDebito`),
  ADD CONSTRAINT `FKNegocicao` FOREIGN KEY (`idNegociacao`) REFERENCES `negociacao` (`idNegociacao`);

--
-- Restrições para tabelas `pagamentos`
--
ALTER TABLE `pagamentos`
  ADD CONSTRAINT `FKNegocia` FOREIGN KEY (`idNegociacao`) REFERENCES `negociacao` (`idNegociacao`);

--
-- Restrições para tabelas `telefones`
--
ALTER TABLE `telefones`
  ADD CONSTRAINT `FKCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `FKDevedor` FOREIGN KEY (`idDevedor`) REFERENCES `devedores` (`idDevedor`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
