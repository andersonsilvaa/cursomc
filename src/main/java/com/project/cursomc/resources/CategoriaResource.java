package com.project.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> Listar() {
		List<Categoria> lstCategoria = new ArrayList<Categoria>();
		
		Categoria categoriaInformatica = new Categoria(1L, "Informática");
		Categoria categoriaEscritorio = new Categoria(2L, "Escritório");
		
		lstCategoria.add(categoriaInformatica);
		lstCategoria.add(categoriaEscritorio);
		
		return lstCategoria;
	}

}
