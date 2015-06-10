package fr.utbm.info.vi51.project.agent;

import com.google.common.base.Objects;
import fr.utbm.info.vi51.framework.agent.AbstractAnimat;
import fr.utbm.info.vi51.framework.agent.BehaviourOutput;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.PerceptionEvent;
import fr.utbm.info.vi51.framework.environment.SimulationAgentReady;
import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.general.behavior.AlertBehaviour;
import fr.utbm.info.vi51.general.behavior.FleeBehaviour;
import fr.utbm.info.vi51.general.behavior.SeekBehaviour;
import fr.utbm.info.vi51.general.behavior.WanderBehaviour;
import fr.utbm.info.vi51.general.behavior.kinematic.KinematicFleeBehaviour;
import fr.utbm.info.vi51.general.behavior.kinematic.KinematicSeekBehaviour;
import fr.utbm.info.vi51.general.behavior.kinematic.KinematicWanderBehaviour;
import fr.utbm.info.vi51.general.behavior.steering.SteeringFleeBehaviour;
import fr.utbm.info.vi51.general.behavior.steering.SteeringSeekBehaviour;
import fr.utbm.info.vi51.general.behavior.steering.SteeringWanderBehaviour;
import fr.utbm.info.vi51.general.influence.TypeChangeInfluence;
import fr.utbm.info.vi51.project.environment.Semantics;
import fr.utbm.info.vi51.project.environment.State;
import io.sarl.core.AgentSpawned;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.lang.annotation.FiredEvent;
import io.sarl.lang.annotation.Generated;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AgentContext;
import io.sarl.lang.core.Event;
import io.sarl.lang.core.EventSpace;
import io.sarl.lang.core.Percept;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Space;
import io.sarl.lang.core.SpaceID;
import java.io.Serializable;
import java.util.UUID;

@SuppressWarnings("all")
public class spectator extends AbstractAnimat {
  protected SeekBehaviour seekBehaviour;
  
  protected FleeBehaviour fleeBehaviour;
  
  protected WanderBehaviour wanderBehaviour;
  
  protected AlertBehaviour alertBehaviour;
  
  protected final float STOP_RADIUS = (MathUtil.PI / 10f);
  
  protected final float SLOW_RADIUS = (MathUtil.PI / 4f);
  
  protected final float WANDER_CIRCLE_DISTANCE = 20f;
  
  protected final float WANDER_CIRCLE_RADIUS = 10f;
  
  protected final float WANDER_MAX_ROTATION = (MathUtil.PI / 2f);
  
  @Percept
  public void _handle_Initialize_0(final Initialize occurrence) {
    super._handle_Initialize_0(occurrence);
    boolean _equals = Objects.equal(this.behaviorType, DynamicType.STEERING);
    if (_equals) {
      SteeringSeekBehaviour _steeringSeekBehaviour = new SteeringSeekBehaviour();
      this.seekBehaviour = _steeringSeekBehaviour;
      SteeringFleeBehaviour _steeringFleeBehaviour = new SteeringFleeBehaviour();
      this.fleeBehaviour = _steeringFleeBehaviour;
      SteeringWanderBehaviour _steeringWanderBehaviour = new SteeringWanderBehaviour(this.WANDER_CIRCLE_DISTANCE, 
        this.WANDER_CIRCLE_RADIUS, 
        this.WANDER_MAX_ROTATION, 
        this.STOP_RADIUS, 
        this.SLOW_RADIUS);
      this.wanderBehaviour = _steeringWanderBehaviour;
      AlertBehaviour _alertBehaviour = new AlertBehaviour(40f);
      this.alertBehaviour = _alertBehaviour;
    } else {
      KinematicSeekBehaviour _kinematicSeekBehaviour = new KinematicSeekBehaviour();
      this.seekBehaviour = _kinematicSeekBehaviour;
      KinematicFleeBehaviour _kinematicFleeBehaviour = new KinematicFleeBehaviour();
      this.fleeBehaviour = _kinematicFleeBehaviour;
      KinematicWanderBehaviour _kinematicWanderBehaviour = new KinematicWanderBehaviour();
      this.wanderBehaviour = _kinematicWanderBehaviour;
    }
    SimulationAgentReady _simulationAgentReady = new SimulationAgentReady();
    this.emit(_simulationAgentReady);
  }
  
