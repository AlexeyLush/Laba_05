package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;
import models.LabWork;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Команда вывода суммы всех значений поля minimalPoint для всех элементов коллекции
 */

public class SumOfMinimalPointCommand extends CommandAbstract {

    public SumOfMinimalPointCommand(){
        setTitle("sum_of_minimal_point");
        setDescription("sum_of_minimal_point : вывести сумму значений поля minimalPoint для всех элементов коллекции");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
