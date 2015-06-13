package fr.utbm.info.vi51.framework.agent;

import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.Influence;
import fr.utbm.info.vi51.framework.environment.StopSimulation;
import fr.utbm.info.vi51.framework.math.Vector2f;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.lang.annotation.EarlyExit;
import io.sarl.lang.annotation.FiredEvent;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AgentContext;
import io.sarl.lang.core.Percept;

import java.util.List;
import java.util.UUID;

import com.google.common.base.Objects;

@SuppressWarnings("all")
public class AbstractAnimat extends Agent {
  protected DynamicType behaviorType;
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    Object _get_1 = occurrence.parameters[1];
    StandardPhysicEnvironment physicSkill = new StandardPhysicEnvironment(
      ((UUID) _get), 
      ((UUID) _get_1));
    Object _get_2 = occurrence.parameters[2];
    this.behaviorType = ((DynamicType) _get_2);
    this.<StandardPhysicEnvironment>setSkill(PhysicEnvironment.class, physicSkill);
  }
  
  public float getMaxLinear(final fr.utbm.info.vi51.framework.environment.Percept p) {
    float _xifexpression = (float) 0;
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      _xifexpression = p.getMaxLinearAcceleration();
    } else {
      _xifexpression = p.getMaxLinearSpeed();
    }
    return _xifexpression;
  }
  
  public float getMaxAngular(final fr.utbm.info.vi51.framework.environment.Percept p) {
    float _xifexpression = (float) 0;
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      _xifexpression = p.getMaxAngularAcceleration();
    } else {
      _xifexpression = p.getMaxAngularSpeed();
    }
    return _xifexpression;
  }
  
  public void emitInfluence(final BehaviourOutput output, final Influence... influences) {
    if ((output != null)) {
      DynamicType _type = output.getType();
      boolean _tripleEquals = (_type == DynamicType.STEERING);
      if (_tripleEquals) {
        Vector2f _linear = output.getLinear();
        float _angular = output.getAngular();
        this.influenceSteering(_linear, _angular, influences);
      } else {
        Vector2f _linear_1 = output.getLinear();
        float _angular_1 = output.getAngular();
        this.influenceKinematic(_linear_1, _angular_1, influences);
      }
    } else {
      Vector2f _vector2f = new Vector2f();
      this.influenceSteering(_vector2f, 0f, influences);
    }
  }
  
  public void doNothing() {
    Vector2f _vector2f = new Vector2f();
    this.influenceKinematic(_vector2f, 0f);
  }
  
  public fr.utbm.info.vi51.framework.environment.Percept first(final List<fr.utbm.info.vi51.framework.environment.Percept> list) {
    fr.utbm.info.vi51.framework.environment.Percept _xifexpression = null;
    boolean _isEmpty = list.isEmpty();
    if (_isEmpty) {
      _xifexpression = null;
    } else {
      _xifexpression = list.get(0);
    }
    return _xifexpression;
  }
  
  @Percept
  public void _handle_StopSimulation_1(final StopSimulation occurrence) {
    this.killMe();
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic(final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic(otherInfluences);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(float,fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(float,fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic(final float angularInfluence, final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic(angularInfluence, otherInfluences);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f,fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f,fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic(final Vector2f linearInfluence, final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic(linearInfluence, otherInfluences);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f,float,fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceKinematic(fr.utbm.info.vi51.framework.math.Vector2f,float,fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceKinematic(final Vector2f linearInfluence, final float angularInfluence, final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceKinematic(linearInfluence, angularInfluence, otherInfluences);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering(final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering(otherInfluences);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(float,fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(float,fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering(final float angularInfluence, final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering(angularInfluence, otherInfluences);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f,fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f,fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering(final Vector2f linearInfluence, final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering(linearInfluence, otherInfluences);
  }
  
  /**
   * See the capacity {@link fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f,float,fr.utbm.info.vi51.framework.environment.Influence[])}.
   * 
   * @see fr.utbm.info.vi51.framework.agent.PhysicEnvironment#influenceSteering(fr.utbm.info.vi51.framework.math.Vector2f,float,fr.utbm.info.vi51.framework.environment.Influence[])
   */
  @Generated
  @ImportedCapacityFeature(PhysicEnvironment.class)
  protected void influenceSteering(final Vector2f linearInfluence, final float angularInfluence, final Influence... otherInfluences) {
    getSkill(fr.utbm.info.vi51.framework.agent.PhysicEnvironment.class).influenceSteering(linearInfluence, angularInfluence, otherInfluences);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Lifecycle#killMe()}.
   * 
   * @see io.sarl.core.Lifecycle#killMe()
   */
  @EarlyExit
  @FiredEvent({ AgentKilled.class, Destroy.class })
  @Generated
  @ImportedCapacityFeature(Lifecycle.class)
  protected void killMe() {
    getSkill(io.sarl.core.Lifecycle.class).killMe();
  }
  
  /**
   * See the capacity {@link io.sarl.core.Lifecycle#spawnInContext(java.lang.Class<? extends io.sarl.lang.core.Agent>,io.sarl.lang.core.AgentContext,java.lang.Object[])}.
   * 
   * @see io.sarl.core.Lifecycle#spawnInContext(java.lang.Class<? extends io.sarl.lang.core.Agent>,io.sarl.lang.core.AgentContext,java.lang.Object[])
   */
  @FiredEvent(AgentSpawned.class)
  @Generated
  @ImportedCapacityFeature(Lifecycle.class)
  protected UUID spawnInContext(final Class<? extends Agent> agentClass, final AgentContext context, final Object... params) {
    return getSkill(io.sarl.core.Lifecycle.class).spawnInContext(agentClass, context, params);
  }
  
  /**
   * See the capacity {@link io.sarl.core.Lifecycle#spawnInContextWithID(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.util.UUID,io.sarl.lang.core.AgentContext,java.lang.Object[])}.
   * 
   * @see io.sarl.core.Lifecycle#spawnInContextWithID(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.util.UUID,io.sarl.lang.core.AgentContext,java.lang.Object[])
   */
  @FiredEvent(AgentSpawned.class)
  @Generated
  @ImportedCapacityFeature(Lifecycle.class)
  protected UUID spawnInContextWithID(final Class<? extends Agent> agentClass, final UUID agentID, final AgentContext context, final Object... params) {
    return getSkill(io.sarl.core.Lifecycle.class).spawnInContextWithID(agentClass, agentID, context, params);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public AbstractAnimat(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public AbstractAnimat(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
