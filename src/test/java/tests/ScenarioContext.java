package tests;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final ThreadLocal<Map<String, Object>> scenarioContext = new ThreadLocal<>();

    public static void setContext(String key, Object value) {
        Map<String, Object> context = scenarioContext.get();
        if (context == null) {
            context = new HashMap<>();
            scenarioContext.set(context);
        }
        context.put(key, value);
    }

    public static Object getContext(String key) {
        Map<String, Object> context = scenarioContext.get();
        if (context != null) {
            return context.get(key);
        }
        return null;
    }

    public static void clearContext() {
        scenarioContext.remove();
    }
}
