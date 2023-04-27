package controller;

import dao.UsuarioDao;
import factory.ConnectionFactory;

public class UsuarioController {
	
	private final UsuarioDao usuarioDao;

public UsuarioController() {
var factory = new ConnectionFactory();	
this.usuarioDao = new UsuarioDao(factory.recuperaConexion());
}
	
public boolean buscarUsuario(String usuario,String contrasena) {
	return usuarioDao.buscarUsuario(usuario, contrasena);
}
}
