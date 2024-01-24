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
                                "Die Liste aller Positionen wird korrekt zurück gegeben, wenn Duplikate vorhanden sind.",
                                JUnitTestRef.ofMethod(() -> DepartmentTest.class.getMethod("testGetListOfPositionsInDepartment_duplicate", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die Liste aller Positionen wird korrekt zurück gegeben, wenn keine Duplikate vorhanden sind.",
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
                                "Die Gehälter aller Employees werden korrekt angepasst, wenn das Department keine Employees hat",
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
                                "Die Methode getPrice() liefert korrekte Werte zurück, falls null als Parameter übergeben wird.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGetPrice_null", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die Methode getPrice() liefert korrekte Werte zurück, falls nicht null als Parameter übergeben wird.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGetPrice_nonNull", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die verbindlichen Anforderungen der Aufgabe wurden eingehalten.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGetPrice_va")),
                                -1
                            )
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.2: Nur bestimmte Produkte gefordert")
                        .addChildCriteria(
                            privateCriterion("Die Methode getProducts() liefert die korrekten Elemente der Liste zurück.", 0, 1)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.3: Übersicht über die Stückzahl")
                        .addChildCriteria(
                            privateCriterion("Die Methode getTotalQuantityOfProduct() liefert die korrekte Anzahl an Produkten für das gegebene Produkt zurück.", 0, 2)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.4: Wieviel Wert steckt denn nun hier drinnen?")
                        .addChildCriteria(
                            privateCriterion("Die Methode getTotalPrice() liefert den Wert der Waren korrekt zurück", 0, 2)
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.5: Eine Lieferung kommt rein")
                        .addChildCriteria(
                            criterion(
                                "Die Methode generateProducts() liefert einen unendlichen Stream zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGenerateProducts"))
                            ),
                            criterion(
                                "Die Methode generateProducts() liefert einen Stream mit korrekten Elementen zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTest.class.getMethod("testGenerateProducts", ProductType.class, double.class, String.class))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.6: Aufstocken")
                        .addChildCriteria(
                            privateCriterion("Die Methode addProducts() fügt die übergebene Menge an Produkten zu \"products\" hinzu.", 0, 1),
                            privateCriterion("Die Methode addProducts() fügt Produkte aus generateProducts() zu \"products\" hinzu.", 0, 1)
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
                                "Die Methode getListOfAllEmployee() liefert korrekt alle Employees aller Departments zurück.",
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
                            criterion(
                                "Die Rückabe der Methode getFilteredProductNames() ist korrekt für ein übergebenes Predicate.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testGetFilteredProductNames_single", JsonParameterSet.class))
                            ),
                            privateCriterion("Die Rückabe der Methode getFilteredProductNames() ist korrekt für kein oder mehrere übergebene Predicates.", 0, 1),
                            privateCriterion("Die Rückabe der Methode getFilteredProductNames() ist vollständig korrekt.", 0, 1)
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
                                "Die zurück gegebene Liste ist korrekt sortiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTest.class.getMethod("testPriceRange_sorted", JsonParameterSet.class))
                            )
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.5: Übersicht der Namen")
                        .addChildCriteria(
                            privateCriterion("Die Rückgabe der Methode getEmployeesSortedByName() ist korrekt sortiert.", 0, 1),
                            privateCriterion("Die Rückgabe der Methode getEmployeesSortedByName() ist korrekt formatiert.", 0, 1)
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
                                "Jeder von getAllProductsByType() zurück gelieferte Wert ist richtig formatiert.",
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
            .grader(graderPrivateOnly(max))
            .minPoints(min)
            .maxPoints(max)
            .build();
    }
}
