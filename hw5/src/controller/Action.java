package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class Action {
    /**
     * Returns the name of the action, used to match the request in the map
     * @return the name of the action
     */
    public abstract String getName();

    /**
     * A method which will send a debugging message to the user indicating
     * that this subclass of action does not handle GET requests.
     * 
     * Actions that do handle GET requests will need to override this method.
     * @param request the request
     * @return the name of the view which displays the error message.
     */
    public String performGet(HttpServletRequest request) {
        // Normally, we would return null to get a 404 error, but for easier debugging...
        request.setAttribute("message",
                "No implementation of performGet() for action " + getName());
        return "action-error-message.jsp";
    }

    /**
     * A method which will send a debugging message to the user indicating
     * that this subclass of action does not handle POST requests.
     * 
     * Actions that do handle POST requests will need to override this method.
     * @param request the request
     * @return the name of the view which displays the error message.
     */
    public String performPost(HttpServletRequest request) {
        // Normally, we would return null to get a 404 error, but for easier debugging...
        request.setAttribute("message",
                "No implementation of performPost() for action " + getName());
        return "action-error-message.jsp";
    }

    /**
     * Class methods to manage dispatching to Actions
     */
    private static Map<String, Action> hash = new HashMap<String, Action>();

    /**
     * Adds an action to the map.
     * @param a the action to be added to the map.
     */
    public static void add(Action a) {
        synchronized (hash) {
            if (hash.get(a.getName()) != null) {
                throw new AssertionError("Two actions with the same name ("
                        + a.getName() + "): " + a.getClass().getName()
                        + " and " + hash.get(a.getName()).getClass().getName());
            }

            hash.put(a.getName(), a);
        }
    }

    /**
     * Looks the action up in the map and then executes it.
     * @param name the name of the action
     * @param request the request to process
     * @return the name of the view
     */
    public static String perform(String name, HttpServletRequest request) {
        Action a;
        synchronized (hash) {
            a = hash.get(name);
        }

        if (a == null) {
            // Normally, we would return null to get a 404 error, but for easier debugging...
            request.setAttribute("message",
                    "There is no action registered for \"" + name + "\"");
            return "action-error-message.jsp";
        }

        if ("GET".equals(request.getMethod())) {
            return a.performGet(request);
        }

        if ("POST".equals(request.getMethod())) {
            return a.performPost(request);
        }
        
        // Normally, we would return null to get a 404 error, but for easier debugging...
        request.setAttribute("message",
                "Unexpected HTTP Method (\"" + request.getMethod() + "\") for \"" + name + "\"");
        return "action-error-message.jsp";
    }
}
