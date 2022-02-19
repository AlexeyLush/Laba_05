package commands.list;

import commands.CommandAbstract;
import commands.models.CommandFields;

public class SumOfMinimalPointCommand extends CommandAbstract {

    public SumOfMinimalPointCommand(){
        setTitle("sum_of_minimal_point");
        setDescription("sum_of_minimal_point : вывести сумму значений поля minimalPoint для всех элементов коллекции");
    }

    @Override
    public void execute(CommandFields commandFields) {

    }
}
