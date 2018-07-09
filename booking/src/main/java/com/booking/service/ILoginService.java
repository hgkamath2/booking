package com.booking.service;

import com.booking.dto.Authentication;

public interface ILoginService {
	
	//should return sessionToken generated
	public String authenticate(Authentication auth);
//	public void validateToken(String authToken);
//	public void signOut(String authToken);
//	public String resetPassword(Authentication auth);
//	public String forgotPassword(String email);
}
