/* 
 * $Id$
 * 
 * Copyright (c) 2011-15 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package fr.utbm.info.vi51.project.environment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.google.common.base.Objects;

import fr.utbm.info.vi51.framework.environment.AbstractEnvironment;
import fr.utbm.info.vi51.framework.environment.AgentBody;
import fr.utbm.info.vi51.framework.environment.DynamicType;
import fr.utbm.info.vi51.framework.environment.Frustum;
import fr.utbm.info.vi51.framework.environment.ImmobileObject;
import fr.utbm.info.vi51.framework.environment.Influence;
import fr.utbm.info.vi51.framework.environment.MobileObject;
import fr.utbm.info.vi51.framework.environment.MotionInfluence;
import fr.utbm.info.vi51.framework.environment.Percept;
import fr.utbm.info.vi51.framework.environment.ShapedObject;
import fr.utbm.info.vi51.framework.environment.SituatedObject;
import fr.utbm.info.vi51.framework.environment.SpatialDataStructure;
import fr.utbm.info.vi51.framework.environment.WorldModelState;
import fr.utbm.info.vi51.framework.gui.WorldModelStateProvider;
import fr.utbm.info.vi51.framework.math.Circle2f;
import fr.utbm.info.vi51.framework.math.MathUtil;
import fr.utbm.info.vi51.framework.math.MotionHull2f;
import fr.utbm.info.vi51.framework.math.Point2f;
import fr.utbm.info.vi51.framework.math.Rectangle2f;
import fr.utbm.info.vi51.framework.math.Shape2f;
import fr.utbm.info.vi51.framework.math.Vector2f;
import fr.utbm.info.vi51.framework.time.StepTimeManager;
import fr.utbm.info.vi51.framework.time.TimeManager;
import fr.utbm.info.vi51.framework.util.LocalizedString;
import fr.utbm.info.vi51.general.frustum.CircleFrustum;
import fr.utbm.info.vi51.general.influence.RemoveInfluence;
import fr.utbm.info.vi51.general.influence.TypeChangeInfluence;
import fr.utbm.info.vi51.general.tree.QuadTree;
import fr.utbm.info.vi51.general.tree.QuadTreeNode;
import fr.utbm.info.vi51.general.tree.iterator.IteratorAllNode;
import fr.utbm.info.vi51.general.tree.iterator.IteratorData;
import fr.utbm.info.vi51.general.tree.iterator.LeafTreeIterator;

/**
 * Model of the world.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
public class WorldModel extends AbstractEnvironment implements WorldModelStateProvider {

	private final static float RABBIT_SIZE = 20f;
	
	private final static float PERCEPTION_RADIUS = 160f;
	
	private final static UUID TARGET_ID = UUID.randomUUID();

	private final SpatialDataStructure<SituatedObject> dataStructure = new QuadTree();
	private final SpatialDataStructure<SituatedObject> dataStructureImmobile = new QuadTree();
	
	private Point2f targetPosition;
	private MouseTarget mouseTarget;

	/**
	 * @param width is the width of the world.
	 * @param height is the height of the world.
	 */
	public WorldModel(float width, float height) {
		super(width, height, new StepTimeManager(500));
		this.dataStructure.initialize(new Rectangle2f(0f, 0f, width, height));
		
		this.dataStructureImmobile.initialize(new Rectangle2f(0f, 0f, width, height));
	}

	/** Replies the spatial data-structure.
	 * 
	 * @return the spatial data-structure.
	 */
	public SpatialDataStructure<SituatedObject> getSpatialDataStructure() {
		return this.dataStructure;
	}
	
	
	/** Replies the spatial data-structureimmobile objetc.
	 * 
	 * @return the spatial data-structure for immobile objetc.
	 */
	public SpatialDataStructure<SituatedObject> getSpatialDataStructureImmobile() {
		return this.dataStructureImmobile;
	}

	/**
	 * {@inheritDoc}
	 */
	public WorldModelState getState() {
		return new ExtendedWorldModelState(this);
	}

	@Override
	protected void onAgentBodyCreated(AgentBody body) {
		this.dataStructure.addData(body);
	}

	@Override
	protected void onAgentBodyDestroyed(AgentBody body) {
		this.dataStructure.removeData(body);
	}
	
	public void setImmobileObject(List<ImmobileObject> list){
		for(ImmobileObject i : list){
			dataStructureImmobile.addData(i);
		}
	}

	/** {@inheritDoc}
	 */
	public void setMouseTarget(Point2f target) {
		synchronized (this) {
			this.targetPosition = (target == null) ? null : target.clone();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Percept> computePerceptionsFor(AgentBody agent) {
		
		List<Percept> shapedPercept = new ArrayList<>();
		SituatedObject obj = null;
		Frustum frustumAgent = agent.getFrustum();
		
		Shape2f<?> frustumShapde = frustumAgent.toShape(agent.getPosition(), agent.getDirection());
		
		Iterator it = dataStructure.dataIterator(frustumShapde);
		
		while(it.hasNext()){
			obj = (SituatedObject) it.next();
			if (!agent.getID().equals(obj.getID())) {
				shapedPercept.add(new Percept(obj));
			}
		}
		
		Iterator itImmobile = dataStructureImmobile.dataIterator(frustumShapde);
		
		while(itImmobile.hasNext()){
			obj = (SituatedObject) itImmobile.next();
			if (!agent.getID().equals(obj.getID())) {
				shapedPercept.add(new Percept(obj));
			}
		}
		
		return shapedPercept;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyInfluences(Collection<MotionInfluence> motionInfluences,
			Collection<Influence> otherInfluences, TimeManager timeManager) {
	
		QuadTreeNode root = new QuadTreeNode(new Rectangle2f(0f, 0f, getWidth(), getHeight()));
		QuadTree<SituatedArtifact> actionTree = new QuadTree(root);
		
		//actionTree.setRoot(root);
		//
		// Consider other influences
		for (Influence influence : otherInfluences) {
			if (influence instanceof RemoveInfluence) {
				assert (this.mouseTarget != null);
				this.dataStructure.removeData(this.mouseTarget);
				this.mouseTarget = null;
				stateChanged();
			} else if (influence instanceof TargetAdditionInfluence) {
				assert (this.mouseTarget == null);
				TargetAdditionInfluence i = (TargetAdditionInfluence) influence;
				this.mouseTarget = new MouseTarget(i.getPosition().getX(), i.getPosition().getY());
				this.dataStructure.addData(this.mouseTarget);
				stateChanged();
			} else if (influence instanceof TeletransportInfluence) {
				assert (this.mouseTarget != null);
				TeletransportInfluence i = (TeletransportInfluence) influence;
				this.dataStructure.removeData(this.mouseTarget);
				this.mouseTarget.setMousePosition(
						i.getPosition(),
						getTimeManager().getLastStepDuration(),
						getWidth(), getHeight());
				this.dataStructure.addData(this.mouseTarget);
				stateChanged();
			} else if (influence instanceof TypeChangeInfluence) {
				TypeChangeInfluence i = (TypeChangeInfluence) influence;
				UUID id = i.getInfluencedObject();
				if (id == null) {
					id = i.getEmitter();
				}
				if (id != null) {
					AgentBody body = getAgentBodyFor(id);
					if (body != null) {
						body.setType(i.getType());
					}
				}
				stateChanged();
			}
		}
		
		// ADD IMMOBILIE OBJECT
		
		IteratorData itImmobile = new IteratorData<SituatedObject>((QuadTree<SituatedObject>)dataStructureImmobile);
		
		while(itImmobile.hasNext()){
			actionTree.addData(new SituatedArtifact((SituatedObject)itImmobile.next(), new Vector2f(2,2), 0));
		}
		
		//
		// Put the influences in a spatial tree
		for (MotionInfluence mi : motionInfluences) {
			UUID id = mi.getInfluencedObject();
			if (id == null) {
				id = mi.getEmitter();
			}
			AgentBody body = getAgentBodyFor(id);

			Vector2f linearMotion;
			float angularMotion;
			if (mi.getType() == DynamicType.KINEMATIC) {
				linearMotion = computeKinematicTranslation(body, mi.getLinearInfluence(), getTimeManager());
				angularMotion = computeKinematicRotation(body, mi.getAngularInfluence(), getTimeManager());
			} else {
				linearMotion = computeSteeringTranslation(body, mi.getLinearInfluence(), getTimeManager());
				angularMotion = computeSteeringRotation(body, mi.getAngularInfluence(), getTimeManager());
			}

			actionTree.addData(new SituatedArtifact(body, linearMotion, angularMotion));
		}	
		
		// Detect conflicts
		Iterator<QuadTreeNode> iterator = new LeafTreeIterator(actionTree.getRoot());
		while (iterator.hasNext()) {
			QuadTreeNode node = iterator.next();
			List<SituatedArtifact> influences = new ArrayList<>();
			do {
				influences.addAll(node.getData());
				node = node.getParent();
			} while (node != null);
			for (int i = 0; i < influences.size(); ++i) {
				SituatedArtifact inf1 = influences.get(i);
				if (!inf1.isEmpty()) {
					Shape2f<?> s1 = inf1.getShape();
					for (SituatedObject obj : getAllObjects()) {
						if ((!(obj instanceof AgentBody)) && (s1.intersects(obj.getShape()))) {
							inf1.clear();
							break;
						}
					}
					if (!inf1.isEmpty() && i < influences.size() - 1) {
						for (int j = i + 1; j < influences.size(); ++j) {
							SituatedArtifact inf2 = influences.get(j);
							if (!inf2.isEmpty()) {
								if (s1.intersects(inf2.getShape())) {
									inf2.clear();
								}
							}
						}
					}
				}
			}
		}
		//
		// Apply Actions
		for (SituatedArtifact action : actionTree.getData()) {
			MobileObject obj = action.getObject();
			if (!action.isEmpty() && obj != null) {
				boolean b = this.dataStructure.removeData(obj);
				assert (b) : "Object cannot be removed from quadtree: " + obj;
				move(obj, action.getLinearMotion(), action.getAngularMotion());
				b = this.dataStructure.addData(obj);
				assert (b) : "Object cannot be added in quadtree: " + obj;
			}
		}
	}

	@Override
	public Iterable<SituatedObject> getAllObjects() {
		return this.dataStructure.getData();
	}

	@Override
	protected List<Influence> computeEndogenousBehaviorInfluences() {
		Point2f target;
		synchronized (this) {
			target = this.targetPosition;
		}
		Influence influence;
		if (target==null) {
			if (this.mouseTarget == null) {
				return Collections.emptyList();
			}
			influence = new RemoveInfluence(TARGET_ID);
		} else if (this.mouseTarget != null) {
			influence = new TeletransportInfluence(TARGET_ID, target);
		} else {
			influence = new TargetAdditionInfluence(TARGET_ID, target);
		}
		return Collections.singletonList(influence);
	}

	/** Create the body of a rabbit.
	 * 
	 * DONT DELETE, keep this example safe
	 */
	public void createRabbit() {
		UUID id = UUID.randomUUID();
		AgentBody body = new AgentBody(
				id,
				new Circle2f(0f, 0f, RABBIT_SIZE), // body
				5f,						// max linear speed m/s
				.5f,						// max linear acceleration (m/s)/s
				MathUtil.PI/4f,				// max angular speed r/s
				MathUtil.PI/10f,			// max angular acceleration (r/s)/s
				new CircleFrustum(id, PERCEPTION_RADIUS));
		body.setName(LocalizedString.getString(WorldModel.class, "RABBIT", getAgentBodyNumber() + 1));
		addAgentBody(
				body,
				randomPosition(),
				(float) Math.random() * MathUtil.TWO_PI);
	}
	
	/** Create the body of a Artist.
	 */
	public void createArtist() {
		UUID id = UUID.randomUUID();
		AgentBody body = new AgentBody(
				id,
				new Circle2f(0f, 0f, RABBIT_SIZE), // body
				3f,						// max linear speed m/s
				.3f,						// max linear acceleration (m/s)/s
				MathUtil.PI/3f,				// max angular speed r/s
				MathUtil.PI/7f,			// max angular acceleration (r/s)/s
				new CircleFrustum(id, PERCEPTION_RADIUS));
		body.setName(LocalizedString.getString(WorldModel.class, Semantics.ARTIST, getAgentBodyNumber() + 1));
		addAgentBody(
				body,
				randomPosition(),
				(float) Math.random() * MathUtil.TWO_PI);
	}
	
	
	/** Create the body of a Spectator.
	 */
	public void createSpectator() {
		UUID id = UUID.randomUUID();
		AgentBody body = new AgentBody(
				id,
				new Circle2f(0f, 0f, RABBIT_SIZE), // body
				3f,						// max linear speed m/s
				.3f,						// max linear acceleration (m/s)/s
				MathUtil.PI/3f,				// max angular speed r/s
				MathUtil.PI/7f,			// max angular acceleration (r/s)/s
				new CircleFrustum(id, PERCEPTION_RADIUS));
		body.setName(LocalizedString.getString(WorldModel.class,Semantics.SPECTATOR, getAgentBodyNumber() + 1));
		addAgentBody(
				body,
				randomPosition(),
				(float) Math.random() * MathUtil.TWO_PI);
	}
	
	/** Create the body of a Spectator.
	 */
	public void createSecurityAgent() {
		UUID id = UUID.randomUUID();
		AgentBody body = new AgentBody(
				id,
				new Circle2f(0f, 0f, RABBIT_SIZE), // body
				3f,						// max linear speed m/s
				.3f,						// max linear acceleration (m/s)/s
				MathUtil.PI/3f,				// max angular speed r/s
				MathUtil.PI/7f,			// max angular acceleration (r/s)/s
				new CircleFrustum(id, PERCEPTION_RADIUS));
		body.setName(LocalizedString.getString(WorldModel.class, Semantics.SECURITY_AGENT, getAgentBodyNumber() + 1));
		addAgentBody(
				body,
				randomPosition(),
				(float) Math.random() * MathUtil.TWO_PI);
	}
	

	protected Point2f randomPosition() {
		
		boolean search = true;
		float x = 1;
		float y = 1;
		SituatedObject obj = null;
		while(search){
			x = (float) Math.random() * getWidth() - RABBIT_SIZE;
			y = (float) Math.random() * getHeight() - RABBIT_SIZE;
			Rectangle2f position = new Rectangle2f(new Point2f(x-1,y-1), new Point2f(x+1,y+1));
			Iterator itImmobile = dataStructureImmobile.dataIterator(position);
			
			if(itImmobile.hasNext()){
				search = true;
				System.out.print("on a un problème");
			}else{
				search = false;
			}
		}
		
		return new Point2f(x, y);
	}

	/**
	 * Real action to apply in the world.
	 * 
	 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
	 * @version $Name$ $Revision$ $Date$
	 */
	private static class SituatedArtifact implements ShapedObject, Comparable<SituatedArtifact> {

		private final MobileObject object;
		private final Vector2f linearMotion;
		private final float angularMotion;
		private final MotionHull2f shape;
		private boolean cleared;
		
		/**
		 * @param object
		 * @param linearMotion
		 * @param angularMotion
		 */
		public SituatedArtifact(SituatedObject object, Vector2f linearMotion, float angularMotion) {
			this.object = object instanceof MobileObject ? (MobileObject) object : null;
			this.linearMotion = linearMotion;
			this.angularMotion = angularMotion;
			this.shape = new MotionHull2f(object.getPosition(), linearMotion, object.getShape().getMaxDemiSize());
		}
		
		/*public SituatedArtifact(SituatedObject objectImmobile){
			this.object = null;
			this.linearMotion = null;
			this.angularMotion = 0;
			this.shape = new MotionHull2f(object.getPosition(), linearMotion, object.getShape().getMaxDemiSize());
		}*/
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof SituatedArtifact) {
				SituatedArtifact a = (SituatedArtifact) obj;
				return Objects.equal(this.object, a.object)
					&& Objects.equal(this.linearMotion, a.linearMotion)
					&& this.angularMotion == a.angularMotion;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hashCode(this.object, this.linearMotion, this.angularMotion);
		}
		
		@Override
		public int compareTo(SituatedArtifact o) {
			if (o == this) {
				return 0;
			}
			if (o == null) {
				return Integer.MAX_VALUE;
			}
			int c;
			if (this.object != null) {
				c = this.object.compareTo(o.object);
				if (c != 0) {
					return c;
				}
			}
			if (this.linearMotion != null) {
				c = this.linearMotion.compareTo(o.linearMotion);
				if (c != 0) {
					return c;
				}
			}
			return Float.compare(this.angularMotion, o.angularMotion);
		}
		
		/** Replies the object to move.
		 *
		 * @return the object to move.
		 */
		public MobileObject getObject() {
			return this.object;
		}
		
		/** Replies the linear motion.
		 * 
		 * @return the linear motion.
		 */
		public Vector2f getLinearMotion() {
			return this.linearMotion;
		}

		/** Replies the angular motion.
		 * 
		 * @return the angular motion.
		 */
		public float getAngularMotion() {
			return this.angularMotion;
		}

		@Override
		public Shape2f<?> getShape() {
			return this.shape.clone();
		}
		
		@Override
		public String toString() {
			return this.linearMotion + "|" + this.angularMotion;
		}
		
		/** Replies if this object contains no movement definition.
		 *
		 * @return <code>true</code> if this object contains a movement definition.
		 */
		public boolean isEmpty() {
			return this.cleared;
		}
		
		/** Clear the movement definition.
		 */
		public void clear() {
			this.cleared = true;
		}

	}
	
}