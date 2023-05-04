package h11;

import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

public class H11_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H11")
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
