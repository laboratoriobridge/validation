package br.ufsc.bridge.platform.validation.engine;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import br.ufsc.bridge.platform.validation.exception.EngineExecutionException;
import br.ufsc.bridge.platform.validation.exception.EngineInitializationException;

public class Engine {

	private static final String NAMESPACE = "validation";
	private static final String SERVER_VALIDATE = "validate";
	private final ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

	public Engine(){
		try {
			InputStream resource = this.getClass().getResourceAsStream("/server-bundle.min.js");
			if (resource == null) {
				throw new EngineInitializationException("Não foi possível encontrar o server-bundle.js para validação");
			}

			this.scriptEngine.eval(new InputStreamReader(resource, "UTF-8"));

		} catch (UnsupportedEncodingException | ScriptException e) {
			throw new EngineInitializationException("Erro ao inicializar validador", e);
		}
	}

	public Object evalRule(String script) throws ScriptException {
		return this.scriptEngine.eval(NAMESPACE+"."+script);
	}

	public Object serverValidate(Object value, Rule rule) {
		try {
			Invocable invocable = (Invocable) this.scriptEngine;
			Object namespaceObject = this.scriptEngine.eval(NAMESPACE);
			return invocable.invokeMethod(namespaceObject, SERVER_VALIDATE, value, rule.get(this));
		} catch (NoSuchMethodException | ScriptException e) {
			throw new EngineExecutionException("Erro ao executar validador", e);
		}
	}

}
