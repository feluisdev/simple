package cv.igrp.simple.shared.domain.exceptions;

public class IgrpResponseStatusException extends RuntimeException {

    private final IgrpProblem<?> problem;

    public IgrpResponseStatusException(IgrpProblem<?> problem) {
        super(problem.getTitle());
        this.problem = problem;
    }

    public IgrpProblem<?> getProblem() {
        return problem;
    }

}