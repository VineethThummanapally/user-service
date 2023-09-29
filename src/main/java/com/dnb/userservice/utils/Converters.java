package com.dnb.userservice.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Converters {

	public String ListToString(List<String> list) {
		return String.join(",", list);
	}

	public List<String> StringToList(String string) {
		return Arrays.asList(string.split(","));
	}
}
