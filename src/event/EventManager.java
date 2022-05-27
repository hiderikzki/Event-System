package event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Event Utility Class for Storing Data and Calling Events
 * @author Hideri : 2022
 */
public class EventManager
{
    /** Storage of all Registered Classes and their Instances */
    public static final HashMap<Class<?>, Object> classRegistry = new HashMap<>();

    /**
     * @param obj Object to be Added to {@link #classRegistry}
     */
    public static void register(Object obj)
    {
        if(!classRegistry.containsKey(obj.getClass()))
        {
            classRegistry.put(obj.getClass(), obj);
        }
    }

    /**
     * @param obj Object to be Removed from {@link #classRegistry}
     */
    public static void unregister(Object obj)
    {
        classRegistry.remove(obj.getClass());
    }

    /**
     * Clear {@link #classRegistry}
     */
    public static void clean()
    {
        classRegistry.clear();
    }

    /**
     * Method Invoker using Reflections
     * @param event Event that Called Invoke with {@link Event#call()}
     */
    public static void invoke(Event event) throws IllegalAccessException, InvocationTargetException, InstantiationException
    {
        for(Map.Entry<Class<?>, Object> entry : classRegistry.entrySet())
        {
            List<Method> methods = filter(event, entry.getKey().getDeclaredMethods());

            for(Method method : methods)
            {
                method.setAccessible(true);
                method.invoke(entry.getValue(), event);
            }
        }
    }

    /**
     * Used for {@link #invoke(Event) invoke}
     * @param event Event to Check for During Filtering
     * @param methods All Target Class Methods
     * @return Filtered Class Method List
     */
    private static List<Method> filter(Event event, Method[] methods)
    {
        return Arrays.stream(methods).filter(method -> {
            Class<?>[] parameters = method.getParameterTypes();
            return Arrays.stream(parameters).anyMatch(cls -> cls.equals(event.getClass())) && parameters.length == 1 && method.isAnnotationPresent(EventTarget.class);
        }).collect(Collectors.toList());
    }
}
