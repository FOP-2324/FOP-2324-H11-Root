package h11;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;
import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.graderPrivateOnly;

public class H11_RubricProviderPrivate implements RubricProvider {

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
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testGetListOfPositionsInDepartment_duplicate",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Die Liste aller Positionen wird korrekt zurück gegeben, wenn keine Duplikate vorhanden sind.",
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testGetListOfPositionsInDepartment_noDuplicate",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testGetListOfPositionsInDepartment_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H1.2: Liste aller Angestellten einer Position")
                        .addChildCriteria(
                            criterion(
                                "Die Liste aller Employees einer Position wird korrekt zurück gegeben.",
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testFilterEmployeeByPosition",
                                    JsonParameterSet.class
                                )),
                                2
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testFilterEmployeeByPosition_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H1.3: Nach Gehalt filtern")
                        .addChildCriteria(
                            criterion(
                                "Die Liste der Employees wird korrekt nach Gehalt gefiltert.",
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testGetNumberOfEmployeesBySalary",
                                    JsonParameterSet.class
                                )),
                                2
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testGetNumberOfEmployeesBySalary_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H1.4: Gehaltserhöhung?")
                        .addChildCriteria(
                            criterion(
                                "Die Gehälter aller Employees werden korrekt angepasst, wenn das Department keine Employees hat",
                                JUnitTestRef.and(
                                    JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                        "testAdjustSalary_empty_positive",
                                        JsonParameterSet.class
                                    )),
                                    JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                        "testAdjustSalary_empty_negative",
                                        JsonParameterSet.class
                                    ))
                                )
                            ),
                            criterion(
                                "Die Gehälter aller Employees wird korrekt angepasst.",
                                JUnitTestRef.and(
                                    JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                        "testAdjustSalary_nonEmpty_negative",
                                        JsonParameterSet.class
                                    )),
                                    JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                        "testAdjustSalary_nonEmpty_positive",
                                        JsonParameterSet.class
                                    ))
                                )
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> DepartmentTestP.class.getMethod(
                                    "testAdjustSalary_va")),
                                -2)
                        )
                        .minPoints(0)
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
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetPrice_null",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Die Methode getPrice() liefert korrekte Werte zurück, falls nicht null als Parameter übergeben"
                                    + " wird.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetPrice_nonNull",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Die verbindlichen Anforderungen der Aufgabe wurden eingehalten.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod("testGetPrice_va")),
                                -1
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetPrice_generalVa")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.2: Nur bestimmte Produkte gefordert")
                        .addChildCriteria(
                            criterion(
                                "Die Methode getProducts() liefert die korrekten Elemente der Liste zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetProducts", JsonParameterSet.class))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetProducts_va")),
                                -1)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.3: Übersicht über die Stückzahl")
                        .addChildCriteria(
                            criterion(
                                "Die Methode getTotalQuantityOfProduct() liefert die korrekte Anzahl an Produkten für das "
                                    + "gegebene Produkt zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetTotalQuantityOfProduct", JsonParameterSet.class)),
                                2
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetTotalPrice_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.4: Wieviel Wert steckt denn nun hier drinnen?")
                        .addChildCriteria(
                            criterion(
                                "Die Methode getTotalPrice() liefert den Wert der Waren korrekt zurück",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetTotalPrice", JsonParameterSet.class)),
                                2
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGetTotalPrice_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.5: Eine Lieferung kommt rein")
                        .addChildCriteria(
                            criterion(
                                "Die Methode generateProducts() liefert einen unendlichen Stream zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod("testGenerateProducts"))
                            ),
                            criterion(
                                "Die Methode generateProducts() liefert einen Stream mit korrekten Elementen zurück.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGenerateProducts",
                                    ProductType.class,
                                    double.class,
                                    String.class
                                ))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testGenerateProducts_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H2.6: Aufstocken")
                        .addChildCriteria(
                            criterion(
                                "Die Methode addProducts() fügt die übergebene Menge an Produkten zu \"products\" hinzu.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testAddProducts", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die Methode addProducts() fügt Produkte aus generateProducts() zu \"products\" hinzu.",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testAddProducts_containsExact"))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> WarehouseTestP.class.getMethod(
                                    "testAddProducts_va")),
                                -2)
                        )
                        .minPoints(0)
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
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetListOfAllEmployee",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetListOfAllEmployee_va")),
                                -1)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.2: Übersicht Gesamtanzahl der Produkte")
                        .addChildCriteria(
                            criterion(
                                "Die Gesamtzahl an Produkten dieses Types wird korrekt berechnet und zurück gegeben.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetQuantityOfProduct",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetQuantityOfProduct_va")),
                                -1)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.3: Filtern der Produkte")
                        .addChildCriteria(
                            criterion(
                                "Die Rückabe der Methode getFilteredProductNames() ist korrekt für ein übergebenes Predicate.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetFilteredProductNames_single",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Die Rückabe der Methode getFilteredProductNames() ist korrekt für kein oder mehrere übergebene"
                                    + " Predicates.",
                                JUnitTestRef.and(
                                    JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                        "testGetFilteredProductNames_multiple", JsonParameterSet.class)),
                                    JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                        "testGetFilteredProductNames_none", JsonParameterSet.class))
                                )
                            ),
                            criterion(
                                "Die Rückabe der Methode getFilteredProductNames() ist vollständig korrekt.",
                                JUnitTestRef.and(
                                    JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                        "testGetFilteredProductNames_single",
                                        JsonParameterSet.class
                                    )),
                                    JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                        "testGetFilteredProductNames_multiple", JsonParameterSet.class)),
                                    JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                        "testGetFilteredProductNames_none", JsonParameterSet.class))
                                )
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetFilteredProductNames_va")),
                                -3)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.4: Preisspanne vorgeben")
                        .addChildCriteria(
                            criterion(
                                "Alle Produkte werden korrekt gefiltert und zurück gegeben.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testPriceRange_filter",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Die zurück gegebene Liste ist korrekt sortiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testPriceRange_sorted",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testPriceRange_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.5: Übersicht der Namen")
                        .addChildCriteria(
                            criterion(
                                "Die Rückgabe der Methode getEmployeesSortedByName() ist korrekt sortiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetEmployeesSortedByName", JsonParameterSet.class))
                            ),
                            criterion(
                                "Die Rückgabe der Methode getEmployeesSortedByName() ist korrekt formatiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetEmployeesSortedByName_formatting",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetEmployeesSortedByName_va")),
                                -2)
                        )
                        .minPoints(0)
                        .build(),
                    Criterion.builder()
                        .shortDescription("H3.6: Schnellübersicht von Produkten")
                        .addChildCriteria(
                            criterion(
                                "Die von getAllProductsByType() zurück gelieferte Liste enthält die erwartete Zahl an "
                                    + "Elementen.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetAllProductsByType_numberItems",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Die von getAllProductsByType() zurück gelieferte Liste enthält nur Elemente von "
                                    + "erwarteten "
                                    + "Typen.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetAllProductsByType_correctItems",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Die von getAllProductsByType() zurück gelieferte Liste ist korrekt sortiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetAllProductsByType_sorted",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion(
                                "Jeder von getAllProductsByType() zurück gelieferte Wert ist richtig formatiert.",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetAllProductsByType_formatting",
                                    JsonParameterSet.class
                                ))
                            ),
                            criterion("Verbindliche Anforderung nicht erfüllt",
                                JUnitTestRef.ofMethod(() -> CompanyTestP.class.getMethod(
                                    "testGetAllProductsByType_va")),
                                -4)
                        )
                        .minPoints(0)
                        .build()
                )
                .build()
        )
        .build();

    public static Criterion privateCriterion(String message, int min, int max) {
        return Criterion.builder()
            .shortDescription(message)
            .grader(graderPrivateOnly(max))
            .minPoints(min)
            .maxPoints(max)
            .build();
    }

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
