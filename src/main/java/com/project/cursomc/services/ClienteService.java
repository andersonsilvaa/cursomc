package com.project.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cursomc.domain.Cliente;
import com.project.cursomc.repositories.ClienteRepository;
import com.project.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente consultaPorId(Long id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
}
