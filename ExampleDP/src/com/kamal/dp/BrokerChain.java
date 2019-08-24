//COr + obsr+ mediator+ momento
package com.kamal.dp;
//java proxy.newinstance
// java provider
// Function interfce
// Dynamic proxy in java - Invocationhandler
// recursive generics for sub classes
// composition vs aggregation vs association
// Autoclosable
// Splititerator
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.kamal.dp.Query.Argument;

class Event<Args> {
	private int index = 0;
	private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

	public int subscribe(Consumer<Args> handler) {
		int i = index;
		handlers.put(index++, handler);
		return i;
	}

	public void unsubscribe(int key) {
		handlers.remove(key);
	}

	public void fire(Args args) {
		handlers.values().stream().forEach(h -> h.accept(args));
	}
}

class Query {

	public String creatureName;

	enum Argument {
		ATTACK, DEFENSE
	}

	public Argument argument;

	public int result;

	public Query() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Query(String creatureName, Argument argument, int result) {
		super();
		this.creatureName = creatureName;
		this.argument = argument;
		this.result = result;
	}
}

class Game {
	public Event<Query> queries = new Event<>();
}

class Creature {
	private Game game;
	public String name;
	public int baseAttack, baseDefense;

	public Creature(Game game, String name, int baseAttack, int baseDefense) {
		super();
		this.game = game;
		this.name = name;
		this.baseAttack = baseAttack;
		this.baseDefense = baseDefense;
	}

	public Game getGame() {
		return game;
	}

	public String getName() {
		return name;
	}

	public int getAttack() {
		Query q = new Query(name, Argument.ATTACK, baseAttack);
		game.queries.fire(q);
		return q.result;
	}

	public int getDefense() {
		Query q = new Query(name, Argument.DEFENSE, baseDefense);
		game.queries.fire(q);
		return q.result;
	}

	@Override
	public String toString() {
		return "Creature [game=" + game + ", name=" + name + ", Attack=" + getAttack() + ", Defense=" + getDefense()
				+ "]";
	}
}

class CreatureModifier {
	protected Game game;
	protected Creature creature;

	public CreatureModifier(Game game, Creature creature) {
		super();
		this.game = game;
		this.creature = creature;
	}

}

class DoubleAttackModifier extends CreatureModifier implements AutoCloseable {
	private final int token;

	public DoubleAttackModifier(Game game, Creature creature) {
		super(game, creature);
		token = game.queries.subscribe(q -> {
			if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.ATTACK) {
				q.result *= 2;
			}
		});
	}

	@Override
	public void close() {
		game.queries.unsubscribe(token);
	}
}

class IncreaseDefenseModifier extends CreatureModifier implements AutoCloseable {
	private final int token;

	public IncreaseDefenseModifier(Game game, Creature creature) {
		super(game, creature);
		token = game.queries.subscribe(q -> {
			if (q.creatureName.equals(creature.name) && q.argument == Query.Argument.DEFENSE) {
				q.result += 3;
			}
		});
	}

	@Override
	public void close() {
		game.queries.unsubscribe(token);
	}
}

public class BrokerChain {
	public static void main(String[] args) {
		Game game = new Game();
		Creature goblin = new Creature(game, "strongGoblin", 2, 2);
		System.out.println(goblin);
		IncreaseDefenseModifier increaseDefenseModifier = new IncreaseDefenseModifier(game, goblin);
		DoubleAttackModifier dam = new DoubleAttackModifier(game, goblin);
		System.out.println(goblin);
		dam.close();
		System.out.println(goblin);
	}
}
