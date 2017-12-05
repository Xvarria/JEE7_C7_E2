package com.empresa.model.db;

import java.util.List;

import javax.ejb.Remote;

import com.empresa.model.external.UserCommand;

@Remote
public interface UserService {

	public List<UserCommand> getAllUsuarios();
	
	public void persist(UserCommand UserCommand);
}

