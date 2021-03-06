package br.com.fip.gati.revistaonline.resources.web;

import java.util.Arrays;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.ValidationMessage;

public class ControllerUtil {

	public static Result includeError(Result result, String msg) {
		result.include("errors", Arrays.asList(new ValidationMessage(msg, "erro")));
		return result;
	}
	
	public static Result includeSucess(Result result, String msg) {
		result.include("sucess", Arrays.asList(new ValidationMessage(msg, "ok")));
		return result;
	}
	
}
