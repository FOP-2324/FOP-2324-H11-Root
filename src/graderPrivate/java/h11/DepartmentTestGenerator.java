package h11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.util.List;

public class DepartmentTestGenerator extends TestGenerator {

    @Test
    public void generateListOfPositions_noDuplicate() throws Throwable {

        generateJson("noDuplicate",
            List.of(TestObjects.departments.get(1)),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("getListOfPositionsInDepartment")),
            List.of(Arguments.arguments())
        );
    }

    @Test
    public void generateListOfPositions_duplicate() throws Throwable {

        generateJson("duplicate",
            List.of(TestObjects.departments.get(0)),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("getListOfPositionsInDepartment")),
            List.of(Arguments.arguments())
        );
    }

    @Test
    public void generateFilterEmployeeByPosition() throws Throwable {

        generateJson("",
            List.of(
                TestObjects.departments.get(0),
                TestObjects.departments.get(1),
                TestObjects.departments.get(2)
            ),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("filterEmployeeByPosition")),
            List.of(
                Arguments.arguments(Position.Admin),
                Arguments.arguments(Position.Manager),
                Arguments.arguments(Position.Developer)
            )
        );
    }

    @Test
    public void generateGetNumberOfEmployeesBySalary() throws Throwable {
        generateJson("",
            List.of(
                TestObjects.departments.get(0),
                TestObjects.departments.get(1),
                TestObjects.departments.get(0),
                TestObjects.departments.get(2)
            ),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("getNumberOfEmployeesBySalary")),
            List.of(
                Arguments.arguments(5.0),
                Arguments.arguments(100000.0),
                Arguments.arguments(Double.MAX_VALUE),
                Arguments.arguments(0)
            )
        );
    }

    @Test
    public void generateAdjustSalary_nonEmpty_positive() throws Throwable {
        generateJson("nonEmpty_positive",
            List.of(
                TestObjects.departments.get(0),
                TestObjects.departments.get(1)
            ),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("adjustSalary")),
            List.of(
                Arguments.arguments(5.0, true),
                Arguments.arguments(100000.0, true)
            ),
            true
        );
    }

    @Test
    public void generateAdjustSalary_nonEmpty_negative() throws Throwable {
        generateJson("nonEmpty_negative",
            List.of(
                TestObjects.departments.get(0),
                TestObjects.departments.get(1)
            ),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("adjustSalary")),
            List.of(
                Arguments.arguments(5.0, false),
                Arguments.arguments(10.0, false)
            ),
            true
        );
    }

    @Test
    public void generateAdjustSalary_empty_positive() throws Throwable {
        generateJson("empty_positive",
            List.of(
                TestObjects.departments.get(2)
            ),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("adjustSalary")),
            List.of(
                Arguments.arguments(10.0, true)
            ),
            true
        );
    }

    @Test
    public void generateAdjustSalary_empty_negative() throws Throwable {
        generateJson("empty_negative",
            List.of(
                TestObjects.departments.get(2)
            ),
            BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("adjustSalary")),
            List.of(
                Arguments.arguments(10.0, false)
            ),
            true
        );
    }
}
