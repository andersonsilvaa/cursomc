package com.project.cursomc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.cursomc.domain.Categoria;
import com.project.cursomc.domain.Produto;
import com.project.cursomc.repositories.CategoriaRepository;
import com.project.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

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
		this.produtoRepository.saveAll(Arrays.asList(produtoComputador, produtoImpressora, produtoMouse));
	}
	
}
