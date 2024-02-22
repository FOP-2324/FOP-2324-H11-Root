package h11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.lang.reflect.Method;
import java.util.List;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;

@TestForSubmission
public class DepartmentTestP extends H11_TestP {

    @ParameterizedTest
    @JsonParameterSetTest(value = "getListOfPositionsInDepartment_noDuplicate.json", customConverters = "customConverters")
    public void testGetListOfPositionsInDepartment_noDuplicate(final JsonParameterSet params) {
        List<Position> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonConverter::toPosition);

        Department department = params.get("department");
        List<Position> actual = department.getListOfPositionsInDepartment();

        assertListEquals(expected, actual, params.toContext("mocked", "arguments"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getListOfPositionsInDepartment_duplicate.json", customConverters = "customConverters")
    public void testGetListOfPositionsInDepartment_duplicate(final JsonParameterSet params) {
        List<Position> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonConverter::toPosition);

        Department department = params.get("department");
        List<Position> actual = department.getListOfPositionsInDepartment();

        assertListEquals(expected, actual, params.toContext("mocked", "arguments"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "filterEmployeeByPosition.json", customConverters = "customConverters")
    public void testFilterEmployeeByPosition(final JsonParameterSet params) {
        List<Employee> expected = JsonConverter.toList(params.getRootNode().get("expected"), JsonConverter::toEmployee);

        Department department = params.get("department");
        List<Employee> actual = department.filterEmployeeByPosition(JsonConverter.toList(params.getRootNode().get("arguments"), JsonConverter::toPosition).get(0));

        assertListEquals(expected, actual, params.toContext("mocked"));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "getNumberOfEmployeesBySalary.json", customConverters = "customConverters")
    public void testGetNumberOfEmployeesBySalary(final JsonParameterSet params) {
        long expected = params.getRootNode().get("expected").asLong();

        Department department = params.get("department");
        long actual = department.getNumberOfEmployeesBySalary(params.getRootNode().get("arguments").get(0).asDouble());

        assertEquals(expected, actual, params.toContext("mocked"), r -> "Returned amount of employees over given salary does not match expected.");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "adjustSalary_nonEmpty_negative.json", customConverters = "customConverters")
    public void testAdjustSalary_nonEmpty_negative(final JsonParameterSet params) {
        Department expected = JsonConverter.toDepartment(params.getRootNode().get("expected"));

        Department department = params.get("department");
        department.adjustSalary(params.getRootNode().get("arguments").get(0).asDouble(), params.getRootNode().get("arguments").get(1).asBoolean());

        assertEquals(expected, department, params.toContext("mocked"), r -> "The Salaries do not match the expected");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "adjustSalary_nonEmpty_positive.json", customConverters = "customConverters")
    public void testAdjustSalary_nonEmpty_positive(final JsonParameterSet params) {
        Department expected = JsonConverter.toDepartment(params.getRootNode().get("expected"));

        Department department = params.get("department");
        department.adjustSalary(params.getRootNode().get("arguments").get(0).asDouble(), params.getRootNode().get("arguments").get(1).asBoolean());

        assertEquals(expected, department, params.toContext("mocked"), r -> "The Salaries do not match the expected");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "adjustSalary_empty_negative.json", customConverters = "customConverters")
    public void testAdjustSalary_empty_negative(final JsonParameterSet params) {
        Department expected = JsonConverter.toDepartment(params.getRootNode().get("expected"));

        Department department = params.get("department");
        department.adjustSalary(params.getRootNode().get("arguments").get(0).asDouble(), params.getRootNode().get("arguments").get(1).asBoolean());

        assertEquals(expected, department, params.toContext("mocked"), r -> "The Salaries do not match the expected");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "adjustSalary_empty_positive.json", customConverters = "customConverters")
    public void testAdjustSalary_empty_positive(final JsonParameterSet params) {
        Department expected = JsonConverter.toDepartment(params.getRootNode().get("expected"));

        Department department = params.get("department");
        department.adjustSalary(params.getRootNode().get("arguments").get(0).asDouble(), params.getRootNode().get("arguments").get(1).asBoolean());

        assertEquals(expected, department, params.toContext("mocked"), r -> "The Salaries do not match the expected");
    }

    @Test
    public void testGetListOfPositionsInDepartment_va() {
        Method method = BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("getListOfPositionsInDepartment")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testFilterEmployeeByPosition_va() {
        Method method = BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("filterEmployeeByPosition")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testGetNumberOfEmployeesBySalary_va() {
        Method method = BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("getNumberOfEmployeesBySalary")).reflection();
        assertNoLoopOrRecursion(method);
    }

    @Test
    public void testAdjustSalary_va() {
        Method method = BasicTypeLink.of(Department.class).getMethod(BasicStringMatchers.identical("adjustSalary")).reflection();
        assertNoLoopOrRecursion(method);
    }
}
