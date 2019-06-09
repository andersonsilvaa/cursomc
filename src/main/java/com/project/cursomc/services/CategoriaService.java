package com.project.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cursomc.domain.Categoria;
import com.project.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria consultaPorId(Long id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
		return categoria.orElse(null);
	}

	
}
