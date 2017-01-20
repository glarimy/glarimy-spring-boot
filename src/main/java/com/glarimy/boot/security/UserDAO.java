package com.glarimy.boot.security;

public interface UserDAO {
	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	UserAttempts getUserAttempts(String username);
}