package h11;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;
import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.graderPrivateOnly;

public class H11_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H11")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("Das Department")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("H1.1: Liste aller Positionen")
                        .addChildCriteria(
                            criterion(
                                "Die Liste aller Positionen wird korrekt zurück gegeben, wenn duplikate vorhanden sind.",
                                JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testGetListOfPositionsInDepartment_duplicate", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die Liste aller Positionen wird korrekt zurück gegeben, wenn keine duplikate vorhanden sind.",
                                JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testGetListOfPositionsInDepartment_noDuplicate", JsonParameterSet.class))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H1.2: Liste aller Angestellten einer Position")
                        .addChildCriteria(
                            criterion(
                                "Die Liste aller Employees einer Position wird korrekt zurück gegeben.",
                                JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testFilterEmployeeByPosition", JsonParameterSet.class)),
                                2
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H1.3: Nach Gehalt filtern")
                        .addChildCriteria(
                            criterion(
                                "Die Liste der Employees wird korrekt nach Gehalt gefiltert.",
                                JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testGetNumberOfEmployeesBySalary", JsonParameterSet.class)),
                                2
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H1.4: Gehaltserhöhung?")
                        .addChildCriteria(
                            criterion(
                                "Die Gehälter aller Employees wird korrekt angepasst, wenn das Department keine Employees hat",
                                JUnitTestRef.and(
                                    JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testAdjustSalary_empty_positive", JsonParameterSet.class)),
                                    JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testAdjustSalary_empty_negative", JsonParameterSet.class))
                                )
                            ),
                            criterion(
                                "Die Gehälter aller Employees wird korrekt angepasst.",
                                JUnitTestRef.and(
                                    JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testAdjustSalary_nonEmpty_negative", JsonParameterSet.class)),
                                    JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testAdjustSalary_nonEmpty_positive", JsonParameterSet.class))
                                )
                            )
                        )
                        .build()
                )
                .build(),
            Criterion.builder()
                .shortDescription("Das Warenhaus")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("H2.1: Produktpreis")
                        .addChildCriteria(
                            criterion(
                                "getPrice() liefert korrekte Werte zurück, falls null als product übergeben wird.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGetPrice_null", JsonParameterSet.class))
                            ),
                            criterion(
                                "getPrice() liefert korrekte Werte zurück, falls nicht null als product übergeben wird.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGetPrice_nonNull", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die Verbindlichen Anforderungen der Aufgabe wurden eingehalten.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGetPrice_va")),
                                -1
                            )
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.2: Nur bestimmte Produkte gefordert")
                        .addChildCriteria(
                            privateCriterion("", 0, 1)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.3: Übersicht über die Stückzahl")
                        .addChildCriteria(
                            privateCriterion("", 0, 2)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.4: Wieviel Wert steckt denn nun hier drinnen?")
                        .addChildCriteria(
                            privateCriterion("", 0, 2)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.5: Eine Lieferung kommt rein")
                        .addChildCriteria(
                            privateCriterion("", 0, 2)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.6: Aufstocken")
                        .addChildCriteria(
                            criterion(
                                "generateProducts() liefert einen unendlichen Stream zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGenerateProducts"))
                            ),
                            criterion(
                                "generateProducts() liefert einen Stream mit korrekten Elementen zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGenerateProducts", ProductType.class, double.class, String.class))
                            )
                        )
                        .build()
                )
                .build(),
            Criterion.builder()
                .shortDescription("Die Company")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("H3.1: Übersicht aller Mitarbeiter")
                        .addChildCriteria(
                            criterion(
                                "getListOfAllEmployee() liefert korrekt alle Employees aller Departments zurück.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testGetListOfAllEmployee", JsonParameterSet.class))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.2: Übersicht Gesamtanzahl der Produkte")
                        .addChildCriteria(
                            criterion(
                                "Die Gesamtzahl an Produkten dieses Types wird korrekt berechnet und zurück gegeben.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testGetQuantityOfProduct", JsonParameterSet.class))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.3: Filtern der Produkte")
                        .addChildCriteria(
                            privateCriterion("", 0, 3)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.4: Preisspanne vorgeben")
                        .addChildCriteria(
                            criterion(
                                "Alle Produkte werden korrekt gefiltert und zurück gegeben.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testPriceRange_filter", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die zurück gelieferten Produkte sind korrekt sortiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testPriceRange_sorted", JsonParameterSet.class))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.5: Übersicht der Namen")
                        .addChildCriteria(
                            privateCriterion("", 0, 2)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.6: Schnellübersicht von Produkten")
                        .addChildCriteria(
                            criterion(
                                "Die von getAllProductsByType() zurück gelieferte Liste enthält die erwartete Zahl an Elementen.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testGetAllProductsByType_numberItems", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die von getAllProductsByType() zurück gelieferte Liste enthält nur Elemente von erwarteten Typen.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testGetAllProductsByType_correctItems", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die von getAllProductsByType() zurück gelieferte Liste ist korrekt sortiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testGetAllProductsByType_sorted", JsonParameterSet.class))
                            ),
                            criterion(
                                "Jeder von getAllProductsByType() zurück gelieferte Wert ist richtig formattiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testGetAllProductsByType_formatting", JsonParameterSet.class))
                            )
                        )
                        .build()
                )
                .build()
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

    public static Criterion privateCriterion(String message, int min, int max){
        return Criterion.builder()
            .shortDescription(message)
            .grader(graderPrivateOnly())
            .minPoints(min)
            .maxPoints(max)
            .build();
    }
}
