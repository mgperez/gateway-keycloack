package org.garpesa.gateway.config;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;

import reactor.core.publisher.Mono;


/**
 * https://github.com/spring-projects/spring-security/issues/4841
 */
public final class ReactiveExpressionUtils {
    /**
     * Evaluates an {@link Expression} that can either return a {@code boolean} or a {@code Mono<Boolean>}.
     * @param expr The {@link Expression}
     * @param ctx The {@link EvaluationContext}
     * @return A {@link Mono} that can be subscribed to containing the result of the expression
     */
    public static Mono<Boolean> evaluateAsBoolean(Expression expr, EvaluationContext ctx) {
        return Mono.defer(() -> {
            try {
                Object value = expr.getValue(ctx);

                if (value instanceof Boolean) {
                    return Mono.just((Boolean) value);
                }
                else if (value instanceof Mono) {
                    return ((Mono<?>) value)
                            .filter(Boolean.class::isInstance)
                            .cast(Boolean.class)
                            .switchIfEmpty(createInvalidTypeMono(expr));
                }
                else {
                    return createInvalidTypeMono(expr);
                }
            }
            catch (EvaluationException ex) {
                return Mono.error(new IllegalArgumentException(String.format("Failed to evaluate expression '%s': %s", expr.getExpressionString(), ex.getMessage()), ex));
            }
        });
    }

    private static Mono<Boolean> createInvalidTypeMono(Expression expr) {
        return Mono.error(new IllegalArgumentException(String.format("Expression '%s' needs to either return boolean or Mono<Boolean> but it does not", expr.getExpressionString())));
    }
}
