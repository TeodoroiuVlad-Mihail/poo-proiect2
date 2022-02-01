package main;

import commands.AddChildren;
import commands.AnnualUpdateCommand;
import commands.CalculateAverageScore;
import commands.CalculateChildrenBudget;
import commands.CommandType;
import commands.GiveChildrenGifts;
import commands.GrowChildren;
import commands.RemoveYoungAdults;
import commands.UpdateChildren;
import commands.AddGifts;

import fileio.ChildrenUpdatesInputData;
import reading.Children;
import reading.Gifts;

import java.util.ArrayList;

/**
 * Receives commands in clear text from the user and transforms them in AnnualUpdateCommand objects
 * It uses the Invoker to execute the given commands
 */
public class Client {

    private Invoker invoker;
    private Children children;
    private double santaBudget;
    private Gifts gifts;
    private Children newChildren;
    private ArrayList<ChildrenUpdatesInputData> childrenUpdates;
    private Gifts newGifts;
    private String strategy;

    Client(final Children children, final double santaBudget, final Gifts gifts) {
        invoker = new Invoker();
        this.children = children;
        this.santaBudget = santaBudget;
        this.gifts = gifts;
        this.newChildren = null;
        this.childrenUpdates = null;

    }

    //This is iirc overloading, something about using the same name for constructor but
    //with different arguments
    Client(final Children children, final double santaBudget, final Gifts gifts,
           final Children newChildren, final ArrayList<ChildrenUpdatesInputData> childrenUpdates,
           final Gifts newGifts, final String strategy) {
        invoker = new Invoker();
        this.children = children;
        this.santaBudget = santaBudget;
        this.gifts = gifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.newGifts = newGifts;
        this.strategy = strategy;
    }

    /**
     * executes the selected command
     */
    public void executeAction(final String commandName) {
        AnnualUpdateCommand command;
        try {
            CommandType commandType = CommandType.fromString(commandName);
            command = getCommand(commandType);
            if (command == null) {
                throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command: " + commandName);
            System.out.println("Available commands:");
            for (CommandType type : CommandType.values()) {
                System.out.println("\t- " + type.getText());
            }
            return;
        }

        invoker.execute(command);

    }

    private AnnualUpdateCommand getCommand(final CommandType type)
            throws IllegalArgumentException {
        if (type.compareTo(CommandType.ADD_CHILDREN) == 0) {
            return new AddChildren(children, newChildren);
        }
        if (type.compareTo(CommandType.CALCULATE_AVERAGE_SCORE) == 0) {
            return new CalculateAverageScore(children);
        }
        if (type.compareTo(CommandType.CALCULATE_CHILDREN_BUDGET) == 0) {
            return new CalculateChildrenBudget(children, santaBudget);
        }
        if (type.compareTo(CommandType.GIVE_CHILDREN_GIFTS) == 0) {
            return new GiveChildrenGifts(children, gifts, strategy);
        }
        if (type.compareTo(CommandType.GROW_CHILDREN) == 0) {
            return new GrowChildren(children);
        }
        if (type.compareTo(CommandType.REMOVE_YOUNG_ADULTS) == 0) {
            return new RemoveYoungAdults(children);
        }
        if (type.compareTo(CommandType.UPDATE_CHILDREN) == 0) {
            return new UpdateChildren(children, childrenUpdates);
        }
        if (type.compareTo(CommandType.ADD_GIFTS) == 0) {
            return new AddGifts(gifts, newGifts);
        }
        return null;
    }
}
