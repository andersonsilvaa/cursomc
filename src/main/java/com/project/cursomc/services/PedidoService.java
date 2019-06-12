package com.project.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cursomc.domain.Pedido;
import com.project.cursomc.repositories.PedidoRepository;
import com.project.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido consultaPorId(Long id) {
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
}
