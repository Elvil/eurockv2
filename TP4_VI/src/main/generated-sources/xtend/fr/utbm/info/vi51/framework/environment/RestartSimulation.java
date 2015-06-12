package fr.utbm.info.vi51.framework.environment;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

@SuppressWarnings("all")
public class RestartSimulation extends Event {
  /**
   * Construct an event. The source of the event is unknown.
   */
  @Generated
  public RestartSimulation() {
    super();
  }
  
  /**
   * Construct an event.
   * @param source - address of the agent that is emitting this event.
   */
  @Generated
  public RestartSimulation(final Address source) {
    super(source);
  }
  
  @Generated
  private final static long serialVersionUID = 588368462L;
}
