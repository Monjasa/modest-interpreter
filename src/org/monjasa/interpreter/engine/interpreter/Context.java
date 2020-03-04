package org.monjasa.interpreter.engine.interpreter;

import org.monjasa.interpreter.engine.semanticanalyzer.SymbolTable;

import java.util.HashMap;
import java.util.Optional;

public class Context {

    private HashMap<String, Optional<?>> globalScope;

    public Context() {
        globalScope = new HashMap<>();
    }

    public HashMap<String, Optional<?>> getGlobalScope() {
        return globalScope;
    }
}
