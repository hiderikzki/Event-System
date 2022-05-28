# Event System
Simple Event System for Java / Minecraft

### Isn't this already a thing?
Originally the one popular event system was hexeption's event system, but as that repository does not exist anymore, i have decided to make a new, smaller and simpler one myself.

# Usage Examples

### Event Class

CustomEvent.java

```java
class CustomEvent extends Event
{
  private int x, y, z;

  public CustomEvent(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  // Getters and Setters...
}
```

### Event Registering

MainClass.java

```java
public MainClass
{
  public void mainFunction()
  {
    EventManager.register(new EventUseClass()); // Preferably using an extensible class for example a Module instead of an Example Class
  }
}
```

### Event Hooking

SomeClass.java

```java
class SomeClass
{
  // Random Code...
  
  public functionForEvent(params...)
  {
    //
    // This can be setup at any point in the method
    //
    final CustomEvent customEvent = new CustomEvent(this.x, this.y, this.z);
    customEvent.call();
    
    // Optional Checking for Cancellation
    if(customEvent.isCancelled())
    {
      return;
    }
    
    // !!! REMEMBER TO ACTUALLY HOOK NEEDED FIELDS TO USE EVENT VALUES INSTEAD OF CLASS VALUES !!!
    System.out.printf("TEST %s %s %s%n", customEvent.getX(), customEvent.getY(), customEvent.getZ());
    
    // OLD (for Example)
    // System.out.printf("TEST %s %s %s%n", this.x, this.y, this.z);
    
    // Other Code...
  }
  
  // More Random Code...
}
```

### Event Usage

EventUseClass.java

```java
class EventUseClass
{
   // Random Code...
   
   @EventTarget
   public void functionName(CustomEvent event)
   {
     // Changing Values (for Example)
     event.setX(26);
     
     // functionForEvent in SomeClass.java would now print "TEST 26 [y value] [z value]"
   }
   
   // More Random Code...
}
```
