package net.shoul.bookManager.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	/*
	 * converts a Java object into JSON representation
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
