package com.project.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.cursomc.domain.Categoria;
import com.project.cursomc.domain.Cidade;
import com.project.cursomc.domain.Cliente;
import com.project.cursomc.domain.Endereco;
import com.project.cursomc.domain.Estado;
import com.project.cursomc.domain.Pagamento;
import com.project.cursomc.domain.PagamentoComBoleto;
import com.project.cursomc.domain.PagamentoComCartao;
import com.project.cursomc.domain.Pedido;
import com.project.cursomc.domain.Produto;
import com.project.cursomc.domain.Telefone;
import com.project.cursomc.domain.enums.EstadoPagamento;
import com.project.cursomc.domain.enums.TipoCliente;
import com.project.cursomc.repositories.CategoriaRepository;
import com.project.cursomc.repositories.ClienteRepository;
import com.project.cursomc.repositories.EstadoRepository;
import com.project.cursomc.repositories.PedidoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoriaInformatica = new Categoria(null, "Informática");
		Categoria categoriaEscritorio = new Categoria(null, "Escritório");
		
		Produto produtoComputador = new Produto(null, "Computador", 2000.00);
		Produto produtoImpressora = new Produto(null, "Impressora", 800.00);
		Produto produtoMouse = new Produto(null, "Mouse", 80.00); 
		
		List<Produto> produtos = Arrays.asList(produtoComputador, produtoImpressora, produtoMouse);
		produtos.forEach(produto->categoriaInformatica.addProduto(produto));
		categoriaEscritorio.addProduto(produtoImpressora);
		
		this.categoriaRepository.saveAll(Arrays.asList(categoriaInformatica, categoriaEscritorio));
		
		/***************************************************************************************************/

		Estado estadoMinasGerais = new Estado(null, "Minas Gerais");
		Estado estadoSaoPaulo = new Estado(null, "São Paulo");
		
		Cidade cidadeUberlandia = new Cidade(null, "Uberlândia");
		Cidade cidadeSaoPaulo = new Cidade(null, "São Paulo");
		Cidade cidadeCampinas = new Cidade(null, "Campinas");
		
		List<Cidade> cidades = Arrays.asList(cidadeSaoPaulo, cidadeCampinas);
		cidades.forEach(cidade->estadoSaoPaulo.addCidade(cidade));
		estadoMinasGerais.addCidade(cidadeUberlandia);
		
		this.estadoRepository.saveAll(Arrays.asList(estadoMinasGerais, estadoSaoPaulo));
		
		/***************************************************************************************************/
		
		Cliente cliente = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		Telefone primeiroTelefone = new Telefone(null, "27363323", cliente);
		Telefone segundoTelefone = new Telefone(null, "93838393", cliente);
		List<Telefone> telefones = Arrays.asList(primeiroTelefone, segundoTelefone);
		telefones.forEach(telefone->cliente.addTelefone(telefone));
		
		Endereco enderecoRuaFlores = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cliente, cidadeUberlandia);
		Endereco enderecoAvenidaMatos = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cliente, cidadeSaoPaulo);
		List<Endereco> enderecos = Arrays.asList(enderecoRuaFlores, enderecoAvenidaMatos);
		enderecos.forEach(endereco->cliente.addEndereco(endereco));
		
		this.clienteRepository.save(cliente);
		
		/***************************************************************************************************/
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido primeiroPedido = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente, enderecoRuaFlores);
		Pedido segundoPedido = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente, enderecoAvenidaMatos);
		
		Pagamento pagamentoComCartao = new PagamentoComCartao(null, EstadoPagamento.QUITADO, primeiroPedido, 6);
		primeiroPedido.setPagamento(pagamentoComCartao);
		
		Pagamento pagamentoComBoleto = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, segundoPedido, sdf.parse("20/10/2017 00:00"), null);
		segundoPedido.setPagamento(pagamentoComBoleto);
		
		List<Pedido> pedidos = Arrays.asList(primeiroPedido, segundoPedido);
		pedidos.forEach(pedido->cliente.addPedido(pedido));
		
		this.pedidoRepository.saveAll(pedidos);
	}
	
}