  @Percept
  public void _handle_PerceptionEvent_1(final PerceptionEvent occurrence) {
    fr.utbm.info.vi51.framework.environment.Percept target = this.first(occurrence.perceptions);
    boolean boolWander = true;
    boolean _or = false;
    Point2f _position = occurrence.body.getPosition();
    boolean _runAlert = this.alertBehaviour.runAlert(_position, occurrence.perceptions);
    if (_runAlert) {
      _or = true;
    } else {
      Serializable _type = occurrence.body.getType();
      boolean _equals = _type.equals(State.ALERTED);
      _or = _equals;
    }
    if (_or) {
      Point2f _position_1 = occurrence.body.getPosition();
      float _currentLinearSpeed = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear = this.getMaxLinear(occurrence.body);
      Point2f _point2f = new Point2f(300, 300);
      BehaviourOutput _runSeek = this.seekBehaviour.runSeek(_position_1, _currentLinearSpeed, _maxLinear, _point2f);
      TypeChangeInfluence _typeChangeInfluence = new TypeChangeInfluence(State.ALERTED);
      this.emitInfluence(_runSeek, _typeChangeInfluence);
      boolWander = false;
    } else {
      if ((target != null)) {
        boolean _and = false;
        Serializable _type_1 = occurrence.body.getType();
        boolean _equals_1 = Objects.equal(_type_1, State.HUNGRY);
        if (!_equals_1) {
          _and = false;
        } else {
          String _name = target.getName();
          boolean _equals_2 = _name.equals(Semantics.STAND_MIAM);
          _and = _equals_2;
        }
        if (_and) {
          Point2f _position_2 = occurrence.body.getPosition();
          float _currentLinearSpeed_1 = occurrence.body.getCurrentLinearSpeed();
          float _maxLinear_1 = this.getMaxLinear(occurrence.body);
          Point2f _position_3 = target.getPosition();
          BehaviourOutput _runSeek_1 = this.seekBehaviour.runSeek(_position_2, _currentLinearSpeed_1, _maxLinear_1, _position_3);
          Serializable _type_2 = occurrence.body.getType();
          TypeChangeInfluence _typeChangeInfluence_1 = new TypeChangeInfluence(_type_2);
          this.emitInfluence(_runSeek_1, _typeChangeInfluence_1);
          boolWander = false;
        }
        boolean _and_1 = false;
        Serializable _type_3 = occurrence.body.getType();
        boolean _equals_3 = Objects.equal(_type_3, State.SEARCH_WATCHING);
        if (!_equals_3) {
          _and_1 = false;
        } else {
          String _name_1 = target.getName();
          boolean _equals_4 = _name_1.equals(Semantics.SCENE);
          _and_1 = _equals_4;
        }
        if (_and_1) {
          Point2f _position_4 = occurrence.body.getPosition();
          float _currentLinearSpeed_2 = occurrence.body.getCurrentLinearSpeed();
          float _maxLinear_2 = this.getMaxLinear(occurrence.body);
          Point2f _position_5 = target.getPosition();
          BehaviourOutput _runSeek_2 = this.seekBehaviour.runSeek(_position_4, _currentLinearSpeed_2, _maxLinear_2, _position_5);
          Serializable _type_4 = occurrence.body.getType();
          TypeChangeInfluence _typeChangeInfluence_2 = new TypeChangeInfluence(_type_4);
          this.emitInfluence(_runSeek_2, _typeChangeInfluence_2);
          boolWander = false;
        }
      }
    }
    if (boolWander) {
      Point2f _position_6 = occurrence.body.getPosition();
      Vector2f _direction = occurrence.body.getDirection();
      float _currentLinearSpeed_3 = occurrence.body.getCurrentLinearSpeed();
      float _maxLinear_3 = this.getMaxLinear(occurrence.body);
      float _currentAngularSpeed = occurrence.body.getCurrentAngularSpeed();
      float _maxAngular = this.getMaxAngular(occurrence.body);
      BehaviourOutput _runWander = this.wanderBehaviour.runWander(_position_6, _direction, _currentLinearSpeed_3, _maxLinear_3, _currentAngularSpeed, _maxAngular);
      Serializable _type_5 = occurrence.body.getType();
      TypeChangeInfluence _typeChangeInfluence_3 = new TypeChangeInfluence(_type_5);
      this.emitInfluence(_runWander, _typeChangeInfluence_3);
    }
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected void emit(final Event e) {
    getSkill(io.sarl.core.DefaultContextInteractions.class).emit(e);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event,io.sarl.lang.core.Scope<io.sarl.lang.core.Address>)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#emit(io.sarl.lang.core.Event,io.sarl.lang.core.Scope<io.sarl.lang.core.Address>)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected void emit(final Event e, final Scope<Address> scope) {
    getSkill(io.sarl.core.DefaultContextInteractions.class).emit(e, scope);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#getDefaultAddress()}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#getDefaultAddress()
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected Address getDefaultAddress() {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).getDefaultAddress();
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#getDefaultContext()}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#getDefaultContext()
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected AgentContext getDefaultContext() {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).getDefaultContext();
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#getDefaultSpace()}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#getDefaultSpace()
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected EventSpace getDefaultSpace() {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).getDefaultSpace();
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultContext(io.sarl.lang.core.AgentContext)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultContext(io.sarl.lang.core.AgentContext)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultContext(final AgentContext context) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultContext(context);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultContext(java.util.UUID)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultContext(java.util.UUID)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultContext(final UUID contextID) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultContext(contextID);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.Space)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.Space)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultSpace(final Space space) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultSpace(space);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.SpaceID)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultSpace(io.sarl.lang.core.SpaceID)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultSpace(final SpaceID space) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultSpace(space);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isDefaultSpace(java.util.UUID)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isDefaultSpace(java.util.UUID)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isDefaultSpace(final UUID space) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isDefaultSpace(space);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#isInDefaultSpace(io.sarl.lang.core.Event)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#isInDefaultSpace(io.sarl.lang.core.Event)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected boolean isInDefaultSpace(final Event event) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).isInDefaultSpace(event);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#receive(java.util.UUID,io.sarl.lang.core.Event)}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#receive(java.util.UUID,io.sarl.lang.core.Event)
   */
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected void receive(final UUID receiver, final Event e) {
    getSkill(io.sarl.core.DefaultContextInteractions.class).receive(receiver, e);
  }
  
  /**
   * See the capacity {@link io.sarl.core.DefaultContextInteractions#spawn(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.lang.Object[])}.
   * 
   * @see io.sarl.core.DefaultContextInteractions#spawn(java.lang.Class<? extends io.sarl.lang.core.Agent>,java.lang.Object[])
   */
  @FiredEvent(AgentSpawned.class)
  @Generated
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  protected UUID spawn(final Class<? extends Agent> aAgent, final Object... params) {
    return getSkill(io.sarl.core.DefaultContextInteractions.class).spawn(aAgent, params);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   */
  @Generated
  public spectator(final UUID parentID) {
    super(parentID, null);
  }
  
  /**
   * Construct an agent.
   * @param parentID - identifier of the parent. It is the identifier of the parent agent and the enclosing contect, at the same time.
   * @param agentID - identifier of the agent. If <code>null</code> the agent identifier will be computed randomly.
   */
  @Generated
  public spectator(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
}
