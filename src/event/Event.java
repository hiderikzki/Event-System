package event;

import java.lang.reflect.InvocationTargetException;

/**
 * Extensible Event Class
 * @author Hideri
 * @since May 27, 2022
 */
public class Event
{
    private boolean cancelled;

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }

    public void call()
    {
        try
        {
            EventManager.invoke(this);
        }
        catch (IllegalAccessException | InvocationTargetException | InstantiationException e)
        {
            throw new RuntimeException("Failed to Invoke Method", e);
        }
    }
}
