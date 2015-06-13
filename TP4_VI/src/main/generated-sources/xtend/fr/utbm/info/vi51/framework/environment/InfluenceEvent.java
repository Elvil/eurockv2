package fr.utbm.info.vi51.framework.environment;

import io.sarl.lang.annotation.Generated;
import io.sarl.lang.core.Event;

import java.util.List;

import org.eclipse.xtext.xbase.lib.Conversions;

/**
 * This event contains an influence
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 */
@SuppressWarnings("all")
public class InfluenceEvent extends Event {
  public final List<Influence> influences;
  
  public InfluenceEvent(final Influence... e) {
    this.influences = ((List<Influence>)Conversions.doWrapArray(e));
  }
  
  @Override
  @Generated
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    if (!super.equals(obj))
      return false;
    InfluenceEvent other = (InfluenceEvent) obj;
    if (this.influences == null) {
      if (other.influences != null)
        return false;
    } else if (!this.influences.equals(other.influences))
      return false;
    return true;
  }
  
  @Override
  @Generated
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.influences== null) ? 0 : this.influences.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the InfluenceEvent event's attributes only.
   */
  @Generated
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("influences  = ").append(this.influences);
    return result.toString();
  }
  
  @Generated
  private final static long serialVersionUID = 567434364L;
}
